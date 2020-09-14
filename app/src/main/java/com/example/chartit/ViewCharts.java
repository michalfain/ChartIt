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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ViewCharts extends AppCompatActivity {
    ListView listView;
    static ChartsProvider charts = new Charts();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_charts);

        listView = findViewById(R.id.list_view);

        final ListAdapter adapter = new ListAdapter(this, charts.getTitles());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ViewCharts.this, AddChart.class);
                intent.putExtra("title", charts.getTitle(i));
                intent.putExtra("verse1", charts.getVerse1(i));
                intent.putExtra("verse2", charts.getVerse2(i));
                intent.putExtra("index", i);
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.chartit"
                        , Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<String>(charts.getTitles());
                sharedPreferences.edit().putStringSet("charts", set).apply();
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
                                charts.removeChart(position);
                                adapter.notifyDataSetChanged();
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
            tvChartName.setText(charts.getTitles().get(position));

            return row;
        }

    }
}

