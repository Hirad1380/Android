package com.example.afinal;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder_favorite extends RecyclerView.ViewHolder{

    ImageView imageView, favbutton;
    TextView title, category, price, count, rate, description;
    Context context;

    public MyViewHolder_favorite(@NonNull View itemView) {
        super(itemView);
        this.context=itemView.getContext();

        imageView = itemView.findViewById(R.id.image_view);
        title = itemView.findViewById(R.id.title);
        category = itemView.findViewById(R.id.category);
        price = itemView.findViewById(R.id.price);
        rate = itemView.findViewById(R.id.rate);
        count = itemView.findViewById(R.id.count);
        description = itemView.findViewById(R.id.description);
        favbutton = itemView.findViewById(R.id.favorite_button);



    }
}
