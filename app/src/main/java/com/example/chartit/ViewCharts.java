package com.example.chartit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.HashSet;
import java.util.List;

public class ViewCharts extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_charts);

        listView = findViewById(R.id.list_view);
//        final SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.chartit"
//                , Context.MODE_PRIVATE);
//        HashSet<String> set2 = (HashSet<String>) sharedPreferences.getStringSet("Chart List", null);
//
//        if (set2 != null) {
//            Charts.getChartsTitles().addAll(set2);
//            }
//
//            final SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putStringSet("Chart List", set2).apply();
//            editor.commit();

            final ListAdapter adapter = new ListAdapter(this, Charts.getChartsTitles());
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(ViewCharts.this, AddChart.class);
                    intent.putExtra("index", i);
                    intent.putExtra("title", Charts.getTitle(i));
                    intent.putExtra("verse1", Charts.getVerse1(i));
                    intent.putExtra("verse2", Charts.getVerse2(i));
//                    Chart c = new Chart(Charts.getTitle(i), Charts.getVerse1(i), Charts.getVerse2(i), Charts.getChords(i));
//                    intent.putExtra("chart", c);
//                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.chartit", Context.MODE_PRIVATE);
//                    HashSet<String> set = new HashSet<>(Charts.getChartsTitles());
//                    sharedPreferences.edit().putStringSet("Chart List", set).apply();
//                    Gson gson = new Gson();
//                    String myChart = gson.toJson(c);
//                    editor.putString("chart", myChart);
//                    editor.commit();
//                    String chartData = sharedPreferences.getString("chart", null);
//                    Chart sfChart = gson.fromJson(chartData, Chart.class);
//                    Charts.addChart(sfChart);
                    startActivity(intent);
                }
            });
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    new AlertDialog.Builder(ViewCharts.this)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Are you sure?")
                            .setMessage("Do you want to delete this chart?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Charts.removeChart(position);
                                    adapter.notifyDataSetChanged();
                                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.chartit"
                                            , Context.MODE_PRIVATE);
                                    HashSet<String> set = new HashSet<>(Charts.getChartsTitles());
                                    sharedPreferences.edit().putStringSet("Chart List", set).apply();
//                                    AddChart.allChartsDetails.remove(Charts.getTitle(position));
                                    Charts.getChartsTitles().remove(Charts.getTitle(position));
                                }
                            })
                            .setNegativeButton("NO!", null)
                            .show();
                    return true;
                }
            });
        }
        class ListAdapter extends ArrayAdapter<String> {

            Context context;
            String chartTitle[];

            ListAdapter(Context context, List<String> chartTitle) {
                super(context, R.layout.row_layout, chartTitle);
                this.context = context;
                this.chartTitle = chartTitle.toArray(new String[0]);
            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View row = layoutInflater.inflate(R.layout.row_layout, parent, false);
                TextView tvChartName = row.findViewById(R.id.tv_chart_name);
                tvChartName.setText(Charts.getChartsTitles().get(position));

                return row;
            }

        }
    }