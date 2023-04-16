package com.example.disneycharacter.entity;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

public class StringArrayConverter {
    @TypeConverter
    public static String[] fromDatabaseValue(String value) {
        if (value == null) {
            return null;
        }
        return new Gson().fromJson(value, String[].class);
    }

    @TypeConverter
    public static String toDatabaseValue(String[] value) {
        if (value == null) {
            return null;
        }
        return new Gson().toJson(value);
    }
}
