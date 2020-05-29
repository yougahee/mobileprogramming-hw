package com.ghdev.moblieprogrammingpractice.eleven;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ghdev.moblieprogrammingpractice.R;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class EleventhActivity extends AppCompatActivity {

	RecyclerView rv_todo;
	EditText et_todo;
	Button btn_todo;
	Todo tododata;

	ArrayList<Todo> list;
	TodoAdapter todoAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eleventh);

		init();
		setRecyclerView();
		onclick();
	}

	public void init() {

		et_todo =(EditText) findViewById(R.id.et_todolist);
		btn_todo = (Button) findViewById(R.id.btn_send_text);
		rv_todo = (RecyclerView) findViewById(R.id.rv_eleventh_act);

	}


	private void setRecyclerView() {
		list = new ArrayList<>();
		todoAdapter = new TodoAdapter(list);

		rv_todo.setAdapter(todoAdapter);
		rv_todo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

	}

	public void onclick() {

		btn_todo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if(et_todo.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(),"할 일을 입력해주세요.", Toast.LENGTH_SHORT).show();
				}else{

					Log.v("TAGG", et_todo.getText().toString());
					tododata = new Todo(et_todo.getText().toString());
					list.add(tododata);

					et_todo.setText(null);
					todoAdapter.notifyDataSetChanged();

				}
			}
		});
	}
}
