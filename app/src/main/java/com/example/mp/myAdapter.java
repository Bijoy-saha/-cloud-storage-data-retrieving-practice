package com.example.mp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.MyViewHolder> {
    private MainActivity2 activity2;
    private List<model> mList;
    public myAdapter(MainActivity2 activity2,List<model>mList)
    {
        this.activity2=activity2;
        this.mList=mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(activity2).inflate(R.layout.item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(mList.get(position).getName());
        holder.email.setText(mList.get(position).getEmail());
        holder.description.setText(mList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,email,description;

     public MyViewHolder(@NonNull View itemView) {
         super(itemView);
         name=itemView.findViewById(R.id.nametext);
         email=itemView.findViewById(R.id.mailtext);
         description=itemView.findViewById(R.id.destext);
     }
 }
}
