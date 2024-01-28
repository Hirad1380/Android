package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class favorite extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);


        new RecyclerDatabase().execute();
        bottomNavigationView = findViewById(R.id.nav_bar_favorite);

        bottomNavigationView.setSelectedItemId(R.id.favorite_icon);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.home_icon){

                startActivity(new Intent(favorite.this, MainActivity.class));
                finish();
                return true;

            } else if (item.getItemId() == R.id.setting_icon) {

                startActivity(new Intent(favorite.this, setting.class));
                finish();
                return true;
            }

            return true;
        });







    }

    private class RecyclerDatabase extends AsyncTask<Void,Void,List<Item>> {

        @Override
        protected List<Item> doInBackground(Void... voids) {

            Database db = Room.databaseBuilder(getApplicationContext(),Database.class,"PRODUCT").build();
            List<Entity> items=db.dao().getAllItems();

            List<Item> l=new ArrayList<Item>();
            for (Entity i:items){

                Item s=new Item(i.title,i.category,i.price, i.image, i.rate, i.count, i.description);
                l.add(s);
            }

            return l;
        }

        @Override
        protected void onPostExecute(List<Item> items) {
            recycler(items);
        }
    };

    public void recycler(List<Item> items){
        Database db= Room.databaseBuilder(getApplicationContext(),Database.class,"PRODUCT").build();
        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter_favorite(getApplicationContext(),items, db.dao()));
    }
}