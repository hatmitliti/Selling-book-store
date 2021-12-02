package com.example.book.Object;

public class QuanLy_Login {
    private String email;
    private String id;
    private String name;

    public QuanLy_Login() {
    }

    public QuanLy_Login(String email, String id, String name) {
        this.email = email;
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
