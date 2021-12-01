package com.example.book.ThuKho.Object;

import java.io.Serializable;
import java.util.ArrayList;

public class ImportProduct {
    private String id;
    private String nameCty;
    private String date;
    private String id_Company;
    private ArrayList<ProductImportObject> list;

    public ImportProduct() {
    }

    public ImportProduct(String id, String nameCty, String date, String id_Company, ArrayList<ProductImportObject> list) {
        this.id = id;
        this.nameCty = nameCty;
        this.date = date;
        this.id_Company = id_Company;
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameCty() {
        return nameCty;
    }

    public void setNameCty(String nameCty) {
        this.nameCty = nameCty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId_Company() {
        return id_Company;
    }

    public void setId_Company(String id_Company) {
        this.id_Company = id_Company;
    }

    public ArrayList<ProductImportObject> getList() {
        return list;
    }

    public void setList(ArrayList<ProductImportObject> list) {
        this.list = list;
    }
}
