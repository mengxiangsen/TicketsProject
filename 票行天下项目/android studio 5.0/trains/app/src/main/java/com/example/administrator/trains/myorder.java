package com.example.administrator.trains;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class myorder extends Activity {


    List<Map<String,String>> list2;

    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            ListView lv = (ListView) findViewById(R.id.lv2);
            ListAdapter la = new SimpleAdapter(myorder.this,list2,R.layout.myorder_list_item,new String[]{"name","identityCard","phone","startPlace","endPlace","date","price"},new int[]{R.id.name,R.id.identify,R.id.phone,R.id.start,R.id.end,R.id.date,R.id.price});
            lv.setAdapter(la);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.activity_myorder);


        new Thread(){
            @Override
            public void run() {
                super.run();
                HttpClient hc = new DefaultHttpClient();

                HttpPost hp = new HttpPost("http://192.168.5.160:8080/Trains/myorder.jsp");

                try {

                    HttpResponse hr = hc.execute(hp);

                    if (hr.getStatusLine().getStatusCode() == 200) {

                        String aa = EntityUtils.toString(hr.getEntity());

                        aa.replace("/r/n", "");
                        aa.trim();

                        JSONArray ja = new JSONArray(aa);

                        list2 = new ArrayList<Map<String, String>>();

                        for (int i = 0; i < ja.length(); i++){
                            Map<String, String> map = new HashMap<String, String>();
                            JSONObject jo = ja.getJSONObject(i);
                            map.put("date", jo.getString("date"));
                            map.put("endPlace", jo.getString("endPlace"));
                            map.put("identityCard", jo.getString("identityCard"));
                            map.put("name", jo.getString("name"));
                            map.put("orderNum", jo.getString("orderNum"));
                            map.put("phone", jo.getString("phone"));
                            map.put("startPlace", jo.getString("startPlace"));
                            map.put("price", jo.getString("price"));
                            list2.add(map);
                        }

                        Message m = new Message();
                        myHandler.sendMessage(m);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }
}
