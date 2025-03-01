package com.example.agriculture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.agriculture.adapter.InfoAdapter;
import com.example.agriculture.models.AppModel;

import java.util.ArrayList;

public class Screen3 extends AppCompatActivity {
    DatabaseHandler dataBaseHandler;
    RecyclerView recyclerView;
    InfoAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);
        ArrayList<AppModel> appModelArrayList = new ArrayList<>();
        dataBaseHandler= new DatabaseHandler(this);
        appModelArrayList= dataBaseHandler.getParentModels();
//        Log.i("helo",appModelArrayList.toString());
        recyclerView=findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new InfoAdapter(this,appModelArrayList);
        recyclerView.setAdapter(adapter);

    }
}