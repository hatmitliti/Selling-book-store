package com.example.book.ChuCuaHang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.book.R;

public class ChuCuaHang_ManHinhThongKeDoanhThu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_cua_hang_____man_hinh_thong_ke_doanh_thu);

        findViewById(R.id.TKDTthongkedonhang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChuCuaHang_ManHinhThongKeDonHang.class));
            }
        });
        findViewById(R.id.TKDTthongkedonhangbihuy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChuCuaHang_ManHinhThongKeDonHangBiHuy.class));
            }
        });
        findViewById(R.id.TKDTsotientrachovanchuyen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChuCuaHang_ManHinhThongKeTienTraDVVC.class));
            }
        });
        findViewById(R.id.TKDTdoanhthu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChuCuaHang_ManHinhDoanhThu.class));
            }
        });
        // toolbarr
        Toolbar toolbar = findViewById(R.id.toobar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}