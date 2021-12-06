package com.example.agriculture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Screen_2 extends AppCompatActivity {

    Button tapToInfoButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

        tapToInfoButton=findViewById(R.id.tap_to_info_button);
        tapToInfoButton.setOnClickListener(v -> {

            Intent intent2=new Intent(this,Screen3.class);
            startActivity(intent2);
        });

    }

}