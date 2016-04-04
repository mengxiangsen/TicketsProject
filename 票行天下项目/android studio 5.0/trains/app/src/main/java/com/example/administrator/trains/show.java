package com.example.administrator.trains;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class show extends Activity {

    String startPlace;
    String endPlace;
    static String date;
    String type;
    List<Map<String,String>> list2;
    List<Map<String,String>> list3;

    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
          if(msg.what==1){
              ListView lv = (ListView) findViewById(R.id.lv);
              ListAdapter la = new SimpleAdapter(show.this,list2,R.layout.activity_show_list_item,new String[]{"vehicleNum","startPlace","endPlace","startTime","endTime","type","price"},new int[]{R.id.vehicleNum,R.id.startPlace,R.id.endPlace,R.id.information,R.id.endTime,R.id.type,R.id.price});
              lv.setAdapter(la);

              lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                  @Override
                  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                      Intent i = new Intent(show.this,order.class);

                      i.putExtra("vehicleNum",list2.get(position).get("vehicleNum").toString());
                      i.putExtra("startPlace",list2.get(position).get("startPlace").toString());
                      i.putExtra("endPlace",list2.get(position).get("endPlace").toString());
                      i.putExtra("startTime",list2.get(position).get("startTime").toString());
                      i.putExtra("endTime",list2.get(position).get("endTime").toString());
                      i.putExtra("price",list2.get(position).get("price").toString());

                      startActivity(i);

                  }


              });}

            if(msg.what==2){
                ListView lv = (ListView) findViewById(R.id.lv);
                ListAdapter la = new SimpleAdapter(show.this,list3,R.layout.activity_show_list_item2,new String[]{"information"},new int[]{R.id.information});
                lv.setAdapter(la);

            }


          }



    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.activity_show);



        new Thread(){
            @Override
            public void run() {
                super.run();
                HttpClient hc = new DefaultHttpClient();

                HttpPost hp = new HttpPost("http://192.168.5.160:8080/Trains/select.jsp");

                Intent intent = getIntent();
                startPlace = intent.getStringExtra("startPlace");
                endPlace = intent.getStringExtra("endPlace");
                date = intent.getStringExtra("date");
                type = intent.getStringExtra("type");

                BasicNameValuePair bv1 = new BasicNameValuePair("startPlace",startPlace);
                BasicNameValuePair bv2 = new BasicNameValuePair("endPlace",endPlace);
                BasicNameValuePair bv3 = new BasicNameValuePair("date",date);
                BasicNameValuePair bv4 = new BasicNameValuePair("type",type);

                List<NameValuePair> list = new ArrayList<NameValuePair>();

                list.add(bv1);
                list.add(bv2);
                list.add(bv3);
                list.add(bv4);

                try {
                    UrlEncodedFormEntity uf = new UrlEncodedFormEntity(list, HTTP.UTF_8);

                    hp.setEntity(uf);
                    HttpResponse hr = hc.execute(hp);

                    if (hr.getStatusLine().getStatusCode() == 200) {

                        String aa = EntityUtils.toString(hr.getEntity());

                        aa.replace("/r/n", "");
                        aa.trim();

                            JSONArray ja = new JSONArray(aa);

                            list2 = new ArrayList<Map<String, String>>();
                            list3 = new ArrayList<Map<String, String>>();

                           if(ja.length()!=0){
                               for (int i = 0; i < ja.length(); i++) {
                                   Map<String, String> map = new HashMap<String, String>();
                                   JSONObject jo = ja.getJSONObject(i);
                                   map.put("vehicleNum", jo.getString("vehicleNum"));
                                   map.put("startPlace", jo.getString("startPlace"));
                                   map.put("endPlace", jo.getString("endPlace"));
                                   map.put("startTime", jo.getString("startTime"));
                                   map.put("endTime", jo.getString("endTime"));
                                   map.put("type", jo.getString("type"));
                                   map.put("price", jo.getString("price"));
                                   map.put("date", jo.getString("date"));
                                   list2.add(map);
                               }

                               Message m = new Message();
                               m.what=1;
                               myHandler.sendMessage(m);
                           }else{

                               Map<String, String> map = new HashMap<String, String>();
                               map.put("information","服务器已爆炸,亲,请速速离去(请检测数据库）");
                               list3.add(map);

                               Message m = new Message();
                               m.what=2;
                               myHandler.sendMessage(m);
                           }


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
