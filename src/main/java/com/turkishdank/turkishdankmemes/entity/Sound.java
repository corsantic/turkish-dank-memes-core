package com.turkishdank.turkishdankmemes.entity;

import java.io.Serializable;

public class Sound  implements Serializable{

    private String id;
    private String name;
    private String fullName;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Sound{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
