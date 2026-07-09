/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlinefood;

/**
 *
 * @author asus
 */
public class Meal {
    private int id;
    private String name;
    private double price;

    public Meal(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return name + " - " + price + " SAR";
    }

    int getQuantity() {
        throw new UnsupportedOperationException("Not supported yet."); }

    int getId() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

