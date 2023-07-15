package com.example.buildx;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Objects;


public class Personal_Info extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userID;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_personal_info);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();


        Button Infoupdate = findViewById(R.id.Update);
        Button saveseller = findViewById(R.id.SaveSeller);
        Button save = findViewById(R.id.Save);
        TextInputEditText name = findViewById(R.id.name);
        TextInputEditText mail = findViewById(R.id.Email);
        TextInputEditText address = findViewById(R.id.address);
        TextInputEditText contact = findViewById(R.id.phone);
        TextInputEditText DOB =findViewById(R.id.DOB);
        TextInputEditText shop1 = findViewById(R.id.shop);

        shop1.setVisibility(View.GONE);


        userID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();

        DocumentReference documentReference = fstore.collection("Customer").document(userID);



        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {


                assert documentSnapshot != null;
                String role = documentSnapshot.getString("role");


                if("customer".equals(role)) {
                    shop1.setVisibility(View.GONE);
                    saveseller.setVisibility(View.GONE);

                    shop1.setEnabled(false);
                    name.setEnabled(false);
                    mail.setEnabled(false);
                    address.setEnabled(false);
                    contact.setEnabled(false);
                    DOB.setEnabled(false);


                    name.setText(documentSnapshot.getString("name"));
                    mail.setText(documentSnapshot.getString("email"));
                    address.setText(documentSnapshot.getString("address"));
                    contact.setText(documentSnapshot.getString("contact"));
                    DOB.setText(documentSnapshot.getString("dob"));
                }
                else {

                    DocumentReference documentReference2 = fstore.collection("Seller").document(userID);

                    documentReference2.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                            shop1.setVisibility(View.VISIBLE);
                            save.setVisibility(View.GONE);

                            shop1.setEnabled(false);
                            name.setEnabled(false);
                            mail.setEnabled(false);
                            address.setEnabled(false);
                            contact.setEnabled(false);
                            DOB.setEnabled(false);


                            assert documentSnapshot != null;
                            name.setText(documentSnapshot.getString("name"));
                            mail.setText(documentSnapshot.getString("email"));
                            address.setText(documentSnapshot.getString("address"));
                            contact.setText(documentSnapshot.getString("contact"));
                            DOB.setText(documentSnapshot.getString("dob"));
                            shop1.setText(documentSnapshot.getString("shop"));
                        }
                    });


                }

            }
        });


        //For update
        Infoupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(getApplicationContext(), "Now you can Update ", Toast.LENGTH_SHORT).show();

                shop1.setEnabled(true);
                name.setEnabled(true);
                mail.setEnabled(true);
                address.setEnabled(true);
                contact.setEnabled(true);
                DOB.setEnabled(true);
            }
        });

        //For saving info
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cname = Objects.requireNonNull(name.getText()).toString().trim();
                String cmail = Objects.requireNonNull(mail.getText()).toString().trim();
                String caddress = Objects.requireNonNull(address.getText()).toString().trim();
                String ccontact = Objects.requireNonNull(contact.getText()).toString().trim();
                String cdob= Objects.requireNonNull(DOB.getText()).toString().trim();

                fstore.collection("Customer").document(userID)
                        .update("name",cname,"email",cmail,"contact",ccontact,"dob",cdob,"address",caddress)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                            }
                        });

                name.setEnabled(false);
                mail.setEnabled(false);
                address.setEnabled(false);
                contact.setEnabled(false);
                DOB.setEnabled(false);


            }
        });

        //for saving seller's data
        saveseller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cname = Objects.requireNonNull(name.getText()).toString().trim();
                String cshop = Objects.requireNonNull(shop1.getText()).toString().trim();
                String cmail = Objects.requireNonNull(mail.getText()).toString().trim();
                String caddress = Objects.requireNonNull(address.getText()).toString().trim();
                String ccontact = Objects.requireNonNull(contact.getText()).toString().trim();
                String cdob= Objects.requireNonNull(DOB.getText()).toString().trim();

                fstore.collection("Seller").document(userID)
                        .update("name",cname,"shop",cshop,"email",cmail,"contact",ccontact,"dob",cdob,"address",caddress)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                            }
                        });


                shop1.setEnabled(false);
                name.setEnabled(false);
                mail.setEnabled(false);
                address.setEnabled(false);
                contact.setEnabled(false);
                DOB.setEnabled(false);

            }
        });



    }
}