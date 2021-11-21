package com.example.agriculture;

import static android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agriculture.adapter.InfoAdapter;
import com.example.agriculture.models.AppModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Screen_4 extends AppCompatActivity {

    private int id;
    private  String content,name;
    DatabaseHandler databaseHandler;

    //    TextView textView;
    LinearLayout layout,contactUs,plantCal;
    TextView ansTV;
    InfoAdapter adapter;
    AutoCompleteTextView spinner1,spinner2;
    RecyclerView recyclerView;
    Integer row=1,spacing=1;
    Integer constant = 43560;
    Boolean toShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen4);
        ArrayList<AppModel> appModels = new ArrayList<>();
        databaseHandler= new DatabaseHandler(this);
//            textView=findViewById(R.id.textView);

        layout = findViewById(R.id.linearLayout);
        ansTV = findViewById(R.id.ansTV);

        toShow=false;

        id=getIntent().getIntExtra("id",0);
        content=getIntent().getStringExtra("content");
        name=getIntent().getStringExtra("name");
        getSupportActionBar().setTitle(name);
        appModels= databaseHandler.getModelsbyID(id);
        spinner1 =  findViewById(R.id.rows_spinner);
        adapter = new InfoAdapter(this, appModels);
        spinner2= findViewById(R.id.vine_spinner);
        recyclerView=findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactUs = findViewById(R.id.contactUs);
        plantCal=findViewById(R.id.plantCal);
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(this,R.layout.dropdown_item,getResources().getStringArray(R.array.row_array));
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this,R.layout.dropdown_item,getResources().getStringArray(R.array.spacing_array));


        spinner1.setAdapter(arrayAdapter1);
        spinner2.setAdapter(arrayAdapter2);
        spinner1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String value = (String) adapterView.getItemAtPosition(i);
                row = Integer.parseInt(value.substring(0,1));
                double d = Math.floor(constant*1.0 / row / spacing);
                Log.i("DoubleValue", String.valueOf(d));
                if(toShow){
                    ansTV.setText(String.valueOf((int) d));
                }

            }
        });
        spinner2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String value =((String) adapterView.getItemAtPosition(i));
                ansTV.setVisibility(View.VISIBLE);
                spacing = Integer.parseInt(value.substring(0,1));
                double d = Math.floor(constant*1.0 / row / spacing);
                Log.i("DoubleValue", String.valueOf(d));
                toShow=true;
                ansTV.setText(String.valueOf((int) d));
            }
        });
        if(name.equalsIgnoreCase("plant protection")){
            Intent intent= new Intent(Screen_4.this,PlantProtectionActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("content",content);
            intent.putExtra("name",name);
            startActivity(intent);
            finish();

        }
       else if(name.equalsIgnoreCase("contact us")){
            recyclerView.setVisibility(View.GONE);
            layout.setVisibility(View.GONE);
            contactUs.setVisibility(View.VISIBLE);
        }
        else if(name.equalsIgnoreCase("plant calculator")){
            recyclerView.setVisibility(View.GONE);
            layout.setVisibility(View.GONE);
            contactUs.setVisibility(View.GONE);
            plantCal.setVisibility(View.VISIBLE);
        }
        else if(appModels.size()==0){
            inflateData(content);
            plantCal.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            contactUs.setVisibility(View.GONE);
        }
        else{
            Log.i("happening",appModels.size()+"");
            recyclerView.setAdapter(adapter);
            plantCal.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            layout.setVisibility(View.GONE);
            contactUs.setVisibility(View.GONE);
        }
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        textView.setText(content);

    }

    public void inflateData(String content) {
        try {
            String[] arr = content.split("\n");
            for (String elem : arr) {
                if(elem.length()<2) return;
//                Log.e("Db contents", elem);
                layout.addView(getView(elem));

            }

        } catch (Exception e) {
            Log.e("sdfdsd", e.getMessage());
            Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
        }
    }
    public View getView(String content) throws IOException {
        // we process 3 types of views
        // 1. #H: headers, where text is bold
        // 2. #I: images
        // 3. normal text content
        Log.i("comtent",content);
        String type = content.substring(0, 2);
        if (type.equals("#H")) {
            TextView textView = new TextView(this);
            textView.setText(content.substring(2));
            textView.setTextSize((20));
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setPadding(0,40,0,20);
            textView.setTypeface(null, Typeface.BOLD);
            return textView;
        } else if (type.equals("#I")) {

            InputStream image = getAssets().open(content.substring(2));
//            InputStream image = getAssets().open(content.substring(2));
            Drawable d = Drawable.createFromStream(image, null);
            ImageView imageView = new ImageView(this);

            imageView.setImageDrawable(d);
            imageView.setPadding(0,30,0,30);
            imageView.setMinimumHeight(500);
            return imageView;
        }else if(type.equals("#E")){
            TextView textView = new TextView(this);
            textView.setText(content.substring(2));
            textView.setTextSize(18);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                textView.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            }
            textView.setPadding(0,40,0,20);
            textView.setTypeface(null, Typeface.ITALIC);
            return textView;


        }

        else {
            TextView textView = new TextView(this);
            textView.setTextColor(Color.parseColor("#616161"));
            textView.setText(content);
            textView.setTextSize((16));
            return textView;
        }
    }
}