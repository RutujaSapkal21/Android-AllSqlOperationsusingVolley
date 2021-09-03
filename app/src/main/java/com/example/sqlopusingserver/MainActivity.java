package com.example.sqlopusingserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void insertdata(View view) {
        Intent intent=new Intent(MainActivity.this,Insertdata.class);
        startActivity(intent);
    }

    public void viewdata(View view) {
        Intent intent=new Intent(MainActivity.this,ViewALLMembers.class);
        startActivity(intent);
    }


}