package com.nivel2.model.domain;

public class Decoration extends Product{

    private String material;

    public Decoration(String name, double price, String material) {
        super(name, price);
        this.material = material.toUpperCase();
    }

    @Override
    public String toString() {
        return "DECO\tId=" + super.getId() + "\tNombre: "+ super.getName() + "\tMat: "+ material + "\tPrecio: " + super.getPrice() + "€";
    }
}
