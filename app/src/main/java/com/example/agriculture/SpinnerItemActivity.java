package com.example.agriculture;

import static android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class SpinnerItemActivity extends AppCompatActivity {
    private int id;
    private  String content,name;
    int toHide;
    DatabaseHandler databaseHandler;
    LinearLayout layout;
    TextView tv;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_item);
        layout=findViewById(R.id.lastLayout);
        id=getIntent().getIntExtra("id",0);
        content=getIntent().getStringExtra("content");
        name=getIntent().getStringExtra("name");
        toHide=getIntent().getIntExtra("position",0);
    Log.i("posiiton",String.valueOf(toHide));
        btn = findViewById(R.id.pestBtn);
        tv=findViewById(R.id.pestTV);
        getSupportActionBar().setTitle(name);
        inflateData(content);
        if(toHide==2){
btn.setVisibility(View.GONE);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setVisibility(View.VISIBLE);
            }
        });
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
        else if (type.equals("#P")){
            TextView textView = new TextView(this);
            tv.setText(content.substring(2));
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