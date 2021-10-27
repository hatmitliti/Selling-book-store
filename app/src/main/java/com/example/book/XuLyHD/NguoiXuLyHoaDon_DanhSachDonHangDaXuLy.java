package com.example.book.XuLyHD;

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
import java.util.ArrayList;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_xu_ly_hoa_don_____danh_sach_don_hang_da_xu_ly);
        setControl();
        setEvent();
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
                } else {
                    edtTKDHDXLthongketheongay.setEnabled(false);
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
                                    Toast.makeText(context, "Đã có sự thay đổi dữ liệu từ hệ thống", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(context, "Vui Lòng Nhập Ngày Để Thống Kê", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(context, "Đã có sự thay đổi dữ liệu từ hệ thống", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(context, "Vui Lòng Chọn Phương Thức Để Thống Kê.", Toast.LENGTH_SHORT).show();
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

    private void setControl() {
        spnTKDHDXLnam = findViewById(R.id.spnTKDHDXLnam);
        spnTKDHDXLthang = findViewById(R.id.spnTKDHDXLthang);
        lvThongKeDonHangDXL = findViewById(R.id.lvthongkedonhangdaxuly);
        btnThongKe = findViewById(R.id.btnTKDHDXLhongKe);
        rdbTKDHDXLTheoNgay = findViewById(R.id.rdbTKDHDXLthongketheongay);
        rdbTKDHDXLTheoThang = findViewById(R.id.rdbTKDHDXLthongketheothang);
        edtTKDHDXLthongketheongay = findViewById(R.id.edtTKDHDXLthongketheongay);
    }
}