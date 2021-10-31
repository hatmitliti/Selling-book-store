package com.example.book.ChuCuaHang.sotienphaitraDVVC;

public class TienTraDVVC {

    private String MaDH;
    private double TriGia, SoTienPhaiTra;

    public TienTraDVVC() {
    }

    public TienTraDVVC(String maDH, double triGia, double soTienPhaiTra) {
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

    public double getTriGia() {
        return TriGia;
    }

    public void setTriGia(double triGia) {
        TriGia = triGia;
    }

    public double getSoTienPhaiTra() {
        return SoTienPhaiTra;
    }

    public void setSoTienPhaiTra(double soTienPhaiTra) {
        SoTienPhaiTra = soTienPhaiTra;
    }
}
