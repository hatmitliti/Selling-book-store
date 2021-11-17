package com.example.book.SoanDon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.book.R;
import com.example.book.SoanDon.adapter.AdapterChuaDongGoi;
import com.example.book.SoanDon.adapter.OrderAdapter;
import com.example.book.SoanDon.models.User;
import com.example.book.XuLyHD.DonHangChoXuLy.Bill;
import com.example.book.XuLyHD.DonHangChoXuLy.Bill_Product;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;

public class DetailBillShipper extends AppCompatActivity {

    OrderAdapter adapter;
    ArrayList<Bill_Product> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.packager_chi_tiet_don_dang_giao);
        list = new ArrayList<>();
        adapter= new OrderAdapter(this, R.layout.packager_order_items, list);
        ImageView imgUserOrderDetail = findViewById(R.id.imgUserOrderDetail);
        TextView txtNameOrderDetail = findViewById(R.id.txtNameOrderDetail);
        TextView txtPhoneOrderDetail = findViewById(R.id.txtPhoneOrderDetail);
        TextView txtDiaChi = findViewById(R.id.txtDiaChi);
        TextView txtnameshipper = findViewById(R.id.txtnameshipper);
        ListView lvProduct = findViewById(R.id.lvProduct);

        lvProduct.setAdapter(adapter);
        Intent intent = getIntent();
        // lấy hình ảnh user và tên
        FirebaseDatabase.getInstance().getReference("users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getKey().equals(intent.getStringExtra("id_user"))) {

                    Picasso.get().load(snapshot.getValue(User.class).getImage()).into(imgUserOrderDetail);
                    User user = snapshot.getValue(User.class);
                    txtNameOrderDetail.setText(user.getName());
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


        // lấy thông tin bill:
        FirebaseDatabase.getInstance().getReference("bills").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getKey().equals(intent.getStringExtra("id"))) {
                    Bill bill = snapshot.getValue(Bill.class);
                    txtPhoneOrderDetail.setText(bill.getPhone());
                    txtDiaChi.setText(bill.getAddress());
                    // lấy tên shipper:
                    FirebaseDatabase.getInstance().getReference("shipper").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            if (snapshot.getKey().equals(bill.getShipper())) {
                                String name = snapshot.child("name").getValue(String.class);
                                txtnameshipper.setText(name);
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


        // lấy thông tin sản phẩm trong bill:
        FirebaseDatabase.getInstance().getReference("bill_detail").child(intent.getStringExtra("id")).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Bill_Product bill_product = snapshot.getValue(Bill_Product.class);
                list.add(bill_product);
                adapter.notifyDataSetChanged();
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