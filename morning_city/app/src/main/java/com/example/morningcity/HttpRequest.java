package com.example.morningcity;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class HttpRequest {
    public void sendPOST(String url, RequestBody requestBody, OnCallback callback){
        OkHttpClient client = new OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build();
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "Bearer " + MainActivity.ChatGPTAPIKEY)
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailCall(e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                System.out.println(response.body().string());
                callback.onOKCall(response.body().string());
            }
        });
    }
    interface OnCallback{
        void onOKCall(String response);
        void onFailCall(String error);
    }
}
