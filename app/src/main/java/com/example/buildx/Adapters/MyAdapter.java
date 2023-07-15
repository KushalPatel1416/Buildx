package com.example.buildx.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildx.R;

import java.util.ArrayList;

import com.example.buildx.Models.SellerList_Model;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myviewholder>{

    ArrayList<SellerList_Model> datalist;

    public MyAdapter(ArrayList<SellerList_Model> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        return  new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.name.setText(datalist.get(position).getName());
        holder.contact.setText(datalist.get(position).getContact());
        holder.address.setText(datalist.get(position).getAddress());
        holder.shop.setText(datalist.get(position).getShop());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView name,contact,address,shop;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.lname);
            contact = itemView.findViewById(R.id.lcontact);
            address = itemView.findViewById(R.id.laddress);
            shop = itemView.findViewById(R.id.lshop);
        }
    }
}
