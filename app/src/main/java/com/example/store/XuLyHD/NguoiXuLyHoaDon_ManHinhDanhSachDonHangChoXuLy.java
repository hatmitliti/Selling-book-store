package com.example.store.XuLyHD;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.store.XuLyHD.DonHangChoXuLy.Bill;
import com.example.store.XuLyHD.DonHangChoXuLy.DonHangChoXuLy;
import com.example.store.XuLyHD.DonHangChoXuLy.DonHangChoXuLyAdapter;
import com.example.store.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NguoiXuLyHoaDon_ManHinhDanhSachDonHangChoXuLy extends AppCompatActivity {
    private ListView lvDonHangChoXuLy;
    Button btnBackDanhSachDHChoXuLy;
    private Context context;
    private ArrayList<Bill> listDonHangChoXuLy = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_xu_ly_hoa_don_____man_hinh_danh_sach_don_hang_cho_xu_ly);
        setControl();
        setEvent();
    }

    private void setEvent() {
        context = this;
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DonHangChoXuLyAdapter donHangChoXuLyAdapter = new DonHangChoXuLyAdapter(R.layout.item_adapter_danhsachdonhangchoxuly, context, listDonHangChoXuLy);
        lvDonHangChoXuLy.setAdapter(donHangChoXuLyAdapter);
        mDatabase.child("bills").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Bill bill = snapshot.getValue(Bill.class);
                if (bill.getStatus() == 0) {
                    listDonHangChoXuLy.add(bill);
                    donHangChoXuLyAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        lvDonHangChoXuLy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bill bill = listDonHangChoXuLy.get(position);
                Intent intent = new Intent(NguoiXuLyHoaDon_ManHinhDanhSachDonHangChoXuLy.this, NguoiXuLyHoaDon_ManHinhChiTietDonHang.class);
                intent.putExtra("TTHD", bill);
                startActivity(intent);
            }
        });


    }

    private void setControl() {
        lvDonHangChoXuLy = findViewById(R.id.lvDanhSachDonHangChoXuLy);
        btnBackDanhSachDHChoXuLy = findViewById(R.id.backDanhSachDHChoXuLy);
    }
}