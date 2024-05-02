package com.example.crud;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView title;
        EditText name , contact , dob;
        Button  insertBtn , updateBtn , deleteBtn , viewBtn;
        DBHelper db;

            name = findViewById(R.id.name);
            contact = findViewById(R.id.contact);
            dob = findViewById(R.id.dob);

            insertBtn = findViewById(R.id.insertBtn);
            updateBtn = findViewById(R.id.updateBtn);
            deleteBtn = findViewById(R.id.deleteBtn);
            viewBtn = findViewById(R.id.viewBtn);

            db = new DBHelper(this);

            insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nameTXT = name.getText().toString();
                    String contactTXT = contact.getText().toString();
                    String dobTXT = dob.getText().toString();

                    Boolean checkInsertData = db.insertUserData(nameTXT, contactTXT, dobTXT);
                    if (checkInsertData==true){
                        Toast.makeText(MainActivity.this, "New Data Inserted", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "New Data Not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checkUpdateData = db.updateUserData(nameTXT, contactTXT, dobTXT);
                if (checkUpdateData==true){
                    Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Data Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();

                Boolean checkDeleteData = db.deleteUserData(nameTXT);
                if (checkDeleteData==true){
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor result = db.getData();
                if (result.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (result.moveToNext()){
                    buffer.append("Name: " + result.getString(0) + "\n");
                    buffer.append("Contact: " + result.getString(1) + "\n");
                    buffer.append("Date Of Birth: " + result.getString(2) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        }
    }
