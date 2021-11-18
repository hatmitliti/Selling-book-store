package com.example.book.ChuCuaHang;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.book.ChuCuaHang.thongkedanhgia.DanhGia;
import com.example.book.ChuCuaHang.thongkedanhgia.DanhGiaAdapter;
import com.example.book.ChuCuaHang.thongkedanhgia.User;
import com.example.book.ChuCuaHang.thongkedanhgia.evaluteFireBase;
import com.example.book.R;
import com.example.book.ThuKho.TKQuanLiSanPham.Product;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class ChuCuaHang_ManHinhThongKeDanhGiaTot extends AppCompatActivity {
    private Spinner spnTKDGXthang, spnTKDGXnam;
    private ListView lvThongKeDanhGiaTot;
    private Context context;
    private ArrayList<DanhGia> listDanhGiaTot;
    private RadioButton rdbTKDGTheoNgay,rdbTKDGTheoThang;
    private Button btnThongKeDanhGia;
    private EditText edtThongKeTheoNgay;
    private ArrayList<String> mKey = new ArrayList<>();
    private HashMap<String,String> listUser = new HashMap<>();
    private ArrayList<Product> listProduct = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_cua_hang_____man_hinh_thong_ke_danh_gia_tot);
        setControl();
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
        // Định dạng số
        NumberFormat currentLocale = NumberFormat.getInstance();
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);
        // Biến FireBase
        DatabaseReference dataEvalute = FirebaseDatabase.getInstance().getReference();
        //Biến Context
        context = this;
        // đổ lữ liệu cho spinner
        String[] dataspinnerThang = {
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"
        };
        String[] dataspinnerNam = {
                "2019", "2020", "2021"
        };
        //Gán Dữ liệu vào Adapter
        ArrayAdapter<String> adapterThang = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, dataspinnerThang);
        ArrayAdapter<String> adapterNam = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, dataspinnerNam);
        //
        adapterNam.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        adapterThang.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        //
        spnTKDGXthang.setAdapter(adapterThang);
        spnTKDGXnam.setAdapter(adapterNam);
        spnTKDGXthang.setEnabled(false);
        spnTKDGXnam.setEnabled(false);

        listDanhGiaTot = new ArrayList<>();
        DanhGiaAdapter danhGiaAdapter = new DanhGiaAdapter( R.layout.item_adapter_thongkedanhgiatotxau,context, listDanhGiaTot);
        lvThongKeDanhGiaTot.setAdapter(danhGiaAdapter);

          /*

        Xử Lý Chọn Phương Thức Thống Kê (Thống Kê Theo Ngày/ Thống Kê Theo Tháng)

         */
        rdbTKDGTheoNgay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rdbTKDGTheoNgay.isChecked() == true) {
                    edtThongKeTheoNgay.setEnabled(true);
                } else {
                    edtThongKeTheoNgay.setEnabled(false);
                }
            }
        });
        rdbTKDGTheoThang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rdbTKDGTheoThang.isChecked() == true) {
                    spnTKDGXthang.setEnabled(true);
                    spnTKDGXnam.setEnabled(true);
                } else {
                    spnTKDGXthang.setEnabled(false);
                    spnTKDGXnam.setEnabled(false);
                }
            }
        });

        // lấy danh sách dữ liệu User
        dataEvalute.child("users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                listUser.put(snapshot.getKey(),snapshot.getValue(User.class).getName());
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
        // Lấy Danh Sách Dữ Liệu Product
        dataEvalute.child("products").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                listProduct.add(snapshot.getValue(Product.class));
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

        /*

        Xử Lý Sự Kiện Cho Button Thống Kê

         */
        btnThongKeDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, listProduct.toString() + "", Toast.LENGTH_SHORT).show();
                if (rdbTKDGTheoNgay.isChecked() == true || rdbTKDGTheoThang.isChecked() == true) {
                    if (rdbTKDGTheoNgay.isChecked() == true) {
                        if (edtThongKeTheoNgay.getText().toString().trim().equals("") == false) {
                            listDanhGiaTot.clear();
                            danhGiaAdapter.notifyDataSetChanged();
                            mKey.clear();
                            dataEvalute.child("evalute").addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                    if (snapshot.getValue(evaluteFireBase.class).getStar() > 3) {
                                        if (snapshot.getValue(evaluteFireBase.class).getDate().contains(edtThongKeTheoNgay.getText()) == true) {

                                            int soSao = snapshot.getValue(evaluteFireBase.class).getStar();
                                            String userName = listUser.get(snapshot.getValue(evaluteFireBase.class).getId_user());
                                            String productName = getProductName(snapshot.getValue(evaluteFireBase.class).getId_product());
                                            DanhGia dg = new DanhGia(userName,productName,soSao);
                                            listDanhGiaTot.add(dg);
                                            danhGiaAdapter.notifyDataSetChanged();
                                            //
                                            String key = snapshot.getKey();
                                            mKey.add(key);
                                        }
                                    }
                                }

                                @Override
                                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                    // lấy địa chỉ id của đối tượng vừa bị thay đổi bên trong mảng mkey
                                    String key = snapshot.getKey();
                                    int index = mKey.indexOf(key);
                                    // thay đổi dữ liệu trong gridview giống với dữ liệu trên firebase
                                    if (snapshot.getValue(evaluteFireBase.class).getStar() > 3 && snapshot.getValue(evaluteFireBase.class).getDate().contains(edtThongKeTheoNgay.getText()) == true
                                            && mKey.contains(key) == false) {

                                        int soSao = snapshot.getValue(evaluteFireBase.class).getStar();
                                        String userName = listUser.get(snapshot.getValue(evaluteFireBase.class).getId_user());
                                        String productName = getProductName(snapshot.getValue(evaluteFireBase.class).getId_product());
                                        DanhGia dg = new DanhGia(userName,productName,soSao);
                                        listDanhGiaTot.add(dg);
                                        mKey.add(snapshot.getKey());

                                    } else if (snapshot.getValue(evaluteFireBase.class).getStar() > 3 && snapshot.getValue(evaluteFireBase.class).getDate().contains(edtThongKeTheoNgay.getText()) == true
                                            && mKey.contains(key) == true) {
                                        int soSao = snapshot.getValue(evaluteFireBase.class).getStar();
                                        String userName = listUser.get(snapshot.getValue(evaluteFireBase.class).getId_user());
                                        String productName = getProductName(snapshot.getValue(evaluteFireBase.class).getId_product());
                                        DanhGia dg = new DanhGia(userName,productName,soSao);
                                        listDanhGiaTot.set(index, dg);

                                    } else if (snapshot.getValue(evaluteFireBase.class).getStar() <= 3 && snapshot.getValue(evaluteFireBase.class).getDate().contains(edtThongKeTheoNgay.getText()) == false
                                            && mKey.contains(key) == true ||
                                            snapshot.getValue(evaluteFireBase.class).getStar() <= 3 && snapshot.getValue(evaluteFireBase.class).getDate().contains(edtThongKeTheoNgay.getText()) == true
                                                    && mKey.contains(key) == true ||
                                            snapshot.getValue(evaluteFireBase.class).getStar() > 3 && snapshot.getValue(evaluteFireBase.class).getDate().contains(edtThongKeTheoNgay.getText()) == false
                                                    && mKey.contains(key) == true) {
                                        listDanhGiaTot.remove(index);
                                        mKey.remove(key);
                                    }
                                    danhGiaAdapter.notifyDataSetChanged();
                                    Toast.makeText(context, "Đã có sự thay đổi dữ liệu từ hệ thống", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                                    String key = snapshot.getKey();
                                    int index = mKey.indexOf(key);
                                    listDanhGiaTot.remove(index);
                                    danhGiaAdapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        } else {
                            listDanhGiaTot.clear();
                            danhGiaAdapter.notifyDataSetChanged();
                            Toast.makeText(context, "Vui Lòng Nhập Ngày Để Thống Kê", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if (rdbTKDGTheoThang.isChecked() == true) {
                        String time;
                        listDanhGiaTot.clear();
                        danhGiaAdapter.notifyDataSetChanged();
                        mKey.clear();
                        if (Integer.parseInt(spnTKDGXthang.getSelectedItem().toString()) < 10) {
                            time = spnTKDGXnam.getSelectedItem().toString() + "/" + "0" + spnTKDGXthang.getSelectedItem().toString();
                        } else {
                            time = spnTKDGXnam.getSelectedItem().toString() + "/" + spnTKDGXthang.getSelectedItem().toString();
                        }
                        dataEvalute.child("evalute").addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                if (snapshot.getValue(evaluteFireBase.class).getStar() > 3) {
                                    if (snapshot.getValue(evaluteFireBase.class).getDate().contains(time) == true) {

                                        int soSao = snapshot.getValue(evaluteFireBase.class).getStar();
                                        String userName = listUser.get(snapshot.getValue(evaluteFireBase.class).getId_user());
                                        String productName = getProductName(snapshot.getValue(evaluteFireBase.class).getId_product());
                                        Toast.makeText(context, snapshot.getValue(evaluteFireBase.class).getId_product() + "", Toast.LENGTH_SHORT).show();
                                        DanhGia dg = new DanhGia(userName,productName,soSao);
                                        listDanhGiaTot.add(dg);
                                        danhGiaAdapter.notifyDataSetChanged();
                                        //
                                        String key = snapshot.getKey();
                                        mKey.add(key);
                                    }
                                }
                            }

                            @Override
                            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                // lấy địa chỉ id của đối tượng vừa bị thay đổi bên trong mảng mkey
                                String key = snapshot.getKey();
                                int index = mKey.indexOf(key);
                                // thay đổi dữ liệu trong gridview giống với dữ liệu trên firebase
                                if (snapshot.getValue(evaluteFireBase.class).getStar() > 3 && snapshot.getValue(evaluteFireBase.class).getDate().contains(time) == true
                                        && mKey.contains(key) == false) {

                                    int soSao = snapshot.getValue(evaluteFireBase.class).getStar();
                                    String userName = listUser.get(snapshot.getValue(evaluteFireBase.class).getId_user());
                                    String productName = getProductName(snapshot.getValue(evaluteFireBase.class).getId_product());
                                    DanhGia dg = new DanhGia(userName,productName,soSao);
                                    listDanhGiaTot.add(dg);
                                    mKey.add(snapshot.getKey());
                                } else if (snapshot.getValue(evaluteFireBase.class).getStar() >  3 && snapshot.getValue(evaluteFireBase.class).getDate().contains(time) == true
                                        && mKey.contains(key) == true) {
                                    int soSao = snapshot.getValue(evaluteFireBase.class).getStar();
                                    String userName = listUser.get(snapshot.getValue(evaluteFireBase.class).getId_user());
                                    String productName = getProductName(snapshot.getValue(evaluteFireBase.class).getId_product());
                                    DanhGia dg = new DanhGia(userName,productName,soSao);
                                    listDanhGiaTot.set(index, dg);
                                } else if (snapshot.getValue(evaluteFireBase.class).getStar() <= 3 && snapshot.getValue(evaluteFireBase.class).getDate().contains(time) == false
                                        && mKey.contains(key) == true ||
                                        snapshot.getValue(evaluteFireBase.class).getStar() <= 3 && snapshot.getValue(evaluteFireBase.class).getDate().contains(time) == true
                                                && mKey.contains(key) == true ||
                                        snapshot.getValue(evaluteFireBase.class).getStar() > 3 && snapshot.getValue(evaluteFireBase.class).getDate().contains(time) == false
                                                && mKey.contains(key) == true) {
                                    listDanhGiaTot.remove(index);
                                    mKey.remove(key);
                                }
                                danhGiaAdapter.notifyDataSetChanged();
                                Toast.makeText(context, "Đã có sự thay đổi dữ liệu từ hệ thống", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                                String key = snapshot.getKey();
                                int index = mKey.indexOf(key);
                                listDanhGiaTot.remove(index);
                                danhGiaAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                } else {
                    Toast.makeText(context, "Vui Lòng Chọn Phương Thức Để Thống Kê.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private String getProductName(String productID)
    {
        for(int i = 0;i<listProduct.size();i++)
        {
            if(listProduct.get(i).getId().equals(productID))
            {
                return listProduct.get(i).getTenSanPham();
            }
        }
        return null;
    }

    private void setControl() {
        lvThongKeDanhGiaTot = findViewById(R.id.lvthongkedanhgiatot);
        spnTKDGXnam = findViewById(R.id.spnTKDGTnam);
        spnTKDGXthang = findViewById(R.id.spnTKDGTthang);
        rdbTKDGTheoNgay = findViewById(R.id.rdbTKDGTthongketheongay);
        rdbTKDGTheoThang = findViewById(R.id.rdbTKDGTthongketheothang);
        edtThongKeTheoNgay = findViewById(R.id.edtTKDGTthongketheongay);
        btnThongKeDanhGia = findViewById(R.id.btnTKDGTThongKe);
    }
}