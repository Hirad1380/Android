package com.example.afinal;

import static android.content.Intent.getIntent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;

import javax.security.auth.Destroyable;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView title, category, price, description, rate, count, Nothing;
    ImageButton moreButton,sharebutton, favButton;
    Context context;

    @SuppressLint("ClickableViewAccessibility")
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        this.context=itemView.getContext();

        imageView = itemView.findViewById(R.id.image_view);
        title = itemView.findViewById(R.id.title);
        category = itemView.findViewById(R.id.category);
        price = itemView.findViewById(R.id.price);
        rate = itemView.findViewById(R.id.rate);
        count = itemView.findViewById(R.id.count);
        description = itemView.findViewById(R.id.description);
        Nothing = itemView.findViewById(R.id.notihng);
        moreButton = itemView.findViewById(R.id.more_button);
        sharebutton=itemView.findViewById(R.id.share_button);
        favButton = itemView.findViewById(R.id.favorite_button);

        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (description.getVisibility() == View.VISIBLE){
                    description.setVisibility(View.GONE);
                    moreButton.setColorFilter(Color.BLACK);
                }else if (description.getVisibility() == View.GONE){
                    description.setVisibility(View.VISIBLE);
                    moreButton.setColorFilter(Color.RED);
                }
            }
        });

        sharebutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    sharebutton.setColorFilter(Color.BLACK);
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    sharebutton.setColorFilter(Color.RED);
                    Sendsms(title.getText().toString(),price.getText().toString(), category.getText().toString());
                }
                return false;
            }
        });

    }
    public void Sendsms(String title,String price, String category){

        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("smsto:"+"222222"));
        intent.putExtra("sms_body","Title: " + title + " Category: " + category + " Price: $" + price);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
    }
}
