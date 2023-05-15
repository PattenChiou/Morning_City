package com.example.morningcity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.CursorTreeAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class News extends AppCompatActivity {

    Button btnSettings;
    String[] items = {"財經", "政治", "娛樂", "社會", "音樂", "藝術", "運動"};
    ListView lvNews;
    SimpleCursorAdapter simpleCursorAdapter;
    MatrixCursor matrixCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_main);

        btnSettings = findViewById(R.id.button2);
        lvNews = findViewById(R.id.listView);

        matrixCursor = new MatrixCursor(new String[]{"_id", "title", "content"});
        matrixCursor.newRow()
                .add("_id", "1")
                .add("title", "［財經］XXX股票大漲！！！")
                .add("content", "今天XXX股票上漲10個百分點...");
        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.news_list, matrixCursor, new String[]{"title", "content"}, new int[]{R.id.textView, R.id.textView2});
        lvNews.setAdapter(simpleCursorAdapter);
    }

    public void showSettings(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("請選擇喜歡的新聞類型");

        builder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

            }
        });
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("取消", null);
        builder.create();
        builder.show();
    }
}