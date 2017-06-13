package com.oyunhamuru.a02alertdialoginternetkontrolu;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by macbook on 13.06.2017.
 */

public class Utility {

    /**
     * internet bağlantısını kontrol eder
     * @param paramContext=Geldiği activity'nin context bilgisi
     * @return= Eğer internet bağlantısı yoksa false,
     * varsa true döner
     */
    public static boolean hasInternetConnection(Context paramContext){

        //Bağlantı Servis bilgilerini çekiyoruz
        ConnectivityManager connectivityManager=(ConnectivityManager)
                paramContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        //İnternet bağlantısı bilgisini çekiyoruz
        NetworkInfo internetBilgisi=connectivityManager.getActiveNetworkInfo();

        //İf ile internet bağlantısını kontrol ediyoruz bağlantı varsa true
        //gönderiyor bağlantı yoksa false gönderiyor.
        if(internetBilgisi!=null && internetBilgisi.isConnectedOrConnecting()){
            return true;
        }else
            return false;
    }
}
