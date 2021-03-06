package com.nivel1.model.domain;

public class Product {

    private static int countId = 1;
    private int id;
    private String name;
    private double price;
    

    public Product(String name, double price) {
        this.id = countId;
        countId++;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
