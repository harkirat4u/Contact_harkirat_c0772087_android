package com.example.contact_harkirat_c0772087_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper mDatabase;
    EditText firstName, lastName, email, address, phone;
    Button Add, btn_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        firstName = findViewById(R.id.m_firstname);
        lastName = findViewById(R.id.m_lastname);
        email=findViewById(R.id.m_email);
        address = findViewById(R.id.m_address);
        phone = findViewById(R.id.m_phone);
        Add = findViewById(R.id.btn_add);
        btn_view = findViewById(R.id.btn_view);
        mDatabase = new DataBaseHelper(this);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });
    }
    private void addUser() {
        String fname = firstName.getText().toString().trim();
        String lname = lastName.getText().toString().trim();
        String mail = email.getText().toString().trim();
        String addrs = address.getText().toString().trim();
        String phn = phone.getText().toString().trim();
        if(fname.isEmpty()) {
            firstName.setError("name required");
            firstName.requestFocus();
            return;
        }
        if(mDatabase.addUser(fname,lname,mail,addrs,phn)){
            Toast.makeText(this, "new Contact added", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Contact not added", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        firstName.setText("");
        lastName.setText("");
        email.setText("");
        address.setText("");
        phone.setText("");
    }
}
