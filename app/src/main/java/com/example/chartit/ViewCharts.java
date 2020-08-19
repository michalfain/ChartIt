package com.example.chartit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ViewCharts extends AppCompatActivity {
    ListView listView;
    String chartsTitle[] = {"My song", "Song", "Sing"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_charts);

        listView = findViewById(R.id.list_view);

        ListAdapter adapter = new ListAdapter(this, chartsTitle);
        listView.setAdapter(adapter);
    }

    class ListAdapter extends ArrayAdapter<String>{

        Context context;
        String chartTitle[];

        ListAdapter(Context context, String chartTitle[]){
            super(context, R.layout.row_layout, chartTitle);
            this.context = context;
            this.chartTitle = chartTitle;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row_layout, parent, false);
            TextView tvChartName = row.findViewById(R.id.tv_chart_name);
            tvChartName.setText(chartsTitle[position]);

            return row;
        }
    }
}