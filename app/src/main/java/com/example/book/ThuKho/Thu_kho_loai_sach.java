package com.example.book.ThuKho;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.book.R;
import com.example.book.ThuKho.Adapter.AdapterCategory;
import com.example.book.ThuKho.Object.Category;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.UUID;

public class Thu_kho_loai_sach extends AppCompatActivity {

    Button btnThemLoaiSach;
    Button btnSuaLoaiSach;
    EditText edtLoaiSach;
    GridView lvLoaiSach;
    Button btnXoaLoaiSach;

    ArrayList<Category> list;
    ArrayList<Integer> listNumber;
    AdapterCategory adapterCategory;
    ArrayList<String> listCategoryProduct;
    Category category;
    int viTri = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thu_kho_loai_sach);
        setControl();
        list = new ArrayList<>();
        listNumber = new ArrayList<>();
        listCategoryProduct = new ArrayList<>();

        btnSuaLoaiSach.setEnabled(false);
        btnXoaLoaiSach.setEnabled(false);


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
        getData();

        adapterCategory = new AdapterCategory(this, R.layout.thu_kho_loai_sach_item, list, listCategoryProduct);
        lvLoaiSach.setAdapter(adapterCategory);


        lvLoaiSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                category = list.get(position);
                edtLoaiSach.setText(category.getName());
                btnThemLoaiSach.setEnabled(false);
                btnSuaLoaiSach.setEnabled(true);
                btnXoaLoaiSach.setEnabled(true);
                viTri = position;
            }
        });
        btnSuaLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtLoaiSach.getText().toString().equals("")) {
                    edtLoaiSach.setError(getResources().getString(R.string.empty_field));
                } else {
                    String str = category.getName();
                    category = null;
                    // sửa trong product
                    DatabaseReference mDatabaseProduct = FirebaseDatabase.getInstance().getReference("products");
                    mDatabaseProduct.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            if (snapshot.child("category").getValue(String.class).equals(str)) {
                                String str = edtLoaiSach.getText().toString();
                                mDatabaseProduct.child(snapshot.getKey()).child("category").setValue(str);
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
                    // sửa trong category
                    DatabaseReference mDatabaseCategory = FirebaseDatabase.getInstance().getReference("categorys");
                    mDatabaseCategory.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            if (snapshot.child("name").getValue(String.class).equals(str)) {
                                mDatabaseCategory.child(snapshot.getKey()).child("name").setValue(edtLoaiSach.getText().toString());
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
                    //  edtLoaiSach.setText("");
                    btnThemLoaiSach.setEnabled(true);
                    btnSuaLoaiSach.setEnabled(false);
                    btnXoaLoaiSach.setEnabled(false);

                    // gọi lại và set lại list
                    list.clear();
                    listCategoryProduct.clear();
                    getData();
                    adapterCategory.notifyDataSetChanged();
                }

            }
        });
        btnXoaLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listNumber.clear();
                for (int j = 0; j < list.size(); j++) {
                    int dem = 0;
                    for (int k = 0; k < listCategoryProduct.size(); k++) {
                        if (listCategoryProduct.get(k).equals(list.get(j).getName())) {
                            dem++;
                        }
                    }
                    listNumber.add(dem);
                }
                if (listNumber.get(viTri) != 0) {
                    Toast.makeText(getApplicationContext(), "Không thể xóa !", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("categorys");
                    mDatabase.child(category.getId()).removeValue();
                    category = null;
                    edtLoaiSach.setText("");
                    btnThemLoaiSach.setEnabled(true);
                    btnSuaLoaiSach.setEnabled(false);
                    btnXoaLoaiSach.setEnabled(false);
                }
            }
        });
        btnThemLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = edtLoaiSach.getText().toString();
                if (!str.equals("")) {
                    String id = UUID.randomUUID().toString();
                    Category category = new Category(id, str);
                    DatabaseReference mDatabaseCategory = FirebaseDatabase.getInstance().getReference("categorys");
                    mDatabaseCategory.child(category.getId()).setValue(category);
                } else {
                    edtLoaiSach.setError(getResources().getString(R.string.empty_field));
                }
            }
        });
    }

    private void setControl() {
        btnThemLoaiSach = findViewById(R.id.btnThemLoaiSach);
        btnSuaLoaiSach = findViewById(R.id.btnSuaLoaiSach);
        edtLoaiSach = findViewById(R.id.edtLoaiSach);
        lvLoaiSach = findViewById(R.id.lvLoaiSach);
        btnXoaLoaiSach = findViewById(R.id.btnXoaLoaiSachItem);
    }

    public void getData() {
        // lấy danh sách loại:
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("categorys");
        mDatabase.addChildEventListener(new ChildEventListener() {
            int num = 0;

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Category category = snapshot.getValue(Category.class);
                list.add(category);
                adapterCategory.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Category category = snapshot.getValue(Category.class);
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).getId().equals(snapshot.child("id").getValue(String.class))) {

                        // lấy lại tên loại cũ
                        String name = list.get(j).getName();
                        //set lại tên loại trong ds loại:
                        list.get(j).setName(snapshot.child("name").getValue(String.class));

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        for (int k = 0; k < listCategoryProduct.size(); k++) {
                            if (listCategoryProduct.get(k).equals(name)) {
                                listCategoryProduct.set(k, category.getName());
                            }
                        }

                        adapterCategory.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                for (int j = 0; j < list.size(); j++) {
                    if (snapshot.getKey().equals(list.get(j).getId())) {
                        list.remove(j);
                        // listNumber.remove(j);
                        adapterCategory.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // lấy danh sách loại trong product
        DatabaseReference mDatabaseProduct = FirebaseDatabase.getInstance().getReference("products");
        mDatabaseProduct.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String categoryStr = snapshot.child("category").getValue(String.class);
                listCategoryProduct.add(categoryStr);
                //   Toast.makeText(getApplicationContext(), categoryStr, Toast.LENGTH_SHORT).show();
                adapterCategory.notifyDataSetChanged();
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
