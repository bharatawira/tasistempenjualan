package com.viss.fishstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.request.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CupangActivity extends AppCompatActivity{
    private ProgressDialog pDialog;
    RecyclerView lv;
    CupangAdapter cupangAdapter;
    ArrayList<Cupang> cupangList;
    public static final String url = "http://10.0.2.2/db_penjualan/get_daftar_cupang.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupang);
        getSupportActionBar().hide();
        lv = findViewById(R.id.rv_cupang);
        lv.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL, false));
        cupangList = new ArrayList<>();
        cupangAdapter = new CupangAdapter(getApplicationContext(), cupangList);
        lv.setAdapter(cupangAdapter);

        getDataCupang();
    }

    private void displayLoader() {
        pDialog = new ProgressDialog(CupangActivity.this);
        pDialog.setMessage("Sedang diproses...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }
    private void getDataCupang() {
        displayLoader();
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, url, null, response -> {
                    pDialog.dismiss();
                    try {
                        if (response.getInt("status") == 0) {
                            JSONArray jsonArray = response.getJSONArray("cupang");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Cupang cupang = new Cupang();
                                cupang.setId(jsonObject.getString("id_cupang"));
                                cupang.setNama(jsonObject.getString("nama_cupang"));
                                cupang.setHarga(jsonObject.getString("harga_cupang"));
                                cupang.setImg_url(jsonObject.getString("gambar_cupang"));
                                cupangList.add(cupang);

                            }
                            cupangAdapter.notifyDataSetChanged();


                        } else {
                            Toast.makeText(getApplicationContext(),
                                    response.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(),
                            error.getMessage(), Toast.LENGTH_SHORT).show();
                });

        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }



}