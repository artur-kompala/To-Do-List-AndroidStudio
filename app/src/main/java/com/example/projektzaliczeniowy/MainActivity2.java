package com.example.projektzaliczeniowy;

import static com.example.projektzaliczeniowy.MainActivity.items;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class MainActivity2 extends AppCompatActivity {
    MainActivity main;
    RadioGroup priorityGroup;
    Button save;
    Button delete;
    EditText taskInput;
    RadioButton radioButton;
    CalendarView calendarview;
    String endDate;
    String task;
    String addDate;
    String priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        main = new MainActivity();
        save = findViewById(R.id.save);
        delete =  findViewById(R.id.delete);
        taskInput =  findViewById(R.id.editTextText);
        priorityGroup = findViewById(R.id.radiogroup);
        calendarview = findViewById(R.id.calendarView2);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        endDate = formatter.format(new Date());

        save.setOnClickListener(view -> {


            addDate = formatter.format(new Date());

            task = taskInput.getText().toString();
            priority = checkButton();

            items.add(new  Item(addDate, endDate,task,priority, false));
            openActivity1();

        });

        calendarview.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String  curDate = String.valueOf(dayOfMonth);
            String  Year = String.valueOf(year);
            String  Month = String.valueOf(month);
            endDate = curDate + "/" + Month + "/" + Year;
        });

    }
    String checkButton(){
        int radioId = priorityGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        return radioButton.getText().toString();
    }

    public void openActivity1(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}