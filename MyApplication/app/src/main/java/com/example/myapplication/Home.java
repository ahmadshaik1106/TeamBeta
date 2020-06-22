package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void login(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
    }

    public void Register(View view) {
        startActivity(new Intent(getApplicationContext(),Register.class));
    }
}