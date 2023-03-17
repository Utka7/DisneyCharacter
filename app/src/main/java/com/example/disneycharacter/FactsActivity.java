package com.example.disneycharacter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FactsActivity extends AppCompatActivity {

    private TextView name ;
    private TextView conetnt;
    private ImageView icon;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("XML", "onCreateView FactsActivity");
        setContentView(R.layout.facts_activity);
        Bundle arguments = getIntent().getExtras();

        int icon = arguments.getInt("icon");
        String name = arguments.getString("name");
        String content = arguments.getString("content");

        this.icon = findViewById(R.id.icon);
        this.name = findViewById(R.id.name);
        this.conetnt = findViewById(R.id.content);

        this.icon.setImageResource(icon);
        this.conetnt.setText(content);
        this.name.setText(name);

        Log.d(TAG, "onCreate: started.");

    }

}
