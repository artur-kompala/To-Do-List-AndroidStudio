package com.example.projektzaliczeniowy;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder  extends RecyclerView.ViewHolder {
    TextView dateAddedView;
    TextView dateEndView;
    TextView taskView;
    TextView priorityView;
    CheckBox statusView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        dateAddedView = itemView.findViewById(R.id.dateAdded);
        dateEndView = itemView.findViewById(R.id.dateEnd);
        taskView = itemView.findViewById(R.id.task);
        priorityView = itemView.findViewById(R.id.priority);
        statusView = itemView.findViewById(R.id.status);

    }
}
