package com.example.book.XuLyHD;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.book.ChuCuaHang.ChuCuaHang_ManHinhThongKeDonHangBiHuy;
import com.example.book.R;

public class NguoiXuLyHoaDon_ManHinhChinh extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_xu_ly_hoa_don__man_hinh_chinh);

        Button XLHDXuLyDonHang = findViewById(R.id.XLHDXuLyDonHang);
        Button XLHDDanhSachDaDuocXuLy = findViewById(R.id.XLHDDanhSachDaDuocXuLy);
        Button XLHDDanhSachDaBiHuy = findViewById(R.id.XLHDDanhSachDaBiHuy);
        Button btnDangXuatXuLyHD = findViewById(R.id.btnDangXuatXuLyHD);

        XLHDXuLyDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NguoiXuLyHoaDon_ManHinhDanhSachDonHangChoXuLy.class));
            }
        });
        XLHDDanhSachDaDuocXuLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NguoiXuLyHoaDon_DanhSachDonHangDaXuLy.class));
            }
        });
        XLHDDanhSachDaBiHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChuCuaHang_ManHinhThongKeDonHangBiHuy.class));
            }
        });
        btnDangXuatXuLyHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
onBackPressed();
            }
        });
    }
}