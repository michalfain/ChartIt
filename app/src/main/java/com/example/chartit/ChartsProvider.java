package com.example.chartit;

import java.util.List;

public interface ChartsProvider {
    List<String> getTitles();
    String getTitle(int i);
    void addTitle(String title);
    void removeChart(int i);
//    void addChords();

}
