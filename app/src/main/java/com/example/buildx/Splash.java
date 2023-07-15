package com.example.buildx;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;



import java.util.Objects;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FirebaseAuth firebaseAuth;
        FirebaseFirestore fstore;
        String userID;

        firebaseAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        userID = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences preference = getSharedPreferences("login",MODE_PRIVATE);
                boolean check = preference.getBoolean("flag",false);

                DocumentReference documentReference = fstore.collection("Customer").document(userID);

                documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                        assert documentSnapshot != null;
                        String role = documentSnapshot.getString("role");

                            if ("customer".equals(role)) {
                                if (check) {    // Logged in
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                                }
                                else {       // If logged out and opens the app first time
                                    startActivity(new Intent(getApplicationContext(), Login_Activity.class));

                                }

                            } else {

                                DocumentReference documentReference = fstore.collection("Seller").document(userID);

                                documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                    @Override
                                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                                        assert documentSnapshot != null;
                                        String role = documentSnapshot.getString("role");

                                        if ("seller".equals(role)) {
                                            if (check) {    // Logged in
                                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                            } else {       // If logged out and opens the app first time
                                                startActivity(new Intent(getApplicationContext(), Login_Activity.class));

                                            }
                                        }
                                    }
                                });



                            }



                }
                });
            }
        },2000);
    }
}