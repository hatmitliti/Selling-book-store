package com.example.book.ChuCuaHang.thongkedanhgia;

import java.util.Date;

public class DanhGia {
    private String NguoiDG,TenSP;
    private int SoSao;

    public DanhGia(String nguoiDG, String tenSP, int soSao) {
        NguoiDG = nguoiDG;
        TenSP = tenSP;
        SoSao = soSao;
    }

    public DanhGia() {
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


    public int getSoSao() {
        return SoSao;
    }

    public void setSoSao(int soSao) {
        SoSao = soSao;
    }
}
