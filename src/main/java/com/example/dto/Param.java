package com.example.dto;

/**
 * Created by cjh on 2017. 3. 23..
 */
public class Param {
    private String key;

    private String value;

    public Param(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
