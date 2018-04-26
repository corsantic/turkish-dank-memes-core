package com.turkishdank.turkishdankmemes.entity;

import java.io.Serializable;

public class Sound  implements Serializable{

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Sound{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
