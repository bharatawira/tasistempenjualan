package com.viss.fishstore;

import java.io.Serializable;

public class Cupang implements Serializable {
    String nama, harga, id, img_url, nama_user;

    public Cupang() {
    }

    public Cupang(String nama, String harga, String id, String img_url, String nama_user) {
        this.nama = nama;
        this.harga = harga;
        this.id = id;
        this.img_url = img_url;
        this.nama_user = nama_user;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }
}
