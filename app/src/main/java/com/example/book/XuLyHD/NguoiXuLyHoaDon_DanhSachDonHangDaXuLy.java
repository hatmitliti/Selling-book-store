package com.example.book.XuLyHD;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.book.ChuCuaHang.thongkedonhang.DonHang;
import com.example.book.ChuCuaHang.thongkedonhang.DonhangAdapter;
import com.example.book.R;

import java.util.ArrayList;

public class NguoiXuLyHoaDon_DanhSachDonHangDaXuLy extends AppCompatActivity {
    private Spinner spnTKDHDXLthang, spnTKDHDXLnam;
    private ListView lvThongKeDonHangDXL;
    private Context context;
    private ArrayList<DonHang> listDonHangDXL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_xu_ly_hoa_don_____danh_sach_don_hang_da_xu_ly);
        setControl();
        setEvent();
    }
    private void setEvent() {
        context = this;
        // đổ lữ liệu cho spinner
        String[] dataspinnerThang = {
                "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"
        };
        String[] dataspinnerNam = {
                "2018", "2019", "2020"
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


        DonHang dh1 = new DonHang("1", "A", "Đồng Nai", "2000000", "Đã Xử Lý");
        DonHang dh2 = new DonHang("2", "B", "Đồng Nai", "2000000", "Đã Xử Lý");
        DonHang dh3 = new DonHang("3", "C", "Đồng Nai", "2000000", "Đã Xử Lý");
        DonHang dh4 = new DonHang("4", "D", "Đồng Nai", "2000000", "Đã Xử Lý");
        DonHang dh5 = new DonHang("5", "E", "Đồng Nai", "2000000", "Đã Xử Lý");
        DonHang dh6 = new DonHang("6", "F", "Đồng Nai", "2000000", "Đã Xử Lý");
        DonHang dh7 = new DonHang("7", "G", "Đồng Nai", "2000000", "Đã Xử Lý");
        DonHang dh8 = new DonHang("8", "H", "Đồng Nai", "2000000", "Đã Xử Lý");
        DonHang dh9 = new DonHang("9", "J", "Đồng Nai", "2000000", "Đã Xử Lý");
        DonHang dh10 = new DonHang("10", "Q", "Đồng Nai", "2000000", "Đã Xử Lý");
        DonHang dh11 = new DonHang("11", "K", "Đồng Nai", "2000000", "Đã Xử Lý");
        listDonHangDXL = new ArrayList<>();

        listDonHangDXL.add(dh1);
        listDonHangDXL.add(dh2);
        listDonHangDXL.add(dh3);
        listDonHangDXL.add(dh4);
        listDonHangDXL.add(dh5);
        listDonHangDXL.add(dh6);
        listDonHangDXL.add(dh7);
        listDonHangDXL.add(dh8);
        listDonHangDXL.add(dh9);
        listDonHangDXL.add(dh10);
        listDonHangDXL.add(dh11);

        DonhangAdapter donhangAdapter = new DonhangAdapter(context, R.layout.item_adapter_thongkedonhang, listDonHangDXL);
        lvThongKeDonHangDXL.setAdapter(donhangAdapter);

    }

    private void setControl() {
        spnTKDHDXLnam = findViewById(R.id.spnTKDHDXLnam);
        spnTKDHDXLthang = findViewById(R.id.spnTKDHDXLthang);
        lvThongKeDonHangDXL = findViewById(R.id.lvthongkedonhangdaxuly);
    }
}