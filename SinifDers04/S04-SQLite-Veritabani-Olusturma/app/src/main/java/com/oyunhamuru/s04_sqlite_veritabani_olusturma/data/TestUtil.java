package com.oyunhamuru.s04_sqlite_veritabani_olusturma.data;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbook on 20.06.2017.
 */

public class TestUtil {
    public static void insertFakeData(SQLiteDatabase db){
        if(db == null){
            return;
        }
        //Kişi listesi oluşturur

        List<ContentValues> list = new ArrayList<ContentValues>();

        //ContentValues HashMap türünde  satırların sütun değerlerini içerir
        //Anahtarlar sütun adları ve sütun değerleri değerleri olmalıdır
        ContentValues cv = new ContentValues();
        cv.put(KisiContract.KisiEnty.COLUM_AD, "Vahit");
        cv.put(KisiContract.KisiEnty.COLUM_SOYAD,"Sal");
        list.add(cv);

        cv = new ContentValues();
        cv.put(KisiContract.KisiEnty.COLUM_AD, "Behlül");
        cv.put(KisiContract.KisiEnty.COLUM_SOYAD,"Dalan");
        list.add(cv);

        cv = new ContentValues();
        cv.put(KisiContract.KisiEnty.COLUM_AD, "Murat");
        cv.put(KisiContract.KisiEnty.COLUM_SOYAD,"Kara");
        list.add(cv);

        cv = new ContentValues();
        cv.put(KisiContract.KisiEnty.COLUM_AD, "Arif");
        cv.put(KisiContract.KisiEnty.COLUM_SOYAD,"Ars");
        list.add(cv);



        //Bir transaciton ile tüm kişileri ekliyoruz
        try
        {
            //Transaction başlatılır
            db.beginTransaction();
            //ilk tablo temizlenir
            db.delete (KisiContract.KisiEnty.TABLE_NAME,null,null);
            //sonra listedeki elemanlar tek tek eklenit
            for(ContentValues c:list){

                /**
                 * db.insert(value1,value2,value3)
                 *
                 * value1 param=ekleme yapılacak tablo ismi
                 * value2 param=String isteğe bağlı, boş olabilir
                 * Sütünlardan birine değer girilmemişse null değerini
                 * almasını sağlar
                 * value3=yeni eklenecek verinin tutulduğu contentvalue objesi
                 */
                db.insert(KisiContract.KisiEnty.TABLE_NAME, null, c);
            }

            //Transaction başarılı olursa aşağıdaki kod
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {

        }
        finally
        {
            //Transaction'u sonlandırma
            db.endTransaction();
        }

    }
}
