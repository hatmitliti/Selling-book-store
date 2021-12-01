package com.example.book.ThuKho;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.book.R;
import com.example.book.ThuKho.Adapter.AdapterCompany;
import com.example.book.ThuKho.Object.Company;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Thu_Kho_QuanLyCongTy extends AppCompatActivity {
    private Context context;
    private ArrayList<Company> companyArrayList;
    private AdapterCompany adapterCompany;
    private ListView lvDanhSachCongTy;
    private ArrayList<String> mkey;
    private Button btnThem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thu_kho_quan_ly_cong_ty);

        setControll();
        setEvent();
    }

    private void setEvent() {
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
        //
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("company");
        mkey = new ArrayList<>();
        //
        context = this;
        companyArrayList = new ArrayList<>();
        adapterCompany = new AdapterCompany(R.layout.activity_managementcompany_item,companyArrayList,context);
        lvDanhSachCongTy.setAdapter(adapterCompany);

        data.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Company company = snapshot.getValue(Company.class);
                companyArrayList.add(company);
                adapterCompany.notifyDataSetChanged();

                String key = snapshot.getKey();
                mkey.add(key);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                int index = mkey.indexOf(snapshot.getKey());
                Company company = snapshot.getValue(Company.class);
                companyArrayList.set(index,company);
                adapterCompany.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                int index = mkey.indexOf(snapshot.getKey());
                companyArrayList.remove(index);
                adapterCompany.notifyDataSetChanged();
                mkey.remove(index);
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,Thu_Kho_AddCompany.class));
            }
        });
    }
    private void setControll() {
        lvDanhSachCongTy = findViewById(R.id.listCompany);
        btnThem = findViewById(R.id.btnLayoutAddCompany);
    }
}