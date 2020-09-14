package com.example.chartit;

import java.util.List;

public interface ChartsProvider {
    List<String> getTitles();
    String getTitle(int i);
    void addTitle(String title);
    void removeChart(int i);
    void addChart(Chart chart);
    List<String> getChords(int i);
    String getVerse1(int i);
    String getVerse2(int i);
}
