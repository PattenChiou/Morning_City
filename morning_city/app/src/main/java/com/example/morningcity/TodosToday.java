package com.example.morningcity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.database.Cursor;


import java.util.Calendar;
import java.util.List;

public class TodosToday extends AppCompatActivity {

    SimpleCursorAdapter simpleCursorAdapter;
    Cursor cursor;

    String date;
    SQLiteDatabase db;
    ListView lvTodosToday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_today);

        lvTodosToday = findViewById(R.id.listViewTodosToday);
        Calendar calendar = Calendar.getInstance();
        date = String.format("%04d/%02d/%02d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));
//        System.out.println(date);
        db = openOrCreateDatabase("db", MODE_PRIVATE, null);
        cursor = db.rawQuery("select * from todos where date = ? order by time", new String[]{date});
        cursor.moveToFirst();
        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.todostoday_list, cursor, new String[]{"time", "content"}, new int[]{R.id.textView4, R.id.textView5});
        lvTodosToday.setAdapter(simpleCursorAdapter);
//        System.out.println(cursor.getString(3));
    }
    public void openTodos(View view){
        Intent it = new Intent();
        it.setClass(this, Todos.class);
        startActivity(it);
    }
}