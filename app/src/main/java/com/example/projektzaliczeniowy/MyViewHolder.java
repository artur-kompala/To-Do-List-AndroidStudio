package com.example.projektzaliczeniowy;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder  extends RecyclerView.ViewHolder {
    TextView dateAddedView;
    TextView dateEndView;
    TextView priorityView;
    CheckBox statusView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        dateAddedView = itemView.findViewById(R.id.dateAdded);
        dateEndView = itemView.findViewById(R.id.dateEnd);
        priorityView = itemView.findViewById(R.id.priority);
        statusView = itemView.findViewById(R.id.status);

    }
}
