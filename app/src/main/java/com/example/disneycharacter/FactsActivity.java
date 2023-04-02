package com.example.disneycharacter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class FactsActivity extends AppCompatActivity {

    private TextView name ;
    private ImageView icon;
    private TextView films;
    private TextView shortFilms;
    private TextView tvShows;
    private TextView videoGames;
    private TextView parkAttractions;
    private TextView allies;
    private TextView enemies;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("XML", "onCreateView FactsActivity");
        setContentView(R.layout.facts_activity);
        Bundle arguments = getIntent().getExtras();

        Intent intent = getIntent();
        Character character = (Character) intent.getSerializableExtra("character");

        this.icon = findViewById(R.id.icon);
        this.name = findViewById(R.id.name);
        this.films = findViewById(R.id.film_data);
        this.shortFilms = findViewById(R.id.shortFilms_data);
        this.tvShows = findViewById(R.id.tvShows_data);
        this.videoGames = findViewById(R.id.videoGames_data);
        this.parkAttractions = findViewById(R.id.parkAttractions_data);
        this.allies = findViewById(R.id.allies_data);
        this.enemies = findViewById(R.id.enemies_data);


        Picasso.get().load(character.getImageUrl()).into(this.icon);
        this.name.setText(character.getName());
        this.films.setText(processArray(character.getFilms()));
        this.shortFilms.setText(processArray(character.getShortFilms()));
        this.tvShows.setText(processArray(character.getTvShows()));
        this.videoGames.setText(processArray(character.getVideoGames()));
        this.parkAttractions.setText(processArray(character.getParkAttractions()));
        this.allies.setText(processArray(character.getAllies()));
        this.enemies.setText(processArray(character.getEnemies()));


        Log.d(TAG, "onCreate: started.");

    }

    public static String processArray(String[] array) {
        if (array == null || array.length == 0) {
            return "нет данных";
        } else {
            return String.join(",", array);
        }
    }

}
