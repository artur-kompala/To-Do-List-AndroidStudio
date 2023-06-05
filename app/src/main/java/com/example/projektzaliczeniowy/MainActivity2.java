package com.example.projektzaliczeniowy;


import static com.example.projektzaliczeniowy.MainActivity.editItem;
import static com.example.projektzaliczeniowy.MainActivity.items;
import static com.example.projektzaliczeniowy.MainActivity.mode;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.gson.Gson;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    MainActivity main;
    RadioGroup priorityGroup;
    Button save;
    Button delete;
    Button clip;
    Button file;
    Button trash;
    EditText taskInput;
    RadioButton radioButton;
    CalendarView calendarview;
    String endDate;
    String task;
    String addDate;
    String priority;
    Uri uri;
    String path;
    Boolean deletePath = false;
    private StorageVolume storageVolume;
    @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE},
                        PackageManager.PERMISSION_GRANTED);

        StorageManager storageManager = (StorageManager) getSystemService(STORAGE_SERVICE);
        List<StorageVolume> storageVolumes = storageManager.getStorageVolumes();
        storageVolume = storageVolumes.get(0);

        taskInput =  findViewById(R.id.editTextText);
        priorityGroup = findViewById(R.id.radiogroup);
        calendarview = findViewById(R.id.calendarView2);
        save = findViewById(R.id.save);
        clip = findViewById(R.id.clip);
        file = findViewById(R.id.file);
        trash = findViewById(R.id.trash);
        main = new MainActivity();

        if(mode == true){
            deleteItem();
            try {
                editItemView();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }







        endDate = formatter.format(new Date());

        save.setOnClickListener(view -> {


            addDate = formatter.format(new Date());

            task = taskInput.getText().toString();
            priority = checkButton();
            if(mode == true){
                updateItem();
            }
            if(mode == false){
                addNewItem();
            }
            saveData();
            openActivity1();

        });

        file.setOnClickListener(view -> {
            openImage(view);
        });
        clip.setOnClickListener(view -> {
            Intent myFileIntent = new Intent(Intent.ACTION_GET_CONTENT);
            myFileIntent.setType("*/*");
            startActivityForResult(myFileIntent,10);
            file.setText("Open File");

        });
        trash.setOnClickListener(view -> {
            addDate = formatter.format(new Date());
            task = taskInput.getText().toString();
            priority = checkButton();
            if(mode == true){
                path = null;
                updateItem();
            }
            saveData();
            openActivity1();
        });


        calendarview.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String  curDate = String.valueOf(dayOfMonth);
            String  Year = String.valueOf(year);
            String  Month = String.valueOf(month+1);
            endDate = curDate + "/" + Month + "/" + Year;
        });

    }
    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(items);
        editor.putString("task list",json);
        editor.apply();
    }

    public void openImage(View view){
        System.out.println(path);
        uri = Uri.parse(storageVolume.getDirectory() + "/"+ removeLeftSide(path));
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri,"*/*");
        startActivity(intent);
    }
    public static String removeLeftSide(String input) {
        int index = input.lastIndexOf(":");
        if (index != -1) {
            return input.substring(index + 1);
        } else {
            return input;
        }
    }
    String checkButton(){
        int radioId = priorityGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        return radioButton.getText().toString();
    }
    public void addNewItem(){
        items.add(new Item(addDate,endDate,task,priority,false,path));
    }
    public void updateItem(){
        int index = items.indexOf(editItem);
        items.set(index, new Item(addDate,endDate,task,priority,false,path));
    }
    public void editItemView() throws ParseException {
        taskInput.setText(editItem.getTask());
        calendarview.setDate(formatter.parse(editItem.getDateEnd()).getTime(),true,true);
        if(editItem.getPriority().equals("Low")){
            radioButton = findViewById(R.id.radioButton);
            radioButton.setChecked(true);
        }
        if(editItem.getPriority().equals("Medium")){
            radioButton = findViewById(R.id.radioButton2);
            radioButton.setChecked(true);
        }
        if(editItem.getPriority().equals("High")){
            radioButton = findViewById(R.id.radioButton3);
            radioButton.setChecked(true);
        }
        path = editItem.getPath();
        System.out.println(path);
        if(path == null){
            file.setVisibility(View.GONE);
        }else{
            trash.setVisibility(View.VISIBLE);
            file.setVisibility(View.VISIBLE);

        }
    }

    public void deleteItem(){
        delete =  findViewById(R.id.delete);
        delete.setVisibility(View.VISIBLE);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.remove(editItem);
                saveData();
                openActivity1();
            }
        });
    }

    public void openActivity1(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                path = data.getData().getPath();
            }
        }
    }
}