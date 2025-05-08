package com.example.lostfoundapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ItemDetailActivity extends AppCompatActivity {
    TextView tvStatus, tvName, tvPhone, tvDesc, tvDate, tvLocation;
    Button btnRemove;
    DBHelper dbHelper;
    int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        tvStatus = findViewById(R.id.tv_detail_status);
        tvName = findViewById(R.id.tv_detail_name);
        tvPhone = findViewById(R.id.tv_detail_phone);
        tvDesc = findViewById(R.id.tv_detail_desc);
        tvDate = findViewById(R.id.tv_detail_date);
        tvLocation = findViewById(R.id.tv_detail_location);
        btnRemove = findViewById(R.id.btn_remove);

        dbHelper = new DBHelper(this);
        itemId = getIntent().getIntExtra("itemId", -1);

        if (itemId != -1) {
            Cursor cursor = dbHelper.getItemById(itemId);
            if (cursor != null && cursor.moveToFirst()) {
                String status = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_STATUS));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_NAME));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_PHONE));
                String desc = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_DESC));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_DATE));
                String location = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_LOCATION));

                tvStatus.setText("Status: " + status);
                tvName.setText("Name: " + name);
                tvPhone.setText("Phone: " + phone);
                tvDesc.setText("Description: " + desc);
                tvDate.setText("Date: " + date);
                tvLocation.setText("Location: " + location);

                cursor.close();
            }
        }

        btnRemove.setOnClickListener(v -> {
            boolean deleted = dbHelper.deleteItem(itemId);
            if (deleted) {
                Toast.makeText(this, "Item removed", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to remove item", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
