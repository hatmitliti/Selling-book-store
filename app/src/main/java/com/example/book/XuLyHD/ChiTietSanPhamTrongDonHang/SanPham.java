package com.example.book.XuLyHD.ChiTietSanPhamTrongDonHang;

public class SanPham {
    private String name;
    private int price;
    private int quality;
    private String image;


    public SanPham() {
    }

    public SanPham(String name, int price, int quality, String image) {
        this.name = name;
        this.price = price;
        this.quality = quality;
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
