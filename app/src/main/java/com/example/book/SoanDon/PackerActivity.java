package com.example.book.SoanDon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.book.R;

public class PackerActivity extends AppCompatActivity {
    ImageView btnChuaDongGoi;
    ImageView btnDaDongGoi;
    ImageView btnDangVanChuyen;
    ImageView btnDaGiao;
    ImageView btnNhapHangSoanDon;
    ImageView btnDonBiHuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.packager_packer_layout);
        // setTitle("Quản lý đóng gói");

        setControl();

        btnDaGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DeliveredActivity.class));
            }
        });
        btnDaDongGoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PackagedActivity.class));
            }
        });
        btnDangVanChuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), InTransitActivity.class));
            }
        });
        btnChuaDongGoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UnpackedActivity.class));
            }
        });

        btnNhapHangSoanDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ComfirmNhanHang.class));
            }
        });


        btnDonBiHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DeliverCancelActivity.class));
            }
        });
    }

    private void setControl() {
        btnChuaDongGoi = findViewById(R.id.btnChuaDongGoi);
        btnDaDongGoi = findViewById(R.id.btnDaDongGoi);
        btnDangVanChuyen = findViewById(R.id.btnDangVanChuyen);
        btnDaGiao = findViewById(R.id.btnDaGiao);
        btnNhapHangSoanDon = findViewById(R.id.btnNhapHangSoanDon);
        btnDonBiHuy = findViewById(R.id.btnDonBiHuy);
    }
}