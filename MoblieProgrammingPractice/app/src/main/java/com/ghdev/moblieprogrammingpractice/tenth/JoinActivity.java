package com.ghdev.moblieprogrammingpractice.tenth;

import androidx.appcompat.app.AppCompatActivity;
import com.ghdev.moblieprogrammingpractice.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class JoinActivity extends AppCompatActivity {


	EditText et_email, et_password;
	Button btn_login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join);

		initVeiw();

		onclick();
	}

	private void initVeiw() {
		btn_login = (Button) findViewById(R.id.btn_login);
		et_email = (EditText) findViewById(R.id.et_email);
		et_password = (EditText) findViewById(R.id.et_password);
	}

	public void onclick() {

		btn_login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(getApplicationContext(), TenthActivity.class);

				if(et_email.getText().toString().equals("") || et_password.getText().toString().equals("")) {
					finish();
				}else{

					intent.putExtra("email", et_email.getText().toString());
					intent.putExtra("pw", et_password.getText().toString());
					startActivity(intent);
					finish();
				}

			}
		});
	}


	@Override
	public void onBackPressed() {
		finish();
	}
}
