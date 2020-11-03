package xyx.game.concrete;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //1确定混凝土配制强度
    //2确定水胶比
    //3计算用水量
    //4计算水泥用量
    //5选中沙率
    //6计算砂石用量
    //7初步配合比


    float tld,//塌落度
    j,//减水率
            d,//碎石直径
            fce;//水泥强度
    private float f,kf;//煤灰掺量比，矿粉掺量比，
    private int sf,skf;//粉煤灰等级1:1-2级  2:3级，kf的1代表s75,2代表s95

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //c15-c55
         //1确定混凝土配制强度
        final float c=30;//默认c30---可选

        tld=180;//塌落度
        d=31.5f;//石头最大直径
        fce=52.5f;//水泥标号
        j=0.24f;//减水率
        f=0;//粉煤灰掺量
        kf=40;//矿粉掺量
        sf=1;//粉煤灰等级
        skf=1;//矿粉等级

        findViewById(R.id.button15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv)).setText(getworlds(15));
                            }
        });
        findViewById(R.id.button20).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv)).setText(getworlds(20));
            }
        });
        findViewById(R.id.button25).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv)).setText(getworlds(25));
            }
        });
        findViewById(R.id.button30).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv)).setText(getworlds(30));
            }
        });
        findViewById(R.id.button35).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv)).setText(getworlds(35));
            }
        });
        findViewById(R.id.button40).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv)).setText(getworlds(40));
            }
        });

        findViewById(R.id.button45).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv)).setText(getworlds(45));
            }
        });
        findViewById(R.id.button50).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv)).setText(getworlds(50));
            }
        });
        findViewById(R.id.button55).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv)).setText(getworlds(55));
            }
        });







    }


    private String getworlds( float c){

        float fb=getfb(f,kf,sf,skf);
        //水灰比
        float wb=getwb(fb,c);

        //设置用水量
        float mw=getmw(d,tld, j);//设置用水量为150kg

        final float mc=mw/wb;//水泥用量

        //确定砂率

        float bs= getsl(wb,tld,d)/100;//c30表的砂率，可调
//        float pg= (float) 2.65,pc= (float) 3.1,ps= (float) 2.6;//密度--可调
//        //公式辅助值 体积法有bug
//        float x= (float) (990-mw-(mc/3.1));
//        //计算石头//固定密度2.65 砂2.6 水泥3.1，否则公式不能用
//        final float mg= (float) (x*2.65*2.6*(1-bs)/(2.6*(1-bs)+2.65));
//        //计算砂
//        final float ms=bs*mg/(1-bs);
//        final float all=mc+mg+ms+mw;
        //容重法2250
        final float ms=(2350-mc-mw)*bs;
        final float mg=ms*(1-bs)/bs;
        final float all=mc+mg+ms+mw;

        String string="容重法配比"+"\n水泥强度"+fce+"\n混凝土强度c"+c+"\n配制GB标准强度"+ getfcu0(c) +
                "\n理论胶砂强度"+fb+
                "\n煤灰"+f+"%矿粉"+kf+"%"+
                "\n水灰比"+ wb
                +"\n塌落度180减水率"+j+"\n用水量"+mw+"\n水泥用量"+mc*(100-f-kf)/100
                +"\n煤灰用量"+mc*f/100
                +"\n矿粉用量"+mc*kf/100
                +"\n砂率"+bs+"\n石头"+mg+"\n砂"+ms+"\n合计"+all;
        return string;
    }



    /**混合胶砂强度计算,用40表示40%,不要传入0.4;
     * 煤灰掺量，矿粉掺量，粉煤灰等级1=1-2级，2=3级,矿粉等级1=S75;2=S95
     * @return**/
    private float getfb(float f, float kf, int sf, int skf){
        float fb = 0;
        float i=1,j=1;

        if (sf==1){
            if (f>0&&f<=10){i=(100-f/2)/100;}
            if (f>10){i=(105-f)/100;;}
        }
        if (sf==2){
            if (f>0&&f<=10){i=(100-f*3/2)/100;;}
            if (f>10){i=(95-f)/100;;//大于40时超出规范
            }
        }




        if (skf==1){

           if (kf>10&&kf<=30){j=(105-kf/2)/100;}
            if (kf>30&&kf<=50){j=(120-kf)/100;}
            if (kf>50&&kf<=60){j=(135-kf)/100;}//超出规范
        }

        if (skf==2){
            if (kf>30&&kf<=50){j=(130-kf)/100;}
            if (kf>50&&kf<=60){j=(145-kf)/100;}//超出规范
        }

        float fce2 = 0;//水泥富余系数
        if (fce==32.5) fce2= (float) (1.12*32.5);
        if (fce==42.5) fce2= (float) (1.16*42.5);
        if (fce==52.5) fce2= (float) (1.1*52.5);
        fb=i*j*fce2;

        return  fb ;
    }

    /**配制强度计算
     * 传入水泥强度**/
    private float getfcu0(float c){
        float fcu0 = 0;//配制强度--拿到配制强度
        float o=0;//强度标准差--固定可调
        if (c<=20)o=4;
        if (c>20&&c<=45)o=5;
        if (c>=50&&c<=55)o=6;
        //强度标准值
        if (c<60)fcu0= (float) (c+1.645*o);
        if (c>=60)fcu0= (float) (c*1.15);
        return fcu0;
    }
    
    /**水胶比计算
     * 传入胶砂强度
     * 水泥标号**/
    private float getwb(float fb,float c){
        float wb = 0;
        float a= (float) 0.53,b= (float) 0.20;

        wb=a*fb/(getfcu0(c)+a*b*fb);//计算公式
        return wb;
    }


    /**d碎石直径,tld塌落度,减水率j,返回用水量**/
    private float getmw(float d,float tld,float j){
        float m = 0;
        if(d==16){
            if(tld>=10&&tld<=30){m=200;};
            if(tld>=35&&tld<=50){m=210;};
            if(tld>=55&&tld<=70){m=220;};
            if(tld>=75&&tld<=90){m=230;};
            if(tld>=95&&tld<=110){m=235;};
            if(tld>=115&&tld<=130){m=240;};
            if(tld>=135&&tld<=150){m=245;};
            if(tld>=155&&tld<=170){m=250;};
            if(tld>=175&&tld<=190){m=255;};
            if(tld>=195&&tld<=210){m=260;};
            if(tld>=215&&tld<=230){m=265;};

        }
        if(d==20){ if(tld>=10&&tld<=30){m=185;};
            if(tld>=35&&tld<=50){m=195;};
            if(tld>=55&&tld<=70){m=205;};
            if(tld>=75&&tld<=90){m=215;};
            if(tld>=95&&tld<=110){m=220;};
            if(tld>=115&&tld<=130){m=225;};
            if(tld>=135&&tld<=150){m=230;};
            if(tld>=155&&tld<=170){m=235;};
            if(tld>=175&&tld<=190){m=240;};
            if(tld>=195&&tld<=210){m=245;};
            if(tld>=215&&tld<=230){m=250;};}
        if(d==31.5){ if(tld>=10&&tld<=30){m=175;};
            if(tld>=35&&tld<=50){m=185;};
            if(tld>=55&&tld<=70){m=195;};
            if(tld>=75&&tld<=90){m=205;};
            if(tld>=95&&tld<=110){m=210;};
            if(tld>=115&&tld<=130){m=215;};
            if(tld>=135&&tld<=150){m=220;};
            if(tld>=155&&tld<=170){m=225;};
            if(tld>=175&&tld<=190){m=230;};
            if(tld>=195&&tld<=210){m=235;};
            if(tld>=215&&tld<=230){m=240;};}
        if(d==40){ if(tld>=10&&tld<=30){m=165;};
            if(tld>=35&&tld<=50){m=175;};
            if(tld>=55&&tld<=70){m=185;};
            if(tld>=75&&tld<=90){m=195;};
            if(tld>=95&&tld<=110){m=200;};
            if(tld>=115&&tld<=130){m=205;};
            if(tld>=135&&tld<=150){m=210;};
            if(tld>=155&&tld<=170){m=215;};
            if(tld>=175&&tld<=190){m=220;};
            if(tld>=195&&tld<=210){m=225;};
            if(tld>=215&&tld<=230){m=230;};}

        if (j==0){j=1;}
        m= (float) (m*(1-j));

        return m;
    }

    /**砂率计算(水灰比,塌落度,石头最大直径)**/
    private float getsl(float wb,float tld,float d){
        float sl = 0;
float jz=0;//均值
        if(d<=16){
            if (wb>=0.2&&wb<0.4){jz= (float) 29.5;sl= (float) (wb-0.3)*30+jz;   }
            if (wb>=0.4&&wb<0.5){jz= (float) 32.5;sl= (float) (wb-0.4)*30+jz;   }
            if (wb>=0.5&&wb<0.6){jz= (float) 35.5;sl= (float) (wb-0.5)*30+jz;   }
            if (wb>=0.6&&wb<0.7){jz= (float) 38.5;sl= (float) (wb-0.6)*30+jz;   }
            if (wb>=0.7)        {jz= (float) 41.5;sl= (float) (wb-0.7)*30+jz;   } }
        if(d>16&&d<=20){
            if (wb>=0.2&&wb<0.4){jz= (float) 28.5;sl= (float) (wb-0.3)*30+jz; }
            if (wb>=0.4&&wb<0.5){jz= (float) 31.5;sl= (float) (wb-0.4)*30+jz;}
            if (wb>=0.5&&wb<0.6){jz= (float) 34.5;sl= (float) (wb-0.5)*30+jz;  }
            if (wb>=0.6&&wb<0.7){jz= (float) 37.5;sl= (float) (wb-0.6)*30+jz; }
            if (wb>=0.7)        {jz= (float) 40.5;sl= (float) (wb-0.7)*30+jz; } }
        if(d>20&&d<=40){
            if (wb>=0.2&&wb<0.4){jz= (float) 26.5;sl= (float) (wb-0.3)*30+jz; }
            if (wb>=0.4&&wb<0.5){jz= (float) 29.5;sl= (float) (wb-0.4)*30+jz;}
            if (wb>=0.5&&wb<0.6){jz= (float) 32.5;sl= (float) (wb-0.5)*30+jz;  }
            if (wb>=0.6&&wb<0.7){jz= (float) 35.5;sl= (float) (wb-0.6)*30+jz; }
            if (wb>=0.7)        {jz= (float) 38.5;sl= (float) (wb-0.7)*30+jz; } }

      if (tld>60){sl=sl+(tld-60)/20;}

        return sl;
    }

}
