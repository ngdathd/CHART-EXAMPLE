package com.example.nadat.chart.entities;

public class Fruit {
    private String Name;
    private String Color;
    private int Revenue;

    public Fruit(String name, String color, int revenue) {
        Name = name;
        Color = color;
        Revenue = revenue;
    }

    public String getName() {
        return Name;
    }

    public String getColor() {
        return Color;
    }

    public int getRevenue() {
        return Revenue;
    }
}
