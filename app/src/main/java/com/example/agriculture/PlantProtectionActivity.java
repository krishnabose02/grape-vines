package com.example.agriculture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.example.agriculture.adapter.DropDownAdapter;
import com.example.agriculture.adapter.InfoAdapter;
import com.example.agriculture.models.AppModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class PlantProtectionActivity extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView,autoCompleteTextView2;
    TextInputLayout textInputLayout;
    DatabaseHandler databaseHandler;
    ArrayList<AppModel> appModels,newAppModels;
int showBtn=0;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_protection);
        appModels = new ArrayList<>();

        id=getIntent().getIntExtra("id",0);
        databaseHandler= new DatabaseHandler(this);
        appModels= databaseHandler.getModelsbyID(id);

        ArrayList<String> arrayList = new ArrayList<>();
        for(AppModel model : appModels){
            arrayList.add(model.getName());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.dropdown_item,arrayList);
        autoCompleteTextView=findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView2=findViewById(R.id.autoCompleteTextView2);
        textInputLayout=findViewById(R.id.textInputLayout);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                autoCompleteTextView2.setText("Choose an option",true);
                Log.i("ye hua", String.valueOf(i));
                showBtn=i;
                newAppModels = new ArrayList<>();
                textInputLayout.setVisibility(View.VISIBLE);
                newAppModels= databaseHandler.getModelsbyID(appModels.get(i).get_id());

                ArrayList<String> arrayList = new ArrayList<>();
                for(AppModel model : newAppModels){
                    arrayList.add(model.getName());
                }
                ArrayAdapter arrayAdapter2 = new ArrayAdapter(getApplicationContext(),R.layout.dropdown_item,arrayList);
                autoCompleteTextView2.setAdapter(arrayAdapter2);
                textInputLayout.setVisibility(View.VISIBLE);
            }
        });
        autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(PlantProtectionActivity.this,SpinnerItemActivity.class);
                intent.putExtra("id", newAppModels.get(i).get_id());
                intent.putExtra("content",newAppModels.get(i).getData());
                intent.putExtra("name",newAppModels.get(i).getName());
                intent.putExtra("position",showBtn);
                startActivity(intent);
            }
        });

    }
}