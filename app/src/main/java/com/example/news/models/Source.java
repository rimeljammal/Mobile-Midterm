package com.example.news.models;

import java.io.Serializable;

/**
 * Created by Rim on 07/03/2018.
 */

public class Source implements Serializable {

    private String id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
