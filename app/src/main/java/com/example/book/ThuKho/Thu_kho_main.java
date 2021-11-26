package com.example.book.ThuKho;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.book.R;
import com.google.firebase.auth.FirebaseAuth;

public class Thu_kho_main extends AppCompatActivity {

    Button btntongHop;
    Button btnhangTon;
    Button btnloaiSach;
    Button btnxuatHang;
    Button btnnhapHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thu_kho_main);
        setControl();
        setAction();

    }

    private void setAction() {
        btntongHop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Thu_kho_tong_hop.class));
            }
        });
        btnhangTon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Thu_kho_hang_ton.class));
            }
        });
        btnloaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Thu_kho_loai_sach.class));
            }
        });
        btnnhapHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Thu_kho_nhap_hang.class));
            }
        });
        btnxuatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Thu_kho_xuat_hang.class));
            }
        });

        // đăng xuất
        findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        });
    }

    private void setControl() {
        btntongHop = findViewById(R.id.btntongHop);
        btnhangTon = findViewById(R.id.btnhangTon);
        btnloaiSach = findViewById(R.id.btnloaiSach);
        btnxuatHang = findViewById(R.id.btnxuatHang);
        btnnhapHang = findViewById(R.id.btnnhapHang);
    }
}
