package com.example.book.ChuCuaHang.thongkedonhang;

public class DonHang {
    private String MaDH;
    private String NguoiDat;
    private String DiaChi;
    private String TriGia;
    private String TinhTrang;

    public DonHang(String maDH, String nguoiDat, String diaChi, String triGia, String tinhTrang) {
        MaDH = maDH;
        NguoiDat = nguoiDat;
        DiaChi = diaChi;
        TriGia = triGia;
        TinhTrang = tinhTrang;
    }

    public String getMaDH() {
        return MaDH;
    }

    public void setMaDH(String maDH) {
        MaDH = maDH;
    }

    public String getNguoiDat() {
        return NguoiDat;
    }

    public void setNguoiDat(String nguoiDat) {
        NguoiDat = nguoiDat;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
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
