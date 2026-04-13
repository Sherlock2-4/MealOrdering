package com.example.mealordering;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataHolder {

    //-----------Drawable----------
    public static Drawable selectedMainDishDrawable;

    //-----------Name-----------
    public static String selectedMainDishName;
    public static ArrayList<String> selectedSideName;
    public static String selectedDrinkName;
    public static ArrayList<String> selectedAddonName;

    //----------Price---------------------
    public static double selectedMainDishPrice;
    public static ArrayList<Double> selectedSidePrice;
    public static double selectedDrinkPrice;
    public static ArrayList<Double> selectedAddonPrice;

    //------------HashMap for menuitems---------------
    public static Map<String, Double> menuItemsMap = new HashMap<>();

    static {

        menuItemsMap.put("Fries", 35.0);
        menuItemsMap.put("Lumpia (4pcs)", 40.0);
        menuItemsMap.put("Soup", 25.0);
        menuItemsMap.put("Extra Rice", 20.0);
        menuItemsMap.put("Water", 00.0);
        menuItemsMap.put("Iced Tea", 20.0);
        menuItemsMap.put("Soda", 25.0);
        menuItemsMap.put("Fruit Shake", 45.0);
        menuItemsMap.put("Egg", 15.0);
        menuItemsMap.put("Extra Sauce", 10.0);
        menuItemsMap.put("Cheese", 20.0);

    }

}
