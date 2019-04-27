package com.example.nadat.chart.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.nadat.chart.R;
import com.example.nadat.chart.entities.Fruit;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainContract.IView {
    private PieChart pieChart;
    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewByIds();

        IMainContract.IPresenter presenter = new MainPresenter(this);
        presenter.getDataForPieChart();
        presenter.getDataForLineChart();
    }

    private void findViewByIds() {
        pieChart = findViewById(R.id.pieChart);
        lineChart = findViewById(R.id.lineChart);
    }

    private void setUpPieChart() {
        pieChart.setExtraOffsets(4f, 4f, 4f, 4f);

        // Vẽ phần hình tròn biến biển đồ tròn thành biểu đồ hình vành khuyên
        pieChart.setDrawHoleEnabled(true);
        // Tô màu cho phần hình tròn
        pieChart.setHoleColor(Color.GREEN);
        // Phần trăm mà phần hình tròn chiếm trên toàn biêu đồ: r/R% = 75
        pieChart.setHoleRadius(75.0f);

        // Vị trí bắt đầu vẽ
        pieChart.setRotationAngle(0.0f);
        // Biểu đồ có thể xoay hay không
        pieChart.setRotationEnabled(false);

        // Phóng to phần biểu đồ được chạm
        pieChart.setHighlightPerTapEnabled(true);
        // Màu của chữ trong từng phần
        pieChart.setEntryLabelColor(Color.BLACK);

        // Biều đồ có chữ ở giữa hay không
        pieChart.setDrawCenterText(true);
        // Chữ ở giữa biểu đồ
        pieChart.setCenterText("Revenue");

        // Thời gian thực hiện animate vẽ biểu đồ
        pieChart.animateY(1400);

        // Mô tả của biểu đồ
        Description description = new Description();
        description.setText("Doanh thu trái cây");
        pieChart.setDescription(description);

        // Hiển thị chú thích trên biểu đồ hay không
        pieChart.getLegend().setEnabled(false);

        pieChart.invalidate();
    }

    @Override
    public void showPieChart(List<Fruit> fruits) {
        List<PieEntry> pieEntries = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();

        for (Fruit fruit : fruits) {
            colors.add(Color.parseColor(fruit.getColor()));
            pieEntries.add(new PieEntry(fruit.getRevenue(), fruit.getName()));
        }

        PieDataSet pieDataSet = new PieDataSet(pieEntries, null);
        pieDataSet.setColors(colors);
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData data = new PieData(pieDataSet);
        data.setValueFormatter(new PercentFormatter(pieChart));

        pieChart.setData(data);
        pieChart.setUsePercentValues(true);

        setUpPieChart();
    }

    @Override
    public void showLineChart(List<Fruit> fruits) {
        List<Entry> lineEntries = new ArrayList<>();

        for (int i = 0; i < fruits.size(); i++) {
            lineEntries.add(new Entry(i + 1, fruits.get(i).getRevenue()));
        }

        LineDataSet dataSet = new LineDataSet(lineEntries, null);
        dataSet.setValueTextSize(0f);
        dataSet.setCircleColor(Color.TRANSPARENT);
        dataSet.setCircleHoleColor(Color.GREEN);
        dataSet.setColor(Color.GREEN);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);

        setUpLineChart(fruits);
    }

    private void setUpLineChart(List<Fruit> fruits) {
        // Hiển thị nền kẻ ô
        lineChart.setDrawGridBackground(false);
        // Mô tả của biểu đồ
        Description description = new Description();
        description.setText("Giá bán trái cây");
        lineChart.setDescription(description);
        // Có thể chạm vào biểu đồ hay không
        lineChart.setTouchEnabled(false);

        // Lấy ra trục Ox
        XAxis xAxis = lineChart.getXAxis();
        // Đặt trục Ox ở vị trí dưới
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // Vẽ các đường thẳng dọc mỗi giá trị trên Ox
        xAxis.setDrawGridLines(true);
        // Vẽ đường thẳng Ox
        xAxis.setDrawAxisLine(true);
        // Giá trị nhỏ nhất trên Ox: MIN
        xAxis.setAxisMinimum(1f);
        // Giá trị lớn nhất trên Ox: MAX
        xAxis.setAxisMaximum(fruits.size());
        // Số cột trên Ox, 2 giá trị tạo 1 cột: COUNT -> (MAX - MIN)/COUNT = độ chia nhỏ nhất
        xAxis.setLabelCount(fruits.size(), true);

        // Lấy ra trục 0y bên trái
        YAxis yAxis = lineChart.getAxisLeft();
        // Hiển thị đường nét đứt
        yAxis.enableGridDashedLine(5.0f, 5.0f, 0.0f);
        // Màu của trục Oy
//        yAxis.setAxisLineColor(Color.TRANSPARENT);
        yAxis.setAxisLineColor(Color.BLACK);
        // Giá trị nhỏ nhất trên Oy: MIN
        yAxis.setAxisMinimum(0f);
        // Giá trị lớn nhất trên Oy: MAX
        yAxis.setAxisMaximum(100000f);
        // Số dòng trên Oy, mỗi giá trị 1 dòng
        yAxis.setLabelCount(6, true);

        // Hiển thị chú thích trên biểu đồ hay không
        lineChart.getLegend().setEnabled(false);
        // Ẩn trục Oy bên phải
        lineChart.getAxisRight().setEnabled(false);

        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
    }

    @Override
    public void showNotificationEmpty() {
        Toast.makeText(this, "No chart data available", Toast.LENGTH_SHORT).show();
    }
}
