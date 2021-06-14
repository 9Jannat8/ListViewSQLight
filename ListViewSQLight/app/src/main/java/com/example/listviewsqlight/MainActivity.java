package com.example.listviewsqlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseHelper databaseHelper;
    private EditText idEditText, nameEditText;
    private Button saveButton, showButton, updateButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idEditText = findViewById(R.id.idEditTextId);
        nameEditText = findViewById(R.id.nameEditTextId);

        saveButton = findViewById(R.id.saveButtonId);
        showButton = findViewById(R.id.showButtonId);
        updateButton = findViewById(R.id.updateButtonId);
        deleteButton = findViewById(R.id.deleteButtonId);

        saveButton.setOnClickListener(this);
        showButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

    }

    @Override
    public void onClick(View v) {

        String id = idEditText.getText().toString();
        String name = nameEditText.getText().toString();

        if (v.getId() == R.id.saveButtonId)
        {
            if (id.equals("") && name.equals("")){
                Toast.makeText(MainActivity.this, "Please enter all the data", Toast.LENGTH_LONG).show();

            } else {
                long rowNumber = databaseHelper.saveData(id, name);
                if (rowNumber > -1) {

                    Toast.makeText(MainActivity.this, "Data is inserted", Toast.LENGTH_LONG).show();
                    idEditText.setText("");
                    nameEditText.setText("");


                } else {

                    Toast.makeText(MainActivity.this, "Data insertion is failed", Toast.LENGTH_LONG).show();
                }
            }



        } else if (v.getId() == R.id.showButtonId) {

            Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
            startActivity(intent);

        } else if (v.getId() == R.id.updateButtonId) {

            Boolean isUpdate = databaseHelper.updateData(id, name);

            if (isUpdate == true) {

                Toast.makeText(getApplicationContext(), "data is updated", Toast.LENGTH_LONG).show();

            } else {

                Toast.makeText(getApplicationContext(), "data is not updated", Toast.LENGTH_LONG).show();
            }

        } else if (v.getId() == R.id.deleteButtonId) {

            int value = databaseHelper.deleteData(id);
            if (value<0) {

                Toast.makeText(getApplicationContext(), "data is not deleted ", Toast.LENGTH_LONG).show();

            } else {

                Toast.makeText(getApplicationContext(), "data is deleted ", Toast.LENGTH_LONG).show();

            }

        }
    }
}

//    <?xml version="1.0" encoding="utf-8"?>
//<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
//        xmlns:app="http://schemas.android.com/apk/res-auto"
//        xmlns:tools="http://schemas.android.com/tools"
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        android:background="#9F1745"
//        android:padding="5dp"
//        android:orientation="vertical"
//        tools:context=".MainActivity">
//
//<EditText
//        android:layout_width="match_parent"
//                android:layout_height="30dp"
//                android:hint="Enter ID"
//                android:textSize="8sp"
//                android:textColorHint="@color/white"
//                android:textCursorDrawable="@color/white"
//                android:backgroundTint="@color/white"
//                android:textColor="@color/teal_200"
//                android:id="@+id/idEditTextId"
//                />
//
//<EditText
//        android:layout_width="match_parent"
//                android:layout_height="30dp"
//                android:hint="Enter Name"
//                android:textSize="8sp"
//                android:textColorHint="@color/white"
//                android:textCursorDrawable="@color/white"
//                android:backgroundTint="@color/white"
//                android:textColor="@color/teal_200"
//                android:id="@+id/nameEditTextId"
//
//                />
//
//<Button
//        android:layout_width="match_parent"
//                android:layout_height="27dp"
//                android:backgroundTint="#C33364"
//                android:layout_marginTop="5dp"
//                android:text="save"
//                android:textSize="6sp"
//                android:textStyle="bold"
//                android:id="@+id/saveButtonId"
//                />
//
//<Button
//        android:layout_width="match_parent"
//                android:layout_height="27dp"
//                android:backgroundTint="#C33364"
//                android:text="show"
//                android:textSize="6sp"
//                android:textStyle="bold"
//                android:id="@+id/showButtonId"
//                />
//
//<Button
//        android:layout_width="match_parent"
//                android:layout_height="27dp"
//                android:backgroundTint="#C33364"
//                android:text="update"
//                android:textStyle="bold"
//                android:textSize="6sp"
//                android:id="@+id/updateButtonId"
//                />
//
//<Button
//        android:layout_width="match_parent"
//                android:layout_height="27dp"
//                android:backgroundTint="#C33364"
//                android:text="delete"
//                android:textSize="6sp"
//                android:textStyle="bold"
//                android:id="@+id/deleteButtonId"
//                />
//
//</LinearLayout>