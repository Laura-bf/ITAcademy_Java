package com.nivel2.model.domain;

public class Flower extends Product{

    private String color;

    public Flower(String name, double price, String color) {
        super(name, price);
        this.color = color;
    }

    @Override
    public String toString() {
        return "FLOR\tId=" + super.getId() + "\tNombre: " + super.getName() + "\tColor: " + color + "\tPrecio: " + super.getPrice() + "€";
    }
}
