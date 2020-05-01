package com.ghdev.moblieprogrammingpractice.third;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ghdev.moblieprogrammingpractice.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setCheckbox();
        setRadioBtn();

    }

    public void setCheckbox() {
        final CheckBox checkBox = findViewById(R.id.checkbox_main);
        final TextView tv = findViewById(R.id.tv_student_id);
        final LinearLayout llRadio = findViewById(R.id.ll_radiogroup);
        final ImageView image = findViewById(R.id.img);
        final RadioGroup radioGroup = findViewById(R.id.radiogroup);

        checkBox.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    tv.setText("스타팅 포켓몬을 선택하세요");
                    llRadio.setVisibility(View.VISIBLE);
                    image.setVisibility(View.INVISIBLE);

                } else {
                    radioGroup.clearCheck();
                    llRadio.setVisibility(View.GONE);
                    tv.setText(R.string.studentId);
                    image.setImageResource(R.drawable.samuel_oak);
                }
            }
        });
    }

    public void setRadioBtn() {
        final RadioGroup rg = findViewById(R.id.radiogroup);
        Button b = findViewById(R.id.btn_select_complete);
        final ImageView image = findViewById(R.id.img);

        int id = rg.getCheckedRadioButtonId();
        final RadioButton rb = findViewById(id);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                Log.v("TAGG", Integer.toString(checkedId));
                image.setVisibility(View.VISIBLE);
                switch (checkedId) {
                    case R.id.radio_chicorita:
                        image.setImageResource(R.drawable.chikorita);
                        break;
                    case R.id.radio_bkain:
                        image.setImageResource(R.drawable.cyndaquil);
                        break;
                    case R.id.radio_riaco:
                        image.setImageResource(R.drawable.totodile);
                        break;
                }
            }
        });

        //선택완료 버튼 클릭시
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int id = rg.getCheckedRadioButtonId();

                if (id != -1) {

                    RadioButton rb = (RadioButton) findViewById(id);

                    image.setImageResource(R.drawable.ash_ketchum);
                    Toast.makeText(getApplicationContext(), rb.getText().toString() + "! 너로 정했다!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "하나를 선택해주세용~", Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}
