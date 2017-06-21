package com.oyunhamuru.s04_sqlite_veritabani_olusturma.data;

/**
 * Kişi bean class'ı
 */

public class Kisi {
    private int id;
    private String ad;
    private String soyad;

    public Kisi(){

    }

    public Kisi(int id, String ad, String soyad) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }
}
