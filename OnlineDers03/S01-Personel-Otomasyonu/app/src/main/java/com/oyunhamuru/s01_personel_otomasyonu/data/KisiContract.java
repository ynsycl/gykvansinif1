package com.oyunhamuru.s01_personel_otomasyonu.data;

import android.provider.BaseColumns;

/**
 * Kontrat sınıfı:
 * Tablolar, sütunlar ve URI'ler için isim tanımlamaları yapan
 * sabitleri bulunduran taşıyıcı olarak düşünebiliriz.
 */

public class KisiContract  {

    /**
     * Tablo içeriklerini tanımlayan dahili sınıf
     */
    public static final class KisiEnty implements BaseColumns{

        // Sabit değişkenkerin tanımlanması
        public static final String TABLE_NAME="kisi";
        public static final String COLUM_AD="ad";
        public static final String COLUM_SOYAD="soyad";

        //ID_KEY intentler arası veri gönderirken kullIYORUZ
        public static final String ID_KEY="idKey";
    }

}
