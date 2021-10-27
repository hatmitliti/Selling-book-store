package com.example.book.SoanDon.models;

public class Shipper {
    private String birth;
    private String id;
    private String name;
    private String phone;

    public Shipper() {
    }

    public Shipper(String birth, String id, String name, String phone) {
        this.birth = birth;
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
