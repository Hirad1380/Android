package com.example.afinal;


import androidx.room.PrimaryKey;

@androidx.room.Entity(tableName = "Product")
public class Entity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String category;
    public String image;
    public String price;
    public String rate;
    public String count;
    public String description;

}
