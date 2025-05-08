package com.example.lostfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCreateAdvert = findViewById(R.id.btn_create_advert);
        Button btnShowAll = findViewById(R.id.btn_show_all);

        btnCreateAdvert.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
            startActivity(intent);
        });

        btnShowAll.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ItemListActivity.class);
            startActivity(intent);
        });
    }
}
