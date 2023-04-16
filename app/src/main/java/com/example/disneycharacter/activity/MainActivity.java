package com.example.disneycharacter.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.disneycharacter.entity.Character;
import com.example.disneycharacter.recylerview.ItemElement;
import com.example.disneycharacter.api.LoadCharacters;
import com.example.disneycharacter.R;
import com.example.disneycharacter.recylerview.adapter.CharacterAdapter;
import com.example.disneycharacter.sqlite.AppDatabase;
import com.example.disneycharacter.sqlite.CharacterDao;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<ItemElement> elements = new ArrayList<ItemElement>();
    ArrayList<Character> characters = new ArrayList<>();
    private Toolbar mToolbar;
    Context context = this;
    private static final String API_URL = "https://api.disneyapi.dev/characters";
    OkHttpClient client ;

    @Override
    protected void onCreate (Bundle saveInstanceElement) {
        super.onCreate(saveInstanceElement);
        setContentView (R.layout.activity_main);

        LoadCharacters l = new LoadCharacters();

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        try {
            get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void get() throws IOException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(API_URL).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException error) {
                call.cancel();
                latch.countDown();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String responseBody = response.body().string();
                handleResponse(responseBody);
                latch.countDown();
            }
        });

        latch.await(); // ожидание ответа от API

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app.db").build();
        Executor executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            CharacterDao characterDao = db.characterDao();
            List<Character> characters = characterDao.getAllCharacter();
            Log.d("MyTag", "This is DB");
            if (!characters.isEmpty()) {
                Log.d("MyTag", "This is DB try");
                // Данные есть в базе данных, загружаем их и показываем пользователю
                runOnUiThread(() -> {
                    RecyclerView recyclerView = findViewById(R.id.recyclerView);
                    CharacterAdapter adapter = new CharacterAdapter(context, characters);
                    recyclerView.setAdapter(adapter);
                });
            }
        });
    }


    private List<Character> handleResponse(String responseBody) {
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(responseBody, JsonObject.class);
        JsonArray dataArray = json.getAsJsonArray("data");
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < dataArray.size(); i++) {
            JsonObject dataObject = dataArray.get(i).getAsJsonObject();
            int id = dataObject.get("_id").getAsInt();
            String name = dataObject.get("name").getAsString();
            String imageUrl = dataObject.get("imageUrl").getAsString();
            String url = dataObject.get("url").getAsString();
            JsonArray filmsArray = dataObject.getAsJsonArray("films");
            JsonArray shortFilmsArray = dataObject.getAsJsonArray("shortFilms");
            JsonArray tvShowsArray = dataObject.getAsJsonArray("tvShows");
            JsonArray videoGamesArray = dataObject.getAsJsonArray("videoGames");
            JsonArray parkAttractionsArray = dataObject.getAsJsonArray("parkAttractions");
            JsonArray alliesArray = dataObject.getAsJsonArray("allies");
            JsonArray enemiesArray = dataObject.getAsJsonArray("enemies");

            Character character = new Character(id, url, name, imageUrl,
                    gson.fromJson(filmsArray, String[].class),
                    gson.fromJson(shortFilmsArray, String[].class),
                    gson.fromJson(tvShowsArray, String[].class),
                    gson.fromJson(videoGamesArray, String[].class),
                    gson.fromJson(parkAttractionsArray, String[].class),
                    gson.fromJson(alliesArray, String[].class),
                    gson.fromJson(enemiesArray, String[].class));
            characters.add(character);
        }

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "app.db").build();
        Executor executor = Executors.newSingleThreadExecutor();
        CharacterDao characterDao = db.characterDao();
        executor.execute(() -> {
            // Проверяем наличие данных в базе данных
            if (characterDao.countCharacters() == 0) {
                // Данных в базе данных нет, вставляем данные
//                characterDao.insertAll(characters);
                db.characterDao().insertAll(characters.toArray(new Character[0]));

            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        CharacterAdapter adapter = new CharacterAdapter(context, characters);
        recyclerView.setAdapter(adapter);

        return characters;
    }

}

