package com.example.book.ChuCuaHang;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class ChuCuaHang_ManHinhThongKeDanhGiaXau extends AppCompatActivity {

    private Spinner spnTKDGXthang, spnTKDGXnam;
    private ListView lvThongKeDanhGiaXau;
    private Context context;
    private ArrayList<DanhGia> listDanhGiaXau;
    private RadioButton rdbTKDGTheoNgay,rdbTKDGTheoThang;
    private Button btnThongKeDanhGia;
    private EditText edtThongKeTheoNgay;
    private ArrayList<String> mKey = new ArrayList<>();
    private HashMap<String,String> listUser = new HashMap<>();
    private ArrayList<Product> listProduct = new ArrayList<>();
    private ImageView dateTimePickerDanhGiaXau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_cua_hang_____man_hinh_thong_ke_danh_gia_xau);
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
        dateTimePickerDanhGiaXau.setEnabled(false);

        listDanhGiaXau = new ArrayList<>();
        DanhGiaAdapter danhGiaAdapter = new DanhGiaAdapter( R.layout.item_adapter_thongkedanhgiatotxau,context, listDanhGiaXau);
        lvThongKeDanhGiaXau.setAdapter(danhGiaAdapter);

          /*

        Xử Lý Chọn Phương Thức Thống Kê (Thống Kê Theo Ngày/ Thống Kê Theo Tháng)

         */
        rdbTKDGTheoNgay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rdbTKDGTheoNgay.isChecked() == true) {
                    edtThongKeTheoNgay.setEnabled(true);
                    dateTimePickerDanhGiaXau.setEnabled(true);
                } else {
                    edtThongKeTheoNgay.setEnabled(false);
                    dateTimePickerDanhGiaXau.setEnabled(false);
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
        dateTimePickerDanhGiaXau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
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
                            listDanhGiaXau.clear();
                            danhGiaAdapter.notifyDataSetChanged();
                            mKey.clear();
                            dataEvalute.child("evalute").addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                    if (snapshot.getValue(evaluteFireBase.class).getStar() <= 3) {
                                        if (snapshot.getValue(evaluteFireBase.class).getDate().contains(edtThongKeTheoNgay.getText()) == true) {

                                           int soSao = snapshot.getValue(evaluteFireBase.class).getStar();
                                            String userName = listUser.get(snapshot.getValue(evaluteFireBase.class).getId_user());
                                            String productName = getProductName(snapshot.getValue(evaluteFireBase.class).getId_product());
                                            DanhGia dg = new DanhGia(userName,productName,soSao);
                                            listDanhGiaXau.add(dg);
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
                                    if (snapshot.getValue(evaluteFireBase.class).getStar() <= 3 && snapshot.getValue(evaluteFireBase.class).getDate().contains(edtThongKeTheoNgay.getText()) == true
                                            && mKey.contains(key) == false) {

                                        int soSao = snapshot.getValue(evaluteFireBase.class).getStar();
                                        String userName = listUser.get(snapshot.getValue(evaluteFireBase.class).getId_user());
                                        String productName = getProductName(snapshot.getValue(evaluteFireBase.class).getId_product());
                                        DanhGia dg = new DanhGia(userName,productName,soSao);
                                        listDanhGiaXau.add(dg);
                                        mKey.add(snapshot.getKey());

                                    } else if (snapshot.getValue(evaluteFireBase.class).getStar() <= 3 && snapshot.getValue(evaluteFireBase.class).getDate().contains(edtThongKeTheoNgay.getText()) == true
                                            && mKey.contains(key) == true) {
                                        int soSao = snapshot.getValue(evaluteFireBase.class).getStar();
                                        String userName = listUser.get(snapshot.getValue(evaluteFireBase.class).getId_user());
                                        String productName = getProductName(snapshot.getValue(evaluteFireBase.class).getId_product());
                                        DanhGia dg = new DanhGia(userName,productName,soSao);
                                        listDanhGiaXau.set(index, dg);

                                    } else if (snapshot.getValue(evaluteFireBase.class).getStar() > 3 && snapshot.getValue(evaluteFireBase.class).getDate().contains(edtThongKeTheoNgay.getText()) == false
                                            && mKey.contains(key) == true ||
                                            snapshot.getValue(evaluteFireBase.class).getStar() > 3 && snapshot.getValue(evaluteFireBase.class).getDate().contains(edtThongKeTheoNgay.getText()) == true
                                                    && mKey.contains(key) == true ||
                                            snapshot.getValue(evaluteFireBase.class).getStar() <= 3 && snapshot.getValue(evaluteFireBase.class).getDate().contains(edtThongKeTheoNgay.getText()) == false
                                                    && mKey.contains(key) == true) {
                                        listDanhGiaXau.remove(index);
                                        mKey.remove(key);
                                    }
                                    danhGiaAdapter.notifyDataSetChanged();
                                    Toast.makeText(context, "Đã có sự thay đổi dữ liệu từ hệ thống", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                                    String key = snapshot.getKey();
                                    int index = mKey.indexOf(key);
                                    listDanhGiaXau.remove(index);
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
                            listDanhGiaXau.clear();
                            danhGiaAdapter.notifyDataSetChanged();
                            Toast.makeText(context, "Vui Lòng Nhập Ngày Để Thống Kê", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if (rdbTKDGTheoThang.isChecked() == true) {
                        String time;
                        listDanhGiaXau.clear();
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
                                if (snapshot.getValue(evaluteFireBase.class).getStar() <= 3) {
                                    if (snapshot.getValue(evaluteFireBase.class).getDate().contains(time) == true) {

                                        int soSao = snapshot.getValue(evaluteFireBase.class).getStar();
                                        String userName = listUser.get(snapshot.getValue(evaluteFireBase.class).getId_user());
                                        String productName = getProductName(snapshot.getValue(evaluteFireBase.class).getId_product());
                                        Toast.makeText(context, snapshot.getValue(evaluteFireBase.class).getId_product() + "", Toast.LENGTH_SHORT).show();
                                        DanhGia dg = new DanhGia(userName,productName,soSao);
                                        listDanhGiaXau.add(dg);
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
                                if (snapshot.getValue(evaluteFireBase.class).getStar() <= 3 && snapshot.getValue(evaluteFireBase.class).getDate().contains(time) == true
                                        && mKey.contains(key) == false) {

                                    int soSao = snapshot.getValue(evaluteFireBase.class).getStar();
                                    String userName = listUser.get(snapshot.getValue(evaluteFireBase.class).getId_user());
                                    String productName = getProductName(snapshot.getValue(evaluteFireBase.class).getId_product());
                                    DanhGia dg = new DanhGia(userName,productName,soSao);
                                    listDanhGiaXau.add(dg);
                                    mKey.add(snapshot.getKey());
                                } else if (snapshot.getValue(evaluteFireBase.class).getStar() <= 3 && snapshot.getValue(evaluteFireBase.class).getDate().contains(time) == true
                                        && mKey.contains(key) == true) {
                                    int soSao = snapshot.getValue(evaluteFireBase.class).getStar();
                                    String userName = listUser.get(snapshot.getValue(evaluteFireBase.class).getId_user());
                                    String productName = getProductName(snapshot.getValue(evaluteFireBase.class).getId_product());
                                    DanhGia dg = new DanhGia(userName,productName,soSao);
                                    listDanhGiaXau.set(index, dg);
                                } else if (snapshot.getValue(evaluteFireBase.class).getStar() > 3 && snapshot.getValue(evaluteFireBase.class).getDate().contains(time) == false
                                        && mKey.contains(key) == true ||
                                        snapshot.getValue(evaluteFireBase.class).getStar() > 3 && snapshot.getValue(evaluteFireBase.class).getDate().contains(time) == true
                                                && mKey.contains(key) == true ||
                                        snapshot.getValue(evaluteFireBase.class).getStar() <= 3 && snapshot.getValue(evaluteFireBase.class).getDate().contains(time) == false
                                                && mKey.contains(key) == true) {
                                    listDanhGiaXau.remove(index);
                                    mKey.remove(key);
                                }
                                danhGiaAdapter.notifyDataSetChanged();
                                Toast.makeText(context, "Đã có sự thay đổi dữ liệu từ hệ thống", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                                String key = snapshot.getKey();
                                int index = mKey.indexOf(key);
                                listDanhGiaXau.remove(index);
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
    private void showDateDialog(){
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i1);
                calendar.set(Calendar.DAY_OF_MONTH, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                edtThongKeTheoNgay.setText("");
                edtThongKeTheoNgay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(ChuCuaHang_ManHinhThongKeDanhGiaXau.this, onDateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void setControl() {
        spnTKDGXnam = findViewById(R.id.spnTKDGXnam);
        spnTKDGXthang = findViewById(R.id.spnTKDGXthang);
        lvThongKeDanhGiaXau = findViewById(R.id.lvthongkedanhgiaxau);
        rdbTKDGTheoNgay = findViewById(R.id.rdbTKDGXthongketheongay);
        rdbTKDGTheoThang = findViewById(R.id.rdbTKDGXthongketheothang);
        edtThongKeTheoNgay = findViewById(R.id.edtTKDGXthongketheongay);
        btnThongKeDanhGia = findViewById(R.id.btnTKDGXThongKe);
        dateTimePickerDanhGiaXau = findViewById(R.id.dateTimePickerDanhGiaXau);
    }
}