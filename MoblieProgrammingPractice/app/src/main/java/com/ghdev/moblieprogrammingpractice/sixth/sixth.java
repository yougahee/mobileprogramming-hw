package com.ghdev.moblieprogrammingpractice.sixth;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ghdev.moblieprogrammingpractice.R;


public class sixth extends AppCompatActivity {

    TextView titletv;
    ConstraintLayout backgroundColor;
    ImageView pikachuImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);

        init();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu1:
                titletv.setText("몬스터볼");
                backgroundColor.setBackgroundColor(Color.rgb(110, 240, 255));
                break;
            case R.id.menu2:
                titletv.setText("체육관");
                backgroundColor.setBackgroundColor(Color.rgb(155, 240, 72));
                break;
            case R.id.menu3:
                titletv.setText("공원");
                backgroundColor.setBackgroundColor(Color.rgb(255, 50, 50));
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    //contextmenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);

        menu.setHeaderTitle("피카츄에게 무엇을 할까??");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        final String[] fruits = new String[]{"라즈열매","나나열매", "파인열매"};
        switch (item.getItemId()) {
            case R.id.give_fruit:

                AlertDialog.Builder dlg = new AlertDialog.Builder(sixth.this);
                dlg.setTitle("어떤 열매를 먹일까?");
                dlg.setItems(fruits, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        toastImg();

                    }
                });

                dlg.setNegativeButton("취소", null);
                dlg.show();

                return true;
            case R.id.sleep:
                Toast.makeText(getApplicationContext(), "잘잤다!!", Toast.LENGTH_LONG).show();
                return true;
        }

        return super.onContextItemSelected(item);
    }


    public void init() {

        titletv = (TextView) findViewById(R.id.tv_sixth_act);
        backgroundColor = (ConstraintLayout) findViewById(R.id.cl_background_color);
        pikachuImg = (ImageView) findViewById(R.id.img_pikachu);

        //기본 셋팅
        backgroundColor.setBackgroundColor(Color.rgb(110, 240, 255));
        registerForContextMenu(pikachuImg);

    }

    public void toastImg() {

        final float scale = getResources().getDisplayMetrics().density;
        int dpInPx  = (int) (100 * scale + 0.5f);

        Toast toast = new Toast(sixth.this);

        ImageView img = new ImageView(sixth.this);

        img.setImageResource(R.drawable.heart);
        img.setAdjustViewBounds(true);
        img.setMaxWidth(dpInPx);
        img.setMaxHeight(dpInPx);

        toast.setView(img);
        toast.setGravity(Gravity.CENTER, 0, -100);
        toast.show();
    }


}
