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
            products.add(new Product("Помогает ли Вам бесплатный общественный Wi-Fi?"));
            products.add(new Product("Упростил ли каршеринг Ваше передвижение по городу?"));
            products.add(new Product("Упрощают ли городские онлайн-сервисы ведение бизнеса?"));
            products.add(new Product("Упростила ли процесс получения медпомощи онлайн-запись к врачу?"));
            products.add(new Product("Онлайн-расписание транспорта упростило пользование им?"));
            products.add(new Product("Повышают ли дорожные камеры безопасность дорожного движения?"));
        }
        recyclerView = (RecyclerView) findViewById(R.id.res);
        // создаем адаптер
        ResAdapter adapter = new ResAdapter(this, products);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);

    }
}