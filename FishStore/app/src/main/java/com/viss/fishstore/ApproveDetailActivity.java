package com.viss.fishstore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.request.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class ApproveDetailActivity extends AppCompatActivity {
    String id_penjualan;
    private ProgressDialog pDialog;
    TextView nama_cupang, harga, nama_pembeli, notelp_pembeli, alamat_pembeli;
    private static final String url = "http://10.0.2.2/db_penjualan/get_penjualan.php";
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_detail);
        getSupportActionBar().hide();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id_penjualan = extras.getString("id_penjualan");
        }

        nama_cupang = findViewById(R.id.approve_nama_cupang_detal);
        harga = findViewById(R.id.approve_harga_detal);
        nama_pembeli = findViewById(R.id.approve_nama_pembeli_detal);
        notelp_pembeli = findViewById(R.id.approve_notelp_pembeli_detal);
        alamat_pembeli = findViewById(R.id.approve_alamat_pembeli_detal);
        img = findViewById(R.id.approve_img_detal);

getCupang();

    }
    private void displayLoader() {
        pDialog = new ProgressDialog(ApproveDetailActivity.this);
        pDialog.setMessage("Sedang diproses...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }
    private void getCupang() {
        displayLoader();
        JSONObject request = new JSONObject();
        try {
            request.put("id_penjualan", id_penjualan);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, url, request, response -> {
                    pDialog.dismiss();
                    try {
                        if (response.getInt("status") == 0) {
                            Glide.with(this).load(response.getString("gambar_cupang")).into(img);
                            nama_cupang.setText(response.getString("nama_cupang"));
                            harga.setText(response.getString("harga_penjualan"));
                            nama_pembeli.setText(response.getString("nama_pembeli"));
                            notelp_pembeli.setText(response.getString("notelp_pembeli"));
                            alamat_pembeli.setText(response.getString("alamat_pembeli"));


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