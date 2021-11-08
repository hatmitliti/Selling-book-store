package com.example.book.SoanDon;import android.content.Intent;import android.os.Bundle;import android.view.View;import android.widget.AdapterView;import android.widget.ArrayAdapter;import android.widget.ListView;import androidx.annotation.NonNull;import androidx.annotation.Nullable;import androidx.appcompat.app.AppCompatActivity;import com.example.book.R;import com.example.book.XuLyHD.DonHangChoXuLy.Bill;import com.google.firebase.database.ChildEventListener;import com.google.firebase.database.DataSnapshot;import com.google.firebase.database.DatabaseError;import com.google.firebase.database.DatabaseReference;import com.google.firebase.database.FirebaseDatabase;import java.util.ArrayList;public class PackagedActivity extends AppCompatActivity {    ArrayList<String> list = new ArrayList<>();    ArrayAdapter<String> adapter;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.packager_shipper_layout);        ListView listView = findViewById(R.id.listPackaged);        setTitle("Đã đóng gói");        adapter = new ArrayAdapter<String>(this, R.layout.packager_unpacked_items, R.id.txtDonhang, list);        listView.setAdapter(adapter);        getDataInDatabase();        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {            @Override            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {                Intent intent = new Intent(getApplicationContext(), PackagedActivityShipper.class);                intent.putExtra("id_bill", list.get(position));                startActivity(intent);            }        });    }    private void getDataInDatabase() {        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();        mDatabase.child("bills").addChildEventListener(new ChildEventListener() {            @Override            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {                Bill bill = snapshot.getValue(Bill.class);                if (bill.getStatus() == 2) {                    list.add(snapshot.getKey());                    adapter.notifyDataSetChanged();                }            }            @Override            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {                String key = snapshot.getKey();                for (int j = 0; j < list.size(); j++) {                    if (list.get(j).equals(key)) {                        list.remove(j);                        adapter.notifyDataSetChanged();                    }                }            }            @Override            public void onChildRemoved(@NonNull DataSnapshot snapshot) {            }            @Override            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {            }            @Override            public void onCancelled(@NonNull DatabaseError error) {            }        });    }}