package com.example.buildx.Seller;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.buildx.Aggregate_Seller;
import com.example.buildx.Bricks_Seller;
import com.example.buildx.Cement_Seller;
import com.example.buildx.R;
import com.example.buildx.Sand_Seller;
import com.example.buildx.Steel_Sellers;
import com.example.buildx.Wood_Seller;


public class Home_frag2 extends Fragment {


    public Home_frag2() {

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

             startActivity(new Intent(getActivity(), Steel_Sellers.class));

            }
        });

        Wood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            startActivity(new Intent(getActivity(), Wood_Seller.class));

            }
        });

        Cement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Cement_Seller.class));


            }
        });

        Aggregate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), Aggregate_Seller.class));

            }
        });

        Bricks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), Bricks_Seller.class));
            }
        });

        Sand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), Sand_Seller.class));

            }
        });

        return  view;
    }


}

