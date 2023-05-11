package com.example.goodmorning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.nio.channels.AsynchronousChannelGroup;

public class MainActivity extends AppCompatActivity {

    TextView tv_1;
    Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_1 = findViewById(R.id.textView_1);
        tv_1.setText("");
        String[] content = new String[50];

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document html = Jsoup.connect("https://astro.click108.com.tw/daily_10.php?iAstro=10").get();
                    Elements div = html.getElementsByTag("div");
                    for(int i = 0; i < div.size(); i++){
                        Element element = div.get(i);
                        if(element.attr("class").equals("TODAY_CONTENT")){
                            Elements p = element.select("p");
                            for(int j = 0; j < p.size(); j++){
                                element = p.get(j);
//                                System.out.println(element.text());
//                                tv_1.setText(tv_1.getText() + element.text() + "\n");
                                content[j] = element.text();
                                Element finalElement = element;
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_1.setText(tv_1.getText() + finalElement.text() + "\n");
                                    }
                                });
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
//        TaskRunner task1 = new TaskRunner();
//        new Thread(task1).start();
//        content = task1.getCallback();
        thread.start();
        for(int i = 0; i < content.length; i++){
            if(content[i] != null){
                System.out.println("a");
//                tv_1.setText(tv_1.getText() + content[i] + "\n");
            }
            else{
                break;
            }
        }
    }
}
//class TaskRunner implements Runnable{
//    String[] content = new String[50];
//    String a = "";
//    @Override
//    public void run() {
//        try {
//            Document html = Jsoup.connect("https://astro.click108.com.tw/daily_10.php?iAstro=10").get();
//            Elements div = html.getElementsByTag("div");
//            for(int i = 0; i < div.size(); i++) {
//                Element element = div.get(i);
//                if (element.attr("class").equals("TODAY_CONTENT")) {
//                    Elements p = element.select("p");
//                    for (int j = 0; j < p.size(); j++) {
//                        element = p.get(j);
////                                System.out.println(element.text());
////                                tv_1.setText(tv_1.getText() + element.text() + "\n");
//                        content[j] = element.text();
//                        a = element.text();
//                    }
//                }
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public String[] getCallback(){
//        System.out.println(a);
//        return content;
//    }
//}
