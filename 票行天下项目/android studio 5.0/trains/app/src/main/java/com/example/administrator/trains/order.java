package com.example.administrator.trains;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
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


public class order extends Activity {

    String vehicleNum;
    String startPlace;
    String endPlace;
    String startTime;
    String endTime;
    String price;
    String date;



    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                Toast.makeText(order.this,"提交成功,可以查看订单哦~~",Toast.LENGTH_LONG).show();
            }
            if(msg.what==2){
                Toast.makeText(order.this,"亲,不能为空",Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.activity_order);

        Intent i = getIntent();
        vehicleNum = i.getStringExtra("vehicleNum");
        startPlace = i.getStringExtra("startPlace");
        endPlace = i.getStringExtra("endPlace");
        startTime = i.getStringExtra("startTime");
        endTime = i.getStringExtra("endTime");
        price = i.getStringExtra("price");
        date = i.getStringExtra("date");

        final TextView czh_tv1 = (TextView)findViewById(R.id.czh_tv1);  //开始地
        final TextView czh_tv4 = (TextView)findViewById(R.id.czh_tv4);  //目的地
        final TextView czh_tv2 = (TextView)findViewById(R.id.czh_tv2);   //
        final TextView czh_tv16 = (TextView)findViewById(R.id.czh_tv16); //开始时间
        final TextView czh_tv14 = (TextView)findViewById(R.id.czh_tv14);  //结束时间
        final TextView czh_tv13 = (TextView)findViewById(R.id.czh_tv13);  //日期
        final TextView czh_tv12 = (TextView)findViewById(R.id.czh_tv12); //票价

        final EditText czh_et1 = (EditText)findViewById(R.id.czh_et1); // 手机号
        final EditText  czh_et2 = (EditText)findViewById(R.id.czh_et2); // 身份证
        final EditText  czh_et3 = (EditText)findViewById(R.id.czh_et3); // 车票价

        Button tijiao = (Button)findViewById(R.id.czh_bt1); //提交

        czh_tv1.setText(startPlace);
        czh_tv4.setText(endPlace);
        czh_tv2.setText(startTime);
        czh_tv16.setText(endTime);
        czh_tv14.setText(vehicleNum);
        czh_tv12.setText(price+"元");
        czh_tv13.setText(show.date);

//        tijiao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                new Thread() {
//                    @Override
//                    public void run() {
//                        super.run();
//
//                        HttpClient hc = new DefaultHttpClient();
//                        HttpPost hp = new HttpPost("http://192.168.5.160:8080/Trains/order.jsp");
//
//                        BasicNameValuePair bv2 = new BasicNameValuePair("name", czh_et1.getText().toString());
//                        BasicNameValuePair bv3 = new BasicNameValuePair("phone", czh_et2.getText().toString());
//                        BasicNameValuePair bv4 = new BasicNameValuePair("identityCard", czh_et3.getText().toString());
//                        BasicNameValuePair bv5 = new BasicNameValuePair("startPlace", czh_tv1.getText().toString());
//                        BasicNameValuePair bv6 = new BasicNameValuePair("endPlace", czh_tv4.getText().toString());
//                        BasicNameValuePair bv7 = new BasicNameValuePair("date", czh_tv13.getText().toString());
//                        BasicNameValuePair bv8 = new BasicNameValuePair("vehicleNum", czh_tv14.getText().toString());
//                        BasicNameValuePair bv9 = new BasicNameValuePair("price", price + "元");
//
//                        List<NameValuePair> list = new ArrayList<NameValuePair>();
//                        list.add(bv2);
//                        list.add(bv3);
//                        list.add(bv4);
//                        list.add(bv5);
//                        list.add(bv6);
//                        list.add(bv7);
//                        list.add(bv8);
//                        list.add(bv9);
//
//                        try {
//                            UrlEncodedFormEntity uf = new UrlEncodedFormEntity(list, HTTP.UTF_8);
//
//                            hp.setEntity(uf);
//
//                            HttpResponse hr = hc.execute(hp);
//
//                            if (hr.getStatusLine().getStatusCode() == 200) {
//
//                                String aa = EntityUtils.toString(hr.getEntity());
//
//                                if (aa.indexOf("OK") != -1) {
//                                    Intent i = new Intent(order.this, success.class);
//                                    startActivity(i);
//                                }
//
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }.start();
//            }
//        });



        tijiao.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setBackgroundDrawable(getResources().getDrawable(R.drawable.pswshape2));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    v.setBackgroundDrawable(getResources().getDrawable(R.drawable.pswshape));

                    new Thread() {
                        @Override
                        public void run() {
                            super.run();

                           if( !czh_et2.getText().toString().equals("") || !czh_et3.getText().toString().equals("") || !czh_et1.getText().toString().equals("")){
                               HttpClient hc = new DefaultHttpClient();
                               HttpPost hp = new HttpPost("http://192.168.5.160:8080/Trains/order.jsp");

                               BasicNameValuePair bv2 = new BasicNameValuePair("name", czh_et1.getText().toString());
                               BasicNameValuePair bv3 = new BasicNameValuePair("phone", czh_et2.getText().toString());
                               BasicNameValuePair bv4 = new BasicNameValuePair("identityCard", czh_et3.getText().toString());
                               BasicNameValuePair bv5 = new BasicNameValuePair("startPlace", czh_tv1.getText().toString());
                               BasicNameValuePair bv6 = new BasicNameValuePair("endPlace", czh_tv4.getText().toString());
                               BasicNameValuePair bv7 = new BasicNameValuePair("date", czh_tv13.getText().toString());
                               BasicNameValuePair bv8 = new BasicNameValuePair("vehicleNum", czh_tv14.getText().toString());
                               BasicNameValuePair bv9 = new BasicNameValuePair("price", price + "元");

                               List<NameValuePair> list = new ArrayList<NameValuePair>();
                               list.add(bv2);
                               list.add(bv3);
                               list.add(bv4);
                               list.add(bv5);
                               list.add(bv6);
                               list.add(bv7);
                               list.add(bv8);
                               list.add(bv9);

                               try {
                                   UrlEncodedFormEntity uf = new UrlEncodedFormEntity(list, HTTP.UTF_8);

                                   hp.setEntity(uf);

                                   HttpResponse hr = hc.execute(hp);

                                   if (hr.getStatusLine().getStatusCode() == 200) {

                                       String aa = EntityUtils.toString(hr.getEntity());

                                       if (aa.indexOf("OK") != -1) {

                                           Message m = new Message();
                                           m.what=1;
                                           myHandler.sendMessage(m);

                                           Intent i = new Intent(order.this, select.class);
                                           startActivity(i);
                                       }

                                   }
                               } catch (IOException e) {
                                   e.printStackTrace();
                               }
                           }else{
                               Message m = new Message();
                               m.what=2;
                               myHandler.sendMessage(m);

                           }
                        }
                    }.start();
                }
                return true;
            }
        });

    }
}