package com.example.projektzaliczeniowy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
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
            items.get(holder.getAdapterPosition()).setStatus(true);

        }else{
            holder.statusView.setPaintFlags(holder.statusView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            items.get(holder.getAdapterPosition()).setStatus(false);
        }

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.statusView.setText(items.get(position).getTask());
        holder.dateAddedView.setText("Dodano:"+ items.get(position).getDateAdded());
        holder.dateEndView.setText("Termin: "+ items.get(position).getDateEnd());
        holder.priorityView.setText(items.get(position).getPriority());
        holder.statusView.setChecked(items.get(position).getStatus());

        check(holder);


        holder.statusView.setOnCheckedChangeListener((compoundButton, b) -> check(holder));


    }


    @Override
    public int getItemCount() {
        return items.size();
    }


}
