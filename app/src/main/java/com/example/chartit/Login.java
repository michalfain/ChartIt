package com.example.chartit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText etLoginEmail, etLoginPassword;
    Button btnLogin;
    TextView tvRegister, tvForgetPassword;
    ProgressBar progressBar;
    FirebaseAuth fbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etLoginEmail = findViewById(R.id.et_login_email);
        etLoginPassword = findViewById(R.id.et_login_password);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);
        tvForgetPassword = findViewById(R.id.tv_forgot_password);
        progressBar = findViewById(R.id.login_progress_bar);
        fbAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etLoginEmail.getText().toString().trim();
                String password = etLoginPassword.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    etLoginEmail.setError("Please enter your email");
                    return;
                }  if(TextUtils.isEmpty(password)){
                    etLoginPassword.setError("Please enter a password");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                fbAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()){
                       Toast.makeText(Login.this, "You are logged in!", Toast.LENGTH_LONG).show();
                       startActivity(new Intent(getApplicationContext(), MainActivity.class));
                   }else {
                       Toast.makeText(Login.this, "ERROR! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);

                   }
                    }
                });
                          }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText resetEmail =  new EditText(view.getContext());
                AlertDialog.Builder resetPassword = new AlertDialog.Builder(view.getContext());
                resetPassword.setTitle("Reset Password?");
                resetPassword.setMessage("Enter your email please");
                resetPassword.setView(resetEmail);

                resetPassword.setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    String mail = resetEmail.getText().toString().trim();
                    fbAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                       Toast.makeText(Login.this, "Check your Email!", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Login.this, "ERROR! " + e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });
                    }
                });
                resetPassword.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                resetPassword.create().show();
            }
        });
            }
}