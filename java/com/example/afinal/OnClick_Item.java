package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class OnClick_Item extends AppCompatActivity {

    TextView title, category, description, price, rate, count;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_click_item);


        title = findViewById(R.id.onclick_title);
        category = findViewById(R.id.onclick_category);
        description = findViewById(R.id.onclick_description);
        imageView = findViewById(R.id.onclick_image_view);
        price = findViewById(R.id.price);
        rate = findViewById(R.id.rate);
        count = findViewById(R.id.count);



        title.setText(getIntent().getExtras().getString("Title"));
        category.setText(getIntent().getExtras().getString("Category"));
        description.setText(getIntent().getExtras().getString("Description"));

        price.setText(getIntent().getExtras().getString("Price"));
        rate.setText(getIntent().getExtras().getString("Rate"));
        count.setText(getIntent().getExtras().getString("Count"));

        String image = getIntent().getStringExtra("Image");
        Glide.with(this).load(image).into(imageView);

    }
}