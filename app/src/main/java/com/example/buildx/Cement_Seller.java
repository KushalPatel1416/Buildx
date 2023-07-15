package com.example.buildx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

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

import com.example.buildx.Adapters.MyAdapter;
import com.example.buildx.Models.SellerList_Model;

public class Cement_Seller extends AppCompatActivity {

    RecyclerView recview;
    ArrayList<SellerList_Model> datalist;
    FirebaseFirestore fstore;
    MyAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cement_seller);

        recview=findViewById(R.id.recview2);
        recview.setLayoutManager(new LinearLayoutManager(this));
        datalist = new ArrayList<>();
        adapter = new MyAdapter(datalist);
        recview.setAdapter(adapter);

        fstore = FirebaseFirestore.getInstance();

        CollectionReference collectionReference = fstore.collection("Seller");
        Query query = collectionReference.orderBy("cement");

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        String bricks = document.getString("cement");

                        if(bricks.equals("YES")){

                            fstore.collection("Seller").get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @SuppressLint("NotifyDataSetChanged")
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                            List<DocumentSnapshot> list =  queryDocumentSnapshots.getDocuments();
//                                            for(DocumentSnapshot d:list){
                                            SellerList_Model obj = document.toObject(SellerList_Model.class);
                                            datalist.add(obj);

//                                            }
                                            adapter.notifyDataSetChanged();

//

                                        }
                                    });

                        }

                    }
                }

            }
        });








    }
}
