package com.example.mealordering;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class SelectionItem extends AppCompatActivity {

    TextView tvMainDish;
    CheckBox cbNone, cbFries, cbLumpia, cbSoup, cbRice;
    CheckBox cbEgg, cbExtraSauce, cbCheese;
    RadioButton rbWater, rbIcedTea, rbSoda, rbFruitShake;
    int numOfSide;
    boolean isSideSelected, isDrinkSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_selection_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        numOfSide = 0;

        tvMainDish = findViewById(R.id.tvMainDish);
        tvMainDish.setText(DataHolder.selectedMainDishName);

        cbNone = findViewById(R.id.cbNone);
        cbFries = findViewById(R.id.cbFries);
        cbLumpia = findViewById(R.id.cbLumpia);
        cbSoup = findViewById(R.id.cbSoup);
        cbRice = findViewById(R.id.cbRice);

        cbEgg = findViewById(R.id.cbEgg);
        cbExtraSauce = findViewById(R.id.cbExtraSauce);
        cbCheese = findViewById(R.id.cbCheese);

        rbWater = findViewById(R.id.rbWater);
        rbIcedTea = findViewById(R.id.rbIcedTea);
        rbSoda = findViewById(R.id.rbSoda);
        rbFruitShake = findViewById(R.id.rbFruitShake);

        isDrinkSelected = false;
        isSideSelected = false;

        DataHolder.selectedSideName = new ArrayList<>();
        DataHolder.selectedSidePrice = new ArrayList<>();
        DataHolder.selectedAddonName = new ArrayList<>();
        DataHolder.selectedAddonPrice = new ArrayList<>();


    }

    public void cbNonePressed(View view) {

        isSideSelected = false;

        if(cbNone.isChecked()) {

            cbFries.setChecked(false);
            cbFries.setEnabled(false);

            cbLumpia.setChecked(false);
            cbLumpia.setEnabled(false);

            cbSoup.setChecked(false);
            cbSoup.setEnabled(false);

            cbRice.setChecked(false);
            cbRice.setEnabled(false);

        } else {

            cbFries.setEnabled(true);
            cbLumpia.setEnabled(true);
            cbSoup.setEnabled(true);
            cbRice.setEnabled(true);
            numOfSide = 0;

        }
    }

    public void sidePressed(View view) {

        isSideSelected = true;

        if(((CheckBox)view).isChecked()) {
            numOfSide++;
        } else {
            numOfSide--;
        }

        if(numOfSide == 2) {

            if(!cbFries.isChecked()){
                cbFries.setEnabled(false);
            }
            if(!cbRice.isChecked()){
                cbRice.setEnabled(false);
            }
            if(!cbSoup.isChecked()){
                cbSoup.setEnabled(false);
            }
            if(!cbLumpia.isChecked()){
                cbLumpia.setEnabled(false);
            }

        } else {

            cbFries.setEnabled(true);
            cbLumpia.setEnabled(true);
            cbSoup.setEnabled(true);
            cbRice.setEnabled(true);

        }

        if (numOfSide == 0) {
            isSideSelected = false;
        }
    }

    public void drinkPressed(View view) {
        isDrinkSelected = true;
    }

    public void backPressed(View view) {
        this.finish();
    }

    public void proceedPressed(View view) {

        DataHolder.selectedSidePrice.clear();
        DataHolder.selectedSideName.clear();

        if (isSideSelected && isDrinkSelected) {
            if(cbFries.isChecked()){
                DataHolder.selectedSideName.add("Fries");
                DataHolder.selectedSidePrice.add(35.0);
            }
            if(cbRice.isChecked()){
                DataHolder.selectedSideName.add("Extra Rice");
                DataHolder.selectedSidePrice.add(20.0);
            }
            if(cbSoup.isChecked()){
                DataHolder.selectedSideName.add("Soup");
                DataHolder.selectedSidePrice.add(25.0);
            }
            if(cbLumpia.isChecked()){
                DataHolder.selectedSideName.add("Lumpia");
                DataHolder.selectedSidePrice.add(40.0);
            }
        }

        if(isDrinkSelected) {

            if(rbWater.isChecked()) {
                DataHolder.selectedDrinkName = "Water";
                DataHolder.selectedDrinkPrice = 0.0;
            }
            if(rbSoda.isChecked()) {
                DataHolder.selectedDrinkName = "Soda";
                DataHolder.selectedDrinkPrice = 25;
            }
            if(rbFruitShake.isChecked()) {
                DataHolder.selectedDrinkName = "Fruit Shake";
                DataHolder.selectedDrinkPrice = 45;
            }
            if(rbIcedTea.isChecked()) {
                DataHolder.selectedDrinkName = "Iced Tea";
                DataHolder.selectedDrinkPrice = 20;
            }

            DataHolder.selectedAddonName.clear();
            DataHolder.selectedAddonPrice.clear();

            if(cbEgg.isChecked()) {
                DataHolder.selectedAddonName.add("Egg");
                DataHolder.selectedAddonPrice.add(15.0);
            }
            if(cbExtraSauce.isChecked()) {
                DataHolder.selectedAddonName.add("Extra Sauce");
                DataHolder.selectedAddonPrice.add(10.0);
            }
            if(cbCheese.isChecked()) {
                DataHolder.selectedAddonName.add("Cheese");
                DataHolder.selectedAddonPrice.add(20.0);
            }

            Intent i = new Intent(this, OrderForm.class);
            startActivity(i);

        } else {
            Toast.makeText(this, "Please select a drink", Toast.LENGTH_SHORT).show();
        }
    }
}