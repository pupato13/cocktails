package com.hautipua.android.cocktails.model;

import java.io.Serializable;

public class Favourite implements Serializable {

    private int Id;

    private String Name;

    public Favourite(int id, String name) {
        this.Id = id;
        this.Name = name;
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
}
