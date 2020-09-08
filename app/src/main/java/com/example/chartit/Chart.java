package com.example.chartit;

import java.util.List;

public class Chart {
    Chart(String title, List<String> chord){
        this.title = title;
        this.chord = chord;
    }
    String title;
    List<String> chord;
}
