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
//        elements.add(new ItemElement ("Ариэль", R.drawable.ariel));
//        elements.add(new ItemElement ("Гайка", R.drawable.gaika));
//        elements.add(new ItemElement ("Джин",  R.drawable.jin));
//        elements.add(new ItemElement ("Микки Маус", R.drawable.mickey));
//        elements.add(new ItemElement ("Перри Утконос",  R.drawable.perry));
        Log.d("XML",  String.valueOf(R.drawable.timon));


        XmlPullParser xpp = getResources().getXml(R.xml.character);
        Parser parser = new Parser();
        if(parser.parse(xpp))
        {
            ArrayList<String> str = parser.getStrings();
            for(String prod: parser.getStrings()){
                Log.d("XML", prod);

            }
            ArrayList<Integer> arr = loadDrawables(R.drawable.class);
            Log.d("XML",  String.valueOf(arr.get(0)));
            for (int i = 0, j = 0; i < str.size()-1; i+=2, j++){
                elements.add(new ItemElement (str.get(i), str.get(i + 1), arr.get(j)));
            }



        }
//        ArrayList<Integer> alist = new ArrayList<Integer>();
//        alist.add(R.drawable.)






    }

    public static ArrayList<Integer>  loadDrawables(Class<?> clz){
        final Field[] fields = clz.getDeclaredFields();
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (Field field : fields) {
            final int drawableId;
            try {
                drawableId = field.getInt(clz);
                arr.add(drawableId);
                Log.d("XML",  String.valueOf(drawableId));

            } catch (Exception e) {
                continue;
            }

        }

        return arr;
    }


}

