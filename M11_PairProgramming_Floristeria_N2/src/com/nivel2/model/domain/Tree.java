package com.nivel2.model.domain;

public class Tree extends Product{

    private double height;

    public Tree(String name, double price, double height) {
        super(name, price);
        this.height = height;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "ARBOL\tId=" + super.getId() +"\tNombre: " +super.getName()+ "\tAltura: " + height + "m\tPrecio: " + super.getPrice() + "€";
    }
}
