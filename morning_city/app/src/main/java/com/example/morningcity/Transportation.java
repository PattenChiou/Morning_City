package com.example.morningcity;

import static java.lang.Math.round;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.database.CursorKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.opencsv.CSVReader;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class Transportation extends AppCompatActivity {
    RecyclerView recyclerView;
    List<String> dataList = new ArrayList<>();
    String tokenUrl = "https://tdx.transportdata.tw/auth/realms/TDXConnect/protocol/openid-connect/token";
    String dataUrl = "https://tdx.transportdata.tw/api/basic/v2/Bus/EstimatedTimeOfArrival/City/NewTaipei/967?%24filter=RouteName%2FZh_tw%20eq%20%27967%27&%24format=JSON";
    public static String accessToken = "";
    RecyclerAdapter recyclerAdapter = new RecyclerAdapter(dataList);
    SQLiteDatabase db;
    int direction = 0;
    Button btnChangeDirection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportation);
        for(int i = 0; i < 55; i++){
            dataList.add("");
        }
        recyclerView = findViewById(R.id.recyclerViewTransportationList);
        btnChangeDirection = findViewById(R.id.buttonTransportationChangeDirection);

        db = openOrCreateDatabase("db", MODE_PRIVATE, null);
        db.execSQL("drop table if exists BusStops");
        db.execSQL("create table if not exists BusStops(" +
                "_id Integer primary key autoincrement," +
                "RouteName varchar(10)," +
                "StopID varchar(6)," +
                "StopName varchar(30)," +
                "StopSequence integer" +
                ")");
        Cursor cursor = db.rawQuery("select * from BusStops where RouteName = ?", new String[]{"967cons"});
        if(cursor.getCount() == 0){
            try{
                CSVReader csvReader = new CSVReader(new InputStreamReader(Transportation.this.getAssets().open("967cons.csv")));
                String[] nextLine;
                String[] header = csvReader.readNext();
                nextLine = csvReader.readNext();
                while(nextLine != null){
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("RouteName", nextLine[0]);
                    contentValues.put("StopID", nextLine[1]);
                    contentValues.put("StopName", nextLine[2]);
                    contentValues.put("StopSequence", nextLine[3]);
                    db.insert("'BusStops'", null, contentValues);
                    nextLine = csvReader.readNext();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        cursor = db.rawQuery("select * from BusStops where RouteName = ?", new String[]{"967trans"});
        if(cursor.getCount() == 0){
            try{
                CSVReader csvReader = new CSVReader(new InputStreamReader(Transportation.this.getAssets().open("967trans.csv")));
                String[] nextLine;
                String[] header = csvReader.readNext();
                nextLine = csvReader.readNext();
                while(nextLine != null){
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("RouteName", nextLine[0]);
                    contentValues.put("StopID", nextLine[1]);
                    contentValues.put("StopName", nextLine[2]);
                    contentValues.put("StopSequence", nextLine[3]);
                    db.insert("'BusStops'", null, contentValues);
                    nextLine = csvReader.readNext();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        RequestBody requestBody = new FormBody.Builder().add("grant_type", "client_credentials").add("client_id", "B1028025-de600d9a-6618-4f37").add("client_secret", "46c6e281-b5e3-41b2-b6f4-d940f3c88c27").build();
        new HttpRequestTransportation().sendPOST(tokenUrl, requestBody, new HttpRequestTransportation.OnCallback() {
            @Override
            public void onOKCall(String response) {
                TransportationTokenResponse transportationTokenResponse = new Gson().fromJson(response, TransportationTokenResponse.class);
                accessToken = transportationTokenResponse.getAccess_token();
                System.out.println(accessToken);
            }

            @Override
            public void onFailCall(String error) {

            }
        });
//        System.out.println(requestBody);
        while(accessToken == ""){

        }
        getInformation("967cons");

    }
    public String makeRequest(){
//        return "{" +
//                "\"model\": \"text-davinci-003\"," +
//                "\"content\": "+ "\"" + content + "\"," +
//                "\"temperature\": 0.5," +
//                "\"max_tokens\": 1000," +
//                "\"top_p\": 1," +
//                "\"frequency_penalty\": 0," +
//                "\"presence_penalty\": 0" +
//                "}";
//        System.out.println(messages);
        JSONObject body = new JSONObject();
        try{
            body.put("grant_type", "client_credentials");
            body.put("client_id", "B1028025-de600d9a-6618-4f37");
            body.put("client_secret", "46c6e281-b5e3-41b2-b6f4-d940f3c88c27");
        }catch(JSONException e){
            e.printStackTrace();
        }

        System.out.println(body.toString());
        return body.toString();
    }
    public void getInformation(String routeName){
        Cursor cursor;
        new HttpRequestTransportation().sendGET(dataUrl, new HttpRequestTransportation.OnCallback() {
            @Override
            public void onOKCall(String response) {
                System.out.println("{\"Data\": " + response + "}");
                TransportationResponse transportationResponse = new Gson().fromJson("{\"Data\": " + response + '}', TransportationResponse.class);
                runOnUiThread(() ->{
                    System.out.println(transportationResponse.getData().get(0).getRouteName().getZh_tw());
                });
                Cursor cursor = db.rawQuery("select * from BusStops where RouteName = ?", new String[]{routeName});
                dataList.clear();
                System.out.println("count:" + cursor.getCount());
                for(int i = 0; i < cursor.getCount(); i++){
                    dataList.add("");
                }
                for(int i = 0; i < transportationResponse.getData().size(); i++){
                    if(transportationResponse.getData().get(i).getDirection() == direction){
                        cursor = db.rawQuery("select * from BusStops where StopID = ?", new String[]{transportationResponse.getData().get(i).getStopID()}, null);
                        cursor.moveToFirst();
                        if(cursor.getCount() == 0){
                            System.out.println(transportationResponse.getData().get(i).getRouteName().getZh_tw());
                        }
                        else{
                            System.out.println(transportationResponse.getData().get(i).getEstimateTime());
                            switch(transportationResponse.getData().get(i).getStopStatus()){
                                case 0:
                                case 1:
                                    dataList.set(cursor.getInt(4) - 1 , cursor.getString(3) + "," + round(transportationResponse.getData().get(i).getEstimateTime()/60) + "分");
                                    break;
                                case 2:
                                    dataList.set(cursor.getInt(4) - 1 , cursor.getString(3) + "," + "交管不停靠");
                                    break;
                                case 3:
                                    dataList.set(cursor.getInt(4) - 1 , cursor.getString(3) + "," + "末班車已過");
                                    break;
                            }

                        }

                    }
                }
                runOnUiThread(() ->{
                    recyclerAdapter = new RecyclerAdapter(dataList);
//                    System.out.println(recyclerAdapter.getItemCount());
                    recyclerAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(recyclerAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Transportation.this));
                });
            }

            @Override
            public void onFailCall(String error) {
                System.out.println(error);
            }
        });
    }
    public void changeDirection(View view){
        if(direction == 0){
            direction = 1;
            btnChangeDirection.setText(getResources().getString(R.string.trans));
            getInformation("967trans");
        }
        else{
            direction = 0;
            btnChangeDirection.setText(getResources().getString(R.string.cons));
            getInformation("967cons");
        }
    }
}