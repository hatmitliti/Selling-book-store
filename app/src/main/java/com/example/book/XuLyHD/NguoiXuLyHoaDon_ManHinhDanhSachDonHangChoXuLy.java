package com.example.book.XuLyHD;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.book.XuLyHD.DonHangChoXuLy.DonHangChoXuLy;
import com.example.book.XuLyHD.DonHangChoXuLy.DonHangChoXuLyAdapter;
import com.example.book.R;

import java.util.ArrayList;

public class NguoiXuLyHoaDon_ManHinhDanhSachDonHangChoXuLy extends AppCompatActivity {
    private ListView lvDonHangChoXuLy;
    private Context context;
    private ArrayList<DonHangChoXuLy> listDonHangChoXuLy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_xu_ly_hoa_don_____man_hinh_danh_sach_don_hang_cho_xu_ly);
        setControl();
        setEvent();
    }
    private void setEvent() {
        context = this;

        DonHangChoXuLy dh1 = new DonHangChoXuLy("DH01","Trần Văn A");
        DonHangChoXuLy dh2 = new DonHangChoXuLy("DH02","Trần Văn B");
        DonHangChoXuLy dh3 = new DonHangChoXuLy("DH03","Trần Văn C");
        DonHangChoXuLy dh4 = new DonHangChoXuLy("DH04","Trần Văn D");
        DonHangChoXuLy dh5 = new DonHangChoXuLy("DH05","Trần Văn F");

        listDonHangChoXuLy = new ArrayList<>();
        listDonHangChoXuLy.add(dh1);
        listDonHangChoXuLy.add(dh2);
        listDonHangChoXuLy.add(dh3);
        listDonHangChoXuLy.add(dh4);
        listDonHangChoXuLy.add(dh5);

        DonHangChoXuLyAdapter donHangChoXuLyAdapter  = new DonHangChoXuLyAdapter(R.layout.item_adapter_danhsachdonhangchoxuly,context,listDonHangChoXuLy);
        lvDonHangChoXuLy.setAdapter(donHangChoXuLyAdapter);
    }
    private void setControl() {
        lvDonHangChoXuLy = findViewById(R.id.lvDanhSachDonHangChoXuLy);
    }
}