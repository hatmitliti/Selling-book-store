package com.example.book.ChuCuaHang;

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

public class ChuCuaHang_ManHinhThongKeDonHang extends AppCompatActivity {
    private Spinner spnTKDHthang,spnTKDHnam;
    private ListView lvThongKeDonHang;
    private Context context;
    private ArrayList<DonHang> listDonHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_cua_hang_____man_hinh_thong_ke_don_hang);
        setControl();
        setEvent();
    }
    private void setEvent() {
        context = this;
        // đổ lữ liệu cho spinner
        String [] dataspinnerThang = {
                "Tháng 1","Tháng 2","Tháng 3","Tháng 4","Tháng 5","Tháng 6","Tháng 7","Tháng 8","Tháng 9","Tháng 10","Tháng 11","Tháng 12"
        };
        String [] dataspinnerNam = {
                "2018","2019","2020"
        };
        //Gán Dữ liệu vào Adapter
        ArrayAdapter<String> adapterThang = new ArrayAdapter<>(context,android.R.layout.simple_spinner_item,dataspinnerThang);
        ArrayAdapter<String> adapterNam = new ArrayAdapter<>(context,android.R.layout.simple_spinner_item,dataspinnerNam);
        //
        adapterNam.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        adapterThang.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        //
        spnTKDHthang.setAdapter(adapterThang);
        spnTKDHnam.setAdapter(adapterNam);
        spnTKDHthang.setEnabled(false);
        spnTKDHnam.setEnabled(false);


        DonHang dh1 = new DonHang("1","A","Đồng Nai","2000000","Đang Giao");
        DonHang dh2 = new DonHang("2","B","Đồng Nai","2000000","Đang Giao");
        DonHang dh3 = new DonHang("3","C","Đồng Nai","2000000","Đang Giao");
        DonHang dh4 =  new DonHang("4","D","Đồng Nai","2000000","Đang Giao");
        DonHang dh5 = new DonHang("5","E","Đồng Nai","2000000","Đang Giao");
        DonHang dh6 = new DonHang("6","F","Đồng Nai","2000000","Đang Giao");
        DonHang dh7 = new DonHang("7","G","Đồng Nai","2000000","Đang Giao");
        DonHang dh8 = new DonHang("8","H","Đồng Nai","2000000","Đang Giao");
        DonHang dh9 = new DonHang("9","J","Đồng Nai","2000000","Đang Giao");
        DonHang dh10 = new DonHang("10","Q","Đồng Nai","2000000","Đang Giao");
        DonHang dh11 = new DonHang("11","K","Đồng Nai","2000000","Đang Giao");
        listDonHang = new ArrayList<>();

        listDonHang.add(dh1);
        listDonHang.add(dh2);
        listDonHang.add(dh3);
        listDonHang.add(dh4);
        listDonHang.add(dh5);
        listDonHang.add(dh6);
        listDonHang.add(dh7);
        listDonHang.add(dh8);
        listDonHang.add(dh9);
        listDonHang.add(dh10);
        listDonHang.add(dh11);

        DonhangAdapter donhangAdapter = new DonhangAdapter(context,R.layout.item_adapter_thongkedonhang,listDonHang);
        lvThongKeDonHang.setAdapter(donhangAdapter);

    }

    private void setControl() {
        spnTKDHnam = findViewById(R.id.spnTKDHnam);
        spnTKDHthang = findViewById(R.id.spnTKDHthang);
        lvThongKeDonHang = findViewById(R.id.lvthongkedonhang);
    }
}