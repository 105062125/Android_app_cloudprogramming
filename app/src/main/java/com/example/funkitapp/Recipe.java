package com.example.funkitapp;

import android.graphics.Bitmap;

public class Recipe {
    private String author;
    private String difficulty;
    private Bitmap image;
    private  int id;
    private String name;
    private String steps;
    private String material;

    public Recipe() { super(); }
    public Recipe(String author, String difficulty, Bitmap image, String steps, String name, String material){
        this.difficulty = difficulty;
        this.image = image;
        this.author = author;
        this.steps = steps;
        this.name = name;
        this.material = material;
    }

    public String getAuthor() { return author; }

    public String getDifficulty() { return difficulty; }

    public Bitmap getImage() { return image; }

    public String getName() { return name; }

    public String getSteps() { return steps; }

    public String getMaterial() { return material; }

    public int getId() { return id; }

    public void setImage(Bitmap image) { this.image = image; }

    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public void setAuthor(String author) { this.author = author; }

    public void setId(int id) { this.id = id; }
}
