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
    public void check(MyViewHolder holder){
        if(holder.statusView.isChecked()){
            holder.statusView.setPaintFlags(holder.statusView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            holder.statusView.setPaintFlags(holder.statusView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.statusView.setText(items.get(position).getTask());
        holder.dateAddedView.setText("Dodano:"+ items.get(position).getDateAdded());
        holder.dateEndView.setText("Termin: "+ items.get(position).getDateEnd());
        holder.priorityView.setText(items.get(position).getPriority());
        holder.statusView.setChecked(items.get(position).getStatus());

        check(holder);


        holder.statusView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    check(holder);
            }
        });


    }


    @Override
    public int getItemCount() {
        return items.size();
    }


}
