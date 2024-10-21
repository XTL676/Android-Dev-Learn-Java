package com.zjbti.exam1.bean;

import java.io.Serializable;

public class FoodBean implements Serializable {
    private static final long serialVersionUID = 2533643248865552140L;
    private String name;
    private String sales;
    private String price;
    private int img;

    public FoodBean() {
    }

    public FoodBean(String name, String sales, String price, int img) {
        this.name = name;
        this.sales = sales;
        this.price = price;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
