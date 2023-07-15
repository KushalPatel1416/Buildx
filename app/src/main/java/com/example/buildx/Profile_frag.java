package com.example.buildx;

import static android.content.Context.MODE_PRIVATE;

import static java.sql.Types.NULL;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buildx.Seller.SellerOf;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.nio.charset.StandardCharsets;
import java.util.Objects;



public class Profile_frag extends Fragment {

    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userID;

    public Profile_frag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_profile_frag, container, false);
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();


        Button logout = rootview.findViewById(R.id.logout);

        CardView personal = rootview.findViewById(R.id.personaldet);
        CardView sells = rootview.findViewById(R.id.sellerOf);
        CardView material = rootview.findViewById(R.id.material);


        userID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();

        DocumentReference documentReference = fstore.collection("Customer").document(userID);

        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

//
                    assert documentSnapshot != null;
                    String role = documentSnapshot.getString("role");

                    if("customer".equals(role)) {
                      sells.setVisibility(View.GONE);
                      material.setVisibility(View.GONE);
                    }
                    else {


                    }

                }
            });

        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),Personal_Info.class);
                startActivity(intent);

            }
        });

        sells.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), SellerOf.class));

            }
        });
        material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });



        //For Logout
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preference = requireActivity().getSharedPreferences("login",MODE_PRIVATE);
                SharedPreferences.Editor edit = preference.edit();

                edit.putBoolean("flag",false);
                edit.apply();

                startActivity(new Intent(getActivity(),Login_Activity.class));

            }
        });
        return rootview;



    }
}