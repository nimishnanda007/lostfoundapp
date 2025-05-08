package com.example.lostfoundapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ItemListActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> itemNames;
    ArrayList<Item> items;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        listView = findViewById(R.id.listView_items);
        dbHelper = new DBHelper(this);

        itemNames = new ArrayList<>();
        items = new ArrayList<>();

        Cursor cursor = dbHelper.getAllItems();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COL_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_NAME));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_DATE));
                String location = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_LOCATION));
                String desc = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_DESC));
                String status = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_STATUS));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_PHONE));
                items.add(new Item(id, name, phone, desc, date, location, status));
                itemNames.add(status + " " + name);
            } while (cursor.moveToNext());
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ItemListActivity.this, ItemDetailActivity.class);
            intent.putExtra("itemId", items.get(position).getId());
            startActivity(intent);
        });
    }
}
