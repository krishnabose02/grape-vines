package com.example.agriculture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agriculture.models.UserModel;
import com.example.agriculture.utils.CSVImporter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button button;
    String email , password;
    EditText emailET , passwordET;
    TextView signUpButton;
    DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailET = findViewById(R.id.user_text);
        passwordET=findViewById(R.id.password_text);
        button=findViewById(R.id.login_button);
        databaseHandler= new DatabaseHandler(this);
        ArrayList<UserModel> userModels = databaseHandler.getAllUsers();

        button.setOnClickListener(v -> {
            email= emailET.getText().toString().trim();
            password=passwordET.getText().toString().trim();

            if(email.equals("") || password.equals("")){
                Toast.makeText(this, "Wrong Credentials !", Toast.LENGTH_SHORT).show();
            }
            else{
                Intent intent1=new Intent(MainActivity.this,Screen_2.class);
                startActivity(intent1);
                finish();

            }


        });

       signUpButton=findViewById(R.id.signUp);
       signUpButton.setOnClickListener(v -> {
         Intent registerIntent=new Intent(MainActivity.this, RegisterActivity.class);
         startActivity(registerIntent);

        });


    }
}