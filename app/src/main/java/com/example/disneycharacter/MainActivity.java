package com.example.disneycharacter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import android.content.res.Resources;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
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


}

