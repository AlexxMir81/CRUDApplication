package com.example.crudapplication.models;

import java.util.List;
import java.util.jar.Attributes;

public class MenuItem {
    private String Name;
    private int weight;
    private String Composition;

    private int menuImage;
    public MenuItem() {
    }
    public MenuItem(String name, int weight, String composition, int menuImage) {
        this.Name = name;
        this.weight = weight;
        this.Composition = composition;
        this.menuImage = menuImage;
    }

    public String getName() {
        return Name;
    }

    public int getWeight() {
        return weight;
    }

    public String getComposition() {
        return Composition;
    }
    public int getMenuImage() {
        return menuImage;
    }
    public void setName(String name) {
        Name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setComposition(String composition) {
        Composition = composition;
    }

    public void setMenuImage(int menuImage) {
        this.menuImage = menuImage;
    }
}
