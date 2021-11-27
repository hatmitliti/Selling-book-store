package com.example.book.ChuCuaHang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.book.R;

public class ChuCuaHang_ManHinhChinh extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_cua_hang_____man_hinh_chinh);


        findViewById(R.id.CCCthongkedoanhthu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChuCuaHang_ManHinhThongKeDoanhThu.class));
            }
        });
        findViewById(R.id.CCCthongkedanhgia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChuCuaHang_ManHinhThongKeDanhGiaKhachHang.class));
            }
        });
        findViewById(R.id.CCCthongkehangtrongkho).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChuCuaHang_ManHinhThongKeKho.class));
            }
        });
        findViewById(R.id.btnLoout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.btnChangepass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChangePassChuCuaHang.class));
            }
        });

    }
}