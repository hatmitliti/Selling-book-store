package com.example.store.ChuCuaHang.doanhthu;

public class DoanhThu {
    private String MaDH;
    private int GiaTriDH;

    public DoanhThu(String maDH, int giaTriDH) {
        MaDH = maDH;
        GiaTriDH = giaTriDH;
    }

    public String getMaDH() {
        return MaDH;
    }

    public void setMaDH(String maDH) {
        MaDH = maDH;
    }

    public int getGiaTriDH() {
        return GiaTriDH;
    }

    public void setGiaTriDH(int giaTriDH) {
        GiaTriDH = giaTriDH;
    }
}
