package com.example.book.ChuCuaHang;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.book.ChuCuaHang.thongkekho.SPKho;
import com.example.book.ChuCuaHang.thongkekho.SPKhoAdapter;
import com.example.book.R;
import com.example.book.ThuKho.TKQuanLiSanPham.Product;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChuCuaHang_ManHinhThongKeKho extends AppCompatActivity {
    private ListView lvThongKeKho;
    private Context context;
    private ArrayList<SPKho> listSPKho;
    private ArrayList<String> mKey = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_cua_hang_____man_hinh_thong_ke_kho);
        setControl();
        setEvent();
    }

    private void setEvent() {
        // Biến FireBase
        DatabaseReference dataProduct = FirebaseDatabase.getInstance().getReference();
        //Biến Context
        context = this;
        listSPKho = new ArrayList<>();
        SPKhoAdapter spKhoAdapter = new SPKhoAdapter(R.layout.item_adapter_thongkekho, context, listSPKho);
        lvThongKeKho.setAdapter(spKhoAdapter);

        dataProduct.child("products").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                SPKho spKho = new SPKho(snapshot.getValue(Product.class).getId(),
                        snapshot.getValue(Product.class).getTenSanPham(), snapshot.getValue(Product.class).getStock());
                listSPKho.add(spKho);
                spKhoAdapter.notifyDataSetChanged();
                //
                String key = snapshot.getKey();
                mKey.add(key);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String key = snapshot.getKey();
                int index = mKey.indexOf(key);
                //
                String maSP =  snapshot.getValue(Product.class).getId();
                String tenSP = snapshot.getValue(Product.class).getTenSanPham();
                int soLuongSP = snapshot.getValue(Product.class).getStock();
                //
                SPKho spKho = new SPKho(maSP,tenSP,soLuongSP);
                listSPKho.set(index, spKho);
                spKhoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                String key = snapshot.getKey();
                int index = mKey.indexOf(key);
                listSPKho.remove(index);
                spKhoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setControl() {
        lvThongKeKho = findViewById(R.id.lvThongKeKho);
    }
}