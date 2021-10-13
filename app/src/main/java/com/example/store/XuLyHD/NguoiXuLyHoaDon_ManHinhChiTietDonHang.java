package com.example.store.XuLyHD;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.store.XuLyHD.ChiTietSanPhamTrongDonHang.SanPham;
import com.example.store.XuLyHD.ChiTietSanPhamTrongDonHang.SanPhamAdapter;
import com.example.store.R;

import java.util.ArrayList;

public class NguoiXuLyHoaDon_ManHinhChiTietDonHang extends AppCompatActivity {
    private ListView lvSanPhamTrongDonHang;
    private Context context;
    private ArrayList<SanPham> listSanPhamTrongDonHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_xu_ly_hoa_don_____man_hinh_chi_tiet_don_hang);
        setControl();
        setEvent();
    }
    private void setEvent() {
        context = this;

        SanPham sp1 = new SanPham("Nhà Giả Kim",1,65000,R.drawable.nhagiakim);
        SanPham sp2 = new SanPham("Rừng Nauy",1,85000,R.drawable.rungnauy);
        SanPham sp3 = new SanPham("Lược Sử Loài Người",2,150000,R.drawable.luocsuloainguoi);

        listSanPhamTrongDonHang = new ArrayList<>();

        listSanPhamTrongDonHang.add(sp1);
        listSanPhamTrongDonHang.add(sp2);
        listSanPhamTrongDonHang.add(sp3);

        SanPhamAdapter sanPhamAdapter  = new SanPhamAdapter(R.layout.item_adapter_sanphamtrongdonhangchitietdonhang,context,listSanPhamTrongDonHang);
        lvSanPhamTrongDonHang.setAdapter(sanPhamAdapter);
    }
    private void setControl() {
        lvSanPhamTrongDonHang = findViewById(R.id.CTDHlvDanhSachSP);
    }
}