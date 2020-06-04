package com.ghdev.moblieprogrammingpractice.eleven;

import java.util.ArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ghdev.moblieprogrammingpractice.R;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.Holder> {

	private ArrayList<Todo> todos;
	private DBHelper dbHelper;

	public TodoAdapter(ArrayList<Todo> todos) {
		this.todos = todos;
		notifyDataSetChanged();
	}

	public static class Holder extends RecyclerView.ViewHolder {
		public TextView tv;
		public ImageButton btnDelete;

		Holder(View itemView) {
			super(itemView);

			tv = itemView.findViewById(R.id.tv_todolist_item);
			btnDelete = itemView.findViewById(R.id.ib_delete_item);
		}
	}

	@NonNull
	@Override
	public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todolist, parent, false);

		dbHelper = DBHelper.getInstance(parent.getContext().getApplicationContext());
		Holder viewholder = new Holder(view);
		return viewholder;
	}

	@Override
	public void onBindViewHolder(@NonNull Holder holder, final int position) {

		holder.tv.setText(" - " + todos.get(position).getTodoName());
		holder.btnDelete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick( View v) {

				dbHelper.delete(todos.get(position).getTodoName());

				todos.remove(position);
				notifyDataSetChanged();
			}
		});
	}

	@Override
	public int getItemCount() {
		return (null != todos ? todos.size() : 0);
	}
}
