package com.example.chartit;

import java.util.ArrayList;
import java.util.List;

public class Charts implements ChartsProvider{

    private List<String> chartsTitle = new ArrayList<String>();
    private List<Chart> allCharts = new ArrayList<Chart>();


    @Override
    public List<String> getTitles() {

        return chartsTitle;
    }

    @Override
    public String getTitle(int i) {

        return chartsTitle.get(i);
    }

    @Override
    public void addTitle(String title) {

        chartsTitle.add(title);
    }

    @Override
    public void removeChart(int i) {

        chartsTitle.remove(i);
    }

    @Override
    public void addChart(Chart chart) {
        allCharts.add(chart);
    }

    @Override
    public List<String> getChords(int i) {

        return allCharts.get(i).chords;
    }
//    @Override
//    public int getIndex(Chart chart) {
//
//        return allCharts.indexOf(chart.title);
//    }

}
