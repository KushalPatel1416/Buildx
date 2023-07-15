package com.example.buildx.Seller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.buildx.MainActivity;
import com.example.buildx.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SellerOf extends AppCompatActivity {

    Spinner spinner,spinner2,spinner3,spinner4,spinner5,spinner6;

    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_of);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        userID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();


        spinner = findViewById(R.id.spin);
        spinner2 = findViewById(R.id.spin2);
        spinner3 = findViewById(R.id.spin3);
        spinner4 = findViewById(R.id.spin4);
        spinner5 = findViewById(R.id.spin5);
        spinner6 = findViewById(R.id.spin6);
        Button save = findViewById(R.id.Save);


        ArrayList<String> array = new ArrayList<>();

        array.add("NO");
        array.add("YES");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,array);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        spinner4.setAdapter(adapter);
        spinner5.setAdapter(adapter);
        spinner6.setAdapter(adapter);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String steel = spinner.getSelectedItem().toString();
                String bricks = spinner2.getSelectedItem().toString();
                String aggregate = spinner3.getSelectedItem().toString();
                String cement = spinner4.getSelectedItem().toString();
                String wood = spinner5.getSelectedItem().toString();
                String sand = spinner6.getSelectedItem().toString();

                Map<String,String> data = new HashMap<>();
                data.put("steel",steel);
                data.put("bricks",bricks);
                data.put("aggregate",aggregate);
                data.put("cement",cement);
                data.put("wood",wood);
                data.put("sand",sand);

                DocumentReference documentReference = fstore.collection("Seller").document(userID);

                documentReference.set(data, SetOptions.merge())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(SellerOf.this, "Successfully Saved", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(SellerOf.this, Profile_frag.class);
//                                startActivity(intent);
//                                finish();
                             sharedpreference2();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });

                sharedpreference();


            }
        });

    }


    private void sharedpreference(){

        SharedPreferences preference = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor edit = preference.edit();

        edit.putBoolean("flag",true);
        edit.apply();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }

    private void sharedpreference2(){

        SharedPreferences preference = getSharedPreferences("sell",MODE_PRIVATE);
        SharedPreferences.Editor edit = preference.edit();

        edit.putBoolean("flag",false);
        edit.apply();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }

}