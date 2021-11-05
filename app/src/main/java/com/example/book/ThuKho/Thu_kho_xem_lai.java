package com.example.book.ThuKho;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.book.R;
import com.example.book.ThuKho.Adapter.AdapterXemLai;
import com.example.book.ThuKho.Object.ProductExImport;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Thu_kho_xem_lai extends AppCompatActivity {
    ListView lvXemLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thu_kho_xem_lai);
        lvXemLai = findViewById(R.id.lvThuKhoXemLai);

        ArrayList<ProductExImport> list = new ArrayList<>();
        AdapterXemLai adapterXemLai = new AdapterXemLai(this, R.layout.thu_kho_xem_lai_item, list);
        lvXemLai.setAdapter(adapterXemLai);


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("exports");

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ProductExImport productExImport = snapshot.getValue(ProductExImport.class);
                list.add(productExImport);
                adapterXemLai.notifyDataSetChanged();
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
