package com.example.chartit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ContactUs extends AppCompatActivity {
    EditText etName, etEmail, etSubject, etMessage;
    String myEmail = "michelle.fain@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etSubject = findViewById(R.id.et_subject);
        etMessage = findViewById(R.id.et_massage);
    }

public void sendEmail(View v){
        if(!etName.getText().toString().isEmpty() && !etEmail.getText().toString().isEmpty() &&
        !etSubject.getText().toString().isEmpty() && !etMessage.getText().toString().isEmpty()){
    String message = etName.getText().toString() + " " + etMessage.getText().toString() + " "
            + etEmail.getText().toString();
    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"michelle.fain@gmail.com"});
    intent.putExtra(Intent.EXTRA_SUBJECT, etSubject.getText().toString());
    intent.putExtra(Intent.EXTRA_TEXT, message);
    intent.setType("message/rfc822");
    startActivity(intent);
} else {
            Toast.makeText(ContactUs.this, "Please enter all fields!", Toast.LENGTH_LONG).show();
        }
    }
}