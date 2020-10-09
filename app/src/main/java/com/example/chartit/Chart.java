package com.example.chartit;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Chart  implements Serializable {
    public Chart() {

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVerse1(String verse1) {
        this.verse1 = verse1;
    }

    public void setVerse2(String verse2) {
        this.verse2 = verse2;
    }

    public void setChords(List<String> chords) {
        this.chords = chords;
    }

    public String getTitle() {
        return title;
    }

    public String getVerse1() {
        return verse1;
    }

    public String getVerse2() {
        return verse2;
    }

    public List<String> getChords() {
        return chords;
    }

    Chart(String title, String verse1, String verse2, List<String> chords){
        if(title != null) {
            this.title = title;
        }else
        {
            this.title = "";
        }
        if(verse1 != null) {
            this.verse1 = verse1;
        }else
        {
            this.verse1 = "";
        }
        if(verse2 != null) {
            this.verse2 = verse2;
        }else
        {
            this.verse2 = "";
        }
            this.chords = chords;
    }
    private String title;
    private String verse1;
    private String verse2;
    private List<String> chords;

    public boolean isEmpty(Chart chart)
    {
        if(chart.getTitle()!="" && chart.getVerse2()!="" &&chart.getVerse1()!="" && chart.getChords().isEmpty())
        {
            return true;
        }
        return  false;
    }

}
