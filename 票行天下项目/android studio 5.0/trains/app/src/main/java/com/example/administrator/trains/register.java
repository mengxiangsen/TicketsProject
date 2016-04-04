package com.example.administrator.trains;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class register extends Activity implements OnClickListener  {

    private Button sensmsButton,verificationButton,countryButton;
    private TextView countryTextView,textView2;
    private EditText phonEditText,verEditText;

    //添加的密码验证
    EditText edt1;
    EditText edt2;

    private static String APPKEY ="7b985099eb84" ;//"7b985099eb84";//463db7238681  27fe7909f8e8
    // 填写从短信SDK应用后台注册得到的APPSECRET
    private static String APPSECRET = "9ebcaebd4e81ab6fca346720c4d69072";
    //"9ebcaebd4e81ab6fca346720c4d69072";//
    public String phString;                                              //3684fd4f0e16d68f69645af1c7f8f5bd

    //添加的密码验证
    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what==2){
                Toast.makeText(register.this,"注册失败",Toast.LENGTH_LONG).show();
            }
            if(msg.what==3){
                Toast.makeText(register.this,"注册成功,亲,可以登录喽~~",Toast.LENGTH_LONG).show();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        sensmsButton=(Button) findViewById(R.id.button1);
        verificationButton=(Button) findViewById(R.id.button2);
        phonEditText=(EditText) findViewById(R.id.editText1);
        verEditText=(EditText) findViewById(R.id.editText2);
        sensmsButton.setOnClickListener(this);
        verificationButton.setOnClickListener(this);

        //添加的密码验证
        edt1 = (EditText) findViewById(R.id.edt1);
        edt2= (EditText) findViewById(R.id.edt2);

//        sensmsButton.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction()==MotionEvent.ACTION_DOWN){
//                    v.setBackgroundDrawable(getResources().getDrawable(R.drawable.register_yanzhengbutton2));
//                }else if(event.getAction()==MotionEvent.ACTION_UP){
//                    v.setBackgroundDrawable(getResources().getDrawable(R.drawable.register_yanzhengbutton));
//                }
//                return true;
//            }
//        });
//
//        verificationButton.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction()==MotionEvent.ACTION_DOWN){
//                    v.setBackgroundDrawable(getResources().getDrawable(R.drawable.register_yanzhengbutton2));
//                }else if(event.getAction()==MotionEvent.ACTION_UP){
//                    v.setBackgroundDrawable(getResources().getDrawable(R.drawable.register_yanzhengbutton));
//                }
//                return true;
//            }
//        });

        //System.loadLibrary("OSbase");
        SMSSDK.initSDK(this, APPKEY, APPSECRET);

        SMSSDK.getFriendsInApp();

        EventHandler eh=new EventHandler(){

            @Override
            public void afterEvent(int event, int result, Object data) {

                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }

        };
        SMSSDK.registerEventHandler(eh);
    }
    @Override
    public void onClick(View v) {

        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.button1://获取验证码

                if (!TextUtils.isEmpty(phonEditText.getText().toString())) {
                    SMSSDK.getVerificationCode("86", phonEditText.getText().toString());
                    phString = phonEditText.getText().toString();
                } else {
                    Toast.makeText(this, "电话不能为空", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.button2://校验验证码
                if(!TextUtils.isEmpty(verEditText.getText().toString())){
                    SMSSDK.submitVerificationCode("86", phString, verEditText.getText().toString());
                }else {
                    Toast.makeText(this, "验证码不能为空", Toast.LENGTH_LONG).show();
                }

                if (edt1.getText().toString().matches("[0-9a-zA-Z]{6}") && edt2.getText().toString().matches("[0-9a-zA-Z]{6}")) {
                    if (edt1.getText().toString().equals(edt2.getText().toString())) {


                        new Thread() {
                            @Override
                            public void run() {
                                super.run();

                                HttpClient hc = new DefaultHttpClient();

//                        HttpGet hg =new HttpGet("http://192.168.5.163:8080/Login_server/index.jsp?name=" + et1.getText().toString() + "&password=" + et2.getText().toString());
                                HttpPost hp = new HttpPost("http://192.168.5.160:8080/Trains/register.jsp");

                                BasicNameValuePair bv1 = new BasicNameValuePair("loginName", phString);
                                BasicNameValuePair bv2 = new BasicNameValuePair("password", edt1.getText().toString());

                                List<NameValuePair> list = new ArrayList<NameValuePair>();

                                list.add(bv1);
                                list.add(bv2);


                                try {
                                    UrlEncodedFormEntity uf = new UrlEncodedFormEntity(list, HTTP.UTF_8);

                                    hp.setEntity(uf);

                                    HttpResponse hr = hc.execute(hp);

                                    if (hr.getStatusLine().getStatusCode() == 200) {

                                        String aa = EntityUtils.toString(hr.getEntity());

                                        if (aa.indexOf("phoneOk") != -1) {

                                            Intent i = new Intent(register.this, MainActivity.class);
                                            startActivity(i);

                                            Message m = new Message();
                                            m.what=3;
                                            myHandler.sendMessage(m);

                                        }

                                        if (aa.indexOf("Inserterror") != -1) {
                                            Message m = new Message();
                                            m.what = 2;
                                            myHandler.sendMessage(m);
                                        }

                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        }.start();


                    } else {
                        Toast.makeText(register.this, "密码不一致", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(register.this, "密码格式不对", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }
    Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            Log.e("event", "event="+event);
            if (result == SMSSDK.RESULT_COMPLETE) {
                //短信注册成功后，返回MainActivity,然后提示新好友
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功
                    Toast.makeText(getApplicationContext(), "提交验证码成功", Toast.LENGTH_SHORT).show();

                    //textView2.setText("提交验证码成功");
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    Toast.makeText(getApplicationContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();
                    //textView2.setText("验证码已经发送");
                }
            }

        }

    };
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }


}
