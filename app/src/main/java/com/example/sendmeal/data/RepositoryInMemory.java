package com.example.sendmeal.data;

import com.example.sendmeal.model.Item;

import java.util.ArrayList;
import java.util.List;

public class RepositoryInMemory {
    private final static List<Item> items = new ArrayList<>();

    public void iniciar(){
        if(items.isEmpty()){
            items.add(new Item("Pizza", "Tomate, Mozzarella, Aceitunas", 380.0, 200, "img_pizza_mozzarella"));
            items.add(new Item("Pizza Provenzal", "Tomate, Mozzarella, Provenzal", 410.0, 210, "img_pizza_provenzal"));
            items.add(new Item("Empanadas", "Jamón y queso", 270.0, 350, "img_empanadas"));
            items.add(new Item("Tarta verduras", "Acelga, espinaca, huevo", 350.0, 150, "img_tarta"));
            items.add(new Item("Canelones", "Acelga, choclo, salsa", 500.0, 400, "img_canelones"));
            items.add(new Item("Hamburguesa", "Medallón carne, jamón, chedar, lechuga, tomate, huevo", 380.0, 200, "img_hamburguesa"));
        }
    }

    public List<Item> list(){
        iniciar();
        return this.items;
    }

    public void add(String title, String description, Double price, Integer calories, String image){
        this.items.add(new Item(title, description, price, calories, image));
    }
}
