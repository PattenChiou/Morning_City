package com.example.morningcity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class Weather extends AppCompatActivity {

    String url = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/O-A0001-001?Authorization=CWB-51CC5FAA-5366-448A-BA96-8FF4C93734D9&stationId=";
    ArrayList<String> messages = new ArrayList<>();
    TextView tvSiteName, tvTemp, tvWeather;
    Spinner spSiteName;
    ArrayList<String> siteNames;
    ArrayList<String> stationIds;
    String site;
    String stationId;
    Button btRefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        siteNames = new ArrayList<>(Arrays.asList(new String[]{getResources().getString(R.string.banqiao), getResources().getString(R.string.newtaipei)}));
        stationIds = new ArrayList<>(Arrays.asList(new String[]{"C0AJ80", "466881"}));

        spSiteName = findViewById(R.id.spinner);
        tvTemp = findViewById(R.id.textViewWeatherTemp);
        tvWeather = findViewById(R.id.textViewWeatherWeather);
        tvSiteName = findViewById(R.id.textViewWeatherRegion);
        btRefresh = findViewById(R.id.buttonWeatherRefresh);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, siteNames);
        spSiteName.setAdapter(adapter);

        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                site = siteNames.get(which);
                stationId = stationIds.get(which);
                tvSiteName.setText(site);
            }
        };
        tvSiteName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Weather.this)
                        .setTitle(R.string.chooseDialogTitle)
                        .setItems(siteNames.toArray(new String[]{}), onClickListener)
                        .setCancelable(true)
                        .setNegativeButton(R.string.chooseDialogNegative, null);
                builder.show();
            }
        });
    }
    public void refresh(View view){
        new HttpRequestWeather().sendGET(url + stationId, new HttpRequestWeather.OnCallback() {
            @Override
            public void onOKCall(String response) {
                System.out.println(response);
                try{
                    WeatherResponse weatherResponse = new Gson().fromJson(response, WeatherResponse.class);
                    System.out.println(weatherResponse.getRecords().getLocation().get(0).getWeatherElement().get(3).getElementValue());
                    runOnUiThread(() ->{
                        //tvSiteName.setText(weatherResponse.getRecords().getLocation().get(0).getLocationName());
                        tvTemp.setText(getResources().getString(R.string.temp) + weatherResponse.getRecords().getLocation().get(0).getWeatherElement().get(3).getElementValue() + "℃");
                        tvWeather.setText(getResources().getString(R.string.weather2) + weatherResponse.getRecords().getLocation().get(0).getWeatherElement().get(14).getElementValue());
                    });
                }
                catch(Exception exception){
                    runOnUiThread(() ->{
                        tvTemp.setText("");
                        tvWeather.setText("");
                        Toast.makeText(Weather.this, getResources().getString(R.string.weatherNoData), Toast.LENGTH_LONG).show();
                    });
                }

//                ChatGPTResponse chatGPTResponse = new Gson().fromJson(response, ChatGPTResponse.class);
//                runOnUiThread(() ->{
//                    messages.add("ChatGPT:" + chatGPTResponse.getChoices().get(0).getMessage().getContent());
//                    customAdapter.notifyDataSetChanged();
//                });
            }

            @Override
            public void onFailCall(String error) {
                System.out.println(error);
//                customAdapter.notifyDataSetChanged();
            }
        });
    }
}