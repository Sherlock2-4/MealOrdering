package com.example.mealordering;

import android.app.TimePickerDialog;
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

public class SelectionItem extends AppCompatActivity {

    TextView tvMainDish;

    CheckBox cbNone, cbFries, cbLumpia, cbSoup, cbRice;

    int numOfSide;

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

//        TimePickerDialog tpd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//
//            }
//        }, 1, 1, false);
//        tpd.show();
    }

    public void cbNonePressed(View view) {

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
    }

    public void backPressed(View view) {
        this.finish();
    }

}