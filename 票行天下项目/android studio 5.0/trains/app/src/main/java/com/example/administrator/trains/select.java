package com.example.administrator.trains;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;


public class select extends Activity {

    String startPlace;
    DatePickerDialog.OnDateSetListener time ;
    String type="全部";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.activity_select);



       final TextView zxh_tv1= (TextView) findViewById(R.id.zxh_tv1);
        final TextView zxh_tv2= (TextView) findViewById(R.id.zxh_tv2);
        final EditText startTime =(EditText)findViewById(R.id.information);
        final TextView s1 = (TextView) findViewById(R.id.s1);
        final TextView s2 = (TextView) findViewById(R.id.s2);
        final TextView s3 = (TextView) findViewById(R.id.s3);
        final  TextView s4 = (TextView) findViewById(R.id.s4);
        final  TextView s5 = (TextView) findViewById(R.id.s5);

        final Button selectButton = (Button)findViewById(R.id.selectButton);

        final Button myorder = (Button)findViewById(R.id.myorder);

        zxh_tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builer = new AlertDialog.Builder(select.this);
                builer.setTitle("开始地");
                builer.setSingleChoiceItems(R.array.startPlace, 0,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startPlace = getResources().getStringArray(R.array.startPlace)[which];
                                zxh_tv1.setText(startPlace);
                            }
                        });
                builer.setPositiveButton("确定", null).show();
            }

        });

        zxh_tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builer = new AlertDialog.Builder(select.this);
                builer.setTitle("目的地");
                builer.setSingleChoiceItems(R.array.startPlace, 0,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startPlace = getResources().getStringArray(R.array.startPlace)[which];
                                zxh_tv2.setText(startPlace);
                            }
                        });
                builer.setPositiveButton("确定", null).show();
            }

        });

        RelativeLayout tv3= (RelativeLayout) findViewById(R.id.tv3);

        time = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear  = monthOfYear + 1;
                startTime.setText(year+"-" +monthOfYear + "-" + dayOfMonth );
            }
        };



        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar=Calendar.getInstance();
                DatePickerDialog dialog=new DatePickerDialog(select.this,time,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));


                dialog.show();


            }
        });

        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1.setBackgroundResource(R.drawable.select);
                s2.setBackgroundResource(R.drawable.select1);
                s3.setBackgroundResource(R.drawable.select1);
                s4.setBackgroundResource(R.drawable.select1);
                s5.setBackgroundResource(R.drawable.select1);
                s1.setTextColor(getResources().getColor(R.color.white));
                s2.setTextColor(getResources().getColor(R.color.black));
                s3.setTextColor(getResources().getColor(R.color.black));
                s4.setTextColor(getResources().getColor(R.color.black));
                s5.setTextColor(getResources().getColor(R.color.black));

//                s1.setBackground(getDrawable(R.drawable.select));
//                s2.setBackground(getDrawable(R.drawable.select1));
//                s3.setBackground(getDrawable(R.drawable.select1));
//                s4.setBackground(getDrawable(R.drawable.select1));
//                s5.setBackground(getDrawable(R.drawable.select1));
                type = "全部";
            }
        });
        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                s1.setBackgroundResource(R.drawable.select1);
                s2.setBackgroundResource(R.drawable.select);
                s3.setBackgroundResource(R.drawable.select1);
                s4.setBackgroundResource(R.drawable.select1);
                s5.setBackgroundResource(R.drawable.select1);
                s1.setTextColor(getResources().getColor(R.color.black));
                s2.setTextColor(getResources().getColor(R.color.white));
                s3.setTextColor(getResources().getColor(R.color.black));
                s4.setTextColor(getResources().getColor(R.color.black));
                s5.setTextColor(getResources().getColor(R.color.black));
                s5.setTextColor(getResources().getColor(R.color.black));
