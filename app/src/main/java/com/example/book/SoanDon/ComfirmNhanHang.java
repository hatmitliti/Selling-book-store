package com.example.book.SoanDon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.book.R;
import com.example.book.SoanDon.adapter.ComfirmAdapter;
import com.example.book.ThuKho.Object.ProductExImport;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ComfirmNhanHang extends AppCompatActivity {

    ListView lvComfirm;
    ComfirmAdapter adapter;
    ArrayList<ProductExImport> list;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.packager_comfirm_nhan_hang);
        lvComfirm = findViewById(R.id.lvComfirm);
        list = new ArrayList<>();
        adapter = new ComfirmAdapter(this, R.layout.packager_comfirm_nhan_hang_item, list);
        lvComfirm.setAdapter(adapter);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("exports").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ProductExImport productExImport = snapshot.getValue(ProductExImport.class);
                productExImport.setId(snapshot.getKey());
                list.add(productExImport);
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

    }
}