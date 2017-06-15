package com.oyunhamuru.n03_fragmentler_arasi_veri_gonderme;

/**
 * Classlar  arasındaki bağı Gevşek Bağlantı İlkesi (Loose Coupling) yapmak için
 * interface ile veri tranferi yapacağız
 */

public interface VeriTasiyici {

    /**
     * Verinin taşınacağı metod
     * @param veri=taşınacak veri
     */
    public void gonder(String veri);
}
