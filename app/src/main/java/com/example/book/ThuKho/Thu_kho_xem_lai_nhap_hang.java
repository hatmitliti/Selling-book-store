package com.example.book.ThuKho;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.book.R;
import com.example.book.ThuKho.Adapter.AdapterViewImport;
import com.example.book.ThuKho.Object.ImportProduct;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class Thu_kho_xem_lai_nhap_hang extends AppCompatActivity {

    ListView lvViewImport;
    ArrayList<ImportProduct> list;
    AdapterViewImport adapterViewImport;
    public static ImportProduct importProduct = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thu_kho_xem_lai_nhap_hang);

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

        lvViewImport = findViewById(R.id.lvViewImport);
        list = new ArrayList<>();
        adapterViewImport = new AdapterViewImport(getApplicationContext(), R.layout.thu_kho_xem_lai_nhap_hang_item, list);
        lvViewImport.setAdapter(adapterViewImport);

        FirebaseDatabase.getInstance().getReference("import_product")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        ImportProduct importProduct = snapshot.getValue(ImportProduct.class);
                        list.add(importProduct);
                        adapterViewImport.notifyDataSetChanged();
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

        lvViewImport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                importProduct = list.get(i);
                Intent intent = new Intent(getApplicationContext(), Thu_kho_xem_chi_tiet_nhap_hang.class);
                startActivity(intent);
            }
        });


    }
}