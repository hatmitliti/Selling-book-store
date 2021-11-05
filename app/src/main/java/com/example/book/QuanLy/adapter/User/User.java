package com.example.book.QuanLy.adapter.User;

import java.io.Serializable;

public class User implements Serializable {
    private String address;
    private String birth;
    private int moneyBuy;
    private String name;
    private String phone;
    private String rank;
    private String image;

    public User() {
    }

    public User(String address, String birth, int moneyBuy, String name, String phone, String rank, String image) {
        this.address = address;
        this.birth = birth;
        this.moneyBuy = moneyBuy;
        this.name = name;
        this.phone = phone;
        this.rank = rank;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "User{" +
                "address='" + address + '\'' +
                ", birth='" + birth + '\'' +
                ", moneyBuy=" + moneyBuy +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", rank='" + rank + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
