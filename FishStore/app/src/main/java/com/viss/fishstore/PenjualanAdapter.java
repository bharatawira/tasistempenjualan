package com.viss.fishstore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.ui.NetworkImageViewPlus;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class PenjualanAdapter extends RecyclerView.Adapter<PenjualanAdapter.MyViewHolder> {

    Context context;
    ArrayList<Penjualan> list;

    public PenjualanAdapter(Context context, ArrayList<Penjualan> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.view_approve, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Penjualan penjualan = list.get(position);
        holder.nama.setText(penjualan.getNama_pembeli());
        holder.n_cupang.setText(penjualan.getNama_cupang());
        holder.harga.setText(penjualan.getHarga_jual());
        Glide.with(context).load(penjualan.getGambar_cupang()).apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background).dontAnimate()).into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(context, ApproveDetailActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent1.putExtra("id_penjualan", list.get(position).getId_penjualan());
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
        TextView nama, harga, n_cupang;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.nama_pembeli_approve);
            harga = itemView.findViewById(R.id.harga_cupang_approve);
            img = itemView.findViewById(R.id.img_cupang_approve);
            n_cupang = itemView.findViewById(R.id.nama_cupang_approve);
        }





    }
}
