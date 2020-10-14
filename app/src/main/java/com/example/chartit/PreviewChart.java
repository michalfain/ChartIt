package com.example.chartit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PreviewChart extends AppCompatActivity {
    TextView tvTitle, tvVerse1, tvVerse2, eChord;
    String title, verse1, verse2;
    List chords = new ArrayList<String>();
//    Bitmap btm, scaledBtm, b;
    private LinearLayout llPdf;
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_chart);
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        tvTitle = findViewById(R.id.tv_pre_title);
        tvVerse1 = findViewById(R.id.tv_pre_verse_1);
        tvVerse2 = findViewById(R.id.tv_pre_verse_2);
        llPdf = findViewById(R.id.preview_view);

        Intent intent = getIntent();
        title = intent.getStringExtra(Constants.title);
        verse1 = intent.getStringExtra(Constants.verse1);
        verse2 = intent.getStringExtra(Constants.verse2);
//        chords = intent.getParcelableArrayListExtra(Constants.chords);
        tvTitle.setText(title);
        tvVerse1.setText(verse1);
        tvVerse2.setText(verse2);
        for (int i = 1; i < 33; i++) {
            eChord = (TextView) findViewById(getResources().getIdentifier("pre_chord" + i, "id", getPackageName()));
            if(Charts.getChords(title).get(i - 1)!= null){
                eChord.setText(Charts.getChords(title).get(i -1));
            }else {
                eChord.setText(" ");
            }
        }


//        btm = BitmapFactory.decodeResource(getResources(), R.drawable.folder);
//        scaledBtm = Bitmap.createScaledBitmap(btm, 1200, 500, false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.preview_chart_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.pdf:
                getPdf();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void getPdf() {
        if (tvTitle.getText().toString().length() == 0) {
            Toast.makeText(PreviewChart.this, "Please enter a title to your chart", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(PreviewChart.this, "Convert to pdf!", Toast.LENGTH_LONG).show();
//            bitmap = loadBitmapFromView(llPdf, llPdf.getWidth(), llPdf.getHeight());
//            createPdf();
            }
//            PdfDocument pdfDocument = new PdfDocument();
//            Paint paint = new Paint();
//            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(400, 600, 1).create();
//            PdfDocument.Page page = pdfDocument.startPage(pageInfo);
//            Canvas canvas = page.getCanvas();
//            canvas.drawText("My Chart", 40, 50, paint);
//            canvas.drawBitmap(scaledBtm, 0, 0, paint);
//            pdfDocument.finishPage(page);
//            File file = new File(Environment.getExternalStorageDirectory(), "/Chart.pdf");
//            Log.d("create pdf", Environment.getExternalStorageDirectory().toString());
//            try {
//                pdfDocument.writeTo(new FileOutputStream(file));
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//            pdfDocument.close();

        }
//    public static Bitmap loadBitmapFromView(View v, int width, int height) {
//        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//        Canvas c = new Canvas(b);
//        v.draw(c);
//
//        return b;
//    }
//    private void createPdf(){
//        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//          Display display = wm.getDefaultDisplay();
//        DisplayMetrics displaymetrics = new DisplayMetrics();
//        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
//        float height = displaymetrics.heightPixels ;
//        float width = displaymetrics.widthPixels ;
//
//            int convertHeight = (int) height, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

//            PdfDocument document = new PdfDocument();
//            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHeight, 1).create();
//            PdfDocument.Page page = document.startPage(pageInfo);
//
//            Canvas canvas = page.getCanvas();
//
//            Paint paint = new Paint();
//            canvas.drawPaint(paint);
//
//            bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHeight, true);
//
//            paint.setColor(Color.BLUE);
//            canvas.drawBitmap(bitmap, 0, 0 , null);
//            document.finishPage(page);

            // write the document content
//             File file = new File(Environment.getExternalStorageDirectory(), "/Chart.pdf");
//            Log.d("create pdf", Environment.getExternalStorageDirectory().toString());
//            try {
//                document.writeTo(new FileOutputStream(file));
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//            String targetPdf = "/sdcard/pdffromlayout.pdf";
//            File filePath;
//            filePath = new File(targetPdf);
//            try {
//                document.writeTo(new FileOutputStream(filePath));
//
//            } catch (IOException e) {
//                e.printStackTrace();
//                Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
//            }

            // close the document
//            document.close();
//            Toast.makeText(this, "PDF is created!!!", Toast.LENGTH_SHORT).show();
//
//            openGeneratedPDF();
//
//        }
//    private void openGeneratedPDF(){
//        File file = new File("/sdcard/pdffromlayout.pdf");
//        if (file.exists())
//        {
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            Uri uri = Uri.fromFile(file);
//            intent.setDataAndType(uri, "application/pdf");
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//            try
//            {
//                startActivity(intent);
//            }
//            catch(ActivityNotFoundException e)
//            {
//                Toast.makeText(PreviewChart.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
//            }
//        }
//    }

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);
    }
