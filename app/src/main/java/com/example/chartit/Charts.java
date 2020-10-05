package com.example.chartit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Charts{

   private static List<String> chartsTitle;
   private static Map<String, Chart> allCharts;

    public static Map<String, Chart> getAllCharts() {
        if(allCharts==null)
        {
            allCharts=new HashMap<>();
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

    public static Map<Integer, String> getChords(int i) {
        return getAllCharts().get(i).getChords();
    }

    public static String getVerse1(int i) {
        return getAllCharts().get(i).getVerse1();
    }

    public static String getVerse2(int i) {
        return getAllCharts().get(i).getVerse2();
    }
}
