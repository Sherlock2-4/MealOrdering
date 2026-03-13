package com.example.mealordering;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderSummary extends AppCompatActivity {

    ListView lv;
    SummaryAdapter adapter;
    List<SummaryListItem> data;

    double totalPrice;
    double finalPrice;
    boolean isPromoCActive;
    DecimalFormat df;

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

        df = new DecimalFormat("###.00");

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

        data.add(new SummaryListItem("Main Dish: " + DataHolder.selectedMainDishName, "₱ " + df.format(DataHolder.selectedMainDishPrice)));
        totalPrice += DataHolder.selectedMainDishPrice;

        data.add(new SummaryListItem("Drink: " + DataHolder.selectedDrinkName, "₱ " + df.format(DataHolder.selectedDrinkPrice)));
        totalPrice += DataHolder.selectedDrinkPrice;

        for(int i = 0; i < DataHolder.selectedSideName.size(); i++) {
            data.add(new SummaryListItem("Side Dish: " + DataHolder.selectedSideName.get(i), "₱ " + df.format(DataHolder.selectedSidePrice.get(i))));
            totalPrice += DataHolder.selectedSidePrice.get(i);
        }

        for(int i = 0; i < DataHolder.selectedAddonName.size(); i++) {
            data.add(new SummaryListItem("Addons: " + DataHolder.selectedAddonName.get(i), "₱ " + df.format(DataHolder.selectedAddonPrice.get(i))));
            totalPrice += DataHolder.selectedAddonPrice.get(i);
        }

        data.add(new SummaryListItem("Sub-Total\n","₱ " + df.format(totalPrice)));

        finalPrice = totalPrice;
        promoC();
        if (!isPromoCActive) {
            promoD();
        }
        promoA();
        promoB();

        data.add(new SummaryListItem("Final Price","₱ " + df.format(finalPrice)));
    }

    public void promoA() {

        if (!DataHolder.selectedDrinkName.equals("Water")) {

            data.add(new SummaryListItem("Applied Promo: Combo Saver\nMain + Drink", "₱ -" + df.format(DataHolder.selectedMainDishPrice * 0.10)));
            finalPrice -= DataHolder.selectedMainDishPrice * 0.10;
        }
    }
    public void promoB() {

        if (DataHolder.selectedSideName.size() == 2) {

            data.add(new SummaryListItem("Applied Promo: Side Bundle\n2 Sides Chosen", "₱ -10.00"));
            finalPrice -= 10;

        }
    }
    public void promoC() {

        if (DataHolder.selectedMainDishName.equals("Veggie Bowl") && DataHolder.selectedDrinkName.equals("Water") && !DataHolder.selectedAddonName.contains("Cheese")) {

            data.add(new SummaryListItem("Applied Promo: Healthy Choice\nVeggie Bowl, Water, No Cheese", "₱ -20.00"));
            finalPrice -= 20;
            isPromoCActive = true;
        }
    }
    public void promoD() {

        if (totalPrice >= 180) {

            data.add(new SummaryListItem("Applied Promo: Student Deal\nTotal Order Price ≥ 180", "₱ -15.00"));
            finalPrice -= 15;
        }

    }

    public void checkOutPressed(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thank You");
        builder.setMessage("Please wait for your order soon.");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                okPressed();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void okPressed() {
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    public void backPressed(View view) {
        this.finish();
    }

}