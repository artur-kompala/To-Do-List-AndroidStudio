package com.example.projektzaliczeniowy;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder  extends RecyclerView.ViewHolder {
    TextView dateAddedView;
    TextView dateEndView;
    TextView taskView;
    TextView priorityView;
    TextView statusView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        dateAddedView = itemView.findViewById(R.id.dateAdded);
        dateEndView = itemView.findViewById(R.id.dateEnd);
        taskView = itemView.findViewById(R.id.task);
        priorityView = itemView.findViewById(R.id.priority);

    }
}
