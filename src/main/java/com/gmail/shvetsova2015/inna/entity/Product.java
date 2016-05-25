package com.gmail.shvetsova2015.inna.entity;

import javax.persistence.*;


@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String type;
    private double price;
    private int timeLimit;

    public Product() {
    }

    public Product(String type, double price, int timeLimit) {
        this.type = type;
        this.price = price;
        this.timeLimit = timeLimit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    @Override
    public String toString() {
        System.out.println("id  type   price  timeLimit");
        return "Product[ " + id + " " + type + " " + price + " " + timeLimit + "]";
    }
}
