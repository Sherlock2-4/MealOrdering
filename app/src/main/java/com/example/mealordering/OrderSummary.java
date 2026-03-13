package com.example.mealordering;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class OrderSummary extends AppCompatActivity {

    ListView lv;
    SummaryAdapter adapter;
    List<SummaryListItem> data;

    double totalPrice;
    double finalPrice;
    boolean isPromoCActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_summary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

       lv = findViewById(R.id.lvSummary);

       data = new ArrayList<>();

       totalPrice = 0;
       finalPrice = 0;

       isPromoCActive = false;

       //Below this should run last;

       addAllSelectedData();
       adapter = new SummaryAdapter(this, data);
       lv.setAdapter(adapter);
    }

    public void addAllSelectedData() {

        data.add(new SummaryListItem("Main Dish: " + DataHolder.selectedMainDishName, "₱ " + DataHolder.selectedMainDishPrice));
        totalPrice += DataHolder.selectedMainDishPrice;

        data.add(new SummaryListItem("Drink: " + DataHolder.selectedDrinkName, "₱ " + DataHolder.selectedDrinkPrice));
        totalPrice += DataHolder.selectedDrinkPrice;

        for(int i = 0; i < DataHolder.selectedSideName.size(); i++) {
            data.add(new SummaryListItem("Side Dish: " + DataHolder.selectedSideName.get(i), "₱ " + DataHolder.selectedSidePrice.get(i)));
            totalPrice += DataHolder.selectedSidePrice.get(i);
        }

        for(int i = 0; i < DataHolder.selectedAddonName.size(); i++) {
            data.add(new SummaryListItem("Addons: " + DataHolder.selectedAddonName.get(i), "₱ " + DataHolder.selectedAddonPrice.get(i)));
            totalPrice += DataHolder.selectedAddonPrice.get(i);
        }

        data.add(new SummaryListItem("Sub-Total\n\n","₱ " + totalPrice));

        finalPrice = totalPrice;
        promoC();
        if (!isPromoCActive) {
            promoD();
        }

        data.add(new SummaryListItem("Final Price","₱ " + finalPrice));
    }

    public void promoA() {}
    public void promoB() {}
    public void promoC() {

        if (DataHolder.selectedMainDishName.equals("Veggie Bowl") && DataHolder.selectedDrinkName.equals("Water") && !DataHolder.selectedAddonName.contains("Cheese")) {

            data.add(new SummaryListItem("Applied Promo: Healthy Choice\nVeggie Bowl, Water, No Cheese", "₱ -20"));
            finalPrice -= 20;
            isPromoCActive = true;
        }
    }
    public void promoD() {

        if (totalPrice >= 180) {

            data.add(new SummaryListItem("Applied Promo: Student Deal\nTotal Order ≥ 180", "₱ -15"));
            finalPrice -= 15;
        }

    }
}