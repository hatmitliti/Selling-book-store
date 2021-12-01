package com.example.book.ThuKho;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.book.R;
import com.example.book.ThuKho.Adapter.AdapterChooseCompany;
import com.example.book.ThuKho.Object.Company;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Thu_kho_chon_cong_ty_nhap_hang extends AppCompatActivity {


    public static Company company = null;
    ListView lvChooseCompany;
    AdapterChooseCompany adapterChooseCompany;
    ArrayList<Company> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thu_kho_nhap_hang_chon_cty_nhap_hang);
        list = new ArrayList<>();
        lvChooseCompany = findViewById(R.id.lvChooseCompany);
        adapterChooseCompany = new AdapterChooseCompany(getApplicationContext(), R.layout.thu_kho_nhap_hang_chon_cty_nhap_hang_item, list);
        lvChooseCompany.setAdapter(adapterChooseCompany);

        FirebaseDatabase.getInstance().getReference("company")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        list.add(snapshot.getValue(Company.class));
                        adapterChooseCompany.notifyDataSetChanged();
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

        lvChooseCompany.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                company = list.get(i);
                onBackPressed();
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