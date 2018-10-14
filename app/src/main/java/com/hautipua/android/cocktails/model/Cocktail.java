package com.hautipua.android.cocktails.model;

import java.io.Serializable;

public class Cocktail implements Serializable
{
    private int Id;

    private String Name;

    private String Ingredients;

    private String Directions;

    private String PhotoId;

    private String SpiritName;

    public Cocktail(int id, String name, String ingredients, String directions, String photoId, String spiritName) {
        this.Id = id;
        this.Name = name;
        this.Ingredients = ingredients;
        this.Directions = directions;
        this.PhotoId = photoId;
        this.SpiritName = spiritName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIngredients() {
        return Ingredients;
    }

    public void setIngredients(String ingredients) {
        Ingredients = ingredients;
    }

    public String getDirections() {
        return Directions;
    }

    public void setDirections(String directions) {
        Directions = directions;
    }

    public String getPhotoId() {
        return PhotoId;
    }

    public void setPhotoId(String photoId) {
        PhotoId = photoId;
    }

    public String getSpiritName() {
        return SpiritName;
    }

    public void setSpiritName(String spiritName) {
        SpiritName = spiritName;
    }
}
