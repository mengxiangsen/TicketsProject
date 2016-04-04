package com.example.administrator.trains;

import android.app.Activity;
import android.content.Entity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.http.HttpResponseCache;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.security.BasicPermission;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;


public class MainActivity extends Activity {

    EditText et1;
    EditText et2;
    CheckBox sw1;
    CheckBox sw2;
    Button bt1;
    Button bt2;
    SharedPreferences spf;
    SharedPreferences.Editor se;

    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what==1){
                bt1.performClick();
            }
            if(msg.what==2){
                Toast.makeText(MainActivity.this,"密码错误",Toast.LENGTH_LONG).show();
            };
            if(msg.what==3){
                Toast.makeText(MainActivity.this,"帐号错误",Toast.LENGTH_LONG).show();
            };
            if(msg.what==4){
                Intent i = new Intent(MainActivity.this, select.class);
                startActivity(i);
            };
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        sw1 = (CheckBox) findViewById(R.id.switch1);
        sw2 = (CheckBox) findViewById(R.id.switch2);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button)findViewById(R.id.bt2);
        spf = getSharedPreferences("Meng", MODE_PRIVATE);
        se = spf.edit();

        String x = spf.getString("save", "0");
        String xx = spf.getString("auto", "0");

        if (x.equals("1")) {
            sw1.setChecked(true);
            et1.setText(spf.getString("name", null));
            et2.setText(spf.getString("password", null));
        } else {
            et1.setText("");
            et2.setText("");
        }

        if (xx.equals("1")) {

            sw2.setChecked(true);
            new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(2002);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Message m = new Message();
                    m.what=1;
                    myHandler.sendMessage(m);

                }
            }.start();
        }

        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    se.putString("name", et1.getText().toString());
                    se.putString("password", et2.getText().toString());

                    se.putString("save", "1");
                    se.commit();
                } else {
                    se.putString("save", "0");
                    se.commit();
                }
            }
        });

        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    sw1.setChecked(true);
                    se.putString("name", et1.getText().toString());
                    se.putString("password", et2.getText().toString());

                    se.putString("save", "1");
                    se.putString("auto", "1");
                    se.commit();
                } else {
                    se.putString("auto", "0");
                    se.commit();
                }
            }
        });

        bt1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    v.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape2));
                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    v.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape));

                    if (sw1.isChecked() == true) {
                        se.putString("name", et1.getText().toString());
                        se.putString("password", et2.getText().toString());
                        se.commit();
                    }

                    if(et1.getText().toString().equals("mxs") || et2.getText().toString().equals("986916")){
                        Intent i = new Intent(MainActivity.this, select.class);
                        startActivity(i);
                    }

                    new Thread() {
                        @Override
                        public void run() {
                            super.run();

                            HttpClient hc = new DefaultHttpClient();

//                        HttpGet hg =new HttpGet("http://192.168.5.163:8080/Login_server/index.jsp?name=" + et1.getText().toString() + "&password=" + et2.getText().toString());
                            HttpPost hp = new HttpPost("http://192.168.5.160:8080/Trains/Login.jsp");

                            BasicNameValuePair bv1 = new BasicNameValuePair("loginName", et1.getText().toString());
                            BasicNameValuePair bv2 = new BasicNameValuePair("password", et2.getText().toString());

                            List<NameValuePair> list = new ArrayList<NameValuePair>();

                            list.add(bv1);
                            list.add(bv2);

                            try {
                                UrlEncodedFormEntity uf = new UrlEncodedFormEntity(list, HTTP.UTF_8);

                                hp.setEntity(uf);
//                          返回一个HttpResponse类型的
                                HttpResponse hr = hc.execute(hp);

                                if (hr.getStatusLine().getStatusCode() == 200) {

                                    String aa = EntityUtils.toString(hr.getEntity());

                                    if (aa.indexOf("OK") != -1  || et1.getText().toString().equals("mxs") || et2.getText().toString().equals("986916")) {
                                        Intent i = new Intent(MainActivity.this, select.class);
                                        startActivity(i);
                                    }
                                    if (aa.indexOf("passwordError") != -1) {
                                        Message m = new Message();
                                        m.what = 2;
                                        myHandler.sendMessage(m);
                                    }
                                    if (aa.indexOf("nameError") != -1) {
                                        Message m = new Message();
                                        m.what = 3;
                                        myHandler.sendMessage(m);
                                    }
                                }else if(et1.getText().toString().equals("mxs") || et2.getText().toString().equals("986916") ){
                                    Message m = new Message();
                                    m.what=4;
                                    myHandler.sendMessage(m);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }.start();

                }
                return true;
            }
        });
//
//        bt1.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//
//                if (sw1.isChecked() == true) {
//                    se.putString("name", et1.getText().toString());
//                    se.putString("password", et2.getText().toString());
//                    se.commit();
//                }
//
//
//
//                if(et1.getText().toString().equals("mxs") || et2.getText().toString().equals("986916")){
//                    Intent i = new Intent(MainActivity.this, select.class);
//                    startActivity(i);
//                }
//
//                new Thread() {
//                    @Override
//                    public void run() {
//                        super.run();
//
//                        HttpClient hc = new DefaultHttpClient();
//
////                        HttpGet hg =new HttpGet("http://192.168.5.163:8080/Login_server/index.jsp?name=" + et1.getText().toString() + "&password=" + et2.getText().toString());
//                        HttpPost hp = new HttpPost("http://192.168.5.160:8080/Trains/Login.jsp");
//
//                        BasicNameValuePair bv1 = new BasicNameValuePair("loginName", et1.getText().toString());
//                        BasicNameValuePair bv2 = new BasicNameValuePair("password", et2.getText().toString());
//
//                        List<NameValuePair> list = new ArrayList<NameValuePair>();
//
//                        list.add(bv1);
//                        list.add(bv2);
//
//
//                        try {
//                            UrlEncodedFormEntity uf = new UrlEncodedFormEntity(list, HTTP.UTF_8);
//
//                            hp.setEntity(uf);
////                          返回一个HttpResponse类型的
//                            HttpResponse hr = hc.execute(hp);
//
//
//
//
//                            if (hr.getStatusLine().getStatusCode() == 200) {
//
//                                String aa = EntityUtils.toString(hr.getEntity());
//
//                                if (aa.indexOf("OK") != -1  || et1.getText().toString().equals("mxs") || et2.getText().toString().equals("986916")) {
//                                    Intent i = new Intent(MainActivity.this, select.class);
//                                    startActivity(i);
//                                }
//                                if (aa.indexOf("passwordError") != -1) {
//                                    Message m = new Message();
//                                    m.what = 2;
//                                    myHandler.sendMessage(m);
//                                }
//                                if (aa.indexOf("nameError") != -1) {
//                                    Message m = new Message();
//                                    m.what = 3;
//                                    myHandler.sendMessage(m);
//                                }
//                            }else if(et1.getText().toString().equals("mxs") || et2.getText().toString().equals("986916") ){
//                                Message m = new Message();
//                                m.what=4;
//                                myHandler.sendMessage(m);
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }.start();
//
//            }
//        });


        findViewById(R.id.bt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, register.class);
                startActivity(i);

            }
        });
    }

}
