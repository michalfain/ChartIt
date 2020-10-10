package com.example.chartit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Charts{

   private static List<String> chartsTitle;
   private static Map<String, Object> allCharts;

    public static Map<String, Object> getAllCharts() {
        if(allCharts == null)
        {
            allCharts = new HashMap<>();
        }
        return allCharts;
    }


     public static  List<String> getChartsTitles() {
        if (chartsTitle == null) {
            chartsTitle = new ArrayList<>();
        }
        return chartsTitle;
    }
     public static String getTitle(int i) {
        return getChartsTitles().get(i);
    }

     public static void addTitle(String title) {
         getChartsTitles().add(title);
    }


    public static void removeChart(int i) {
        getChartsTitles().remove(i);
    }

    public static void addChart(Chart chart) {

        getAllCharts().put(chart.getTitle(), chart);
    }

    public static List<String> getChords(String title) {
        return (List)((Map)(getAllCharts().get(title))).get(Constants.chords);
    }

    public static String getVerse1(String title) {
        return ((Map)(getAllCharts().get(title))).get(Constants.verse1).toString();
    }

    public static String getVerse2(String title) {
        return ((Map)(getAllCharts().get(title))).get(Constants.verse2).toString();
    }
}
