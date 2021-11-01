package com.example.agriculture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.agriculture.utils.CSVImporter;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               CSVImporter importer = new CSVImporter(getApplicationContext());
               importer.importModels();
               importer.importUserModels();
               Intent splashIntent= new Intent(SplashActivity.this,Screen_2.class);
               startActivity(splashIntent);
               finish();
           }
       }, 3000);
    }
}