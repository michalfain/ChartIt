package com.example.chartit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ContactUs extends AppCompatActivity {
    EditText etName, etSubject, etMessage;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        etName = findViewById(R.id.et_name);
        etSubject = findViewById(R.id.et_subject);
        etMessage = findViewById(R.id.et_massage);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(Constants.users).child(Constants.email);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userEmail = snapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

public void sendEmail(View v){
        if(!etName.getText().toString().isEmpty() && !etSubject.getText().toString().isEmpty() &&
                !etMessage.getText().toString().isEmpty()){
    String message = etName.getText().toString() + " " + etMessage.getText().toString() + userEmail;
    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{Constants.myEmail});
    intent.putExtra(Intent.EXTRA_SUBJECT, etSubject.getText().toString());
    intent.putExtra(Intent.EXTRA_TEXT, message);
    intent.setType("message/rfc822");
    startActivity(intent);
    etName.setText("");
    etSubject.setText("");
    etMessage.setText("");
            Toast.makeText(ContactUs.this, "Thank you for your feedback!", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(ContactUs.this, "Please enter all fields!", Toast.LENGTH_LONG).show();
        }
    }
}