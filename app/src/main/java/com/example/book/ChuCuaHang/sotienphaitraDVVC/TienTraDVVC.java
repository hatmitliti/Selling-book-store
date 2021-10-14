package com.example.book.ChuCuaHang.sotienphaitraDVVC;

public class TienTraDVVC {

    private String MaDH;
    private int  TriGia, SoTienPhaiTra;

    public TienTraDVVC(String maDH, int triGia, int soTienPhaiTra) {
        MaDH = maDH;
        TriGia = triGia;
        SoTienPhaiTra = soTienPhaiTra;
    }

    public String getMaDH() {
        return MaDH;
    }

    public void setMaDH(String maDH) {
        MaDH = maDH;
    }

    public int getTriGia() {
        return TriGia;
    }

    public void setTriGia(int triGia) {
        TriGia = triGia;
    }

    public int getSoTienPhaiTra() {
        return SoTienPhaiTra;
    }

    public void setSoTienPhaiTra(int soTienPhaiTra) {
        SoTienPhaiTra = soTienPhaiTra;
    }
}
