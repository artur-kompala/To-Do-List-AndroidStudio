package com.example.projektzaliczeniowy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

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
}