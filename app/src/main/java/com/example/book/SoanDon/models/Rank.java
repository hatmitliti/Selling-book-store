package com.example.book.SoanDon.models;

public class Rank implements Comparable<Rank> {
    private String id;
    private String name;
    private int money;

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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Rank() {
    }

    public Rank(String id, String name, int money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    @Override
    public int compareTo(Rank rank) {
        if (money > rank.getMoney()) {
            return 1;
        } else return -1;
    }
}
