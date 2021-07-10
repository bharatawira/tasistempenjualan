package com.viss.fishstore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.ui.NetworkImageViewPlus;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class CupangAdapter extends RecyclerView.Adapter<CupangAdapter.MyViewHolder> {

    Context context;
    ArrayList<Cupang> list;

    public CupangAdapter(Context context, ArrayList<Cupang> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.view_data, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cupang cupang = list.get(position);
        holder.username.setText(cupang.getNama_user());
        Glide.with(context).load(cupang.getImg_url()).apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background).dontAnimate()).into(holder.img);
        holder.nama.setText(cupang.getNama());
        holder.harga.setText(cupang.getHarga());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(context, InputCupangActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent1.putExtra("id_cupang", list.get(position).getId());
                context.startActivity(intent1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        NetworkImageViewPlus img ;
        TextView nama, harga, username;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img_cupang);
            nama = itemView.findViewById(R.id.nama_cupang);
            harga = itemView.findViewById(R.id.harga_cupang);
            username = itemView.findViewById(R.id.id_user);
        }





    }
}
