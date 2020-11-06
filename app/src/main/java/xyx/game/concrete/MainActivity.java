package xyx.game.concrete;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,SeekBar.OnSeekBarChangeListener {

    //1确定混凝土配制强度
    //2确定水胶比
    //3计算用水量
    //4计算水泥用量
    //5选中沙率
    //6计算砂石用量
    //7初步配合比

    private   CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9,cb10,cb11;//十一个框选框
    private TextView tv1,tv2,tv3,tv4,tv5;//进度条文字
    private SeekBar sb1,sb2,sb3,sb4,sb5;//进度条
    private EditText ed;//含水量
    private Switch mswitch,sw2;//容重开关,砂率开关
    private TextView tvts;//提示text

    private Switch sw3,sw4,sw5,sw6,sw7;//用水量控制因素2


    private  float tld,//塌落度
    j,//减水率
            d,//碎石直径
            fce;//水泥强度
    private float f,kf;//煤灰掺量比，矿粉掺量比，
    private  float sw;//砂的含水量
    private int sf,skf;//粉煤灰等级1:1-2级  2:3级，kf的1代表s75,2代表s95

    private float rz;//容重
    float bs;//砂率


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
       tv5=findViewById(R.id.tv05);
       tv5.setVisibility(View.INVISIBLE);

       sb1=findViewById(R.id.sb1);
       sb2=findViewById(R.id.sb2);
       sb3=findViewById(R.id.sb3);
       sb4=findViewById(R.id.sb4);
       sb5=findViewById(R.id.sb5);
       sb5.setVisibility(View.INVISIBLE);

       sb1.setOnSeekBarChangeListener(this);
       sb2.setOnSeekBarChangeListener(this);
       sb3.setOnSeekBarChangeListener(this);
       sb4.setOnSeekBarChangeListener(this);
       sb5.setOnSeekBarChangeListener(this);

       tvts=findViewById(R.id.tvts);
       tvts.setVisibility(View.INVISIBLE);


       ed=findViewById(R.id.ed1);
       mswitch=findViewById(R.id.switch1);
       mswitch.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (mswitch.isChecked()){
                   rz=2250.0f;

               }
               else {
                   rz=2350.0f;
               }
           }
       });

       sw2=findViewById(R.id.sw2);
       sw2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (sw2.isChecked()){
                   tv5.setVisibility(View.VISIBLE);
                   sb5.setVisibility(View.VISIBLE);
               }else {
                   tv5.setVisibility(View.INVISIBLE);
                   sb5.setVisibility(View.INVISIBLE);
               }
           }
       });

       sw3=findViewById(R.id.sw3);
       sw4=findViewById(R.id.sw4);
       sw5=findViewById(R.id.sw5);
       sw6=findViewById(R.id.sw6);
       sw7=findViewById(R.id.sw7);

       sw3.setOnClickListener(this);
       sw4.setOnClickListener(this);
       sw5.setOnClickListener(this);
       sw6.setOnClickListener(this);
       sw7.setOnClickListener(this);



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
        sw=0;//水的含量默认为0
        rz=2350;//容重默认2350


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

        tvts.setVisibility(View.INVISIBLE);
        tvts.setText("");

        sw=getsw();//获取含水率

        float fb=getfb(f,kf,sf,skf);//获取胶砂强度

        float wb=getwb(fb,c);//获取水灰比


        //设置用水量
        float mw=getmw(d,tld, j);//获取初步用水量

        mw=changmw(mw);//根据砂模式初步调整用水量

        final float mc=mw/wb;//胶凝材料用量

        float mf=mc/100*f;//粉煤灰质量
        float mkf=mc/100*kf;//矿粉质量


        tssay(wb);//提示开始提示适配错误



        //确定砂率//如果开关是开
        if (sw2.isChecked()){
            float a=sb5.getProgress();
            bs= Float.parseFloat(save2(a*100/10000));
        }else {
          bs= getsl(wb,tld,d)/100;//获取砂率(通过水灰比跟石头直径)
              }
