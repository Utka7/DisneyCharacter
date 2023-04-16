package com.example.disneycharacter.sqlite;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.disneycharacter.entity.Character;

import java.util.List;
@Dao
public interface CharacterDao {
    @Insert
    void insertAll(Character  ... characters);

    @Delete
    void delete(Character character);

    @Query("SELECT * FROM characters")
    List<Character> getAllCharacter();

    @Query("SELECT * FROM characters WHERE id = :сharacterId")
    Character getCharacterById(int сharacterId);

    @Query("SELECT COUNT(*) FROM characters")
    int countCharacters();
}
