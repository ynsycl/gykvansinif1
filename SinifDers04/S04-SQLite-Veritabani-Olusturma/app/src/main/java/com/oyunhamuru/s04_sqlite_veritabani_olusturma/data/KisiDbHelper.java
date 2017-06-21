package com.oyunhamuru.s04_sqlite_veritabani_olusturma.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * KisiDbHelper class'ına kalıtım ile  SQLiteOpenHelper class'ı
 * kalıtılır.Bu sayede bizi uğraştıracak bir çok kodu hazır olarak
 * kullanma imkanı buluruz
 * SQLiteOpenHelper'i extend yapınca zorunlu olan onCreate ve onUpdate()
 * metodları imlement edilmeli
 */
public class KisiDbHelper extends SQLiteOpenHelper {

    //Veritabanı ismi
    private static final String DATABASE_NAME="kisiler.db";

    //Veritananı versiyonu
    private static final int DATABASE_VERSION=1;

    // Yapılandırıcı metod tanımı
    public KisiDbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * onCreate Metodu ile veritabanı oluşturulur.
     * Bu metodu biz çağırmıyoruz. Veritabanında obje oluşturduğumuzda
     * otomatik çağrılıyor
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Yeni bir tablo oluşturma sorgusu
        final String SQL_CREATE_KISI_TABLE="CREATE TABLE "+ KisiContract.KisiEnty.TABLE_NAME+
                " ( "+ KisiContract.KisiEnty._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                KisiContract.KisiEnty.COLUM_AD+" TEXT NOT NULL, "+
                KisiContract.KisiEnty.COLUM_SOYAD+" TEXT NOT NULL );";

        //Hazırladığımız sorguyu execSQL ile çalıştırıyoruz ve tabloyu veritabanında
        //oluşturuyoruz
        db.execSQL(SQL_CREATE_KISI_TABLE);
    }


    // onUpdate metodu ile veritabanı güncellenir
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Eğer veritabanı versiyonu değiştirse onUpgrade metodu ile tablo
        //Güncellenir

        db.execSQL("DROP TABLE IF EXISTS "+ KisiContract.KisiEnty.TABLE_NAME);
        onCreate(db);


    }
}
