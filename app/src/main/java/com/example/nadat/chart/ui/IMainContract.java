package com.example.nadat.chart.ui;

import com.example.nadat.chart.entities.Fruit;

import java.util.List;

public interface IMainContract {
    interface IView {
        void showPieChart(List<Fruit> fruits);

        void showLineChart(List<Fruit> fruits);

        void showNotificationEmpty();
    }

    interface IPresenter {
        void getDataForPieChart();

        void getDataForLineChart();
    }
}
