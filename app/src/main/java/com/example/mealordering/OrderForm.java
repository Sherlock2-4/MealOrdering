package com.example.mealordering;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class OrderForm extends AppCompatActivity {

    EditText etName, etPhone, etTime;
    int currentHour = 0, currentMinute = 0;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm a");

        currentHour = LocalDateTime.now().getHour();
        currentMinute = LocalDateTime.now().getMinute();

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);

        etTime = findViewById(R.id.etTime);
        etTime.setText(convertTime(currentHour, currentMinute));
    }

    public void setTimePressed(View view) {
            TimePickerDialog tpd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @SuppressLint("NewApi")
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                String time = convertTime(hourOfDay, minute);

                etTime.setText(time);
            }
        }, currentHour, currentMinute, false);
        tpd.show();
    }

    public void confirmPressed(View view) {
        String name = etName.getText()+"";
        String phone = etPhone.getText()+"";

        if(!name.isEmpty() && !phone.isEmpty()){
            Intent i = new Intent(this, OrderSummary.class);
            startActivity(i);
        }else{
            Toast.makeText(this, "Please fill up the form.", Toast.LENGTH_LONG).show();
        }
    }

    public String convertTime(int hourOfDay, int minute){
        DecimalFormat df = new DecimalFormat("00");

        String time = "";

        if(hourOfDay > 12){
            time = hourOfDay-12 + ":" + df.format(minute) + " PM";
        }else{
            time = hourOfDay + ":" + df.format(minute) + " AM";
        }
        return time;
    }

    public void backPressed(View view) {
        this.finish();
    }
}