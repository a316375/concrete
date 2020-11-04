package xyx.game.concrete;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,SeekBar.OnSeekBarChangeListener {

    //1确定混凝土配制强度
    //2确定水胶比
    //3计算用水量
    //4计算水泥用量
    //5选中沙率
    //6计算砂石用量
    //7初步配合比

    private   CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9,cb10,cb11;//十一个框选框
    private TextView tv1,tv2,tv3,tv4;//进度条文字
    private SeekBar sb1,sb2,sb3,sb4;//进度条


    private  float tld,//塌落度
    j,//减水率
            d,//碎石直径
            fce;//水泥强度
    private float f,kf;//煤灰掺量比，矿粉掺量比，
    private int sf,skf;//粉煤灰等级1:1-2级  2:3级，kf的1代表s75,2代表s95


    private void in(){
        cb1=findViewById(R.id.cb01);
        cb1.setOnClickListener(this);

        cb2=findViewById(R.id.cb02);
        cb2.setOnClickListener(this);

        cb3=findViewById(R.id.cb03);
        cb3.setOnClickListener(this);

        cb4=findViewById(R.id.cb04);
        cb4.setOnClickListener(this);

        cb5=findViewById(R.id.cb05);
        cb5.setOnClickListener(this);

        cb6=findViewById(R.id.cb06);
        cb6.setOnClickListener(this);

        cb7=findViewById(R.id.cb07);
        cb7.setOnClickListener(this);

        cb8=findViewById(R.id.cb08);
        cb8.setOnClickListener(this);

        cb9=findViewById(R.id.cb09);
        cb9.setOnClickListener(this);

        cb10=findViewById(R.id.cb10);
        cb10.setOnClickListener(this);

        cb11=findViewById(R.id.cb11);
        cb11.setOnClickListener(this);

       tv1=findViewById(R.id.tv01);
       tv2=findViewById(R.id.tv02);
       tv3=findViewById(R.id.tv03);
       tv4=findViewById(R.id.tv04);

       sb1=findViewById(R.id.sb1);
       sb2=findViewById(R.id.sb2);
       sb3=findViewById(R.id.sb3);
       sb4=findViewById(R.id.sb4);

       sb1.setOnSeekBarChangeListener(this);
       sb2.setOnSeekBarChangeListener(this);
       sb3.setOnSeekBarChangeListener(this);
       sb4.setOnSeekBarChangeListener(this);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //c15-c55
        //1确定混凝土配制强度
        final float c=30;//默认c30---可选

        tld=10;//塌落度
        d=16.0f;//石头最大直径
        fce=32.5f;//水泥标号
        j=0.0f;//减水率
        f=0;//粉煤灰掺量
        kf=0;//矿粉掺量
        sf=1;//粉煤灰等级
        skf=1;//矿粉等级


        in();//寻找id初始化



        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv)).setText(getworlds(15));
                            }
        });
        findViewById(R.id.bt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv)).setText(getworlds(20));
            }
        });
        findViewById(R.id.bt3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv)).setText(getworlds(25));
            }
        });
        findViewById(R.id.bt4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv)).setText(getworlds(30));
            }
        });
        findViewById(R.id.bt5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv)).setText(getworlds(35));
            }
        });
        findViewById(R.id.bt6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv)).setText(getworlds(40));
            }
        });

        findViewById(R.id.bt7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv)).setText(getworlds(45));
            }
        });
        findViewById(R.id.bt8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv)).setText(getworlds(50));
            }
        });
        findViewById(R.id.bt9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv)).setText(getworlds(55));
            }
        });


    }








     //设置文字
    private String getworlds( float c){

        float fb=getfb(f,kf,sf,skf);
        //水灰比
        float wb=getwb(fb,c);

        //设置用水量
        float mw=getmw(d,tld, j);//设置用水量为150kg

        final float mc=mw/wb;//胶凝材料用量

        float mf=mc/100*f;//粉煤灰质量
        float mkf=mc/100*kf;//矿粉质量

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



        String string="容重法配比"+
                "\n减水率"+j+
                "\n水泥强度"+fce+"\n混凝土强度c"+c+"\n配制GB标准强度"+ getfcu0(c) +
                "\n理论胶砂强度"+fb+
                "\n煤灰"+f+"%矿粉"+kf+"%"+
                "\n设计所需水灰比"+ wb
                +"\n塌落度"+tld
                +"\n碎石最大直径"+d+"mm"+
                "\n胶凝材料"+mc+
                "\n用水量"+mw+
                "\n水泥用量"+mc*(100-f-kf)/100
                +"\n煤灰用量"+mf
                +"\n矿粉用量"+mkf
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

        if (j!=0) m= (float) (m*(1-j/100));

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cb01:
                d=16;
                if (cb1.isChecked()==true){
                cb2.setChecked(false);
                cb3.setChecked(false);
                cb4.setChecked(false);
                }
                cb1.setChecked(true);
                break;
            case R.id.cb02:
                d=20;
                if (cb2.isChecked()==true){
                    cb1.setChecked(false);
                    cb3.setChecked(false);
                    cb4.setChecked(false);
                }
                cb2.setChecked(true);
                break;
            case R.id.cb03:
                d=31.5f;
                if (cb3.isChecked()==true){
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                    cb4.setChecked(false);
                }
                cb3.setChecked(true);
                break;
            case R.id.cb04:
                d=40f;
                if (cb4.isChecked()==true){
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                }
                cb4.setChecked(true);
                break;
            case R.id.cb05:
                sf=1;
                cb6.setChecked(false);
                cb5.setChecked(true);
                break;
            case R.id.cb06:
                sf=2;
                cb5.setChecked(false);
                cb6.setChecked(true);
                break;
            case R.id.cb07:
                skf=1;
                cb8.setChecked(false);
                cb7.setChecked(true);
                break;
            case R.id.cb08:
                skf=2;
                cb7.setChecked(false);
                cb8.setChecked(true);
                break;
            case R.id.cb09:
                fce=32.5f;
                cb9.setChecked(true);
                cb10.setChecked(false);
                cb11.setChecked(false);
                break;
            case R.id.cb10:
                fce=42.5f;
                cb9.setChecked(false);
                cb10.setChecked(true);
                cb11.setChecked(false);
                break;
            case R.id.cb11:
                fce=52.5f;
                cb9.setChecked(false);
                cb10.setChecked(false);
                cb11.setChecked(true);
                break;



            default:break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.sb1:
                tv1.setText("减水率"+progress);
                j=progress;
                break;
            case R.id.sb2:
                tv2.setText("塌落度"+progress);

                tld=progress;
                break;
            case R.id.sb3:
                tv3.setText("煤灰掺量"+progress);
                f=progress;
                break;
            case R.id.sb4:
                tv4.setText("矿粉掺量"+progress);
                kf=progress;
                break;
            default:break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()){
            case R.id.sb1:break;
            case R.id.sb2:break;
            case R.id.sb3:break;
            case R.id.sb4:break;
            default:break;
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()){
            case R.id.sb1:break;
            case R.id.sb2:break;
            case R.id.sb3:break;
            case R.id.sb4:break;
            default:break;
        }
    }
}
