package com.example.chartit;

import java.util.List;

public class Chart extends Charts{
    Chart(String title, String verse1, String verse2, List<String> chords){
        this.title = title;
        this.chords = chords;
        this.verse1 = verse1;
        this.verse2 = verse2;
    }
    String title;
    String verse1;
    String verse2;
    List<String> chords;
}
