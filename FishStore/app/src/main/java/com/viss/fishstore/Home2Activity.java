package com.viss.fishstore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Home2Activity extends AppCompatActivity {

    LinearLayout logout, input, pending, setting;
    private SessionHandler session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        getSupportActionBar().hide();
        session = new SessionHandler(getApplicationContext());
        logout = findViewById(R.id.btn_logout1);
        input = findViewById(R.id.btn_input1);
        pending = findViewById(R.id.btn_pending);
        setting = findViewById(R.id.btn_setting1);

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(intent);
            }
        });
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home2Activity.this, CupangActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home2Activity.this, ApproveActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(view -> new AlertDialog.Builder(Home2Activity.this)
                .setTitle("Konfirmasi")
                .setMessage("Anda yakin mau logout ?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Ya, Logout", (dialog, whichButton) -> {
                    session.logoutUser();
                    Intent intent = new Intent(Home2Activity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("Tidak", null).show());
    }
}