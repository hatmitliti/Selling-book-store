package com.example.book.QuanLy.manage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.book.QuanLy.adapter.BlackList.BlackListAddAdapter;
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

public class BlackList_add extends AppCompatActivity {

    private Context context;
    private ArrayList<User> userList = new ArrayList<>();
    private ArrayList<String> mkey = new ArrayList<>();
    private ArrayList<User> blackList = new ArrayList<>();
    private ListView lvBlackListAdd;
    private EditText edtSearch;
    private Button btnSearch, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_black_list_add);

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
        DatabaseReference dataUser = FirebaseDatabase.getInstance().getReference();
        //Biến Context
        BlackListAddAdapter blackListAddAdapter = new BlackListAddAdapter(context, userList, R.layout.manage_blacklist_add_item);
        lvBlackListAdd.setAdapter(blackListAddAdapter);
//        dataUser.child("users").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                User user = snapshot.getValue(User.class);
//                String key = snapshot.getKey();
//                dataUser.child("blacklist").addChildEventListener(new ChildEventListener() {
//                    @Override
//                    public void onChildAdded(@NonNull DataSnapshot snapshot1, @Nullable String previousChildName) {
//                        String key1 = snapshot1.getKey();
//                        User data = snapshot1.getValue(User.class);
//                        if (!key1.equals(key)) {
//                            userList.add(user);
//                            blackListAddAdapter.notifyDataSetChanged();
//                        }
//                    }
//
//                    @Override
//                    public void onChildChanged(@NonNull DataSnapshot snapshot2, @Nullable String previousChildName) {
//                    }
//
//                    @Override
//                    public void onChildRemoved(@NonNull DataSnapshot snapshot3) {
//                        String key3 = snapshot3.getKey();
//                        if (!key3.equals(key)) {
//                            if (userList.contains(snapshot3.getValue(User.class)) == false) {
//                                userList.add(snapshot3.getValue(User.class));
//                                blackListAddAdapter.notifyDataSetChanged();
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onChildMoved(@NonNull DataSnapshot snapshot4, @Nullable String previousChildName) {
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                    }
//                });
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
        dataUser.child("blacklist").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                blackList.add(user);
                mkey.add(snapshot.getKey());
                if(userList.contains(user)==true)
                {
                    userList.remove(user);
                    blackListAddAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                blackList.remove(user);
                mkey.remove(snapshot.getKey());
                if(userList.contains(user)==false)
                {
                    userList.add(user);
                    blackListAddAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        dataUser.child("users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if(mkey.contains(snapshot.getKey())==false)
                {
                    userList.add(user);
                    mkey.add(snapshot.getKey());
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

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtSearch.getText().toString().equals("")) {
                    edtSearch.setError(getResources().getString(R.string.empty_field));
                } else {
                    dataUser.child("users").addChildEventListener(new ChildEventListener() {
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
        lvBlackListAdd = findViewById(R.id.listUserBlackList);
        edtSearch = findViewById(R.id.searchAddUserBlackList);
        btnSearch = findViewById(R.id.btnTimAddUserBlackList);
       // back = findViewById(R.id.backThemTKBlackList);
    }
}