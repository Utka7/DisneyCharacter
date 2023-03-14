package com.example.disneycharacter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ItemElement> elements = new ArrayList<ItemElement>();
    private Toolbar mToolbar;

    @Override
    protected void onCreate (Bundle saveInstanceElement) {
        super.onCreate(saveInstanceElement);
        setContentView (R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        // начальная инициализация списка
        setInitialData();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        // создаем адаптер
        ElementAdapter adapter = new ElementAdapter(this, elements);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
    }

    private void setInitialData(){

        elements.add(new ItemElement ("Ариэль", R.drawable.ariel));
        elements.add(new ItemElement ("Гайка", R.drawable.gaika));
        elements.add(new ItemElement ("Джин",  R.drawable.jin));
        elements.add(new ItemElement ("Микки Маус", R.drawable.mickey));
        elements.add(new ItemElement ("Перри Утконос",  R.drawable.perry));
    }
}
