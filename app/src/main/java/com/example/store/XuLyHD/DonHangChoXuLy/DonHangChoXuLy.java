package com.example.store.XuLyHD.DonHangChoXuLy;

public class DonHangChoXuLy {
    private String MaDH;
    private String TenNguoiDat;

    public DonHangChoXuLy(String maDH, String tenNguoiDat) {
        MaDH = maDH;
        TenNguoiDat = tenNguoiDat;
    }

    public String getMaDH() {
        return MaDH;
    }

    public void setMaDH(String maDH) {
        MaDH = maDH;
    }

    public String getTenNguoiDat() {
        return TenNguoiDat;
    }

    public void setTenNguoiDat(String tenNguoiDat) {
        TenNguoiDat = tenNguoiDat;
    }
}
