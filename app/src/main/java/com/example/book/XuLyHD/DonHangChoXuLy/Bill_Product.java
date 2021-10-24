package com.example.book.XuLyHD.DonHangChoXuLy;

public class Bill_Product {

    private String name;
    private int price;
    private int quality;

    public Bill_Product() {
    }

    public Bill_Product(String name, int price, int quality) {
        this.name = name;
        this.price = price;
        this.quality = quality;
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

}
