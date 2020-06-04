package com.ghdev.moblieprogrammingpractice.eleven;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	private static volatile DBHelper instance;
	private SQLiteDatabase db = null;

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "todo.db";

	//singleton
	public static DBHelper getInstance(Context context) {
		if(instance == null) {
			synchronized (DBHelper.class){
				if(instance == null) {
					instance = new DBHelper(context);
				}
			}
		}
		return instance;
	}

	private DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

		if(db == null) {
			db = getWritableDatabase();
		}
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TodoDBContract.SQL_CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(TodoDBContract.SQL_DROP_TABLE);
		onCreate(db);
	}

	public ArrayList<Todo> getAllTodo() {
		ArrayList<Todo> todolist = new ArrayList<Todo>();
		db = getReadableDatabase();

		Cursor cursor = db.rawQuery(TodoDBContract.SQL_SELECT_TABLE,null);

		while(cursor.moveToNext()) {
			String todotitle = cursor.getString(0);
			todolist.add(new Todo(todotitle));
		}
		cursor.close();

		return todolist;
	}

	//데이터 삽입
	public void insert(Todo todo){
		ContentValues values = new ContentValues();
		values.put(TodoDBContract.COL_TITLE, todo.getTodoName());
		db.insert(TodoDBContract.TABLE_TODO, null, values);
	}

	public void delete(String title) {

		//db.delete()
		String selection = TodoDBContract.COL_TITLE + "= ?";
		String[] selectionArgs = new String[] {title};
		db.delete(TodoDBContract.TABLE_TODO, selection, selectionArgs);
	}
}
