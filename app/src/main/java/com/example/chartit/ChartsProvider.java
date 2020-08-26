package com.example.chartit;

import java.util.List;

public interface ChartsProvider {
    List<String> getTitles();
    void addTitle(String title);
    void addChords();
}
