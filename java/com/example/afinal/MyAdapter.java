package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

//import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> implements Filterable{

    Context context;
    List<Item> items;
    List<Item> itemscopy;

    public MyAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
        this.itemscopy = new ArrayList<>(items);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("JavaApp",Context.MODE_PRIVATE);
        Boolean ImageBool = sharedPreferences.getBoolean("Image",true);
        Boolean TitleBool = sharedPreferences.getBoolean("Title",true);
        Boolean CategoryBool = sharedPreferences.getBoolean("Category",true);
        Boolean PriceBool = sharedPreferences.getBoolean("Price",true);

        String Title=String.valueOf(items.get(position).getTitle());
        String Category=String.valueOf(items.get(position).getCategory());
        String Imageurl=String.valueOf(items.get(position).getImage());
        String Price = String.valueOf(items.get(position).getPrice());
        String Rate = String.valueOf(items.get(position).getRate());
        String Count = String.valueOf(items.get(position).getCount());
        String Description = String.valueOf(items.get(position).getDescription());


        if(!ImageBool){
            holder.imageView.setVisibility(View.GONE);
        }
        if(!TitleBool){
            holder.title.setVisibility(View.GONE);
        }
        if(!CategoryBool){
            holder.category.setVisibility(View.GONE);
        }
        if(!PriceBool){
            holder.price.setVisibility(View.GONE);
            holder.Nothing.setVisibility(View.GONE);
        }


        final Item item = items.get(position);

        holder.title.setText(items.get(position).getTitle());
        holder.category.setText(items.get(position).getCategory());
        holder.price.setText(items.get(position).getPrice());
        holder.rate.setText(items.get(position).getRate());
        holder.count.setText(items.get(position).getCount());
        holder.description.setText(items.get(position).getDescription());

        Glide.with(context).load(items.get(position).getImage()).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), OnClick_Item.class);
                intent.putExtra("Title",item.getTitle());
                intent.putExtra("Category",item.getCategory());
                intent.putExtra("Description",item.getDescription());
                intent.putExtra("Price",item.getPrice());
                intent.putExtra("Rate",item.getRate());
                intent.putExtra("Count",item.getCount());
                intent.putExtra("Image",item.getImage());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                v.getContext().startActivity(intent);

            }
        });


        holder.favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.favButton.setColorFilter(Color.RED);


                Entity newitem=new Entity();
                newitem.title=Title;
                newitem.category=Category;
                newitem.image= Imageurl;
                newitem.price = Price;
                newitem.rate = Rate;
                newitem.count = Count;
                newitem.description = Description;

                new InsertAsync(MainActivity.database.dao()).execute(newitem);

            }
        });

    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    static class InsertAsync extends AsyncTask<Entity,Void,Void>{
        private DAO d;
        public InsertAsync(DAO d) {
            this.d=d;
        }

        @Override
        protected Void doInBackground(Entity... entities) {
            d.insert(entities[0]);
            return null;
        }
    };

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String filterPattern = constraint.toString().toLowerCase().trim();

                FilterResults results = new FilterResults();
                List<Item> filteredList = new ArrayList<>();

                if (TextUtils.isEmpty(filterPattern)) {
                    filteredList.addAll(itemscopy);
                } else {
                    for (Item item: itemscopy){
                        if (item.getTitle().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results.values != null) {
                    items.clear();
                    items.addAll((List<Item>) results.values);
                    notifyDataSetChanged();
                }
            }
        };
    }
}
