package com.example.nadat.chart.ui;

import com.example.nadat.chart.entities.Fruit;
import com.example.nadat.chart.managers.FruitManager;

import java.util.List;

class MainPresenter implements IMainContract.IPresenter {
    private IMainContract.IView mIView;

    MainPresenter(IMainContract.IView mIView) {
        this.mIView = mIView;
    }

    @Override
    public void getDataForPieChart() {
        List<Fruit> fruits = FruitManager.getFruitManagerIns().getFruits();
        if (fruits == null || fruits.size() == 0) {
            mIView.showNotificationEmpty();
        } else {
            mIView.showPieChart(fruits);
        }
    }

    @Override
    public void getDataForLineChart() {
        List<Fruit> fruits = FruitManager.getFruitManagerIns().getFruits();
        if (fruits == null || fruits.size() == 0) {
            mIView.showNotificationEmpty();
        } else {
            mIView.showLineChart(fruits);

        }
    }
}
