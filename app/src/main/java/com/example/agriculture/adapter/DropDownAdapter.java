package com.example.agriculture.adapter;

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


import com.example.agriculture.models.AppModel;

import java.util.ArrayList;

public class DropDownAdapter extends RecyclerView.Adapter<DropDownAdapter.dropDownViewHolder> {

    ArrayList<AppModel> data;
    Context context;

    public DropDownAdapter(Context context, ArrayList<AppModel> data) {
        this.data=data;
        this.context=context;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public dropDownViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dropdown_item,parent,false);
        dropDownViewHolder viewHolder=new dropDownViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull dropDownViewHolder holder, int position) {
        holder.text.setText(data.get(position).getName());

    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    public class dropDownViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public dropDownViewHolder(final View View) {
            super(View);
            text=View.findViewById(R.id.dropdown_item);
        }
    }
}
