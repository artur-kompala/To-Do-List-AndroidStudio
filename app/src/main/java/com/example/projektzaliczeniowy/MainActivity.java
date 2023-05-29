package com.example.projektzaliczeniowy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Item> items = new ArrayList<>();
    TextView yearView;
    TextView dateView;
    RecyclerView recyclerView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yearView = findViewById(R.id.yearView);
        dateView = findViewById(R.id.dateView);
        recyclerView = findViewById(R.id.recyclerview);
        button =  findViewById(R.id.button);

        button.setOnClickListener(view -> openActivity2());

        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat(" MMM dd E");
        Calendar c = Calendar.getInstance();
        String year = String.valueOf(c.get(Calendar.YEAR));
        String date = formatter.format(new Date());

        yearView.setText(year);
        dateView.setText(date);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));
    }

    public void openActivity2(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

}