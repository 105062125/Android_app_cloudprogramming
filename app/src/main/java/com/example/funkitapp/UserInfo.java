package com.example.funkitapp;

public class UserInfo {
    private float star;
    private String name;
    private String level;

    public UserInfo() { super(); }
    public UserInfo(float star, String name, String level) {
        super();
        this.star = star;
        this.name = name;
        this.level = level;
    }

    public float getStar() { return star; }

    public String getLevel() { return level; }

    public String getName() { return name; }

    public void setLevel(String level) { this.level = level; }

    public void setName(String name) { this.name = name; }

    public void setStar(float star) { this.star = star; }
}
