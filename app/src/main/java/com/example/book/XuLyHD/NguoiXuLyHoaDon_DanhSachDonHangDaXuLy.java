package com.example.book.XuLyHD;

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

import com.example.book.ChuCuaHang.ChuCuaHang_ManHinhThongKeTienTraDVVC;
import com.example.book.ChuCuaHang.thongkedonhang.DonHang;
import com.example.book.ChuCuaHang.thongkedonhang.DonhangAdapter;
import com.example.book.Dialog.NotificationDialog;
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

public class NguoiXuLyHoaDon_DanhSachDonHangDaXuLy extends AppCompatActivity {
    private Spinner spnTKDHDXLthang, spnTKDHDXLnam;
    private ListView lvThongKeDonHangDXL;
    private Context context;
    private ArrayList<DonHang> listDonHangDXL;
    private Button btnThongKe;
    private RadioButton rdbTKDHDXLTheoNgay, rdbTKDHDXLTheoThang;
    private EditText edtTKDHDXLthongketheongay;
    private ArrayList<String> mKey = new ArrayList<>();
    private ImageView dateTimePickerDHDXL;
    private NotificationDialog notificationDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_xu_ly_hoa_don_____danh_sach_don_hang_da_xu_ly);
        notificationDialog = new NotificationDialog(this);
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
        spnTKDHDXLthang.setAdapter(adapterThang);
        spnTKDHDXLnam.setAdapter(adapterNam);
        spnTKDHDXLthang.setEnabled(false);
        spnTKDHDXLnam.setEnabled(false);
        dateTimePickerDHDXL.setEnabled(false);

        listDonHangDXL = new ArrayList<>();
        DonhangAdapter donhangAdapter = new DonhangAdapter(context, R.layout.item_adapter_thongkedonhang, listDonHangDXL);
        lvThongKeDonHangDXL.setAdapter(donhangAdapter);

        /*

        Xử Lý Chọn Phương Thức Thống Kê (Thống Kê Theo Ngày/ Thống Kê Theo Tháng)

         */
        rdbTKDHDXLTheoNgay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rdbTKDHDXLTheoNgay.isChecked() == true) {
                    edtTKDHDXLthongketheongay.setEnabled(true);
                    dateTimePickerDHDXL.setEnabled(true);
                } else {
                    edtTKDHDXLthongketheongay.setEnabled(false);
                    dateTimePickerDHDXL.setEnabled(false);
                }
            }
        });
        rdbTKDHDXLTheoThang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rdbTKDHDXLTheoThang.isChecked() == true) {
                    spnTKDHDXLnam.setEnabled(true);
                    spnTKDHDXLthang.setEnabled(true);
                } else {
                    spnTKDHDXLnam.setEnabled(false);
                    spnTKDHDXLthang.setEnabled(false);
                }
            }
        });
        //
        dateTimePickerDHDXL.setOnClickListener(new View.OnClickListener() {
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
                if (rdbTKDHDXLTheoNgay.isChecked() == true || rdbTKDHDXLTheoThang.isChecked() == true) {
                    if (rdbTKDHDXLTheoNgay.isChecked() == true) {
                        if (edtTKDHDXLthongketheongay.getText().toString().trim().equals("") == false) {
                            listDonHangDXL.clear();
                            donhangAdapter.notifyDataSetChanged();
                            mKey.clear();
                            databill.child("bills").addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                    if (snapshot.getValue(Bill.class).getStatus() == 1
                                            || snapshot.getValue(Bill.class).getStatus() == 2
                                            || snapshot.getValue(Bill.class).getStatus() == 3
                                            || snapshot.getValue(Bill.class).getStatus() == 4
                                            || snapshot.getValue(Bill.class).getStatus() == 5
                                            || snapshot.getValue(Bill.class).getStatus() == 6
                                            || snapshot.getValue(Bill.class).getStatus() == 7
                                            || snapshot.getValue(Bill.class).getStatus() == 8
                                            || snapshot.getValue(Bill.class).getStatus() == 9
                                            || snapshot.getValue(Bill.class).getStatus() == 10) {
                                        if (snapshot.getValue(Bill.class).getDate().contains(edtTKDHDXLthongketheongay.getText()) == true) {
                                            String MaDH = snapshot.getValue(Bill.class).getId();
                                            double tongGiaTriDonHang = snapshot.getValue(Bill.class).getTotalMoney() - snapshot.getValue(Bill.class).getDiscount();
                                            String giaTien = en.format(tongGiaTriDonHang) + " VNĐ";
                                            String TrangThaiDH = "Đã Xử Lý";
                                            listDonHangDXL.add(new DonHang(MaDH, giaTien, TrangThaiDH));
                                            donhangAdapter.notifyDataSetChanged();
                                            //
                                            String key = snapshot.getKey();
                                            mKey.add(key);

                                        }
                                    }
                                }

                                @Override
                                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                    String key = snapshot.getKey();
                                    int index = mKey.indexOf(key);
                                    if (testBillStatus(snapshot.getValue(Bill.class).getStatus()) && snapshot.getValue(Bill.class).getDate().contains(edtTKDHDXLthongketheongay.getText()) == true
                                            && mKey.contains(snapshot.getKey()) == false) {
                                        String MaDH = snapshot.getValue(Bill.class).getId();
                                        double tongGiaTriDonHang = snapshot.getValue(Bill.class).getTotalMoney() - snapshot.getValue(Bill.class).getDiscount();
                                        String giaTien = en.format(tongGiaTriDonHang) + " VNĐ";
                                        String trangThaiDH = "Đã Xử Lý";
                                        DonHang donHang = new DonHang(MaDH, giaTien, trangThaiDH);
                                        listDonHangDXL.add(donHang);
                                        mKey.add(snapshot.getKey());

                                    } else if (testBillStatus(snapshot.getValue(Bill.class).getStatus())
                                            && snapshot.getValue(Bill.class).getDate().contains(edtTKDHDXLthongketheongay.getText()) == true
                                            && mKey.contains(snapshot.getKey()) == true) {

                                        String MaDH = snapshot.getValue(Bill.class).getId();
                                        double tongGiaTriDonHang = snapshot.getValue(Bill.class).getTotalMoney() - snapshot.getValue(Bill.class).getDiscount();
                                        String giaTien = en.format(tongGiaTriDonHang) + " VNĐ";
                                        String trangThaiDH = "Đã Xử Lý";
                                        DonHang donHang = new DonHang(MaDH, giaTien, trangThaiDH);
                                        listDonHangDXL.set(index, donHang);
                                    } else if (!testBillStatus(snapshot.getValue(Bill.class).getStatus()) && snapshot.getValue(Bill.class).getDate().contains(edtTKDHDXLthongketheongay.getText()) == false
                                            && mKey.contains(snapshot.getKey()) == true
                                            || testBillStatus(snapshot.getValue(Bill.class).getStatus()) && snapshot.getValue(Bill.class).getDate().contains(edtTKDHDXLthongketheongay.getText()) == false
                                            && mKey.contains(snapshot.getKey()) == true
                                            || !testBillStatus(snapshot.getValue(Bill.class).getStatus()) && snapshot.getValue(Bill.class).getDate().contains(edtTKDHDXLthongketheongay.getText()) == true
                                            && mKey.contains(snapshot.getKey()) == true) {
                                        listDonHangDXL.remove(index);
                                        mKey.remove(snapshot.getKey());
                                    }
                                    donhangAdapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                                    String key = snapshot.getKey();
                                    int index = mKey.indexOf(key);
                                    listDonHangDXL.remove(index);
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
                            listDonHangDXL.clear();
                            donhangAdapter.notifyDataSetChanged();
                            notificationDialog.startErrorDialog(getResources().getString(R.string.nhap_ngay_de_thong_ke));
                        }
                    } else if (rdbTKDHDXLTheoThang.isChecked() == true) {
                        String time;
                        listDonHangDXL.clear();
                        donhangAdapter.notifyDataSetChanged();
                        mKey.clear();
                        if (Integer.parseInt(spnTKDHDXLthang.getSelectedItem().toString()) < 10) {
                            time = spnTKDHDXLnam.getSelectedItem().toString() + "/" + "0" + spnTKDHDXLthang.getSelectedItem().toString();
                        } else {
                            time = spnTKDHDXLnam.getSelectedItem().toString() + "/" + spnTKDHDXLthang.getSelectedItem().toString();
                        }
                        databill.child("bills").addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                if (snapshot.getValue(Bill.class).getStatus() == 1
                                        || snapshot.getValue(Bill.class).getStatus() == 2
                                        || snapshot.getValue(Bill.class).getStatus() == 3
                                        || snapshot.getValue(Bill.class).getStatus() == 4
                                        || snapshot.getValue(Bill.class).getStatus() == 5
                                        || snapshot.getValue(Bill.class).getStatus() == 6
                                        || snapshot.getValue(Bill.class).getStatus() == 7
                                        || snapshot.getValue(Bill.class).getStatus() == 8
                                        || snapshot.getValue(Bill.class).getStatus() == 9
                                        || snapshot.getValue(Bill.class).getStatus() == 10) {
                                    if (snapshot.getValue(Bill.class).getDate().contains(time) == true) {
                                        String MaDH = snapshot.getValue(Bill.class).getId();
                                        double tongGiaTriDonHang = snapshot.getValue(Bill.class).getTotalMoney() - snapshot.getValue(Bill.class).getDiscount();
                                        String giaTien = en.format(tongGiaTriDonHang) + " VNĐ";
                                        String TrangThaiDH = "Đã Xử Lý";
                                        listDonHangDXL.add(new DonHang(MaDH, giaTien, TrangThaiDH));
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
                                if (testBillStatus(snapshot.getValue(Bill.class).getStatus()) && snapshot.getValue(Bill.class).getDate().contains(time) == true
                                        && mKey.contains(snapshot.getKey()) == false) {
                                    String MaDH = snapshot.getValue(Bill.class).getId();
                                    double tongGiaTriDonHang = snapshot.getValue(Bill.class).getTotalMoney() - snapshot.getValue(Bill.class).getDiscount();
                                    String giaTien = en.format(tongGiaTriDonHang) + " VNĐ";
                                    String trangThaiDH = "Đã Xử Lý";
                                    DonHang donHang = new DonHang(MaDH, giaTien, trangThaiDH);
                                    listDonHangDXL.add(donHang);
                                    mKey.add(snapshot.getKey());
                                } else if (testBillStatus(snapshot.getValue(Bill.class).getStatus())
                                        && snapshot.getValue(Bill.class).getDate().contains(time) == true
                                        && mKey.contains(snapshot.getKey()) == true) {

                                    String MaDH = snapshot.getValue(Bill.class).getId();
                                    double tongGiaTriDonHang = snapshot.getValue(Bill.class).getTotalMoney() - snapshot.getValue(Bill.class).getDiscount();
                                    String giaTien = en.format(tongGiaTriDonHang) + " VNĐ";
                                    String trangThaiDH = "Đã Xử Lý";
                                    DonHang donHang = new DonHang(MaDH, giaTien, trangThaiDH);
                                    listDonHangDXL.set(index, donHang);
                                } else if (!testBillStatus(snapshot.getValue(Bill.class).getStatus()) && snapshot.getValue(Bill.class).getDate().contains(time) == false
                                        && mKey.contains(snapshot.getKey()) == true
                                        || testBillStatus(snapshot.getValue(Bill.class).getStatus()) && snapshot.getValue(Bill.class).getDate().contains(time) == false
                                        && mKey.contains(snapshot.getKey()) == true
                                        || !testBillStatus(snapshot.getValue(Bill.class).getStatus()) && snapshot.getValue(Bill.class).getDate().contains(time) == true
                                        && mKey.contains(snapshot.getKey()) == true) {
                                    listDonHangDXL.remove(index);
                                    mKey.remove(snapshot.getKey());
                                }
                                donhangAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                                String key = snapshot.getKey();
                                int index = mKey.indexOf(key);
                                listDonHangDXL.remove(index);
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
                    notificationDialog.startErrorDialog(getResources().getString(R.string.chon_phuong_thuc_de_thong_ke));
                }
            }
        });

    }

    // so sánh trạng thái đơn hàng
    private boolean testBillStatus(int billStatus) {
        for (int i = 1; i < 11; i++) {
            if (billStatus == i) {
                return true;
            }
        }
        return false;
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
                edtTKDHDXLthongketheongay.setText("");
                edtTKDHDXLthongketheongay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(NguoiXuLyHoaDon_DanhSachDonHangDaXuLy.this, onDateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    private void setControl() {
        spnTKDHDXLnam = findViewById(R.id.spnTKDHDXLnam);
        spnTKDHDXLthang = findViewById(R.id.spnTKDHDXLthang);
        lvThongKeDonHangDXL = findViewById(R.id.lvthongkedonhangdaxuly);
        btnThongKe = findViewById(R.id.btnTKDHDXLhongKe);
        rdbTKDHDXLTheoNgay = findViewById(R.id.rdbTKDHDXLthongketheongay);
        rdbTKDHDXLTheoThang = findViewById(R.id.rdbTKDHDXLthongketheothang);
        edtTKDHDXLthongketheongay = findViewById(R.id.edtTKDHDXLthongketheongay);
        dateTimePickerDHDXL = findViewById(R.id.dateTimePickerDonHangDaXuLy);
    }
}