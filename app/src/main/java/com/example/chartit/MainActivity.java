package com.example.chartit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    TextView tvAddChart, tvViewCharts, tvContactUs;
    Button btnVerify;
    TextView tvNotVerify;
    FirebaseAuth fbAuth;
    String userId;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAddChart = findViewById(R.id.add_chart);
        tvViewCharts = findViewById(R.id.view_charts);
        tvContactUs = findViewById(R.id.contact_us);
        btnVerify = findViewById(R.id.btn_verify);
        tvNotVerify = findViewById(R.id.tv_not_verified);
        fbAuth = FirebaseAuth.getInstance();
        userId = fbAuth.getCurrentUser().getUid();
        user = fbAuth.getCurrentUser();

        if(!user.isEmailVerified()){
            btnVerify.setVisibility(View.VISIBLE);
            tvNotVerify.setVisibility(View.VISIBLE);
            btnVerify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(MainActivity.this, "Please check your email for verification", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "ERROR! " + e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });
                }
            });
        }else btnVerify.setVisibility(View.GONE);
         tvNotVerify.setVisibility(View.GONE);

        tvAddChart.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, AddChart.class);
                        startActivity(intent);

                    }
                }
        );
        tvViewCharts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewCharts.class);
                startActivity(intent);

            }
        });
        tvContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactUs.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.log_out:
                logOut();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void  logOut(){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MainActivity.this, Login.class));
        finish();
    }
}