package com.example.buildx;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.buildx.Adapters.Adapter_Engineer;
import com.example.buildx.Adapters.MyAdapter;
import com.example.buildx.Models.Engineer_Model;
import com.example.buildx.Models.SellerList_Model;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class Engineer_frag extends Fragment {

    RecyclerView recview;
    ArrayList<Engineer_Model> datalist;
    FirebaseFirestore fstore;
    Adapter_Engineer adapter;


    public Engineer_frag() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View rootview =  inflater.inflate(R.layout.fragment_engineer_frag, container, false);

        CardView card = rootview.findViewById(R.id.engineer);
        CardView card2 = rootview.findViewById(R.id.contracter);

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Contracter.class));
            }
        });

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Engineer_2.class));
            }
        });


//        recview= rootview.findViewById(R.id.recview7);
//        recview.setLayoutManager(new LinearLayoutManager(getContext()));
////        datalist = new ArrayList<>();
////        adapter = new Adapter_Engineer(datalist);
////        recview.setAdapter(adapter);
//
//        fstore = FirebaseFirestore.getInstance();


//                            fstore.collection("Engineers").get()
//                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                                        @SuppressLint("NotifyDataSetChanged")
//                                        @Override
//                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//                                            List<DocumentSnapshot> list =  queryDocumentSnapshots.getDocuments();
//                                            for(DocumentSnapshot d:list){
//                                           Engineer_Model obj = d.toObject(Engineer_Model.class);
//                                            datalist.add(obj);
//
//                                            }
//                                            adapter.notifyDataSetChanged();
//
//                                        }
//                                    });

//        fstore.collection("Engineers")
//                .get()
//                .addOnCompleteListener(task -> {
//
//                        List<Engineer_Model> dataList = new ArrayList<>();
//                        for (QueryDocumentSnapshot document : task.getResult()) {
//                            Engineer_Model data = document.toObject(Engineer_Model.class);
//                            dataList.add(data);
//                        }
//                        Adapter_Engineer adapter = new Adapter_Engineer(datalist);
//                        recview.setAdapter(adapter);
//                });



        return rootview;
    }

}