//                s1.setBackground(getDrawable(R.drawable.select1));
//                s2.setBackground(getDrawable(R.drawable.select));
//                s3.setBackground(getDrawable(R.drawable.select1));
//                s4.setBackground(getDrawable(R.drawable.select1));
//                s5.setBackground(getDrawable(R.drawable.select1));
                type="G/D/C" ;
            }
        });
        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1.setBackgroundResource(R.drawable.select1);
                s2.setBackgroundResource(R.drawable.select1);
                s3.setBackgroundResource(R.drawable.select);
                s4.setBackgroundResource(R.drawable.select1);
                s5.setBackgroundResource(R.drawable.select1);
                s1.setTextColor(getResources().getColor(R.color.black));
                s2.setTextColor(getResources().getColor(R.color.black));
                s3.setTextColor(getResources().getColor(R.color.white));
                s4.setTextColor(getResources().getColor(R.color.black));
                s5.setTextColor(getResources().getColor(R.color.black));


//                s1.setBackground(getDrawable(R.drawable.select1));
//                s2.setBackground(getDrawable(R.drawable.select1));
//                s3.setBackground(getDrawable(R.drawable.select));
//                s4.setBackground(getDrawable(R.drawable.select1));
//                s5.setBackground(getDrawable(R.drawable.select1));
                type="Z字头";
            }
        });
        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1.setBackgroundResource(R.drawable.select1);
                s2.setBackgroundResource(R.drawable.select1);
                s3.setBackgroundResource(R.drawable.select1);
                s4.setBackgroundResource(R.drawable.select);
                s5.setBackgroundResource(R.drawable.select1);
                s1.setTextColor(getResources().getColor(R.color.black));
                s2.setTextColor(getResources().getColor(R.color.black));
                s3.setTextColor(getResources().getColor(R.color.black));
                s4.setTextColor(getResources().getColor(R.color.white));
                s5.setTextColor(getResources().getColor(R.color.black));

//                s1.setBackground(getDrawable(R.drawable.select1));
//                s2.setBackground(getDrawable(R.drawable.select1));
//                s3.setBackground(getDrawable(R.drawable.select1));
//                s4.setBackground(getDrawable(R.drawable.select));
//                s5.setBackground(getDrawable(R.drawable.select1));
                type="T字头";
            }
        });

        s5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1.setBackgroundResource(R.drawable.select1);
                s2.setBackgroundResource(R.drawable.select1);
                s3.setBackgroundResource(R.drawable.select1);
                s4.setBackgroundResource(R.drawable.select1);
                s5.setBackgroundResource(R.drawable.select);
                s1.setTextColor(getResources().getColor(R.color.black));
                s2.setTextColor(getResources().getColor(R.color.black));
                s3.setTextColor(getResources().getColor(R.color.black));
                s4.setTextColor(getResources().getColor(R.color.black));
                s5.setTextColor(getResources().getColor(R.color.white));

//                s1.setBackground(getDrawable(R.drawable.select1));
//                s2.setBackground(getDrawable(R.drawable.select1));
//                s3.setBackground(getDrawable(R.drawable.select1));
//                s4.setBackground(getDrawable(R.drawable.select1));
//                s5.setBackground(getDrawable(R.drawable.select));
                type="其他";
            }
        });

//
//        selectButton.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//
//
//                    Intent i = new Intent(select.this,show.class);
//                    i.putExtra("startPlace", zxh_tv1.getText().toString());
//                    i.putExtra("endPlace",zxh_tv2.getText().toString());
//                    i.putExtra("type", type);
//                    i.putExtra("date",startTime.getText().toString());
//                    startActivity(i);
//            }
//        });

        selectButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setBackgroundDrawable(getResources().getDrawable(R.drawable.selectbutton2));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    v.setBackgroundDrawable(getResources().getDrawable(R.drawable.selectbutton));

                    Intent i = new Intent(select.this,show.class);
                    i.putExtra("startPlace", zxh_tv1.getText().toString());
                    i.putExtra("endPlace",zxh_tv2.getText().toString());
                    i.putExtra("type", type);
                    i.putExtra("date",startTime.getText().toString());
                    startActivity(i);
                }
                return true;
            }
        });




        myorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(select.this,myorder.class);
                startActivity(i);
            }
        });
    }

}