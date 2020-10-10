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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.HashSet;
import java.util.List;

public class ViewCharts extends AppCompatActivity {
    ListView listView;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_charts);

        listView = findViewById(R.id.list_view);

        final ListAdapter adapter = new ListAdapter(this, Charts.getChartsTitles());
        reference = FirebaseDatabase.getInstance().getReference(Constants.users).child(Constants.userCharts);
        reference.addValueEventListener(new ValueEventListener() {
            //TODO circle thingi
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Charts.getChartsTitles().clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Charts.getChartsTitles().add(dataSnapshot.getKey());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    reference.addValueEventListener(new ValueEventListener() {
////                        @Override
////                        public void onDataChange(@NonNull DataSnapshot snapshot) {
////
////                        }
////
////                        @Override
////                        public void onCancelled(@NonNull DatabaseError error) {
////
////                        }
////                    });
                    Intent intent = new Intent(ViewCharts.this, AddChart.class);
//                    intent.putExtra(Constants.index, i);
//                    intent.putExtra(Constants.title, Charts.getTitle(i));
//                    intent.putExtra(Constants.verse1, Charts.getVerse1(i));
//                    intent.putExtra(Constants.verse2, Charts.getVerse2(i));
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
                                    sharedPreferences.edit().putStringSet(Constants.chartList, set).apply();
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