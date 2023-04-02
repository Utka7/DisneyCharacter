package com.example.disneycharacter;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoadCharacters {
    private static final String API_URL = "https://api.disneyapi.dev/characters";
    OkHttpClient client = new OkHttpClient();

    public LoadCharacters(){
        runAsync(API_URL);

    }

    void runAsync(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(@NonNull Call call, @NonNull
            IOException error) {
                error.printStackTrace();
            }
            @Override public void onResponse(@NonNull Call call, @NonNull Response
                    response) throws IOException {
                String responseBody = response.body().string();
//                System.out.println(responseBody);

            }
        });
    }
}
