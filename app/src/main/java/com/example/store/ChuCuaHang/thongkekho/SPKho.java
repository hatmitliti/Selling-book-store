package com.example.store.ChuCuaHang.thongkekho;

public class SPKho {
    private String MaSP,TenSP;
    private  int SoLuong;

    public SPKho(String maSP, String tenSP, int soLuong) {
        MaSP = maSP;
        TenSP = tenSP;
        SoLuong = soLuong;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String maSP) {
        MaSP = maSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }
}
