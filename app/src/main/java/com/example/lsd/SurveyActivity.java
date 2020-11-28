package com.example.lsd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class SurveyActivity extends AppCompatActivity {

    ArrayList<Product> products = new ArrayList();
    ListView productList;

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
        productList = (ListView) findViewById(R.id.list);
        Adapter adapter = new Adapter(this, R.layout.listitem, products);
        productList.setAdapter(adapter);

    }
}