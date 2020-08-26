package com.example.chartit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ContactUs extends AppCompatActivity {
    EditText etName, etEmail, etSubject, etMassage;
    String myEmail = "michelle.fain@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etSubject = findViewById(R.id.et_subject);
        etMassage = findViewById(R.id.et_massage);
    }

public void sendEmail(View v){
    String message = etName.getText().toString() + " " + etMassage.getText().toString() + " "
            + etEmail.getText().toString();
    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + myEmail));
    intent.putExtra(Intent.EXTRA_SUBJECT, etSubject.getText().toString());
    intent.putExtra(Intent.EXTRA_TEXT, message);
    startActivity(intent);
}
}