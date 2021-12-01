package com.example.book.ThuKho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.book.R;
import com.example.book.ThuKho.Adapter.AdapterDetailImport;
import com.example.book.ThuKho.Object.ImportProduct;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Thu_kho_xem_chi_tiet_nhap_hang extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thu_kho_xem_chi_tiet_nhap_hang);


        TextView txtNameImport = findViewById(R.id.txtNameImport);
        TextView txtmst = findViewById(R.id.txtmst);
        TextView txtid = findViewById(R.id.txtid);
        ListView lvDetailImport = findViewById(R.id.lvDetailImport);


        ImportProduct importProduct = Thu_kho_xem_lai_nhap_hang.importProduct;


        txtNameImport.setText(importProduct.getNameCty());
        txtmst.setText(importProduct.getDate());
        txtid.setText(importProduct.getId_Company());

        AdapterDetailImport adapterDetailImport = new AdapterDetailImport
                (getApplicationContext(), R.layout.thu_kho_xem_chi_tiet_nhap_hang_item, importProduct.getList());

        lvDetailImport.setAdapter(adapterDetailImport);

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