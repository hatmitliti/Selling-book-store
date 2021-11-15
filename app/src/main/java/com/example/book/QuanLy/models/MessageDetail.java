package com.example.book.QuanLy.models;

public class MessageDetail {
    private String content;
    private String who;

    public MessageDetail() {
    }

    public MessageDetail(String content, String who) {
        this.content = content;
        this.who = who;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
