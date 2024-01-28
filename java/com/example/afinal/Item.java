package com.example.afinal;

import android.content.ContentValues;

public class Item {

    String title;
    String category;
    String price;
    String rate;
    String count;
    String image;
    String description;

    public Item(String title, String category, String price, String image, String description, String rate, String count) {
        this.title = title;
        this.category = category;
        this.price = price;
        this.rate = rate;
        this.count = count;
        this.image = image;
        this.description = description;
    }
    private boolean isFavorite;

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public ContentValues Product(){
        ContentValues contentValues = new ContentValues();

        contentValues.put("image",image);
        contentValues.put("title",title);
        contentValues.put("category",category);
        contentValues.put("price",price);
        contentValues.put("description",description);

        return contentValues;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
