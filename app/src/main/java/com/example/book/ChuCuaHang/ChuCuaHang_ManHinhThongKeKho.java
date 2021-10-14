package com.example.book.ChuCuaHang;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.book.ChuCuaHang.thongkekho.SPKho;
import com.example.book.ChuCuaHang.thongkekho.SPKhoAdapter;
import com.example.book.R;

import java.util.ArrayList;

public class ChuCuaHang_ManHinhThongKeKho extends AppCompatActivity {
    private ListView lvThongKeKho;
    private Context context;
    private ArrayList<SPKho> listSPKho;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_cua_hang_____man_hinh_thong_ke_kho);
        setControl();
        setEvent();
    }
    private void setEvent() {
        context = this;
        SPKho sp1 = new SPKho("S1","Sách 1",20);
        SPKho sp2 = new SPKho("S2","Sách 2",20);
        SPKho sp3 = new SPKho("S3","Sách 3",20);
        SPKho sp4 = new SPKho("S4","Sách 4",20);

        listSPKho = new ArrayList<>();
        listSPKho.add(sp1);
        listSPKho.add(sp2);
        listSPKho.add(sp3);
        listSPKho.add(sp4);

        SPKhoAdapter spKhoAdapter = new SPKhoAdapter(R.layout.item_adapter_thongkekho,context,listSPKho);
        lvThongKeKho.setAdapter(spKhoAdapter);
    }
    private void setControl() {
        lvThongKeKho = findViewById(R.id.lvThongKeKho);
    }
}