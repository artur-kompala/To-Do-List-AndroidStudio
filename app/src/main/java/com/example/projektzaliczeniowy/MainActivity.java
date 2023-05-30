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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements SelectListener{

    public static ArrayList<Item> items = new ArrayList<>();
    public static boolean mode = false;
    public static Item editItem;
    TextView yearView;
    TextView dateView;
    RecyclerView recyclerView;
    Button button;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addItemButton();
        displayData();
        displayItems();
    }
    public void displayItems(){
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this,items,this);
        recyclerView.setAdapter(myAdapter);
    }
    public void displayData(){
        yearView = findViewById(R.id.yearView);
        dateView = findViewById(R.id.dateView);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat(" MMM dd E");
        Calendar c = Calendar.getInstance();
        String year = String.valueOf(c.get(Calendar.YEAR));
        String date = formatter.format(new Date());
        yearView.setText(year);
        dateView.setText(date);
    }

    public void openActivity2(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
    public void addItemButton(){
        button =  findViewById(R.id.button);
        mode = false;
        button.setOnClickListener(view -> openActivity2());
    }
    @Override
    public void onItemClicked(Item item) {
        mode = true;
        editItem = item;
        openActivity2();
    }

}