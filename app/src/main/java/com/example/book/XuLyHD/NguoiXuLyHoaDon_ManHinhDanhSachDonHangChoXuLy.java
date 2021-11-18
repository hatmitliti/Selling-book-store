package com.example.book.XuLyHD;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.book.R;
import com.example.book.XuLyHD.DonHangChoXuLy.Bill;
import com.example.book.XuLyHD.DonHangChoXuLy.DonHangChoXuLyAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NguoiXuLyHoaDon_ManHinhDanhSachDonHangChoXuLy extends AppCompatActivity {
    private ListView lvDonHangChoXuLy;
    private Context context;
    private ArrayList<Bill> listDonHangChoXuLy = new ArrayList<>();
    ArrayList<String> mKey = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_xu_ly_hoa_don_____man_hinh_danh_sach_don_hang_cho_xu_ly);
        setControl();
        setEvent();


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

    private void setEvent() {
        context = this;
        DatabaseReference databill = FirebaseDatabase.getInstance().getReference();
        DonHangChoXuLyAdapter donHangChoXuLyAdapter = new DonHangChoXuLyAdapter(R.layout.item_adapter_danhsachdonhangchoxuly, context, listDonHangChoXuLy);
        lvDonHangChoXuLy.setAdapter(donHangChoXuLyAdapter);

        databill.child("bills").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Bill bill = snapshot.getValue(Bill.class);
                if (bill.getStatus() == 0) {
                    listDonHangChoXuLy.add(bill);
                    donHangChoXuLyAdapter.notifyDataSetChanged();
                    // lấy id của các sản phẩm
                    String key = snapshot.getKey();
                    mKey.add(key);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // lấy địa chỉ id của đối tượng vừa bị thay đổi bên trong mảng mkey
                String key = snapshot.getKey();
                int index = mKey.indexOf(key);
                Bill bill = snapshot.getValue(Bill.class);
                // thay đổi dữ liệu trong gridview giống với dữ liệu trên firebase
//                listDonHangChoXuLy.set(index, snapshot.getValue(Bill.class));
//                donHangChoXuLyAdapter.notifyDataSetChanged();
//                Toast.makeText(context, listDonHangChoXuLy.get(index).getStatus()+"", Toast.LENGTH_SHORT).show();
                if (bill.getStatus() != 0) {
                    if (index != -1 && listDonHangChoXuLy.get(index).getId().trim().equals(bill.getId().trim())) {
                        listDonHangChoXuLy.remove(index);
                        mKey.remove(key);
                        donHangChoXuLyAdapter.notifyDataSetChanged();
                    }
                } else {
                    if (index != -1 && listDonHangChoXuLy.get(index).getId().trim().equals(bill.getId().trim())) {
                        listDonHangChoXuLy.set(index, bill);
                        donHangChoXuLyAdapter.notifyDataSetChanged();
                    } else {
                        listDonHangChoXuLy.add(bill);
                        mKey.add(key);
                        donHangChoXuLyAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                String key = snapshot.getKey();
                int index = mKey.indexOf(key);
                Bill bill = snapshot.getValue(Bill.class);
                if (bill.getStatus() == 0) {
                    listDonHangChoXuLy.remove(index);
                    mKey.remove(key);
                    donHangChoXuLyAdapter.notifyDataSetChanged();
                }

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
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bill bill = listDonHangChoXuLy.get(i);
                Intent intent = new Intent(NguoiXuLyHoaDon_ManHinhDanhSachDonHangChoXuLy.this, NguoiXuLyHoaDon_ManHinhChiTietDonHang.class);
                intent.putExtra("TTDH", bill);
                startActivity(intent);
            }
        });


    }

    private void setControl() {
        lvDonHangChoXuLy = findViewById(R.id.lvDanhSachDonHangChoXuLy);
    }
}