package com.example.store.ThuKho.TKQuanLySanPham;

public class Product {
    private String hinhAnh;
    private String tenHinhAnh;
    private String id;
    private String tenSanPham;
    private int giaTien;
    private String category;
    private double star;
    private int stock;
    private int sold;
    private String description;
    private String author;

    public Product() {

    }

    public Product(String hinhAnh, String tenHinhAnh, String id, String tenSanPham, int giaTien, String category, double star, int stock, int sold, String description, String author) {
        this.hinhAnh = hinhAnh;
        this.tenHinhAnh = tenHinhAnh;
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.giaTien = giaTien;
        this.category = category;
        this.star = star;
        this.stock = stock;
        this.sold = sold;
        this.description = description;
        this.author = author;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTenHinhAnh() {
        return tenHinhAnh;
    }

    public void setTenHinhAnh(String tenHinhAnh) {
        this.tenHinhAnh = tenHinhAnh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Product{" +
                "hinhAnh='" + hinhAnh + '\'' +
                ", tenHinhAnh='" + tenHinhAnh + '\'' +
                ", id='" + id + '\'' +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", giaTien=" + giaTien +
                ", category='" + category + '\'' +
                ", star=" + star +
                ", stock=" + stock +
                ", sold=" + sold +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
