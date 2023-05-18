package com.example.projektzaliczeniowy;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Item> items;

    public MyAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.taskView.setText(items.get(position).getTask());
        holder.dateAddedView.setText(items.get(position).getDateAdded());
        holder.dateEndView.setText(items.get(position).getDateEnd());
        holder.priorityView.setText(items.get(position).getPriority());
        holder.statusView.setChecked(items.get(position).getStatus());


        holder.statusView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(holder.statusView.isChecked() == true){
                    holder.taskView.setPaintFlags(holder.taskView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }else{
                    holder.taskView.setPaintFlags(holder.taskView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                }

            }
        });


    }


    @Override
    public int getItemCount() {
        return items.size();
    }
}
