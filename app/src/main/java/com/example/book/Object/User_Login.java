package com.example.book.Object;

public class User_Login {
    private String address;
    private String birth;
    private String image;
    private int moneyBuy;
    private String name;
    private String nameImage;
    private String phone;
    private String rank;

    public User_Login() {
    }

    public User_Login(String address, String birth, String image, int moneyBuy, String name, String nameImage, String phone, String rank) {
        this.address = address;
        this.birth = birth;
        this.image = image;
        this.moneyBuy = moneyBuy;
        this.name = name;
        this.nameImage = nameImage;
        this.phone = phone;
        this.rank = rank;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getMoneyBuy() {
        return moneyBuy;
    }

    public void setMoneyBuy(int moneyBuy) {
        this.moneyBuy = moneyBuy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
