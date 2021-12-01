package com.example.book.ThuKho.Object;

public class ProductImportObject {
    private String id_Product;
    private String name;
    private int quality;
    private int price;

    public ProductImportObject() {
    }

    public ProductImportObject(String id_Product, String name, int quality, int price) {
        this.id_Product = id_Product;
        this.name = name;
        this.quality = quality;
        this.price = price;
    }

    public String getId_Product() {
        return id_Product;
    }

    public void setId_Product(String id_Product) {
        this.id_Product = id_Product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
