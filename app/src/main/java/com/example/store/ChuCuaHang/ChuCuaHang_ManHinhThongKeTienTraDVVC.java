package com.example.store.ChuCuaHang;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.store.ChuCuaHang.sotienphaitraDVVC.TienTraDVVC;
import com.example.store.ChuCuaHang.sotienphaitraDVVC.TienTraDVVCAdapter;
import com.example.store.R;

import java.util.ArrayList;

public class ChuCuaHang_ManHinhThongKeTienTraDVVC extends AppCompatActivity {
    private Spinner spnSTPTDVVCthang, spnSTPTDVVCnam;
    private ListView lvThongKeSTPTDVVC;
    private Context context;
    private ArrayList<TienTraDVVC> listTienPhaiTraDVVC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_cua_hang_____man_hinh_thong_ke_tien_tra_d_v_v_c);
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
        spnSTPTDVVCthang.setAdapter(adapterThang);
        spnSTPTDVVCnam.setAdapter(adapterNam);
        spnSTPTDVVCthang.setEnabled(false);
        spnSTPTDVVCnam.setEnabled(false);

        TienTraDVVC t1 = new TienTraDVVC("1",200000,20000);
        TienTraDVVC t2 = new TienTraDVVC("2",200000,20000);
        TienTraDVVC t3 = new TienTraDVVC("3",200000,20000);
        TienTraDVVC t4 = new TienTraDVVC("4",200000,20000);
        listTienPhaiTraDVVC = new ArrayList<>();

        listTienPhaiTraDVVC.add(t1);
        listTienPhaiTraDVVC.add(t2);
        listTienPhaiTraDVVC.add(t3);
        listTienPhaiTraDVVC.add(t4);


        TienTraDVVCAdapter tienTraDVVCAdapter = new TienTraDVVCAdapter(context, R.layout.item_adapter_tientradonvivanchuyen, listTienPhaiTraDVVC);
        lvThongKeSTPTDVVC.setAdapter(tienTraDVVCAdapter);

    }

    private void setControl() {
        spnSTPTDVVCnam = findViewById(R.id.spnTKSTPTDVVCnam);
        spnSTPTDVVCthang = findViewById(R.id.spnTKSTPTDVVCthang);
        lvThongKeSTPTDVVC = findViewById(R.id.lvthongkesotienphaitraDVVC);
    }
}