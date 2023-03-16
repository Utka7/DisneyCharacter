package com.example.disneycharacter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class FactsActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getIntent().getExtras();

        String text = arguments.getString("name").toString();
        int icon = arguments.getInt("icon");

        setContentView(R.layout.facts);
        Log.d(TAG, "onCreate: started.");


    }
}
