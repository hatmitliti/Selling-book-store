package com.example.book.ThuKho;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.book.ChuCuaHang.ChangePassChuCuaHang;
import com.example.book.MainActivity;
import com.example.book.R;
import com.google.firebase.auth.FirebaseAuth;

public class Thu_kho_main extends AppCompatActivity {

   private ImageView imgTongHop,imgLoaiSach,imgNhapHang,imgXuatHang,imgHangTon,imgCongTy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thu_kho_main);
        setControl();
        setAction();

    }

    private void setAction() {
        imgTongHop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Thu_kho_tong_hop.class));
            }
        });
        imgHangTon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Thu_kho_hang_ton.class));
            }
        });
        imgLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Thu_kho_loai_sach.class));
            }
        });
        imgNhapHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Thu_kho_nhap_hang.class));
            }
        });
        imgXuatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Thu_kho_xuat_hang.class));
            }
        });
        imgCongTy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Thu_Kho_QuanLyCongTy.class));
            }
        });

        // đăng xuất
        findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finishAffinity();
            }
        });

        findViewById(R.id.btnChangepass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChangePassThuKho.class));
            }
        });
    }

    private void setControl() {
        imgTongHop = findViewById(R.id.btntongHop);
        imgHangTon = findViewById(R.id.btnhangTon);
        imgLoaiSach = findViewById(R.id.btnloaiSach);
        imgXuatHang = findViewById(R.id.btnxuatHang);
        imgNhapHang = findViewById(R.id.btnnhapHang);
        imgCongTy = findViewById(R.id.btnQuanLyCongTy);
    }
}
