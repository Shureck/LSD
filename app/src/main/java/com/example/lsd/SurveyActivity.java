package com.example.lsd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class SurveyActivity extends AppCompatActivity {

    ArrayList<Product> products = new ArrayList();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        if(products.size()==0){
            products.add(new Product("Картофель"));
            products.add(new Product("Чай"));
            products.add(new Product("Яйца"));
            products.add(new Product("Молоко"));
            products.add(new Product("Макароны"));
        }
        recyclerView = (RecyclerView) findViewById(R.id.res);
        // создаем адаптер
        ResAdapter adapter = new ResAdapter(this, products);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);

    }
}