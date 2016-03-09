package com.lpiotrko.reflection.dao;

public class Phone {

    private Long id;
    private String make;
    private String model;
    public String operatingSystem;
    private int yop;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYop() {
        return yop;
    }

    public void setYop(int yop) {
        this.yop = yop;
    }
}
