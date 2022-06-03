/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ZERO_ONE
 */
public class DataBarang {

    private String nama, kode, penyuplai, status;
    private Integer stok, harga;

    public DataBarang() {
        this("", "", "", 0, 0, "");
    }

    //kontruktor dengan status
    public DataBarang(String nama, String kode, String penyuplai, Integer stok, Integer harga, String status) {
        this.nama = nama;
        this.kode = kode;
        this.penyuplai = penyuplai;
        this.stok = stok;
        this.harga = harga;
        this.status = status;
    }

    //tanpa status
//    public DataBarang(String nama,String kode,String penyuplai, int kuantitas,int harga) {
//        this.nama = new SimpleStringProperty(nama);
//        this.kode = new SimpleStringProperty(kode);
//        this.penyuplai = new SimpleStringProperty(penyuplai);
//        this.stok = new SimpleIntegerProperty(stok);
//        this.harga = new SimpleIntegerProperty(harga);
//    }
//        this.kuantitas = new SimpleIntegerProperty(kuantitas);
//        this.harga = new SimpleIntegerProperty(harga);
//    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPenyuplai() {
        return penyuplai;
    }

    public void setPenyuplai(String penyuplai) {
        this.penyuplai = penyuplai;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }
}
