package com.example.chartit;

import java.util.ArrayList;
import java.util.List;

public class Chart extends Charts{
    Chart(String title, List<String> chords){
        this.title = title;
        this.chords = chords;
    }
    String title;
    List<String> chords;
}
