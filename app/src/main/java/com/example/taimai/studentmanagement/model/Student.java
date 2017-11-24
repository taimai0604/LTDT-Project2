package com.example.taimai.studentmanagement.model;

import io.realm.RealmObject;

/**
 * Created by TaiMai on 11/24/2017.
 */

public class Student extends RealmObject{
    private int id;
    private String name;
    private String code;
    private String description;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
