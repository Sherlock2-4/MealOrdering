package com.example.mealordering;

import android.graphics.drawable.Drawable;

public class MainDishListItem {

    Drawable dMainDish;
    String mainDishName;
    double mainDishPrice;

    public MainDishListItem(Drawable dMainDish, String mainDishName, double mainDishPrice) {
        this.dMainDish = dMainDish;
        this.mainDishName = mainDishName;
        this.mainDishPrice = mainDishPrice;
    }
}
