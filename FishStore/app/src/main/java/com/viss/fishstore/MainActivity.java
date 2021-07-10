package com.viss.fishstore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.request.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    public static final String login_url = "http://10.0.2.2/db_penjualan/login.php";
    private EditText et_username;
    private EditText et_password;
    private String username;
    private String password;
    private SessionHandler session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        session = new SessionHandler(getApplicationContext());

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        Button login = findViewById(R.id.btn_login);

        login.setOnClickListener(v -> {
            username = et_username.getText().toString().toLowerCase().trim();
            password = et_password.getText().toString().trim();
            if (validateInputs()) {
                login();
            }
        });

    }
    private boolean validateInputs() {
        if (username.equals("")) {
            et_username.setError("Username tidak boleh kosong");
            et_username.requestFocus();
            return false;
        }
        if (password.equals("")) {
            et_password.setError("Password tidak boleh kosong");
            et_password.requestFocus();
            return false;
        }
        return true;
    }

    private void displayLoader() {
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Sedang diproses...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void login() {
        displayLoader();

        JSONObject request = new JSONObject();
        try {
            request.put("username", username);
            request.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, login_url, request, response -> {
                    pDialog.dismiss();
                    try {
                        if (response.getInt("status") == 0) {
                            session.loginUser(response.getString("id"),
                                    response.getString("name"));
                            if (response.getString("role").equals("user")) {
                                Intent i = new Intent(getApplicationContext(), Home2Activity.class);
                                i.putExtra("username", username);
                                startActivity(i);
                                finish();
                            }
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