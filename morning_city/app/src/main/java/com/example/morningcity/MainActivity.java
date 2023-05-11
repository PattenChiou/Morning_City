package com.example.morningcity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.nio.channels.AsynchronousChannelGroup;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicReference;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    Handler mHandler = new Handler();
    static String ChatGPTAPIKEY = "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";
    static String url = "https://api.openai.com/v1/chat/completions";
    String result = "";
    int a = 0;
    Boolean done = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.textView_1);
        tv1.setText("");
        getAstro();
        while(done == false){

        }

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(makeRequest("給我這篇運勢的摘要：" + result), mediaType);
        new HttpRequest().sendPOST(url, requestBody, new HttpRequest.OnCallback() {
            @Override
            public void onOKCall(String response) {
                System.out.println(response);
                ChatGPTResponse chatGPTResponse = new Gson().fromJson(response, ChatGPTResponse.class);
                runOnUiThread(() ->{
                    tv1.setText(chatGPTResponse.getChoices().get(0).getMessage().getContent());
                });
            }

            @Override
            public void onFailCall(String error) {
                tv1.setText(error);
            }
        });

    }
    public void getAstro(){
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
//                                tv1.setText(tv1.getText() + element.text() + "\n");
                                content[j] = element.text();
                                Element finalElement = element;
//                                mHandler.post(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        tv1.setText(tv1.getText() + finalElement.text() + "\n");
//                                    }
//                                });
                                runOnUiThread(() ->{
                                    tv1.setText(tv1.getText() + finalElement.text() + "\n");
                                });
                                synchronized (MainActivity.this){
                                    result += finalElement.text();
                                }
                            }
                        }
                    }
                    synchronized (MainActivity.this){
                        done = true;
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
    }
    public String makeRequest(String content){
//        return "{" +
//                "\"model\": \"text-davinci-003\"," +
//                "\"content\": "+ "\"" + content + "\"," +
//                "\"temperature\": 0.5," +
//                "\"max_tokens\": 1000," +
//                "\"top_p\": 1," +
//                "\"frequency_penalty\": 0," +
//                "\"presence_penalty\": 0" +
//                "}";
        JSONArray messages = new JSONArray();
        JSONObject system_message = new JSONObject();
        JSONObject json = new JSONObject();
        try{
            system_message.put("role", "system");
            system_message.put("content", "You are a manager who can organize information for users.");
        }catch(JSONException e){
            e.printStackTrace();
        }
        messages.put(system_message);
        try{
            json.put("role", "user");
            json.put("content", content);
        }catch(JSONException e){
            e.printStackTrace();
        }
        messages.put(json);
//        System.out.println(messages);
        JSONObject body = new JSONObject();
        try{
            body.put("model", "gpt-3.5-turbo");
            body.put("messages", messages);
            body.put("temperature", 0.5);
            body.put("max_tokens", 1000);
            body.put("top_p", 1);
            body.put("frequency_penalty", 0);
            body.put("presence_penalty", 0);
        }catch(JSONException e){
            e.printStackTrace();
        }

        System.out.println(body.toString());
        return body.toString();
    }
//    private WeakHashMap<String,Object> makeRequest(String input){
//        WeakHashMap<String,Object> weakHashMap = new WeakHashMap<>();
//        weakHashMap.put("model","text-davinci-003");
//        weakHashMap.put("content",input);
//        weakHashMap.put("temperature",0.5);
//        weakHashMap.put("max_tokens",1000);
//        weakHashMap.put("top_p",1);
//        weakHashMap.put("frequency_penalty",0);
//        weakHashMap.put("presence_penalty",0);
//        return weakHashMap;
//    }
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
////                                tv1.setText(tv1.getText() + element.text() + "\n");
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
