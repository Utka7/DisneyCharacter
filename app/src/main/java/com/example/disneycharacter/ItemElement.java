package com.example.disneycharacter;

public class ItemElement {
    private String name;
    private String content;
    private int icon;

    public ItemElement(String name,String content, int icon){
        this.name=name;
        this.content = content;
        this.icon=icon;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return this.icon;
    }
    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String name) {
        this.content = content;
    }




}
