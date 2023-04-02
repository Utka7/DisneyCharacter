package com.example.disneycharacter;

import java.io.Serializable;

public class Character implements Serializable {

    private int id;
    private String url;
    private String name;
    private String imageUrl;
    private String[] films;
    private String[] shortFilms;
    private String[] tvShows;
    private String[] videoGames;
//    private String algiment;
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

//    public String getAlgiment() {
//        return algiment;
//    }
//
//    public void setAlgiment(String algiment) {
//        this.algiment = algiment;
//    }

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
}
