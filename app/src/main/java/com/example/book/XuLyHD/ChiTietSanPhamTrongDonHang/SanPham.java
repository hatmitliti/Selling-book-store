package com.example.book.XuLyHD.ChiTietSanPhamTrongDonHang;

public class SanPham {
    private String TenSP;
    private int SoLuongSP, GiaSP, imgAnhSP;

    public SanPham(String tenSP, int soLuongSP, int giaSP, int imgAnhSP) {
        TenSP = tenSP;
        SoLuongSP = soLuongSP;
        GiaSP = giaSP;
        this.imgAnhSP = imgAnhSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public int getSoLuongSP() {
        return SoLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        SoLuongSP = soLuongSP;
    }

    public int getGiaSP() {
        return GiaSP;
    }

    public void setGiaSP(int giaSP) {
        GiaSP = giaSP;
    }

    public int getImgAnhSP() {
        return imgAnhSP;
    }

    public void setImgAnhSP(int imgAnhSP) {
        this.imgAnhSP = imgAnhSP;
    }
}
