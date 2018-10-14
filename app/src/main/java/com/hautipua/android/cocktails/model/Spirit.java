package com.hautipua.android.cocktails.model;

import java.io.Serializable;

public class Spirit implements Serializable {

    private int Id;

    private String Name;

    private int Quantity;

    public Spirit(int id, String name, int quantity) {
        this.Id = id;
        this.Name = name;
        this.Quantity = quantity;
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

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
