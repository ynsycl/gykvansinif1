package com.oyunhamuru.a03listviewozellestirme;

/**
 * Created by macbook on 13.06.2017.
 */

public class Kisi {
    private String  isim;
    private boolean kadinMi;

    public Kisi(String isim, boolean kadinMi) {
        super();
        this.isim = isim;
        this.kadinMi = kadinMi;
    }

    @Override
    public String toString() {
        return isim;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public boolean isKadinMi() {
        return kadinMi;
    }

    public void setKadinMi(boolean kadinMi) {
        this.kadinMi = kadinMi;
    }
}
