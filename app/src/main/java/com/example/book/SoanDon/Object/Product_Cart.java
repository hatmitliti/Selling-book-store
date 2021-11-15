package com.example.book.SoanDon.Object;

public class Product_Cart {
    private  String name;
    private  int price;
    private  int quality;

    public Product_Cart() {
    }

    public Product_Cart(String name, int price, int quality) {
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
