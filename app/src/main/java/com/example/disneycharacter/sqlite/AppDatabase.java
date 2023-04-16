package com.example.disneycharacter.sqlite;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.disneycharacter.entity.Character;

@Database(entities = {Character.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CharacterDao characterDao();
}
