/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ASUS
 */
public class Transaksi {

    private String nama;
    private String tanggal;
    private String jumlah;
    private String harga;
    private String total;

    public Transaksi() {
        this(" ", " ", " ", " ", " ");
    }

    public Transaksi(String nama, String tanggal, String jumlah, String harga, String total) {
        this.nama = nama;
        this.tanggal = tanggal;
        this.jumlah = jumlah;
        this.harga = harga;
        this.total = total;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

}
