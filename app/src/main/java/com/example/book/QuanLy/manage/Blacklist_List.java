package com.example.book.QuanLy.manage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.book.QuanLy.adapter.BlackList.BlackListAdapter;
import com.example.book.QuanLy.adapter.User.User;
import com.example.book.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Blacklist_List extends AppCompatActivity {
    private Context context;
    private ArrayList<User> userList = new ArrayList<>();
    private ListView lvBlackListList;
    private EditText edtSearch;
    private Button btnSearch, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_blacklist_list);

        setControll();
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
        //
        NumberFormat currentLocale = NumberFormat.getInstance();
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);
        // Biến FireBase
        DatabaseReference dataBlackList = FirebaseDatabase.getInstance().getReference();
        //Biến Context
        BlackListAdapter blackListAddAdapter = new BlackListAdapter(context, userList, R.layout.manage_blacklist_list_item);
        lvBlackListList.setAdapter(blackListAddAdapter);

        dataBlackList.child("blacklist").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                userList.add(snapshot.getValue(User.class));
                blackListAddAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                userList.remove(snapshot.getValue(User.class));
                blackListAddAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtSearch.getText().toString().equals("")) {
                    Toast.makeText(context, "Vui Lòng Nhập Dữ Liệu Trước Khi Tìm Kiếm", Toast.LENGTH_SHORT).show();
                } else {
                    dataBlackList.child("blacklist").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            if (snapshot.getValue(User.class).getName().contains(edtSearch.getText().toString())) {
                                userList.clear();
                                userList.add(snapshot.getValue(User.class));
                                blackListAddAdapter.notifyDataSetChanged();
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
        });

    }

    private void setControll() {
        lvBlackListList = findViewById(R.id.listDSTKBlackList);
        edtSearch = findViewById(R.id.searchDSTKBlackList);
        btnSearch = findViewById(R.id.btnDSTKTimBackList);
      //  back = findViewById(R.id.backDSTKBlackList);
    }
}