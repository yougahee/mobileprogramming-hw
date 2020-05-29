package com.ghdev.moblieprogrammingpractice.tenth;

import androidx.appcompat.app.AppCompatActivity;
import com.ghdev.moblieprogrammingpractice.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TenthActivity extends AppCompatActivity {

	TextView signup;
	EditText et_email, et_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenth);

		init();

		click();
    }

	private void init() {

    	signup = (TextView) findViewById(R.id.tv_signup);
    	et_email = (EditText) findViewById(R.id.et_email);
    	et_password = (EditText) findViewById(R.id.et_password);

    	Intent getIntent = getIntent();
		et_email.setText(getIntent.getStringExtra("email"));
		et_password.setText(getIntent.getStringExtra("pw"));



	}

	private void click() {

		signup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
				startActivity(intent);

			}
		});
	}

}
