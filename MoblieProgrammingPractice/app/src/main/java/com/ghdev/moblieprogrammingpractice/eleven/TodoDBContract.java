package com.ghdev.moblieprogrammingpractice.eleven;

public class TodoDBContract {

	private TodoDBContract() { }

	public static final String TABLE_TODO = "todo";
	public static final String COL_TITLE = "todo_title";

	//create table
	public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_TODO + " (" + COL_TITLE + " CHAR(20) PRIMARY KEY" + " )";

	//drop table
	public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_TODO;

	//select
	public static final String SQL_SELECT_TABLE = "SELECT * FROM " + TABLE_TODO;

	//delete one
	public static final String SQL_DELETE_ENTRIES = "DELETE FROM " + TABLE_TODO + " WHERE " + COL_TITLE + "=";
}
