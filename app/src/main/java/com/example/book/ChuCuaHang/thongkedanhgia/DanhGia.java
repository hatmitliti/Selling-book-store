package com.example.book.ChuCuaHang.thongkedanhgia;

public class DanhGia {
    private String NguoiDG,TenSP,NoiDung;
    private int SoSao;

    public DanhGia(String nguoiDG, String tenSP, String noiDung, int soSao) {
        NguoiDG = nguoiDG;
        TenSP = tenSP;
        NoiDung = noiDung;
        SoSao = soSao;
    }

    public String getNguoiDG() {
        return NguoiDG;
    }

    public void setNguoiDG(String nguoiDG) {
        NguoiDG = nguoiDG;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public int getSoSao() {
        return SoSao;
    }

    public void setSoSao(int soSao) {
        SoSao = soSao;
    }
}
