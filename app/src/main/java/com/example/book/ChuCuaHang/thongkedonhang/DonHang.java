package com.example.book.ChuCuaHang.thongkedonhang;

public class DonHang {
    private String MaDH;
    private String TriGia;
    private String TinhTrang;

    public DonHang() {
    }

    public DonHang(String maDH, String triGia, String tinhTrang) {
        MaDH = maDH;
        TriGia = triGia;
        TinhTrang = tinhTrang;
    }

    public String getMaDH() {
        return MaDH;
    }

    public void setMaDH(String maDH) {
        MaDH = maDH;
    }

    public String getTriGia() {
        return TriGia;
    }

    public void setTriGia(String triGia) {
        TriGia = triGia;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        TinhTrang = tinhTrang;
    }
}
