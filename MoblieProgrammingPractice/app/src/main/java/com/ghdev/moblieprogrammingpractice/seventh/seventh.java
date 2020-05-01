package com.ghdev.moblieprogrammingpractice.seventh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ghdev.moblieprogrammingpractice.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class seventh extends AppCompatActivity {

    Button btnsave, btnedit;
    LinearLayout llvisible;
    TextView tvCalendar;
    EditText etCalendar;
    CalendarView calendarView;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventh);

        init();
        setCal();
    }

    public void init() {
        btnsave = findViewById(R.id.btn_save);
        btnedit = findViewById(R.id.btn_edit_);

        llvisible = findViewById(R.id.ll_enter_calendar);

        tvCalendar = findViewById(R.id.tv_calendar);
        etCalendar = findViewById(R.id.et_calendar);

        calendarView = findViewById(R.id.cv_calendarview);
    }

    public void setCal() {

        //선택을 안하고! 현재 날짜에서 추가를 하고 싶다면?!

        Calendar cal = Calendar.getInstance();
        int tyear = cal.get(Calendar.YEAR);
        int tmonth = cal.get(Calendar.MONTH);
        int tday = cal.get(Calendar.DAY_OF_MONTH);

        //기본일정setting
        settingReadFile(tyear,tmonth, tday);
        clickBtnEdit();
        clickBtnSave(tyear,tmonth, tday);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //(default) 날짜 선택했을 때,
                llvisible.setVisibility(View.INVISIBLE);
                btnedit.setVisibility(View.VISIBLE);

                //setText
                settingReadFile(year, month, dayOfMonth);

                //일정수정을 누르는 경우
                clickBtnEdit();

                //반영을 누르는 경우
                clickBtnSave(year, month, dayOfMonth);
            }
        });
    }

    //tvCalendar에 데이터가 존재하면 setText
    public void settingReadFile(int year, int month, int dayOfMonth) {
        fileName = Integer.toString(year) +"_" + Integer.toString(month) + "_" + Integer.toString(dayOfMonth) + ".txt";
        String str = readDiary(fileName);
        tvCalendar.setText(str);
    }

    //수정버튼
    public void clickBtnEdit() {
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etCalendar.setText("");
                llvisible.setVisibility(View.VISIBLE);
                btnedit.setVisibility(View.INVISIBLE);
            }
        });
    }


    //반영버튼
    public void clickBtnSave(final int year, final int month, final int dayOfMonth){

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //뷰!
                llvisible.setVisibility(View.INVISIBLE);
                btnedit.setVisibility(View.VISIBLE);

                //파일저장
                fileName = Integer.toString(year) +"_" + Integer.toString(month) + "_" + Integer.toString(dayOfMonth) + ".txt";
                Log.v("TAGG", fileName);

                //파일 쓰기
                writeDiary(fileName);

                String str = readDiary(fileName);
                tvCalendar.setText(str);

            }
        });
    }

    //파일쓰기
    //사실, 파일이 안쓰여졌을때는 예외처리? 해줘야하는데ㅎ 잘 되서 그냥 냅둠
    Boolean writeDiary(String fileName) {
        try{
            FileOutputStream outfs = openFileOutput(fileName, Context.MODE_PRIVATE);
            String str = etCalendar.getText().toString();
            outfs.write(str.getBytes());
            outfs.close();
            Toast.makeText(getApplicationContext(), fileName + "이 저장됨", Toast.LENGTH_LONG).show();
            return true;

        }catch (IOException e) {
            return false;
        }
    }

    //fileName에 있는 text읽기
    String readDiary(String fileName) {
        String diaryStr = null;
        FileInputStream inFs;
        try {
            inFs = openFileInput(fileName);
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt).trim());

        } catch (IOException e) {
            diaryStr = "일정이 없습니다.";
        }
        return diaryStr;
    }

}
