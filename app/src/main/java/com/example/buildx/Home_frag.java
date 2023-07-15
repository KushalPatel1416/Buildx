package com.example.buildx;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class Home_frag extends Fragment {


    public Home_frag() {

        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home_frag, container, false);

        CardView Steel = view.findViewById(R.id.Steel);
        CardView Wood = view.findViewById(R.id.Wood);
        CardView Cement = view.findViewById(R.id.Cement);
        CardView Sand = view.findViewById(R.id.Sand);
        CardView Bricks = view.findViewById(R.id.Bricks);
        CardView Aggregate = view.findViewById(R.id.Aggregate);


        Steel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             startActivity(new Intent(getActivity(),Steel_Sellers.class));

            }
        });

        Wood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            startActivity(new Intent(getActivity(),Wood_Seller.class));

            }
        });

        Cement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),Cement_Seller.class));


            }
        });

        Aggregate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),Aggregate_Seller.class));

            }
        });

        Bricks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),Bricks_Seller.class));
            }
        });

        Sand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),Sand_Seller.class));

            }
        });

        return  view;
    }


}

