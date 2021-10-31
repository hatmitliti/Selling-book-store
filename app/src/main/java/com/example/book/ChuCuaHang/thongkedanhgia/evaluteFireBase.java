package com.example.book.ChuCuaHang.thongkedanhgia;

public class evaluteFireBase {
    private String date, id, id_product,id_user;
    private int star;

    public evaluteFireBase() {
    }

    public evaluteFireBase(String date, String id, String id_product, String id_user, int star) {
        this.date = date;
        this.id = id;
        this.id_product = id_product;
        this.id_user = id_user;
        this.star = star;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public int  getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
