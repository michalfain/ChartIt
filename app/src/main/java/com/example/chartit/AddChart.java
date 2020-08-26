package com.example.chartit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class AddChart extends AppCompatActivity {
    public static Map<Integer, AutoCompleteTextView> etChordsMap = new HashMap<>();
    EditText etTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chart);
        etTitle = findViewById(R.id.ed_title);

        String[] chords = {"C", "Cm", "Cdim", "Caug", "Cmaj7", "C7", "Cm7", "CmMaj7", "Cm7b5", "Cdim7",
                "Cb", "Cbm", "Cbdim", "Cbaug", "Cbmaj7", "Cb7", "Cbm7", "CbmMaj7", "Cbm7b5", "Cbdim7",
                "C#", "C#m", "C#dim", "C#aug", "C#maj7", "C#7", "C#m7", "C#mMaj7", "C#m7b5", "C#dim7",
                "D", "Dm", "Ddim", "Daug", "Dmaj7", "D7", "Dm7", "DmMaj7", "Dm7b5", "Ddim7",
                "Db", "Dbm", "Dbdim", "Dbaug", "Dbmaj7", "Db7", "Dbm7", "DbmMaj7", "Dbm7b5", "Dbdim7",
                "D#", "D#m", "D#dim", "D#aug", "D#maj7", "D#7", "D#m7", "D#mMaj7", "D#m7b5", "D#dim7",
                "E", "Em", "Edim", "Eaug", "Emaj7", "E7", "Em7", "EmMaj7", "Em7b5", "Edim7",
                "Eb", "Ebm", "Ebdim", "Ebaug", "Ebmaj7", "Eb7", "Ebm7", "EbmMaj7", "Ebm7b5", "Ebdim7",
                "E#", "E#m", "E#dim", "E#aug", "E#maj7", "E#7", "E#m7", "E#mMaj7", "E#m7b5", "E#dim7",
                "F", "Fm", "Fdim", "Faug", "Fmaj7", "F7", "Fm7", "FmMaj7", "Fm7b5", "Fdim7",
                "Fb", "Fbm", "Fbdim", "Fbaug", "Fbmaj7", "Fb7", "Fbm7", "FbmMaj7", "Fbm7b5", "Fbdim7",
                "F#", "F#m", "#Fdim", "F#aug", "F#maj7", "F#7", "F#m7", "F#mMaj7", "F#m7b5", "F#dim7",
                "G", "Gm", "Gdim", "Gaug", "Gmaj7", "G7", "Gm7", "GmMaj7", "Gm7b5", "Gdim7",
                "Gb", "Gbm", "Gbdim", "Gbaug", "Gbmaj7", "Gb7", "Gbm7", "GbmMaj7", "Gbm7b5", "Gbdim7",
                "G#", "G#m", "G#dim", "G#aug", "G#maj7", "G#7", "G#m7", "G#mMaj7", "G#m7b5", "G#dim7",
                "A", "Am", "Adim", "Aaug", "Amaj7", "A7", "Am7", "AmMaj7", "Am7b5", "Adim7",
                "Ab", "Abm", "Abdim", "Abaug", "Abmaj7", "Ab7", "Abm7", "AbmMaj7", "Abm7b5", "Abdim7",
                "A#", "A#m", "A#dim", "A#aug", "A#maj7", "A#7", "A#m7", "A#mMaj7", "A#m7b5", "A#dim7",
                "B", "Bm", "Bdim", "Baug", "Bmaj7", "B7", "Bm7", "BmMaj7", "Bm7b5", "Bdim7",
                "Bb", "Bbm", "Bbdim", "Bbaug", "Bbmaj7", "Bb7", "Bbm7", "BbmMaj7", "Bbm7b5", "Bbdim7",
                "B#", "B#m", "B#dim", "B#aug", "B#maj7", "B#7", "B#m7", "B#mMaj7", "B#m7b5", "B#dim7"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, chords);
        for (int i = 1; i < 9; i++) {
            AutoCompleteTextView eChord = (AutoCompleteTextView) findViewById(getResources().getIdentifier("chord" + i, "id", getPackageName()));
            eChord.setThreshold(1);
            eChord.setAdapter(adapter);
            etChordsMap.put(i, eChord);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_chart_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
switch (item.getItemId()){
    case R.id.save:
        save();
        Toast.makeText(AddChart.this, "Chart was saved!", Toast.LENGTH_LONG).show();
    case R.id.reset:
        resetChart();
        Toast.makeText(AddChart.this, "Chart was reset", Toast.LENGTH_LONG).show();
}
        return super.onOptionsItemSelected(item);
    }

    public void save(){
        ChartsProvider charts = new Charts(etTitle.getText().toString());
    }
    private void resetChart(){

    }
}