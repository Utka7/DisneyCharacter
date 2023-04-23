package com.example.disneycharacter.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
@Entity(tableName = "characters")
@TypeConverters({StringArrayConverter.class})
public class Character implements Serializable, Parcelable {
    @PrimaryKey
    private int id;
    private String url;
    private String name;
    private String imageUrl;
    private String[] films;
    private String[] shortFilms;
    private String[] tvShows;
    private String[] videoGames;
    private String[] parkAttractions;
    private String[] allies;
    private String[] enemies;

    public Character(int id, String url, String name, String imageUrl, String[] films,
                     String[] shortFilms, String[] tvShows, String[] videoGames,
                     String[] parkAttractions, String[] allies, String[] enemies) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.imageUrl = imageUrl;
        this.films = films;
        this.shortFilms = shortFilms;
        this.tvShows = tvShows;
        this.videoGames = videoGames;
        this.parkAttractions = parkAttractions;
        this.allies = allies;
        this.enemies = enemies;
    }

    protected Character(Parcel in) {
        id = in.readInt();
        url = in.readString();
        name = in.readString();
        imageUrl = in.readString();
        films = in.createStringArray();
        shortFilms = in.createStringArray();
        tvShows = in.createStringArray();
        videoGames = in.createStringArray();
        parkAttractions = in.createStringArray();
        allies = in.createStringArray();
        enemies = in.createStringArray();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String[] getFilms() {
        return films;
    }

    public void setFilms(String[] films) {
        this.films = films;
    }

    public String[] getShortFilms() {
        return shortFilms;
    }

    public void setShortFilms(String[] shortFilms) {
        this.shortFilms = shortFilms;
    }

    public String[] getTvShows() {
        return tvShows;
    }

    public void setTvShows(String[] tvShows) {
        this.tvShows = tvShows;
    }

    public String[] getVideoGames() {
        return videoGames;
    }

    public void setVideoGames(String[] videoGames) {
        this.videoGames = videoGames;
    }

    public String[] getParkAttractions() {
        return parkAttractions;
    }

    public void setParkAttractions(String[] parkAttractions) {
        this.parkAttractions = parkAttractions;
    }

    public String[] getAllies() {
        return allies;
    }

    public void setAllies(String[] allies) {
        this.allies = allies;
    }

    public String[] getEnemies() {
        return enemies;
    }

    public void setEnemies(String[] enemies) {
        this.enemies = enemies;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(url);
        dest.writeString(name);
        dest.writeString(imageUrl);
        dest.writeStringArray(films);
        dest.writeStringArray(shortFilms);
        dest.writeStringArray(tvShows);
        dest.writeStringArray(videoGames);
        dest.writeStringArray(parkAttractions);
        dest.writeStringArray(allies);
        dest.writeStringArray(enemies);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Character> CREATOR = new Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel in) {
            return new Character(in);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };
}
