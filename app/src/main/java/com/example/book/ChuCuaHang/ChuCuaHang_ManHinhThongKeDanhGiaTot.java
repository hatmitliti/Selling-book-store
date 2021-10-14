package com.example.book.ChuCuaHang;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.book.ChuCuaHang.thongkedanhgia.DanhGia;
import com.example.book.ChuCuaHang.thongkedanhgia.DanhGiaAdapter;
import com.example.book.R;

import java.util.ArrayList;

public class ChuCuaHang_ManHinhThongKeDanhGiaTot extends AppCompatActivity {
    private Spinner spnTKDGTthang, spnTKDGTnam;
    private ListView lvThongKeDanhGiaTot;
    private Context context;
    private ArrayList<DanhGia> listDanhGiaTot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_cua_hang_____man_hinh_thong_ke_danh_gia_tot);
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
        spnTKDGTthang.setAdapter(adapterThang);
        spnTKDGTnam.setAdapter(adapterNam);
        spnTKDGTthang.setEnabled(false);
        spnTKDGTnam.setEnabled(false);

        DanhGia dg1 = new DanhGia("A","Sách 1","acbxyz",5);
        DanhGia dg2 = new DanhGia("B","Sách 2","acbxyz",5);
        DanhGia dg3 = new DanhGia("C","Sách 3","acbxyz",5);
        DanhGia dg4 = new DanhGia("D","Sách 4","acbxyz",5);
        DanhGia dg5 = new DanhGia("X","Sách 5","acbxyz",5);
        DanhGia dg6 = new DanhGia("Y","Sách 6","acbxyz",5);
        listDanhGiaTot = new ArrayList<>();

        listDanhGiaTot.add(dg1);
        listDanhGiaTot.add(dg2);
        listDanhGiaTot.add(dg3);
        listDanhGiaTot.add(dg4);
        listDanhGiaTot.add(dg5);
        listDanhGiaTot.add(dg6);


        DanhGiaAdapter danhGiaAdapter = new DanhGiaAdapter( R.layout.item_adapter_thongkedanhgiatotxau,context, listDanhGiaTot);
        lvThongKeDanhGiaTot.setAdapter(danhGiaAdapter);

    }

    private void setControl() {
        spnTKDGTnam = findViewById(R.id.spnTKDGTnam);
        spnTKDGTthang = findViewById(R.id.spnTKDGTthang);
        lvThongKeDanhGiaTot = findViewById(R.id.lvthongkedanhgiatot);
    }
}