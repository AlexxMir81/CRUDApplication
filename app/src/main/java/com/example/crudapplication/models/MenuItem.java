package com.example.crudapplication.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.jar.Attributes;

public class MenuItem {
    private int id;
    private String name;
    private int weight;
    private String composition;

    private int menuImage;
    public MenuItem() {
    }
    public MenuItem(int id, String name, int weight, String composition, int menuImage) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.composition = composition;
        this.menuImage = menuImage;
    }
    public MenuItem(String name, int weight, String composition, int menuImage) {
        this.name = name;
        this.weight = weight;
        this.composition = composition;
        this.menuImage = menuImage;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public String getComposition() {
        return composition;
    }
    public int getMenuImage() {
        return menuImage;
    }
    public void setName(String name) {
        name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setComposition(String composition) {
        composition = composition;
    }

    public void setMenuImage(int menuImage) {
        this.menuImage = menuImage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, composition);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MenuItem menuItem = (MenuItem) obj;
        return id == menuItem.id && name == menuItem.name && weight == menuItem.weight && composition == menuItem.composition
                && menuImage == menuItem.menuImage;
    }

    @NonNull
    @Override
    public String toString() {
        return id+") " + name + " " + composition;
    }
}
