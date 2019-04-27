package com.example.nadat.chart.managers;

import com.example.nadat.chart.entities.Fruit;

import java.util.ArrayList;
import java.util.List;

public class FruitManager {
    private static final FruitManager fruitManagerIns = new FruitManager();
    private List<Fruit> mFruits;

    public static FruitManager getFruitManagerIns() {
        return fruitManagerIns;
    }

    private FruitManager() {
        mFruits = new ArrayList<>();
        mFruits.add(new Fruit("Lemon", "#ffeb3b", 10000));
        mFruits.add(new Fruit("Blueberry", "#2196f3", 20000));
        mFruits.add(new Fruit("Coconut", "#795548", 50000));
        mFruits.add(new Fruit("Orange", "#ff9800", 30000));
        mFruits.add(new Fruit("Apple", "#f44336", 60000));
    }

    public List<Fruit> getFruits() {
        return mFruits;
    }
}
