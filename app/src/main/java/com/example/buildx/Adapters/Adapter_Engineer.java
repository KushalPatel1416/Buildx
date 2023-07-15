package com.example.buildx.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildx.R;

import java.util.ArrayList;

import com.example.buildx.Models.Engineer_Model;

public class Adapter_Engineer extends RecyclerView.Adapter<Adapter_Engineer.viewholder>{

    ArrayList<Engineer_Model> datalist;

    public Adapter_Engineer(ArrayList<Engineer_Model> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_engineer,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.name.setText(datalist.get(position).getName());
        holder.contact.setText(datalist.get(position).getContact());
        holder.experience.setText(datalist.get(position).getExperience());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class viewholder extends RecyclerView.ViewHolder
    {
        TextView name,contact,experience;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.Ename);
            contact = (TextView)itemView.findViewById(R.id.Econtact);
            experience = (TextView)itemView.findViewById(R.id.Eexperience);
        }
    }
}
