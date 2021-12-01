package com.example.book.ThuKho.Object;

import java.io.Serializable;

public class Company implements Serializable {
    private String name,address,Phone,Email,id;
    private long taxcode;

    public Company() {
    }

    public Company(String name, String address, String phone, String email, String id, long taxcode) {
        this.name = name;
        this.address = address;
        Phone = phone;
        Email = email;
        this.id = id;
        this.taxcode = taxcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTaxcode() {
        return taxcode;
    }

    public void setTaxcode(long taxcode) {
        this.taxcode = taxcode;
    }
}
