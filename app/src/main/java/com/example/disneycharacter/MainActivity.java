package com.example.disneycharacter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import android.content.res.Resources;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;

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
        }
        // начальная инициализация списка
//        setInitialData();
//        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//        // создаем адаптер
//        ElementAdapter adapter = new ElementAdapter(this, elements);
//        // устанавливаем для списка адаптер
//        recyclerView.setAdapter(adapter);
    }

    private void setInitialData(){

        XmlPullParser xpp = getResources().getXml(R.xml.character);
        Parser parser = new Parser();
        if(parser.parse(xpp))
        {
            ArrayList<String> str = parser.getStrings();
            ArrayList<Integer> arr = loadDrawables(R.drawable.class);
            arr.remove(3);
            arr.remove(3);

            for (int i = 0, j = 0; i < str.size()-1; i+=2, j++){
                elements.add(new ItemElement (str.get(i), str.get(i + 1), arr.get(j)));
            }

        }

    }

    public static ArrayList<Integer>  loadDrawables(Class<?> clz){
        final Field[] fields = clz.getDeclaredFields();
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (Field field : fields) {
            final int drawableId;
            try {
                drawableId = field.getInt(clz);
                arr.add(drawableId);
            } catch (Exception e) {
                continue;
            }

        }

        return arr;
    }

//    public void onSuccess(String responseInString) {
//        ElementAdapter.updateCardPreviewRecycler(monsterConverter.toMonsterClassDtoList(responseInString));
//    }
    void get() throws IOException{

        client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(@NonNull Call call, @NonNull
            IOException error) {
                call.cancel();
            }
            @Override public void onResponse(@NonNull Call call, @NonNull Response
                    response) throws IOException {
                String responseBody = response.body().string();
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//
                            Gson gson = new Gson();
                            JsonObject json = gson.fromJson(responseBody,JsonObject.class);
                            System.out.println(json);

                            JsonArray dataArray = json.getAsJsonArray("data");

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

                            Character character = new Character(id,url,name,imageUrl,
                                    gson.fromJson(filmsArray, String[].class),
                                    gson.fromJson(shortFilmsArray, String[].class),
                                    gson.fromJson(tvShowsArray, String[].class),
                                    gson.fromJson(videoGamesArray, String[].class),
                                    gson.fromJson(parkAttractionsArray, String[].class),
                                    gson.fromJson(alliesArray, String[].class),
                                    gson.fromJson(enemiesArray, String[].class));

                            characters.add(character);
                        }

                        System.out.println(characters.get(0).getName());


                            RecyclerView recyclerView = findViewById(R.id.recyclerView);
                            // создаем адаптер
                            CharacterAdapter adapter = new CharacterAdapter(context, characters);
                            // устанавливаем для списка адаптер
                            recyclerView.setAdapter(adapter);

                    }
                });
            }
        });
    }


}

