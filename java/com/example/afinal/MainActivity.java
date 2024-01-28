package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Dao;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import kotlin.experimental.ExperimentalTypeInference;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    FloatingActionButton floatingActionButton;
    public static Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences SharedPreferences = getSharedPreferences("JavaApp", Context.MODE_PRIVATE);





        database=Room.databaseBuilder(getApplicationContext(),Database.class,"PRODUCT").build();

        new DownloadFilesTask().execute();

        bottomNavigationView = findViewById(R.id.nav_bar_home);

        bottomNavigationView.setSelectedItemId(R.id.home_icon);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.favorite_icon){

                Intent intent = new Intent(MainActivity.this, favorite.class);
                startActivity(intent);
                finish();
                return true;

            } else if (item.getItemId() == R.id.setting_icon) {

                Intent intent = new Intent(MainActivity.this, setting.class);
                startActivity(intent);
                finish();
                return true;
            }

            return true;
        });

        EditText searchEditText = findViewById(R.id.search_bar);

        floatingActionButton = findViewById(R.id.floating_action_button);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchEditText.getVisibility() == View.VISIBLE){
                    searchEditText.setVisibility(View.GONE);
                } else if (searchEditText.getVisibility() == View.GONE) {
                    searchEditText.setVisibility(View.VISIBLE);
                }
            }
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                myAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().isEmpty()) {
                    myAdapter.getFilter().filter(null);
                }
            }
        });

    }

    private class DownloadFilesTask extends AsyncTask<Void, Void, List<Item>> {
        TextView t;
        @Override
        protected List<Item> doInBackground(Void... voids) {

            try {
                HttpURLConnection connection = null;
                URL url = new URL("https://fakestoreapi.com/products");
                connection = (HttpURLConnection)url.openConnection();
                int response = connection.getResponseCode();
                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                String s = builder.toString();
                JSONArray jo = new JSONArray(s);


                List<Item> items=new ArrayList<Item>();
                for (int i=0; i<jo.length();i++) {
                    JSONObject product = jo.getJSONObject(i);
                    String title=product.getString("title");
                    String category=product.getString("category");

                    String price= product.getString("price");
                    String rate= product.getJSONObject("rating").getString("rate");
                    String count= product.getJSONObject("rating").getString("count");
                    String imageurl=product.getString("image");
                    String description = product.getString("description");
                    items.add(new Item(title,category,price,imageurl, description, rate, count));
                }


                return items;
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        protected void onPostExecute(List<Item> items) {
            recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            myAdapter = new MyAdapter(getApplicationContext(), items);
            recyclerView.setAdapter(myAdapter);

        }
    }

    public void recycler(List<Item> items){
        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));
    }
}