package com.example.buildx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Signup_as_a extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_as);

        Button customer = findViewById(R.id.customer);
        Button seller = findViewById(R.id.seller);

        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(Signup_as_a.this,Signup_for_Customer.class);
                startActivity(intent);
                finish();
            }
        });

        seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(Signup_as_a.this,Signup_for_Seller.class);
                startActivity(intent);
                finish();

            }
        });


    }
    @Override
    public void onBackPressed() {

        startActivity(new Intent(this,Login_Activity.class));
    }
}