package com.example.morningcity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    Button btnTodos, btnTransportation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnTodos = findViewById(R.id.buttonHomeTodos);
        btnTransportation = findViewById(R.id.buttonHomeTransportation);
    }
    public void openTodos(View view){
        Intent it = new Intent();
        it.setClass(this, Todos.class);
        startActivity(it);
    }
    public void openTransportation(View view){
        Intent it = new Intent();
        it.setClass(this, Transportation.class);
        startActivity(it);
    }
    public void openWeather(View view){
        Intent it = new Intent();
        it.setClass(this, Weather.class);
        startActivity(it);
    }
    public void openNews(View view){
        Intent it = new Intent();
        it.setClass(this, Page_news.class);
        startActivity(it);
    }
    public void openBreakfast(View view){
        Intent it = new Intent();
        it.setClass(this, Page_breakfast.class);
        startActivity(it);
    }
    public void openStar(View view){
        Intent it = new Intent();
        it.setClass(this, Page_star.class);
        startActivity(it);
    }
}