package com.example.projektzaliczeniowy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView yearView = findViewById(R.id.yearView);
        TextView dateView = findViewById(R.id.dateView);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });




        SimpleDateFormat formater = new SimpleDateFormat(" MMM dd E");
        Calendar c = Calendar.getInstance();
        String year = String.valueOf(c.get(Calendar.YEAR));
        String date = formater.format(new Date());

        yearView.setText(year);
        dateView.setText(date);



        List<Item> items = new ArrayList<Item>();
        items.add(new  Item("26/12/2001","12/12/2005","Kupić mleko","High",true));
        items.add(new Item("01/01/2023", "31/12/2023", "Przeczytać nową książkę", "Medium", false));
        items.add(new Item("15/06/2023", "30/06/2023", "Zaplanować wakacyjny wyjazd", "High", false));
        items.add(new Item("01/03/2023", "31/03/2023", "Zorganizować urodzinową imprezę", "High", false));
        items.add(new Item("10/07/2023", "15/07/2023", "Napisać list do przyjaciela", "Low", false));
        items.add(new Item("01/05/2023", "07/05/2023", "Zacząć nowy kurs online", "Medium", false));
        items.add(new Item("15/04/2023", "30/04/2023", "Przeprowadzić wiosenne porządki", "Medium", false));
        items.add(new Item("01/08/2023", "31/08/2023", "Zacząć regularne treningi", "High", false));
        items.add(new Item("15/09/2023", "30/09/2023", "Zaplanować spotkanie biznesowe", "High", false));
        items.add(new Item("01/11/2023", "30/11/2023", "Udekorować dom na Święta", "High", false));
        items.add(new Item("15/10/2023", "31/10/2023", "Zapisać się na kurs gotowania", "Low", false));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));


    }
    public void openActivity2(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    };
}