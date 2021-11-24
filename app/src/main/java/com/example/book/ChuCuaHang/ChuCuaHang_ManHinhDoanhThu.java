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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.book.ChuCuaHang.doanhthu.DoanhThu;
import com.example.book.ChuCuaHang.doanhthu.DoanhThuAdapter;
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

public class ChuCuaHang_ManHinhDoanhThu extends AppCompatActivity {
    private Spinner spnDTthang, spnDTnam;
    private ListView lvThongKeDoanhThu;
    private Context context;
    private ArrayList<DoanhThu> listDoanhThu;
    private RadioButton rdbTKDTTheoThang, rdbTKDTTheoNgay;
    private EditText edtTKDTTheoNgay;
    private Button btnThongKeDoanhThu;
    private ArrayList<String> mKey = new ArrayList<>();
    private TextView tvTongSoDoanhThu;
    private ImageView dateTimePickerDoanhThu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_cua_hang_____man_hinh_doanh_thu);
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
        spnDTthang.setAdapter(adapterThang);
        spnDTnam.setAdapter(adapterNam);
        spnDTnam.setEnabled(false);
        spnDTthang.setEnabled(false);
        dateTimePickerDoanhThu.setEnabled(false);
        //
        listDoanhThu = new ArrayList<>();
        DoanhThuAdapter doanhThuAdapter = new DoanhThuAdapter(R.layout.item_adapter_doanhthu, context, listDoanhThu);
        lvThongKeDoanhThu.setAdapter(doanhThuAdapter);
         /*

        Xử Lý Chọn Phương Thức Thống Kê (Thống Kê Theo Ngày/ Thống Kê Theo Tháng)

         */
        rdbTKDTTheoNgay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rdbTKDTTheoNgay.isChecked() == true) {
                    edtTKDTTheoNgay.setEnabled(true);
                    dateTimePickerDoanhThu.setEnabled(true);
                } else {
                    edtTKDTTheoNgay.setEnabled(false);
                    dateTimePickerDoanhThu.setEnabled(false);
                }
            }
        });
        rdbTKDTTheoThang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rdbTKDTTheoThang.isChecked() == true) {
                    spnDTthang.setEnabled(true);
                    spnDTnam.setEnabled(true);
                } else {
                    spnDTthang.setEnabled(false);
                    spnDTnam.setEnabled(false);
                }
            }
        });
        //
        dateTimePickerDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        /*

        Xử Lý Sự Kiện Cho Button Thống Kê

         */

        btnThongKeDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rdbTKDTTheoNgay.isChecked() == true || rdbTKDTTheoThang.isChecked() == true) {
                    if (rdbTKDTTheoNgay.isChecked() == true) {
                        if (edtTKDTTheoNgay.getText().toString().trim().equals("") == false) {
                            listDoanhThu.clear();
                            doanhThuAdapter.notifyDataSetChanged();
                            mKey.clear();
                            databill.child("bills").addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                    if (snapshot.getValue(Bill.class).getStatus() == 7) {
                                        if (snapshot.getValue(Bill.class).getDate().contains(edtTKDTTheoNgay.getText()) == true) {
                                            String MaDH = snapshot.getValue(Bill.class).getId();
                                            double tongGiaTriDonHang = snapshot.getValue(Bill.class).getTotalMoney() - snapshot.getValue(Bill.class).getDiscount();
                                            listDoanhThu.add(new DoanhThu(MaDH, tongGiaTriDonHang));
                                            doanhThuAdapter.notifyDataSetChanged();
                                            //
                                            String key = snapshot.getKey();
                                            mKey.add(key);
                                            tvTongSoDoanhThu.setText("Tổng Doanh Thu Là: " + tongDoanhThu(listDoanhThu));
                                        }
                                    }
                                }

                                @Override
                                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                    // lấy địa chỉ id của đối tượng vừa bị thay đổi bên trong mảng mkey
                                    String key = snapshot.getKey();
                                    int index = mKey.indexOf(key);
                                    // thay đổi dữ liệu trong gridview giống với dữ liệu trên firebase
                                    if (snapshot.getValue(Bill.class).getStatus() == 7 && snapshot.getValue(Bill.class).getDate().contains(edtTKDTTheoNgay.getText()) == true
                                            && mKey.contains(snapshot.getKey()) == false) {
                                        String MaDH = snapshot.getValue(Bill.class).getId();
                                        double tongGiaTriDonHang = snapshot.getValue(Bill.class).getTotalMoney() - snapshot.getValue(Bill.class).getDiscount();
                                        DoanhThu doanhThu = new DoanhThu(MaDH,tongGiaTriDonHang);
                                        listDoanhThu.add(doanhThu);
                                        mKey.add(snapshot.getKey());

                                    } else if (snapshot.getValue(Bill.class).getStatus() == 7 && snapshot.getValue(Bill.class).getDate().contains(edtTKDTTheoNgay.getText()) == true
                                            && mKey.contains(snapshot.getKey()) == true) {
                                        String MaDH = snapshot.getValue(Bill.class).getId();
                                        double tongGiaTriDonHang = snapshot.getValue(Bill.class).getTotalMoney() - snapshot.getValue(Bill.class).getDiscount();
                                        DoanhThu doanhThu = new DoanhThu(MaDH,tongGiaTriDonHang);
                                        listDoanhThu.set(index, doanhThu);


                                    } else if (snapshot.getValue(Bill.class).getStatus() != 7 && snapshot.getValue(Bill.class).getDate().contains(edtTKDTTheoNgay.getText()) == false && mKey.contains(snapshot.getKey()) == true ||
                                            snapshot.getValue(Bill.class).getStatus() == 7 && snapshot.getValue(Bill.class).getDate().contains(edtTKDTTheoNgay.getText()) == false && mKey.contains(snapshot.getKey()) == true ||
                                            snapshot.getValue(Bill.class).getStatus() != 7 && snapshot.getValue(Bill.class).getDate().contains(edtTKDTTheoNgay.getText()) == true && mKey.contains(snapshot.getKey()) == true) {
                                        listDoanhThu.remove(index);
                                        mKey.remove(key);
                                    }
                                    doanhThuAdapter.notifyDataSetChanged();
                                    tvTongSoDoanhThu.setText("Tổng Doanh Thu Là: " + tongDoanhThu(listDoanhThu));
                                    Toast.makeText(context, "Đã có sự thay đổi dữ liệu từ hệ thống", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                                    String key = snapshot.getKey();
                                    int index = mKey.indexOf(key);
                                    listDoanhThu.remove(index);
                                    doanhThuAdapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        } else {
                            listDoanhThu.clear();
                            doanhThuAdapter.notifyDataSetChanged();
                            Toast.makeText(context, "Vui Lòng Nhập Ngày Để Thống Kê", Toast.LENGTH_SHORT).show();
                        }
                    } else if (rdbTKDTTheoThang.isChecked() == true) {
                        String time;
                        listDoanhThu.clear();
                        doanhThuAdapter.notifyDataSetChanged();
                        mKey.clear();
                        if (Integer.parseInt(spnDTthang.getSelectedItem().toString()) < 10) {
                            time = spnDTnam.getSelectedItem().toString() + "/" + "0" + spnDTthang.getSelectedItem().toString();
                        } else {
                            time = spnDTnam.getSelectedItem().toString() + "/" + spnDTthang.getSelectedItem().toString();
                        }
                        databill.child("bills").addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                if (snapshot.getValue(Bill.class).getStatus() == 7) {
                                    if (snapshot.getValue(Bill.class).getDate().contains(time) == true) {
                                        String MaDH = snapshot.getValue(Bill.class).getId();
                                        double tongGiaTriDonHang = snapshot.getValue(Bill.class).getTotalMoney() - snapshot.getValue(Bill.class).getDiscount();
                                        listDoanhThu.add(new DoanhThu(MaDH, tongGiaTriDonHang));
                                        doanhThuAdapter.notifyDataSetChanged();
                                        //
                                        String key = snapshot.getKey();
                                        mKey.add(key);
                                        tvTongSoDoanhThu.setText("Tông doanh thu là: " + tongDoanhThu(listDoanhThu));
                                    }
                                }

                            }

                            @Override
                            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                // lấy địa chỉ id của đối tượng vừa bị thay đổi bên trong mảng mkey
                                String key = snapshot.getKey();
                                int index = mKey.indexOf(key);
                                // thay đổi dữ liệu trong gridview giống với dữ liệu trên firebase
                                if (snapshot.getValue(Bill.class).getStatus() == 7 && snapshot.getValue(Bill.class).getDate().contains(time) == true
                                        && mKey.contains(snapshot.getKey()) == false) {
                                    String MaDH = snapshot.getValue(Bill.class).getId();
                                    double tongGiaTriDonHang = snapshot.getValue(Bill.class).getTotalMoney() - snapshot.getValue(Bill.class).getDiscount();
                                    DoanhThu doanhThu = new DoanhThu(MaDH,tongGiaTriDonHang);
                                    listDoanhThu.add(doanhThu);
                                    mKey.add(snapshot.getKey());
                                } else if (snapshot.getValue(Bill.class).getStatus() == 7 && snapshot.getValue(Bill.class).getDate().contains(time) == true
                                        && mKey.contains(snapshot.getKey()) == true) {
                                    String MaDH = snapshot.getValue(Bill.class).getId();
                                    double tongGiaTriDonHang = snapshot.getValue(Bill.class).getTotalMoney() - snapshot.getValue(Bill.class).getDiscount();
                                    DoanhThu doanhThu = new DoanhThu(MaDH,tongGiaTriDonHang);
                                    listDoanhThu.set(index, doanhThu);
                                } else if (snapshot.getValue(Bill.class).getStatus() != 7 && snapshot.getValue(Bill.class).getDate().contains(time) == false && mKey.contains(snapshot.getKey()) == true ||
                                        snapshot.getValue(Bill.class).getStatus() == 7 && snapshot.getValue(Bill.class).getDate().contains(time) == false && mKey.contains(snapshot.getKey()) == true ||
                                        snapshot.getValue(Bill.class).getStatus() != 7 && snapshot.getValue(Bill.class).getDate().contains(time) == true && mKey.contains(snapshot.getKey()) == true) {
                                    listDoanhThu.remove(index);
                                    mKey.remove(key);
                                }
                                doanhThuAdapter.notifyDataSetChanged();
                                Toast.makeText(context, "Đã có sự thay đổi dữ liệu từ hệ thống", Toast.LENGTH_SHORT).show();
                                tvTongSoDoanhThu.setText("Tông doanh thu là: " + tongDoanhThu(listDoanhThu));
                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                                String key = snapshot.getKey();
                                int index = mKey.indexOf(key);
                                listDoanhThu.remove(index);
                                doanhThuAdapter.notifyDataSetChanged();
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
    private String tongDoanhThu(ArrayList<DoanhThu> doanhThu) {
        // Định dạng số
        NumberFormat currentLocale = NumberFormat.getInstance();
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);
        double tongTien = 0;
        for (int i = 0; i < doanhThu.size(); i++) {
            tongTien += doanhThu.get(i).getGiaTriDH();
        }
        return en.format(tongTien) + "VNĐ";
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
                edtTKDTTheoNgay.setText("");
                edtTKDTTheoNgay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(ChuCuaHang_ManHinhDoanhThu.this, onDateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    private void setControl() {
        spnDTnam = findViewById(R.id.spnDTnam);
        spnDTthang = findViewById(R.id.spnDTthang);
        lvThongKeDoanhThu = findViewById(R.id.lvthongkedoanhthu);
        rdbTKDTTheoNgay = findViewById(R.id.rdbDTthongketheongay);
        rdbTKDTTheoThang = findViewById(R.id.rdbDTthongketheothang);
        edtTKDTTheoNgay = findViewById(R.id.edtDTthongketheongay);
        btnThongKeDoanhThu = findViewById(R.id.btnDTThongKe);
        tvTongSoDoanhThu = findViewById(R.id.tvTongSoDoanhThu);
        dateTimePickerDoanhThu = findViewById(R.id.dateTimePickerDoanhThu);
    }
}