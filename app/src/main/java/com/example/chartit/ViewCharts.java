package com.example.chartit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ViewCharts extends AppCompatActivity {
    ListView listView;
    EditText etTitle;
    ChartsProvider charts = new Charts("my song");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_charts);

        listView = findViewById(R.id.list_view);

        ListAdapter adapter = new ListAdapter(this, charts.getTitles());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ViewCharts.this, AddChart.class);
                startActivity(intent);
            }
        });
    }

    class ListAdapter extends ArrayAdapter<String>{

        Context context;
        String chartTitle[];

        ListAdapter(Context context, List<String> chartTitle){
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