package com.example.disneycharacter;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class Parser {
    private ArrayList<String> strings;

    public Parser(){
        Log.d("XML", "INIPARSER");
        strings = new ArrayList<>();
    }

    public ArrayList<String> getStrings(){
        return  strings;
    }

    public boolean parse(XmlPullParser xpp){
        boolean status = true;
        String currentString = null;
        boolean inEntry = false;
        String textValue = "";

        try{
            int eventType = xpp.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){

                String tagName = xpp.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        if("string".equalsIgnoreCase(tagName)){
                            inEntry = true;
                            currentString = "";

                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();

                        break;
                    case XmlPullParser.END_TAG:
                        if(inEntry){
                            if("string".equalsIgnoreCase(tagName)){
                                currentString= textValue;
                                strings.add(currentString);
                                inEntry = false;
                            }
                        }
                        break;
                    default:
                }
                eventType = xpp.next();
            }
        }
        catch (Exception e){
            status = false;
            e.printStackTrace();
        }
        return  status;
    }
}
