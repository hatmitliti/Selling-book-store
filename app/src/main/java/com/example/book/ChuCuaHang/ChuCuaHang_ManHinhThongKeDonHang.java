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

public class ChuCuaHang_ManHinhThongKeDonHang extends AppCompatActivity {
    private Spinner spnTKDHthang, spnTKDHnam;
    private ListView lvThongKeDonHang;
    private Context context;
    private ArrayList<DonHang> listDonHang;
    private Button btnThongKe;
    private RadioButton rdbTKDHTheoNgay, rdbTKDHTheoThang;
    private EditText edtTKDHthongketheongay;
    private ArrayList<String> mKey = new ArrayList<>();
    private ImageView dateTimePickerDonHang;
    private NotificationDialog notificationDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_cua_hang_____man_hinh_thong_ke_don_hang);
        notificationDialog =new NotificationDialog(this);
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
        spnTKDHthang.setAdapter(adapterThang);
        spnTKDHnam.setAdapter(adapterNam);
        spnTKDHthang.setEnabled(false);
        spnTKDHnam.setEnabled(false);
        dateTimePickerDonHang.setEnabled(false);

        listDonHang = new ArrayList<>();
        DonhangAdapter donhangAdapter = new DonhangAdapter(context, R.layout.item_adapter_thongkedonhang, listDonHang);
        lvThongKeDonHang.setAdapter(donhangAdapter);

          /*

        Xử Lý Chọn Phương Thức Thống Kê (Thống Kê Theo Ngày/ Thống Kê Theo Tháng)

         */
        rdbTKDHTheoNgay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rdbTKDHTheoNgay.isChecked() == true) {
                    edtTKDHthongketheongay.setEnabled(true);
                    dateTimePickerDonHang.setEnabled(true);
                } else {
                    edtTKDHthongketheongay.setEnabled(false);
                    dateTimePickerDonHang.setEnabled(false);
                }
            }
        });
        rdbTKDHTheoThang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rdbTKDHTheoThang.isChecked() == true) {
                    spnTKDHnam.setEnabled(true);
                    spnTKDHthang.setEnabled(true);
                } else {
                    spnTKDHnam.setEnabled(false);
                    spnTKDHthang.setEnabled(false);
                }
            }
        });
        dateTimePickerDonHang.setOnClickListener(new View.OnClickListener() {
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
                if (rdbTKDHTheoNgay.isChecked() == true || rdbTKDHTheoThang.isChecked() == true) {
                    if (rdbTKDHTheoNgay.isChecked() == true) {
                        if (edtTKDHthongketheongay.getText().toString().trim().equals("") == false) {
                            listDonHang.clear();
                            donhangAdapter.notifyDataSetChanged();
                            mKey.clear();
                            databill.child("bills").addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                    if (snapshot.getValue(Bill.class).getDate().contains(edtTKDHthongketheongay.getText()) == true) {
                                        String MaDH = snapshot.getValue(Bill.class).getId();
                                        double tongGiaTriDonHang = snapshot.getValue(Bill.class).getTotalMoney() - snapshot.getValue(Bill.class).getDiscount();
                                        String giaTien = en.format(tongGiaTriDonHang) + " VNĐ";
                                        String TrangThaiDH = billStatus(snapshot.getValue(Bill.class).getStatus());
                                        listDonHang.add(new DonHang(MaDH, giaTien, TrangThaiDH));
                                        donhangAdapter.notifyDataSetChanged();
                                        //
                                        String key = snapshot.getKey();
                                        mKey.add(key);

                                    }
                                }

                                @Override
                                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                    // lấy địa chỉ id của đối tượng vừa bị thay đổi bên trong mảng mkey
                                    String key = snapshot.getKey();
                                    int index = mKey.indexOf(key);
                                    // thay đổi dữ liệu trong gridview giống với dữ liệu trên firebase
                                    if(snapshot.getValue(Bill.class).getDate().contains(edtTKDHthongketheongay.getText()) == true && mKey.contains(key))
                                    {
                                        String MaDH = snapshot.getValue(Bill.class).getId();
                                        double tongGiaTriDonHang = snapshot.getValue(Bill.class).getTotalMoney() - snapshot.getValue(Bill.class).getDiscount();
                                        String giaTien = en.format(tongGiaTriDonHang) + " VNĐ";
                                        String trangThaiDH = billStatus(snapshot.getValue(Bill.class).getStatus());
                                        DonHang donHang = new DonHang(MaDH, giaTien, trangThaiDH);
                                        listDonHang.set(index, donHang);
                                    }
                                    else if (snapshot.getValue(Bill.class).getDate().contains(edtTKDHthongketheongay.getText()) == true && !mKey.contains(key))
                                    {
                                        String MaDH = snapshot.getValue(Bill.class).getId();
                                        double tongGiaTriDonHang = snapshot.getValue(Bill.class).getTotalMoney() - snapshot.getValue(Bill.class).getDiscount();
                                        String giaTien = en.format(tongGiaTriDonHang) + " VNĐ";
                                        String trangThaiDH = billStatus(snapshot.getValue(Bill.class).getStatus());
                                        DonHang donHang = new DonHang(MaDH, giaTien, trangThaiDH);
                                        listDonHang.add(donHang);
                                        mKey.add(key);
                                    }else if (snapshot.getValue(Bill.class).getDate().contains(edtTKDHthongketheongay.getText()) == false && mKey.contains(key))
                                    {
                                        listDonHang.remove(index);
                                        donhangAdapter.notifyDataSetChanged();
                                        mKey.remove(key);
                                    }
                                    donhangAdapter.notifyDataSetChanged();
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
                            notificationDialog.startErrorDialog(getResources().getString(R.string.nhap_ngay_de_thong_ke));
                        }
                    } else if (rdbTKDHTheoThang.isChecked() == true) {
                        String time;
                        listDonHang.clear();
                        donhangAdapter.notifyDataSetChanged();
                        mKey.clear();
                        if (Integer.parseInt(spnTKDHthang.getSelectedItem().toString()) < 10) {
                            time = spnTKDHnam.getSelectedItem().toString() + "/" + "0" + spnTKDHthang.getSelectedItem().toString();
                        } else {
                            time = spnTKDHnam.getSelectedItem().toString() + "/" + spnTKDHthang.getSelectedItem().toString();
                        }
                        databill.child("bills").addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                if (snapshot.getValue(Bill.class).getDate().contains(time) == true) {
                                    String MaDH = snapshot.getValue(Bill.class).getId();
                                    double tongGiaTriDonHang = snapshot.getValue(Bill.class).getTotalMoney() - snapshot.getValue(Bill.class).getDiscount();
                                    String giaTien = en.format(tongGiaTriDonHang) + " VNĐ";
                                    String TrangThaiDH = billStatus(snapshot.getValue(Bill.class).getStatus());
                                    listDonHang.add(new DonHang(MaDH, giaTien, TrangThaiDH));
                                    donhangAdapter.notifyDataSetChanged();
                                    //
                                    String key = snapshot.getKey();
                                    mKey.add(key);
                                }
                            }

                            @Override
                            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                // lấy địa chỉ id của đối tượng vừa bị thay đổi bên trong mảng mkey
                                String key = snapshot.getKey();
                                int index = mKey.indexOf(key);
                                // thay đổi dữ liệu trong gridview giống với dữ liệu trên firebase
                                if(snapshot.getValue(Bill.class).getDate().contains(time) == true && mKey.contains(key))
                                {
                                    String MaDH = snapshot.getValue(Bill.class).getId();
                                    double tongGiaTriDonHang = snapshot.getValue(Bill.class).getTotalMoney() - snapshot.getValue(Bill.class).getDiscount();
                                    String giaTien = en.format(tongGiaTriDonHang) + " VNĐ";
                                    String trangThaiDH = billStatus(snapshot.getValue(Bill.class).getStatus());
                                    DonHang donHang = new DonHang(MaDH, giaTien, trangThaiDH);
                                    listDonHang.set(index, donHang);

                                }
                                else if (snapshot.getValue(Bill.class).getDate().contains(time) == true && !mKey.contains(key))
                                {
                                    String MaDH = snapshot.getValue(Bill.class).getId();
                                    double tongGiaTriDonHang = snapshot.getValue(Bill.class).getTotalMoney() - snapshot.getValue(Bill.class).getDiscount();
                                    String giaTien = en.format(tongGiaTriDonHang) + " VNĐ";
                                    String trangThaiDH = billStatus(snapshot.getValue(Bill.class).getStatus());
                                    DonHang donHang = new DonHang(MaDH, giaTien, trangThaiDH);
                                    listDonHang.add(donHang);
                                    mKey.add(key);
                                }else if (snapshot.getValue(Bill.class).getDate().contains(time) == false && mKey.contains(key))
                                {
                                    listDonHang.remove(index);
                                    donhangAdapter.notifyDataSetChanged();
                                    mKey.remove(key);
                                }
                                donhangAdapter.notifyDataSetChanged();
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
                    notificationDialog.startErrorDialog(getResources().getString(R.string.chon_phuong_thuc_de_thong_ke));
                }
            }
        });
    }

    // Set Trạng Thái Đơn Hàng
    private String billStatus(int status)
    {
        switch (status)
        {
            case -1:
                return "Đã Bị Hủy";
            case 0:
                return "Đã Đặt";
            case 1:
                return "Đã Xác Nhận";
            case 2:
                return "Đã Đóng Gói";
            case 3:
                return "Đã Giao Shipper";
            case 4:
                return "Đang Giao";
            case 5:
                return "Đã Giao";
            case 6:
                return "Đã Giao Tiền Cho Soạn Đơn";
            case 7:
                return "Soạn Đơn Xác Nhận Đã Nhận Tiền";
            case 8:
                return "Hàng Bị Hủy Và Trả Lại Soạn Đơn";
            case 9:
                return "Soạn Đơn Đã Xác Nhận Nhận Lại Hàng Bị Hủy";
            case 10:
                return "Đơn Bị Hủy Trong Lúc Đang Giao";
            default:
                return null;
        }
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
                edtTKDHthongketheongay.setText("");
                edtTKDHthongketheongay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(ChuCuaHang_ManHinhThongKeDonHang.this, onDateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void setControl() {
        spnTKDHnam = findViewById(R.id.spnTKDHnam);
        spnTKDHthang = findViewById(R.id.spnTKDHthang);
        lvThongKeDonHang = findViewById(R.id.lvthongkedonhang);
        spnTKDHnam = findViewById(R.id.spnTKDHnam);
        spnTKDHthang = findViewById(R.id.spnTKDHthang);
        lvThongKeDonHang = findViewById(R.id.lvthongkedonhang);
        edtTKDHthongketheongay = findViewById(R.id.edtTKDHthongketheongay);
        rdbTKDHTheoNgay = findViewById(R.id.rdbTKDHthongketheongay);
        rdbTKDHTheoThang = findViewById(R.id.rdbTKDHthongketheothang);
        btnThongKe = findViewById(R.id.btnTKDHThongKe);
        dateTimePickerDonHang = findViewById(R.id.dateTimePickerDonHang);
    }
}