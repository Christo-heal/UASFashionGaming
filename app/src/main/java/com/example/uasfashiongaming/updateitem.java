package com.example.uasfashiongaming;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updateitem extends AppCompatActivity {
    protected Cursor cursor;
    Database database;
    Button btnUpdate;
    EditText updateName, updatePrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateitem);
        btnUpdate = findViewById(R.id.updateBtn);
        database = new Database(updateitem.this);
        updateName = findViewById(R.id.updateName);
        updatePrice = findViewById(R.id.updatePrice);  // Anda melakukan kesalahan ketik. Variabel harus updatePrice bukan updateName.

        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM product WHERE itemName = '" +
                getIntent().getStringExtra("itemName")+"'",null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            cursor.moveToPosition(0);
            updateName.setText(cursor.getString(0).toString());
            updatePrice.setText(cursor.getString(1).toString());
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("update product set itemName ='" +
                        updateName.getText().toString()+"', itemPrice = '"+
                        updatePrice.getText().toString()+"'where itemName ='"+
                        getIntent().getStringExtra("itemName")+"'");
                Toast.makeText(updateitem.this,"Item sudah di update", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}