package com.viss.fishstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.request.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    RecyclerView lv;
    PenjualanAdapter adapter;
    ArrayList<Penjualan> penjualanlist;
    User user;
    private static final String url = "http://10.0.2.2/db_penjualan/get_approve.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().hide();;
        SessionHandler session = new SessionHandler(this);
        user = session.getUserDetails();
        penjualanlist = new ArrayList<>();
        lv = findViewById(R.id.rv_history);
        lv.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL, false));
        adapter = new PenjualanAdapter(getApplicationContext(), penjualanlist);
        lv.setAdapter(adapter);
        getDataCupang();
    }
    private void displayLoader() {
        pDialog = new ProgressDialog(HistoryActivity.this);
        pDialog.setMessage("Sedang diproses...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void getDataCupang() {
        displayLoader();
        JSONObject request = new JSONObject();
        try {
            request.put("id_user", user.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, url, request, response -> {
                    pDialog.dismiss();
                    try {
                        if (response.getInt("status") == 0) {
                            JSONArray jsonArray = response.getJSONArray("penjualan");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                if(jsonObject.getString("approve").equals("true")){
                                    Penjualan penjualan = new Penjualan();
                                    penjualan.setId_penjualan(jsonObject.getString("id_penjualan"));
                                    penjualan.setNama_pembeli(jsonObject.getString("nama_pembeli"));
                                    penjualan.setNama_cupang(jsonObject.getString("nama_cupang"));
                                    penjualan.setGambar_cupang(jsonObject.getString("gambar_cupang"));
                                    penjualan.setHarga_jual(jsonObject.getString("harga_penjualan"));
                                    penjualanlist.add(penjualan);
                                }
                            }
                            adapter.notifyDataSetChanged();
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