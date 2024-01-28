package com.example.afinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter_favorite extends RecyclerView.Adapter<MyViewHolder_favorite>{

    Context context;
    List<Item> items;

    private DAO d;


    public Item getItem(int position){
        return items.get(position);
    }

    public MyAdapter_favorite(Context context, List<Item> items,DAO d) {
        this.context = context;
        this.items = items;
        this.d=d;

    }

    @NonNull
    @Override
    public MyViewHolder_favorite onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder_favorite(LayoutInflater.from(context).inflate(R.layout.delete_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_favorite holder, int position) {

        String item=items.get(position).getTitle();
        holder.title.setText(items.get(position).getTitle());
        holder.category.setText(items.get(position).getCategory());
        holder.price.setText(items.get(position).getPrice());
        holder.count.setText(items.get(position).getCount());
        holder.rate.setText(items.get(position).getRate());
        holder.description.setText(items.get(position).getDescription());
        Glide.with(context).load(items.get(position).getImage()).into(holder.imageView);

        holder.itemView.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DeleteTask(holder.getAdapterPosition(), item).execute();
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    private class DeleteTask extends AsyncTask<Void, Void, Void> {
        private int position;
        private String itemTitle;

        public DeleteTask(int position, String itemTitle) {
            this.position = position;
            this.itemTitle = itemTitle;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            d.Deletbytitle(itemTitle);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            items.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, items.size());
        }
    }
}
