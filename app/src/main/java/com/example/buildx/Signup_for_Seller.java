package com.example.buildx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Objects;

import com.example.buildx.Models.Seller_Model;

public class Signup_for_Seller extends AppCompatActivity {

    private int year,month,day;


    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_for_seller);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        EditText name = findViewById(R.id.Sellername);
        EditText mail = findViewById(R.id.sEmail);
        EditText DOB = findViewById(R.id.sDOB);
        EditText shop = findViewById(R.id.sShopName);
        EditText contact = findViewById(R.id.sContact);
        EditText address = findViewById(R.id.sAddress);
        EditText password = findViewById(R.id.sPassword);
        EditText password2 = findViewById(R.id.sPassword2);
        Button signup = findViewById(R.id.sSignup);


        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Signup_for_Seller.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        DOB.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = name.getText().toString().trim();
                String mail1 = mail.getText().toString().trim();
                String shop1 = shop.getText().toString().trim();
                String DOB1 = DOB.getText().toString().trim();
                String contact1 = contact.getText().toString().trim();
                String address1 = address.getText().toString().trim();
                String password1 = password.getText().toString().trim();
                String role = "seller";

                //Validation

                if (name1.isEmpty()) {
                    name.setError("Please Enter Username");
                    if(password1.isEmpty())
                    password.setError("Please Enter Password");
                    if(mail1.isEmpty())
                    mail.setError("Please Enter Mail");
                    if(shop1.isEmpty())
                    shop.setError("Please Enter Shop name");
                    if(contact1.isEmpty())
                    contact.setError("Please Enter Contact");
                    if(address1.isEmpty())
                    address.setError("Please Enter Address");
                    if(DOB1.isEmpty())
                    DOB.setError("Please Enter Date of Birth");
                    Toast.makeText(Signup_for_Seller.this, "These are required", Toast.LENGTH_SHORT).show();

                } else {

                    String pass2 = password2.getText().toString().trim();

                    if(password1.equals(pass2)) {

                        firebaseAuth.createUserWithEmailAndPassword(mail1, password1)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {


                                        firebaseFirestore.collection("Seller")
                                                .document(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                                                .set(new Seller_Model(name1, mail1, DOB1, shop1, contact1, address1, password1,role));

                                        Toast.makeText(Signup_for_Seller.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), Login_Activity.class));


                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Signup_for_Seller.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                    else{

                    }
                }

            }
        });
            }
    @Override
    public void onBackPressed() {

        startActivity(new Intent(this,Signup_as_a.class));
    }
        }
        


