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

public class MainActivity extends AppCompatActivity {

    ListView lv;
    MainDishAdapter adapter;
    List<MainDishListItem> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lv = findViewById(R.id.lvMainDish);

        data = new ArrayList<>();
        data.add(new MainDishListItem(getDrawable(R.drawable.chicken_rice), "Chicken Rice", 95.00));
        data.add(new MainDishListItem(getDrawable(R.drawable.beef_tapa), "Beef Tapa", 120.00));
        data.add(new MainDishListItem(getDrawable(R.drawable.pork_sisig), "Pork Sisig", 130.00));
        data.add(new MainDishListItem(getDrawable(R.drawable.veggie_bowl), "Veggie Bowl", 85.00));

        adapter = new MainDishAdapter(this, data);
        lv.setAdapter(adapter);

    }
}