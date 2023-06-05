package com.example.projektzaliczeniowy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
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
    Context context;
    private ReminderNotificationHelper notificationHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        loadData();
        addItemButton();
        displayData();
        displayItems();
        notificationHelper = new ReminderNotificationHelper(MainActivity.this);
        notificationHelper.generateReminderNotificationForToday();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.sort_date_asc){
            Toast.makeText(this, "Sort Date End Ascending", Toast.LENGTH_SHORT).show();
            Collections.sort(items,Item.DateAscComparator);
            myAdapter.notifyDataSetChanged();
        }else
            if(id == R.id.sort_status_done){
                Toast.makeText(this, "Sorted by status done", Toast.LENGTH_SHORT).show();
                Collections.sort(items,Item.StatusDoneComparator);
                myAdapter.notifyDataSetChanged();
            }else
            if(id == R.id.sort_status_notdone){
                Toast.makeText(this, "Sorted by status not done", Toast.LENGTH_SHORT).show();
                Collections.sort(items,Item.StatusNotDoneComparator);
                myAdapter.notifyDataSetChanged();
            }else
                if(id == R.id.sort_task_az){
                    Toast.makeText(this, "Sorted by task A-Z", Toast.LENGTH_SHORT).show();
                    Collections.sort(items,Item.TaskAZComparator);
                    myAdapter.notifyDataSetChanged();
                }else
                    if(id == R.id.sort_task_za){
                        Toast.makeText(this, "Sorted by task Z-A", Toast.LENGTH_SHORT).show();
                        Collections.sort(items,Item.TaskZAComparator);
                        myAdapter.notifyDataSetChanged();
                    }else
                    if(id == R.id.sort_date_des){
                        Toast.makeText(this, "Sort Date End Descending", Toast.LENGTH_SHORT).show();
                        Collections.sort(items,Item.DateDesComparator);
                        myAdapter.notifyDataSetChanged();
                    }
        return super.onOptionsItemSelected(item);
    }
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list",null);
        Type type = new TypeToken<ArrayList<Item>>() {}.getType();
        items = gson.fromJson(json,type);
        if(items == null){
            items = new ArrayList<>();
        }
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