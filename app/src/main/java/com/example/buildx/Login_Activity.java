package com.example.buildx;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buildx.Seller.SellerOf;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Objects;


public class Login_Activity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore fstore;
    String userID;

    boolean doubletap = false;
    final int duration = 2000;
    View view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();

        view = findViewById(R.id.contentview);

        EditText username = findViewById(R.id.Username);
        EditText password = findViewById(R.id.Password);
        TextView signup =findViewById(R.id.textView4);
        Button login = findViewById(R.id.loginbtn);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView forgot = findViewById(R.id.forgot);



        //For Signup
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextsign;
                nextsign = new Intent(Login_Activity.this,Signup_as_a.class);
                startActivity(nextsign);
            }
        });

        //Login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String username1 = username.getText().toString().trim();
                String password1 = password.getText().toString().trim();

                //Validation
                if (username1.isEmpty() && password1.isEmpty()) {
                    username.setError("Please Enter Username");
                    password.setError("Please Enter Password");
                    Toast.makeText(Login_Activity.this, "These are required", Toast.LENGTH_SHORT).show();
                }
                else {

                    firebaseAuth.signInWithEmailAndPassword(username1, password1)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {

                                    login();

                                    Toast.makeText(Login_Activity.this, "Login Successfully", Toast.LENGTH_SHORT).show();


                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Login_Activity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            });

                }
            }
        });


        //forgot password
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Forgot_password.class));
            }
        });

    }


    private void sharedpreference2() {

        SharedPreferences preference = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor edit = preference.edit();

        edit.putBoolean("flag",true);
        edit.apply();

        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }
    private void sharedpreference() {

        SharedPreferences preference = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor edit = preference.edit();

        edit.putBoolean("flag",true);
        edit.apply();

        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }



//    //BackPressed fun..
    @Override
    public void onBackPressed() {
//        if(doubletap){
//        super.onBackPressed();
//        return;
//        }
//        else {
//            doubletap=true;
//            Snackbar.make(view,"Tap again to Exit",duration).show();
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run(){
//                    doubletap = false;
//                }
//            },duration);
//        }
        finish();


    }

    private void login(){

        firebaseAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        userID = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();

        DocumentReference documentReference = fstore.collection("Customer").document(userID);


        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                assert documentSnapshot != null;
                String role = documentSnapshot.getString("role");

                        if("customer".equals(role)) {

                            startActivity(new Intent(Login_Activity.this, MainActivity.class));
                            sharedpreference(); //If logged in

                        } else {
                            DocumentReference documentReference2 = fstore.collection("Seller").document(userID);

                            documentReference2.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                @Override
                                public void onEvent(@Nullable DocumentSnapshot documentSnapshot2, @Nullable FirebaseFirestoreException error) {

                                    assert documentSnapshot2 != null;
                                    String role = documentSnapshot2.getString("role");

                                    if ("seller".equals(role)) {

                                        startActivity(new Intent(Login_Activity.this, MainActivity.class));
                                        sharedpreference(); //If logged in

                                    }
                                }

                            });


                    }


                }
            });
        }
}


