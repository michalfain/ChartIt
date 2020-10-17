package com.example.chartit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gkemon.XMLtoPDF.PdfGenerator;
import com.gkemon.XMLtoPDF.PdfGeneratorListener;
import com.gkemon.XMLtoPDF.model.FailureResponse;
import com.gkemon.XMLtoPDF.model.SuccessResponse;

import java.io.File;
import java.util.List;

public class PreviewChart extends AppCompatActivity {
    TextView tvTitle, tvVerse1, tvVerse2, eChord;
    String title, verse1, verse2;
    LinearLayout llPdf;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_chart);
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        context = getApplicationContext();
        tvTitle = findViewById(R.id.tv_pre_title);
        tvVerse1 = findViewById(R.id.tv_pre_verse_1);
        tvVerse2 = findViewById(R.id.tv_pre_verse_2);
        llPdf = findViewById(R.id.preview_view);

        Intent intent = getIntent();
        title = intent.getStringExtra(Constants.title);
        verse1 = intent.getStringExtra(Constants.verse1);
        verse2 = intent.getStringExtra(Constants.verse2);
        tvTitle.setText(title);
        tvVerse1.setText(verse1);
        tvVerse2.setText(verse2);
        List myChords = Charts.getChords(title);
        for (int i = 1; i < 33; i++) {
            eChord = (TextView) findViewById(getResources().getIdentifier("pre_chord" + i, "id", getPackageName()));
            if(myChords.get(i - 1) != null) {
                eChord.setText(myChords.get(i - 1).toString());
            }else {
                eChord.setText(" ");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.preview_chart_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pdf:
                getPdf();
                break;
//
//            case R.id.open:
//                openFolder();
//                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getPdf() {
        if (tvTitle.getText().toString().length() == 0) {
            Toast.makeText(PreviewChart.this, "Please enter a title to your chart", Toast.LENGTH_LONG).show();
        } else {
            createPdf();
            Toast.makeText(PreviewChart.this, "Convert to pdf!", Toast.LENGTH_LONG).show();

        }
    }
    private void requestForSpecificPermission() {
        ActivityCompat.requestPermissions(PreviewChart.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
    }
    private void createPdf() {
        requestForSpecificPermission();
        PdfGenerator.getBuilder()
                .setContext(context)
                .fromViewSource()
                .fromView(llPdf)
                .setDefaultPageSize(PdfGenerator.PageSize.A4)
                .setFileName(title)
                .setFolderName("ChartIt/MyCharts")
                .openPDFafterGeneration(true)
                .build(new PdfGeneratorListener() {
                    @Override
                    public void onFailure(FailureResponse failureResponse) {
                        super.onFailure(failureResponse);
                    }

                    @Override
                    public void showLog(String log) {
                        super.showLog(log);
                        /*It shows logs of events inside the pdf generation process*/
                    }

                    @Override
                    public void onSuccess(SuccessResponse response) {
                        super.onSuccess(response);
                    }
                });
    }

//    public void openFolder(){
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        Uri uri = Uri.parse("/sdcard" +  File.separator + "ChartIt/MyCharts" + File.separator);
//        intent.setDataAndType(uri, "text/* application/pdf");
//        startActivity(Intent.createChooser(intent, "Open folder"));
//    }
}