package com.example.store.XuLyHD;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.store.ChuCuaHang.ChuCuaHang_ManHinhThongKeDonHangBiHuy;
import com.example.store.R;

public class NguoiXuLyHoaDon_ManHinhChinh extends AppCompatActivity {
    Button btnXuLyHoaDon, btnDanhSachDaDuocXuLy, btnDanhSachDaBiHuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_xu_ly_hoa_don__man_hinh_chinh);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnXuLyHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NguoiXuLyHoaDon_ManHinhDanhSachDonHangChoXuLy.class));
            }
        });
        btnDanhSachDaDuocXuLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NguoiXuLyHoaDon_DanhSachDonHangDaXuLy.class));
            }
        });
        btnDanhSachDaBiHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChuCuaHang_ManHinhThongKeDonHangBiHuy.class));
            }
        });
    }

    private void setControl() {
        btnXuLyHoaDon = findViewById(R.id.XLHDXuLyDonHang);
        btnDanhSachDaDuocXuLy = findViewById(R.id.XLHDDanhSachDaDuocXuLy);
        btnDanhSachDaBiHuy = findViewById(R.id.XLHDDanhSachDaBiHuy);
    }
}