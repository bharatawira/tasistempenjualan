package com.viss.fishstore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.request.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class InputCupangActivity extends AppCompatActivity {
    private ProgressDialog pDialog;
    public static final String url = "http://10.0.2.2/db_penjualan/get_cupang.php";
    private static final String url_post = "http://10.0.2.2/db_penjualan/tambah_penjualan.php";
    String id_cupang;
    TextView nama, harga;
    ImageView gambar;
    EditText np, nop, ap, harga_jual;
    Button simpan;
    String nama_pembeli, notelp_pembeli, alamat_pembeli, harga_penjualan;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_cupang);
        getSupportActionBar().hide();
        Bundle extras = getIntent().getExtras();
        SessionHandler session = new SessionHandler(this);
        user = session.getUserDetails();
        if (extras != null) {
            id_cupang = extras.getString("id_cupang");
        }

        nama = findViewById(R.id.input_nama);
        harga = findViewById(R.id.input_harga);
        gambar = findViewById(R.id.input_gambar);
        np = findViewById(R.id.et_nama_pembeli);
        nop = findViewById(R.id.et_notelp_pembeli);
        ap = findViewById(R.id.et_alamat_pembeli);
        harga_jual = findViewById(R.id.et_harga_penjualan);
        simpan = findViewById(R.id.btn_input_penjualan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                nama_pembeli = np.getText().toString();
                notelp_pembeli = nop.getText().toString();
                alamat_pembeli = ap.getText().toString();
                harga_penjualan = harga_jual.getText().toString();
                if (validateInputs()) {
                    tambahPenjualan();
                }
            }
        });

        getCupang();

    }

    private void tambahPenjualan() {
        displayLoader();
        JSONObject request = new JSONObject();
        try {
            request.put("id_user", user.getId());
            request.put("id_cupang", id_cupang);
            request.put("nama_pembeli", nama_pembeli);
            request.put("notelp_pembeli", notelp_pembeli);
            request.put("alamat_pembeli", alamat_pembeli);
            request.put("harga_penjualan", harga_penjualan);;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, url_post, request, response -> {
                    pDialog.dismiss();
                    try {
                        if (response.getInt("status") == 0) {
                            Toast.makeText(getApplicationContext(),
                                    response.getString("message"), Toast.LENGTH_SHORT).show();
                            finish();
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

    private boolean validateInputs() {
        if (nama_pembeli.equals("")) {
            np.setError("Nama Pembeli tidak boleh kosong");
            np.requestFocus();
            return false;
        }
        if (notelp_pembeli.equals("")) {
            nop.setError("No Telp Pembeli tidak boleh kosong");
            nop.requestFocus();
            return false;
        }
        if (harga_penjualan.equals("")) {
            harga_jual.setError("harga jual tidak boleh kosong");
            harga_jual.requestFocus();
            return false;
        }
        if (alamat_pembeli.equals("")) {
            ap.setError("alamat pembeli tidak boleh kosong");
            ap.requestFocus();
            return false;
        }

        return true;
    }

    private void displayLoader() {
        pDialog = new ProgressDialog(InputCupangActivity.this);
        pDialog.setMessage("Sedang diproses...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }
    private void getCupang() {
        displayLoader();
        JSONObject request = new JSONObject();
        try {
            request.put("id_cupang", id_cupang);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, url, request, response -> {
                    pDialog.dismiss();
                    try {
                        if (response.getInt("status") == 0) {
                            nama.setText(response.getString("nama_cupang"));
                            harga.setText(response.getString("harga_cupang"));
                            Glide.with(this).load(response.getString("gambar_cupang")).into(gambar);

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