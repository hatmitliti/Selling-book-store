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

import com.example.book.ChuCuaHang.thongkedonhang.DonHang;
import com.example.book.ChuCuaHang.thongkedonhang.DonhangAdapter;
import com.example.book.R;
import com.example.book.XuLyHD.DonHangChoXuLy.Bill;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ChuCuaHang_ManHinhThongKeDonHangBiHuy extends AppCompatActivity {

    private Spinner spnTKDHBHthang, spnTKDHBHnam;
    private ListView lvThongKeDonHang;
    private Context context;
    private ArrayList<DonHang> listDonHang;
    private Button btnThongKe;
    private RadioButton rdbTKDHBHTheoNgay, rdbTKDHBHTheoThang;
    private EditText edtTKDHBHthongketheongay;
    private ArrayList<String> mKey = new ArrayList<>();
    private ImageView dateTimePickerDonHangBiHuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_cua_hang_____man_hinh_thong_ke_don_hang_bi_huy);
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
        DatabaseReference databill = FirebaseDatabase.getInstance().getReference();
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
        spnTKDHBHthang.setAdapter(adapterThang);
        spnTKDHBHnam.setAdapter(adapterNam);
        spnTKDHBHnam.setEnabled(false);
        spnTKDHBHthang.setEnabled(false);
        dateTimePickerDonHangBiHuy.setEnabled(false);


        listDonHang = new ArrayList<>();
        DonhangAdapter donhangAdapter = new DonhangAdapter(context, R.layout.item_adapter_thongkedonhang, listDonHang);
        lvThongKeDonHang.setAdapter(donhangAdapter);

          /*

        Xử Lý Chọn Phương Thức Thống Kê (Thống Kê Theo Ngày/ Thống Kê Theo Tháng)

         */
        rdbTKDHBHTheoNgay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rdbTKDHBHTheoNgay.isChecked() == true) {
                    edtTKDHBHthongketheongay.setEnabled(true);
                    dateTimePickerDonHangBiHuy.setEnabled(true);
                } else {
                    edtTKDHBHthongketheongay.setEnabled(false);
                    dateTimePickerDonHangBiHuy.setEnabled(false);
                }
            }
        });
        rdbTKDHBHTheoThang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rdbTKDHBHTheoThang.isChecked() == true) {
                    spnTKDHBHnam.setEnabled(true);
                    spnTKDHBHthang.setEnabled(true);
                } else {
                    spnTKDHBHnam.setEnabled(false);
                    spnTKDHBHthang.setEnabled(false);
                }
            }
        });
        //
        dateTimePickerDonHangBiHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
        /*

        Xử Lý Sự Kiện Cho Button Thống Kê

         */

        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rdbTKDHBHTheoNgay.isChecked() == true || rdbTKDHBHTheoThang.isChecked() == true) {
                    if (rdbTKDHBHTheoNgay.isChecked() == true) {
                        if (edtTKDHBHthongketheongay.getText().toString().trim().equals("") == false) {
                            listDonHang.clear();
                            donhangAdapter.notifyDataSetChanged();
                            mKey.clear();
                            databill.child("bills").addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                    if (snapshot.getValue(Bill.class).getStatus() == -1) {
                                        if (snapshot.getValue(Bill.class).getDate().contains(edtTKDHBHthongketheongay.getText()) == true) {
                                            String MaDH = snapshot.getValue(Bill.class).getId();
                                            double tongGiaTriDonHang = snapshot.getValue(Bill.class).getTotalMoney() - snapshot.getValue(Bill.class).getDiscount();
                                            String giaTien = en.format(tongGiaTriDonHang) + " VNĐ";
                                            String TrangThaiDH = "Đã Bị Hủy";
                                            listDonHang.add(new DonHang(MaDH, giaTien, TrangThaiDH));
                                            donhangAdapter.notifyDataSetChanged();
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
                                    if (snapshot.getValue(Bill.class).getStatus() == -1 && snapshot.getValue(Bill.class).getDate().contains(edtTKDHBHthongketheongay.getText()) == true &&
                                            mKey.contains(snapshot.getKey()) == false) {
                                        String MaDH = snapshot.getValue(Bill.class).getId();
                                        double tongGiaTriDonHang = snapshot.getValue(Bill.class).getTotalMoney() - snapshot.getValue(Bill.class).getDiscount();
                                        String giaTien = en.format(tongGiaTriDonHang) + " VNĐ";
                                        String trangThaiDH = "Đã Bị Hủy";
                                        DonHang donHang = new DonHang(MaDH, giaTien, trangThaiDH);
                                        listDonHang.add(donHang);
                                        mKey.add(key);
                                    } else if (snapshot.getValue(Bill.class).getStatus() == -1 && snapshot.getValue(Bill.class).getDate().contains(edtTKDHBHthongketheongay.getText()) == true &&
                                            mKey.contains(snapshot.getKey()) == true) {
                                        String MaDH = snapshot.getValue(Bill.class).getId();
                                        double tongGiaTriDonHang = snapshot.getValue(Bill.class).getTotalMoney() - snapshot.getValue(Bill.class).getDiscount();
                                        String giaTien = en.format(tongGiaTriDonHang) + " VNĐ";
                                        String trangThaiDH = "Đã Bị Hủy";
                                        DonHang donHang = new DonHang(MaDH, giaTien, trangThaiDH);
                                        listDonHang.set(index, donHang);
                                    } else if (snapshot.getValue(Bill.class).getStatus() != -1 && snapshot.getValue(Bill.class).getDate().contains(edtTKDHBHthongketheongay.getText()) == false &&
                                            mKey.contains(snapshot.getKey()) == true ||
                                            snapshot.getValue(Bill.class).getStatus() == -1 && snapshot.getValue(Bill.class).getDate().contains(edtTKDHBHthongketheongay.getText()) == false &&
                                                    mKey.contains(snapshot.getKey()) == true ||
                                            snapshot.getValue(Bill.class).getStatus() != -1 && snapshot.getValue(Bill.class).getDate().contains(edtTKDHBHthongketheongay.getText()) == true &&
                                                    mKey.contains(snapshot.getKey()) == true) {
                                        listDonHang.remove(index);
                                        mKey.remove(key);

                                    }
                                    donhangAdapter.notifyDataSetChanged();
                                    Toast.makeText(context, "Đã có sự thay đổi dữ liệu từ hệ thống", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                                    String key = snapshot.getKey();
                                    int index = mKey.indexOf(key);
                                    listDonHang.remove(index);
                                    donhangAdapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        } else {
                            listDonHang.clear();
                            donhangAdapter.notifyDataSetChanged();
                            Toast.makeText(context, "Vui Lòng Nhập Ngày Để Thống Kê", Toast.LENGTH_SHORT).show();
                        }
                    } else if (rdbTKDHBHTheoThang.isChecked() == true) {
                        String time;
                        listDonHang.clear();
                        donhangAdapter.notifyDataSetChanged();
                        mKey.clear();
                        if (Integer.parseInt(spnTKDHBHthang.getSelectedItem().toString()) < 10) {
                            time = spnTKDHBHnam.getSelectedItem().toString() + "/" + "0" + spnTKDHBHthang.getSelectedItem().toString();
                        } else {
                            time = spnTKDHBHnam.getSelectedItem().toString() + "/" + spnTKDHBHthang.getSelectedItem().toString();
                        }
                        databill.child("bills").addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                if (snapshot.getValue(Bill.class).getStatus() == -1) {
                                    if (snapshot.getValue(Bill.class).getDate().contains(time) == true) {
                                        String MaDH = snapshot.getValue(Bill.class).getId();
                                        double tongGiaTriDonHang = snapshot.getValue(Bill.class).getTotalMoney() - snapshot.getValue(Bill.class).getDiscount();
                                        String giaTien = en.format(tongGiaTriDonHang) + " VNĐ";
                                        String TrangThaiDH = "Đã Bị Hủy";
                                        listDonHang.add(new DonHang(MaDH, giaTien, TrangThaiDH));
                                        donhangAdapter.notifyDataSetChanged();
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
                                if (snapshot.getValue(Bill.class).getStatus() == -1 && snapshot.getValue(Bill.class).getDate().contains(time) == true &&
                                        mKey.contains(snapshot.getKey()) == false) {
                                    String MaDH = snapshot.getValue(Bill.class).getId();
                                    double tongGiaTriDonHang = snapshot.getValue(Bill.class).getTotalMoney() - snapshot.getValue(Bill.class).getDiscount();
                                    String giaTien = en.format(tongGiaTriDonHang) + " VNĐ";
                                    String trangThaiDH = "Đã Bị Hủy";
                                    DonHang donHang = new DonHang(MaDH, giaTien, trangThaiDH);
                                    listDonHang.add(donHang);
                                    mKey.add(key);
                                } else if (snapshot.getValue(Bill.class).getStatus() == -1 && snapshot.getValue(Bill.class).getDate().contains(time) == true &&
                                        mKey.contains(snapshot.getKey()) == true) {
                                    String MaDH = snapshot.getValue(Bill.class).getId();
                                    double tongGiaTriDonHang = snapshot.getValue(Bill.class).getTotalMoney() - snapshot.getValue(Bill.class).getDiscount();
                                    String giaTien = en.format(tongGiaTriDonHang) + " VNĐ";
                                    String trangThaiDH = "Đã Bị Hủy";
                                    DonHang donHang = new DonHang(MaDH, giaTien, trangThaiDH);
                                    listDonHang.set(index, donHang);
                                } else if (snapshot.getValue(Bill.class).getStatus() != -1 && snapshot.getValue(Bill.class).getDate().contains(time) == false &&
                                        mKey.contains(snapshot.getKey()) == true ||
                                        snapshot.getValue(Bill.class).getStatus() == -1 && snapshot.getValue(Bill.class).getDate().contains(time) == false &&
                                                mKey.contains(snapshot.getKey()) == true ||
                                        snapshot.getValue(Bill.class).getStatus() != -1 && snapshot.getValue(Bill.class).getDate().contains(time) == true &&
                                                mKey.contains(snapshot.getKey()) == true) {
                                    listDonHang.remove(index);
                                    mKey.remove(key);

                                }
                                donhangAdapter.notifyDataSetChanged();
                                Toast.makeText(context, "Đã có sự thay đổi dữ liệu từ hệ thống", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                                String key = snapshot.getKey();
                                int index = mKey.indexOf(key);
                                listDonHang.remove(index);
                                donhangAdapter.notifyDataSetChanged();
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
    private void showDateDialog(){
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i1);
                calendar.set(Calendar.DAY_OF_MONTH, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                edtTKDHBHthongketheongay.setText("");
                edtTKDHBHthongketheongay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(ChuCuaHang_ManHinhThongKeDonHangBiHuy.this, onDateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void setControl() {
        spnTKDHBHnam = findViewById(R.id.spnTKDHBHnam);
        spnTKDHBHthang = findViewById(R.id.spnTKDHBHthang);
        lvThongKeDonHang = findViewById(R.id.lvthongkedonhangbihuy);
        edtTKDHBHthongketheongay = findViewById(R.id.edtTKDHBHthongketheongay);
        rdbTKDHBHTheoNgay = findViewById(R.id.rdbTKDHBHthongketheongay);
        rdbTKDHBHTheoThang = findViewById(R.id.rdbTKDHBHthongketheothang);
        btnThongKe = findViewById(R.id.btnTKDHBHThongKe);
        dateTimePickerDonHangBiHuy = findViewById(R.id.dateTimePickerDonHangBiHuy);
    }
}