package com.example.book.QuanLy.models;

public class Employee {
    private String id;
    private String name;
    private String birth;
    private String sdt;
    private String email;
    private int luongCB;
    private String permission;

    public Employee() {
    }

    public Employee(String id, String name, String birth, String sdt, String email, int luongCB, String permission) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.sdt = sdt;
        this.email = email;
        this.luongCB = luongCB;
        this.permission = permission;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return id;
    }

    public void setUsername(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLuongCB() {
        return luongCB;
    }

    public void setLuongCB(int luongCB) {
        this.luongCB = luongCB;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
