package com.example.book.ChuCuaHang.doanhthu;

public class DoanhThu {
    private String MaDH;
    private double GiaTriDH;

    public DoanhThu() {
    }

    public DoanhThu(String maDH, double giaTriDH) {
        MaDH = maDH;
        GiaTriDH = giaTriDH;
    }

    public String getMaDH() {
        return MaDH;
    }

    public void setMaDH(String maDH) {
        MaDH = maDH;
    }

    public double getGiaTriDH() {
        return GiaTriDH;
    }

    public void setGiaTriDH(int giaTriDH) {
        GiaTriDH = giaTriDH;
    }
}
