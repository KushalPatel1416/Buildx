package com.example.buildx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_password extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        firebaseAuth = FirebaseAuth.getInstance();
        EditText username = findViewById(R.id.fUsername);
        Button send = findViewById(R.id.send);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String username1 = username.getText().toString().trim();
                if (username1.isEmpty()) {
                    username.setError("Please Enter Email");
                } else {
                    firebaseAuth.sendPasswordResetEmail(username1)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(Forgot_password.this, "Email sent", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Forgot_password.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });


    }
}