package com.oyunhamuru.s01_personel_otomasyonu.data;

/**
 * Kişi bean class'ı
 *
 * Bölye bir class oluşturarak kişi türünde değişkenler tanımlayabileceğiz
 *
 */

public class Kisi {
    private int id;
    private String ad;
    private String soyad;




    /**
     * Constructor metodlarını oluşturma
     * sonra tekrardan fareye sağ tıklayıp Generate'i seç
     * Generate'den constructor'ı seç
     * tüm değişkenleri seçip okYe bas
     *
     * Aynı işlemleri constructor'a kadar yapıp
     * Construcyor dan Select None 'ı seçerek boş
     * bir constructor oluştur
     */

    public Kisi(){

    }

    public Kisi(int id, String ad, String soyad) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
    }


    /**
     * Getter ve Setter metodlarını oluşturma
     *fareye sağ tıklayıp Generate!i seç
     * Generate'ten Getter ve Setter'i seç
     * sonra tüm değişkenleri seçip Ok'ye bas
     */

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
