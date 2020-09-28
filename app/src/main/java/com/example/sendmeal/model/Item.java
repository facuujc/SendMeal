package com.example.sendmeal.model;

public class Item {
    private String title;
    private String description;
    private Double price;
    private Integer calories;

    public Item() {
    }

    public Item(String title, String description, Double price, Integer calories) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.calories = calories;
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
}
