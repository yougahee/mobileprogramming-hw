package com.ghdev.moblieprogrammingpractice.forth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghdev.moblieprogrammingpractice.R;

import java.text.ChoiceFormat;

public class MainFourthActivity extends AppCompatActivity {

    Chronometer chronometer;
    Button btnStart, btnStop, btnRecord, btnContinue, btnReset;
    LinearLayout llstart, llstop, llreset, innerLayout;
    long stopTime=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fourth);

        init();

        btnclick();
    }

    public void init() {
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        btnStart = (Button) findViewById(R.id.btn_start);
        btnStop = (Button) findViewById(R.id.btn_stop);
        btnRecord = (Button) findViewById(R.id.btn_record);
        btnContinue = (Button) findViewById(R.id.btn_continue);
        btnReset = (Button) findViewById(R.id.btn_reset);

        //ll
        llstart = (LinearLayout) findViewById(R.id.ll_start);
        llstop = (LinearLayout) findViewById(R.id.ll_stop_record);
        llreset =(LinearLayout) findViewById(R.id.ll_continue_reset);

        innerLayout = (LinearLayout)findViewById(R.id.ll_inner_layout);

    }

    //버튼 클릭
    public void btnclick() {
        //시작버튼
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                llstart.setVisibility(View.GONE);
                llstop.setVisibility(View.VISIBLE);

                chronometer.start();

            }
        });

        //중지
        btnStop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                chronometer.stop();
                stopTime = chronometer.getBase() - SystemClock.elapsedRealtime();

                llstop.setVisibility(View.GONE);
                llreset.setVisibility(View.VISIBLE);
            }
        });

        //구간기록
        btnRecord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                makeText();

            }
        });

        //초기화
        btnReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                llreset.setVisibility(View.GONE);
                llstart.setVisibility(View.VISIBLE);

                //구간기록 삭제
                innerLayout.removeAllViews();

                //00으로 초기화
                chronometer.setBase(SystemClock.elapsedRealtime());

                stopTime = 0;
            }
        });


        //계속하기
        btnContinue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                llreset.setVisibility(View.GONE);
                llstop.setVisibility(View.VISIBLE);

                chronometer.setBase(SystemClock.elapsedRealtime() + stopTime);
                chronometer.start();

            }
        });


    }

    private void makeText() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10,10,10,10);

        TextView markText = new TextView(this);
        markText.setLayoutParams(params);

        markText.setGravity(Gravity.CENTER);
        markText.setText(chronometer.getText());
        markText.setTextSize(25);
        innerLayout.addView(markText, 0);

    }
}
