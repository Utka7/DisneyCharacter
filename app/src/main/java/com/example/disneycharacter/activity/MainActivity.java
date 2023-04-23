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
    private Toolbar mToolbar;
    @Override
    protected void onCreate (Bundle saveInstanceElement) {
        super.onCreate(saveInstanceElement);
        setContentView (R.layout.activity_main);

        LoadCharacters l = new LoadCharacters();

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

    }


}

