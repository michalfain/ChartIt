package com.example.chartit;

import java.util.ArrayList;
import java.util.List;

public class Charts implements ChartsProvider{

    private List<String> chartsTitle = new ArrayList<String>();


    @Override
    public List<String> getTitles() {

        return chartsTitle;
    }

    @Override
    public void addTitle(String title) {

        chartsTitle.add(title);
    }

    @Override
    public void addChords() {

    }
}