//        float pg= (float) 2.65,pc= (float) 3.1,ps= (float) 2.6;//密度--可调
//        //公式辅助值 体积法有bug
//        float x= (float) (990-mw-(mc/3.1));
//        //计算石头//固定密度2.65 砂2.6 水泥3.1，否则公式不能用
//        final float mg= (float) (x*2.65*2.6*(1-bs)/(2.6*(1-bs)+2.65));
//        //计算砂
//        final float ms=bs*mg/(1-bs);
//        final float all=mc+mg+ms+mw;
        //容重法2350
        float ms=(rz-mc-mw)*bs;//未减水的细集料质量
        final float mg=ms*(1-bs)/bs;

        float ms2=ms*(100+sw)/100;//砂的用量=砂*(1+含水率)//加了含水率后的沙子质量
        mw=mw-(ms*sw/100);//用水量=原用水量-砂的水量//扣除含水后的水的质量


        final float all=mc+mg+ms2+mw;



        String string="容重法配比"+" 砂含水率"+getsw()+
                "\n减水率"+j+ "%碎石最大直径"+d+"mm"+
                "\n水泥强度"+fce +" 塌落度"+tld
                +"\nC"+c+"配制GB标准强度"+ getfcu0(c) +
                "\n理论胶砂强度"+fb+
                "\n煤灰"+f+"%矿粉"+kf+"%"+
                "\n设计所需水灰比"+ wb
               +"\n砂率"+bs+
                "\n用水量"+mw+
                "\n胶凝材料"+save2(mc)+
                 "\n[水泥"+save2(mc*(100-f-kf)/100)
                +" 煤灰"+save2(mf)
                +" 矿粉"+save2(mkf)
                +"]\n砂"+save2(ms2)
                +"\n石头"+save2(mg)+"\n合计"+all
                +"\n胶凝:细集料:粗集料="+mc/mc+":"+save2(ms/mc)+":"+save2(mg/mc);

        if(mw==0)string+="\n尾数1-5的塌落度可能不在规范表格范围内，请重新更改塌落度";
        return string;
    }

    //保留2位数
    private String save2(float f){
        DecimalFormat format=new DecimalFormat("##0.00");
        return format.format(f) ;}

    private void tssay(float wb) {
        String ts="";
        if (wb>0.7||wb<0.3){//针对硅酸盐水泥，区别于普通硅酸盐水泥
            ts+="普通钢筋混凝土水灰比正常应处于0.3-0.7之间\n";
        }


        if(wb<=0.4){
            if (kf==0&&f>45){ts+="粉煤灰超标45% ";}
            if(f==0&&kf>65){ts+="矿粉超标65% ";}
            if (f!=0&&kf!=0&&kf+f>65){ts+="混合料超标65% ";}
        }
        if (wb>0.4){
            if (kf==0&&f>40){ts+="粉煤灰超标40% ";}
            if(f==0&&kf>55){ts+="矿粉超标55% ";}
            if (f!=0&&kf!=0&&kf+f>55){ts+="混合料超标55% ";}
        }

        tvts.setText(ts);
        tvts.setVisibility(View.VISIBLE);
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

    //获取砂率
    private float getsw(){

        if (TextUtils.isEmpty(ed.getText()))return 0.0f;
        float sw=Float.parseFloat(ed.getText().toString())>=500.0f?
                0.00f:
                100*Float.parseFloat(ed.getText().toString())/(500.0f-Float.parseFloat(ed.getText().toString()));
        return  sw;
    }

    /**通过砂的细度调整用水量**/
    private float changmw(float mw){
        if (sw3.isChecked())mw-=10;
        if (sw4.isChecked())mw-=5;
        if (sw5.isChecked())mw+=0;
        if (sw6.isChecked())mw+=5;
        if (sw7.isChecked())mw+=10;

        return mw;
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




                //三个开关
                case R.id.sw3:
                    sw3.setChecked(true);
                    sw4.setChecked(false);
                    sw5.setChecked(false);
                    sw6.setChecked(false);
                    sw7.setChecked(false);
                break;
                case R.id.sw4:
                    sw3.setChecked(false);
                    sw4.setChecked(true);
                    sw5.setChecked(false);
                    sw6.setChecked(false);
                    sw7.setChecked(false);
                break;
                case R.id.sw5:
                    sw3.setChecked(false);
                    sw4.setChecked(false);
                    sw5.setChecked(true);
                    sw6.setChecked(false);
                    sw7.setChecked(false);
                break;
                case R.id.sw6:
                    sw3.setChecked(false);
                    sw4.setChecked(false);
                    sw5.setChecked(false);
                    sw6.setChecked(true);
                    sw7.setChecked(false);
                break;
                case R.id.sw7:
                    sw3.setChecked(false);
                    sw4.setChecked(false);
                    sw5.setChecked(false);
                    sw6.setChecked(false);
                    sw7.setChecked(true);
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
            case R.id.sb5:
                tv5.setText("砂率"+progress);
                break;
            default:break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()){

            default:break;
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()){
            case R.id.sb1:j=seekBar.getProgress();break;//
            case R.id.sb2:tld=seekBar.getProgress();break;
            case R.id.sb3:f=seekBar.getProgress();break;
            case R.id.sb4:kf=seekBar.getProgress();break;
            case R.id.sb5: break;//砂率不做调整
            default:break;
        }
    }




}
