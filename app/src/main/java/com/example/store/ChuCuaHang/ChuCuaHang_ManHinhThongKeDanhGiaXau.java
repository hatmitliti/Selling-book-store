package com.example.store.ChuCuaHang;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.store.ChuCuaHang.thongkedanhgia.DanhGia;
import com.example.store.ChuCuaHang.thongkedanhgia.DanhGiaAdapter;
import com.example.store.R;

import java.util.ArrayList;

public class ChuCuaHang_ManHinhThongKeDanhGiaXau extends AppCompatActivity {

    private Spinner spnTKDGXthang, spnTKDGXnam;
    private ListView lvThongKeDanhGiaXau;
    private Context context;
    private ArrayList<DanhGia> listDanhGiaXau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_cua_hang_____man_hinh_thong_ke_danh_gia_xau);
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
        spnTKDGXthang.setAdapter(adapterThang);
        spnTKDGXnam.setAdapter(adapterNam);
        spnTKDGXthang.setEnabled(false);
        spnTKDGXnam.setEnabled(false);

        DanhGia dg1 = new DanhGia("A","Sách 1","acbxyz",2);
        DanhGia dg2 = new DanhGia("B","Sách 2","acbxyz",2);
        DanhGia dg3 = new DanhGia("C","Sách 3","acbxyz",3);
        DanhGia dg4 = new DanhGia("D","Sách 4","acbxyz",3);
        DanhGia dg5 = new DanhGia("X","Sách 5","acbxyz",1);
        DanhGia dg6 = new DanhGia("Y","Sách 6","acbxyz",1);
        listDanhGiaXau = new ArrayList<>();

        listDanhGiaXau.add(dg1);
        listDanhGiaXau.add(dg2);
        listDanhGiaXau.add(dg3);
        listDanhGiaXau.add(dg4);
        listDanhGiaXau.add(dg5);
        listDanhGiaXau.add(dg6);


        DanhGiaAdapter danhGiaAdapter = new DanhGiaAdapter( R.layout.item_adapter_thongkedanhgiatotxau,context, listDanhGiaXau);
        lvThongKeDanhGiaXau.setAdapter(danhGiaAdapter);

    }

    private void setControl() {
        spnTKDGXnam = findViewById(R.id.spnTKDGXnam);
        spnTKDGXthang = findViewById(R.id.spnTKDGXthang);
        lvThongKeDanhGiaXau = findViewById(R.id.lvthongkedanhgiaxau);
    }
}