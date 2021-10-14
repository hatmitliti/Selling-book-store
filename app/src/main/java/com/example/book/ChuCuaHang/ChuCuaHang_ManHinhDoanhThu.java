package com.example.book.ChuCuaHang;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.book.ChuCuaHang.doanhthu.DoanhThu;
import com.example.book.ChuCuaHang.doanhthu.DoanhThuAdapter;
import com.example.book.R;

import java.util.ArrayList;

public class ChuCuaHang_ManHinhDoanhThu extends AppCompatActivity {
    private Spinner spnDTthang, spnDTnam;
    private ListView lvThongKeDoanhThu;
    private Context context;
    private ArrayList<DoanhThu> listDoanhThu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_cua_hang_____man_hinh_doanh_thu);
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
        spnDTthang.setAdapter(adapterThang);
        spnDTnam.setAdapter(adapterNam);
        spnDTthang.setEnabled(false);
        spnDTnam.setEnabled(false);

        DoanhThu dt1 = new DoanhThu("1",200000);
        DoanhThu dt2 = new DoanhThu("2",200000);
        DoanhThu dt3 = new DoanhThu("3",200000);
        DoanhThu dt4 = new DoanhThu("4",200000);
        listDoanhThu = new ArrayList<>();

        listDoanhThu.add(dt1);
        listDoanhThu.add(dt2);
        listDoanhThu.add(dt3);
        listDoanhThu.add(dt4);


        DoanhThuAdapter doanhThuAdapter = new DoanhThuAdapter( R.layout.item_adapter_doanhthu,context, listDoanhThu);
        lvThongKeDoanhThu.setAdapter(doanhThuAdapter);

    }

    private void setControl() {
        spnDTnam = findViewById(R.id.spnDTnam);
        spnDTthang = findViewById(R.id.spnDTthang);
        lvThongKeDoanhThu = findViewById(R.id.lvthongkedoanhthu);
    }
}