package com.example.agriculture;

import static android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    LinearLayout layout,contactUs;
    InfoAdapter adapter;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen4);
        ArrayList<AppModel> appModels = new ArrayList<>();
        databaseHandler= new DatabaseHandler(this);
//            textView=findViewById(R.id.textView);

        layout = findViewById(R.id.linearLayout);

        id=getIntent().getIntExtra("id",0);
        content=getIntent().getStringExtra("content");
        name=getIntent().getStringExtra("name");
        getSupportActionBar().setTitle(name);
        appModels= databaseHandler.getModelsbyID(id);
        recyclerView=findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactUs = findViewById(R.id.contactUs);
        adapter=new InfoAdapter(this,appModels);
        if(name.equalsIgnoreCase("contact us")){
            recyclerView.setVisibility(View.GONE);
            layout.setVisibility(View.GONE);
            contactUs.setVisibility(View.VISIBLE);
        }
        else if(appModels.size()==0){
            inflateData(content);

            recyclerView.setVisibility(View.GONE);
            contactUs.setVisibility(View.GONE);
        }
        else{
            Log.i("happening",appModels.size()+"");
            recyclerView.setAdapter(adapter);

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