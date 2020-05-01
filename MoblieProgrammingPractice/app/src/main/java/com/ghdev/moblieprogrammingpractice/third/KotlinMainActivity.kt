package com.ghdev.moblieprogrammingpractice.third

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ghdev.moblieprogrammingpractice.R
import kotlinx.android.synthetic.main.activity_main.*

class KotlinMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setCheckbox()
        setRadioBtn()
    }

    fun setCheckbox() {
        checkbox_main.setOnClickListener {
            if (checkbox_main.isChecked) {
                tv_student_id.text = "스타팅 포켓몬을 선택하세요"
                ll_radiogroup.visibility = View.VISIBLE
                img.visibility = View.INVISIBLE
            } else {
                radiogroup.clearCheck()
                ll_radiogroup.visibility = View.GONE
                tv_student_id.setText(R.string.studentId)
                img.setImageResource(R.drawable.samuel_oak)
            }
        }

    }

    fun setRadioBtn() {

        radiogroup.setOnCheckedChangeListener { group, checkedId ->

            img.visibility = View.VISIBLE

            Log.v("TAGG", checkedId.toString());

            if (checkedId == R.id.radio_chicorita) {
                img.setImageResource(R.drawable.chikorita)
            } else if (checkedId == R.id.radio_bkain) {
                img.setImageResource(R.drawable.cyndaquil)
            } else if (checkedId == R.id.radio_riaco){
                img.setImageResource(R.drawable.totodile)
            }

        }

        btn_select_complete.setOnClickListener{
            //radiogroup의 값이 있다면..!! 없다면 선택해달라는 toast 띄우기

            var id: Int = radiogroup.checkedRadioButtonId

            if(id != -1) {
                //같은 코드의 반복...!??!?
                val radio : RadioButton = findViewById(id)
                img.setImageResource(R.drawable.ash_ketchum)
                Toast.makeText(applicationContext, radio.text.toString() + "! 너로 정했다!", Toast.LENGTH_LONG).show()
            }else {
                Toast.makeText(applicationContext, "하나를 선택해주세용~", Toast.LENGTH_LONG).show()
            }
        }
    }
}