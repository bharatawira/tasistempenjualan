package com.viss.fishstore;

public class Penjualan {
    String id_penjualan, id_user, nama_cupang, gambar_cupang, nama_pembeli, notelp_pembeli, alamat_pembeli, harga_jual;
    boolean selected = false;

    public Penjualan() {
    }

    public Penjualan(String id_penjualan, String id_user, String nama_cupang, String gambar_cupang, String nama_pembeli, String notelp_pembeli, String alamat_pembeli, String harga_jual, boolean selected) {
        this.id_penjualan = id_penjualan;
        this.id_user = id_user;
        this.nama_cupang = nama_cupang;
        this.gambar_cupang = gambar_cupang;
        this.nama_pembeli = nama_pembeli;
        this.notelp_pembeli = notelp_pembeli;
        this.alamat_pembeli = alamat_pembeli;
        this.harga_jual = harga_jual;
        this.selected = selected;
    }

    public String getId_penjualan() {
        return id_penjualan;
    }

    public void setId_penjualan(String id_penjualan) {
        this.id_penjualan = id_penjualan;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getNama_cupang() {
        return nama_cupang;
    }

    public void setNama_cupang(String nama_cupang) {
        this.nama_cupang = nama_cupang;
    }

    public String getGambar_cupang() {
        return gambar_cupang;
    }

    public void setGambar_cupang(String gambar_cupang) {
        this.gambar_cupang = gambar_cupang;
    }

    public String getNama_pembeli() {
        return nama_pembeli;
    }

    public void setNama_pembeli(String nama_pembeli) {
        this.nama_pembeli = nama_pembeli;
    }

    public String getNotelp_pembeli() {
        return notelp_pembeli;
    }

    public void setNotelp_pembeli(String notelp_pembeli) {
        this.notelp_pembeli = notelp_pembeli;
    }

    public String getAlamat_pembeli() {
        return alamat_pembeli;
    }

    public void setAlamat_pembeli(String alamat_pembeli) {
        this.alamat_pembeli = alamat_pembeli;
    }

    public String getHarga_jual() {
        return harga_jual;
    }

    public void setHarga_jual(String harga_jual) {
        this.harga_jual = harga_jual;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
