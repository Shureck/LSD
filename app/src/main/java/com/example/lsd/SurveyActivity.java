package com.example.lsd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SurveyActivity extends AppCompatActivity {

    ArrayList<Product> products = new ArrayList();
    RecyclerView recyclerView;
    private final OkHttpClient client = new OkHttpClient();
    String str;

    class IOAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return sendData(params[0]);
        }

        @Override
        protected void onPostExecute(String response) {
            str = response;
            Log.d("DDD",response);
        }
    }

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

        StringBuilder stringBuilder = new StringBuilder();

//        new IOAsyncTask().execute("mutation {" +
//                "createDatum(input: {datum: {" +
//                "c1:"+  1 +"," +
//                "c2: 3" +
//                "}}) {" +
//                "datum {" +
//                "id" +
//                "}" +
//                "}" +
//                "}");
    }

    public String sendData(String str){
        ObjectMapper om = new ObjectMapper();
        try {
            ObjectNode on = om.createObjectNode();
            on.put("query", str);
            on.put("variables", om.createObjectNode());

            Log.d("writeValueAsBytes", om.writeValueAsString(on));

            Request request = new Request.Builder()
                    .url("http://192.168.236.14:5000/graphql")
                    .post(RequestBody.create(om.writeValueAsBytes(on), MediaType.parse("application/json; charset=utf-8")))
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}