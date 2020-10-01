package com.example.sendmeal.model;

import android.graphics.drawable.Drawable;

public class Item {
    private String title;
    private String description;
    private Double price;
    private Integer calories;
    private String image;

    public Item() {
    }

    public Item(String title, String description, Double price, Integer calories, String image) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.calories = calories;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
