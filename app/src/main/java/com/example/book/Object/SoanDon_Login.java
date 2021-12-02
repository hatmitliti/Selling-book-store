package com.example.book.Object;

public class SoanDon_Login {
    private String birth;
    private String email;
    private String id;
    private int luongCB;
    private String name;
    private String permission;
    private String sdt;
    private String username;

    public SoanDon_Login() {
    }

    public SoanDon_Login(String birth, String email, String id, int luongCB, String name, String permission, String sdt, String username) {
        this.birth = birth;
        this.email = email;
        this.id = id;
        this.luongCB = luongCB;
        this.name = name;
        this.permission = permission;
        this.sdt = sdt;
        this.username = username;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLuongCB() {
        return luongCB;
    }

    public void setLuongCB(int luongCB) {
        this.luongCB = luongCB;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
