package com.example.agriculture.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agriculture.R;
import com.example.agriculture.Screen3;
import com.example.agriculture.Screen_2;
import com.example.agriculture.Screen_4;
import com.example.agriculture.models.AppModel;

import java.util.ArrayList;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.infoViewHolder> {

    ArrayList<AppModel> data;
    Context context;

    public InfoAdapter(Context context, ArrayList<AppModel> data) {
        this.data=data;
        this.context=context;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public infoViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info,parent,false);
        infoViewHolder viewHolder=new infoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull InfoAdapter.infoViewHolder holder, @SuppressLint("RecyclerView") int position) {
       holder.text.setText(data.get(position).getName());
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent3=new Intent(context, Screen_4.class);
               intent3.putExtra("id", data.get(position).get_id());
               intent3.putExtra("content",data.get(position).getData());
               intent3.putExtra("name",data.get(position).getName());
               context.startActivity(intent3);

           }
       });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class infoViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public infoViewHolder(final View View) {
            super(View);
             text=View.findViewById(R.id.text1);
        }
    }
}
