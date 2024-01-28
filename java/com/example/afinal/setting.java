package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class setting extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    SwitchCompat imagebtn, titlebtn, categorybtn, pricebtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        SharedPreferences sharedPreferences = getSharedPreferences("JavaApp", Context.MODE_PRIVATE);

        Boolean Image = sharedPreferences.getBoolean("Image",true);
        Boolean Title = sharedPreferences.getBoolean("Title",true);
        Boolean Category = sharedPreferences.getBoolean("Category",true);
        Boolean Price = sharedPreferences.getBoolean("Price",true);




        imagebtn = findViewById(R.id.image_switch_buton);
        titlebtn = findViewById(R.id.title_switch_buton);
        categorybtn = findViewById(R.id.category_switch_buton);
        pricebtn = findViewById(R.id.price_switch_buton);

        imagebtn.setChecked(Image);
        titlebtn.setChecked(Title);
        categorybtn.setChecked(Category);
        pricebtn.setChecked(Price);




        imagebtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("Image", true);
                    editor.apply();
                }
                else{
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("Image", false);
                    editor.apply();
                }
            }
        });


        titlebtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("Title", true);
                    editor.apply();
                }
                else{
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("Title", false);
                    editor.apply();
                }
            }
        });

        categorybtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("Category", true);
                    editor.apply();
                }
                else{
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("Category", false);
                    editor.apply();
                }
            }
        });

        pricebtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("Price", true);
                    editor.apply();
                }
                else{
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("Price", false);
                    editor.apply();
                }
            }
        });


        bottomNavigationView = findViewById(R.id.nav_bar_setting);

        bottomNavigationView.setSelectedItemId(R.id.setting_icon);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.home_icon){

                startActivity(new Intent(setting.this, MainActivity.class));
                finish();
                return true;

            } else if (item.getItemId() == R.id.favorite_icon) {

                startActivity(new Intent(setting.this, favorite.class));
                finish();
                return true;
            }

            return true;
        });


    }
}