package com.example.chartit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddChart extends AppCompatActivity {
    public static Map<Integer, AutoCompleteTextView> etChordsMap = new HashMap<>();
    EditText etTitle, etVerse1, etVerse2;
    static Map allChartsDetails = new HashMap();
    List<String> selectedChordsList;
    String title, verse1, verse2;
    String titleFromIntent, verse1FromIntent, verse2FromIntent;
    FirebaseAuth fbAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chart);
        etTitle = findViewById(R.id.ed_title);
        etVerse1 = findViewById(R.id.et_verse_1);
        etVerse2 = findViewById(R.id.et_verse_2);
        fbAuth = FirebaseAuth.getInstance();
        title = etTitle.getText().toString();
        verse1 = etVerse1.getText().toString();
        verse2 = etVerse2.getText().toString();
        selectedChordsList  = new ArrayList<String>();

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
        for (int i = 1; i < 33; i++) {
            AutoCompleteTextView eChord = (AutoCompleteTextView) findViewById(getResources().getIdentifier("chord" + i, "id", getPackageName()));
            eChord.setThreshold(1);
            eChord.setAdapter(adapter);
            etChordsMap.put(i, eChord);
        }
        Intent intent = getIntent();
        titleFromIntent = intent.getStringExtra(Constants.title);
        etTitle.setText(titleFromIntent);
        verse1FromIntent = intent.getStringExtra(Constants.verse1);
        etVerse1.setText(verse1FromIntent);
        verse2FromIntent = intent.getStringExtra(Constants.verse2);
        etVerse2.setText(verse2FromIntent);
        if(titleFromIntent != null) {
            setEtChords(titleFromIntent);
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
        break;

    case R.id.reset:
        resetChart();
        break;

    case R.id.preview:
        showPreview();
        break;
}
        return super.onOptionsItemSelected(item);
    }

    private void save(){
        if(!etTitle.getText().toString().isEmpty()) {
            Chart chart = new Chart();
            chart.setTitle(etTitle.getText().toString());
            if (!etVerse1.getText().toString().isEmpty()) {
                chart.setVerse1(etVerse1.getText().toString());
            }
            if (!etVerse2.getText().toString().isEmpty()) {
                chart.setVerse2(etVerse2.getText().toString());
            }
            for(Integer index : etChordsMap.keySet()){
                selectedChordsList.add(index - 1, etChordsMap.get(index).getText().toString());
            }
            chart.setChords(selectedChordsList);
            Charts.addTitle(chart.getTitle());
            Charts.addChart(chart);
            allChartsDetails.put(chart.getTitle(), chart);


            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference(Constants.users).child(Constants.userCharts);
            reference.updateChildren(Charts.getAllCharts());
            Toast.makeText(AddChart.this, "Chart was saved!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(AddChart.this, "Please enter chart's title", Toast.LENGTH_LONG).show();
        }
    }
        private void setEtChords(String title) {
        List<String> etChordsBoard = Charts.getChords(title);
        for(Integer index : etChordsMap.keySet())
        {
            etChordsMap.get(index).setText(etChordsBoard.get(index - 1));
        }

    }

    private void resetChart(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddChart.this);
        alertDialog.setTitle("Are You Sure");
        alertDialog.setMessage("Reset chart?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                reset();
                Toast.makeText(AddChart.this, "Chart was reset!", Toast.LENGTH_LONG).show();

            }
        });
        alertDialog.setNegativeButton("No", null);
        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

    private void reset(){
        etTitle.setText("");
        etVerse1.setText("");
        etVerse2.setText("");
        for(AutoCompleteTextView etChord:etChordsMap.values())
        {
            etChord.setText("");
        }
    }
    private void showPreview(){
        Intent intent = new Intent(AddChart.this, PreviewChart.class);
        intent.putExtra(Constants.title, titleFromIntent);
        intent.putExtra(Constants.verse1, verse1FromIntent);
        intent.putExtra(Constants.verse2, verse2FromIntent);
//        intent.putExtra(Constants.chords, (Parcelable) Charts.getChords(titleFromIntent));
        startActivity(intent);
    }
}