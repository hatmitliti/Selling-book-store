package com.example.book.ThuKho.Object;

public class ProductExImport {
    private String name;
    private Integer quality;
    private boolean confirm;

    public ProductExImport() {
    }

    public ProductExImport(String name, Integer quality, boolean confirm) {
        this.name = name;
        this.quality = quality;
        this.confirm = confirm;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }
}
