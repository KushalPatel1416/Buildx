package com.example.buildx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.buildx.Adapters.Adapter_Engineer;
import com.example.buildx.Models.Engineer_Model;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import com.example.buildx.Adapters.MyAdapter;
import com.example.buildx.Models.SellerList_Model;

public class Engineer_2 extends AppCompatActivity {

    RecyclerView recview;
    ArrayList<Engineer_Model> datalist;
    FirebaseFirestore fstore;
    Adapter_Engineer adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engineer2);

        recview=findViewById(R.id.recview8);
        recview.setLayoutManager(new LinearLayoutManager(this));
        datalist = new ArrayList<>();
        adapter = new Adapter_Engineer(datalist);
        recview.setAdapter(adapter);

        fstore = FirebaseFirestore.getInstance();


                            fstore.collection("Engineer").get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @SuppressLint("NotifyDataSetChanged")
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                            List<DocumentSnapshot> list =  queryDocumentSnapshots.getDocuments();
                                            for(DocumentSnapshot d:list){
                                           Engineer_Model obj = d.toObject(Engineer_Model.class);
                                            datalist.add(obj);

                                            }
                                            adapter.notifyDataSetChanged();

                                        }
                                    });


    }
}
