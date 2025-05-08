package com.example.lostfoundapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddItemActivity extends AppCompatActivity {
    EditText etName, etPhone, etDesc, etDate, etLocation;
    RadioGroup rgStatus;
    Button btnSave;
    DBHelper dbHelper;
    private static final String TAG = "AddItemActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_phone);
        etDesc = findViewById(R.id.et_desc);
        etDate = findViewById(R.id.et_date);
        etLocation = findViewById(R.id.et_location);
        rgStatus = findViewById(R.id.rg_status);
        btnSave = findViewById(R.id.btn_save);

        dbHelper = new DBHelper(this);

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String desc = etDesc.getText().toString().trim();
            String date = etDate.getText().toString().trim();
            String location = etLocation.getText().toString().trim();

            int selectedId = rgStatus.getCheckedRadioButtonId();
            RadioButton selectedRadio = selectedId != -1 ? findViewById(selectedId) : null;
            String status = (selectedRadio != null) ? selectedRadio.getText().toString() : "";

            Log.d(TAG, "Attempting insert with: " + name + ", " + phone + ", " + desc + ", " + date + ", " + location + ", " + status);

            if (name.isEmpty() || phone.isEmpty() || desc.isEmpty() || date.isEmpty() || location.isEmpty() || status.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                boolean inserted = dbHelper.insertItem(name, phone, desc, date, location, status);
                Log.d(TAG, "Insert result: " + inserted);
                if (inserted) {
                    Toast.makeText(this, "Advert saved successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Database insert failed (check logs)", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
