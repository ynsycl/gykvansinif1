package com.oyunhamuru.s00_activity_lifecyle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    private static final String MAIN_ACTIVTY_TAG=MainActivity.class.getSimpleName();


    /**
     *create metodu
     *Program açılırken ilk çağrılan metodtur.
     *View'in yüklendiği ve ayarlandığı yer onCreatedir.
     *Activity burada görünür değildir.
     * @param savedInstanceState parametredi veri kaybını önlemek için veri atadığımız parametre
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showLog("Activity Created");
    }





    /**
     * start metodu
     * Activity bu metotdan sonra görünürdür.
     */
    @Override
    protected void onStart() {
        super.onStart();
        showLog("Activity started");
    }

    /**
     * start metodu
     * onResume çağrılır.
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        showLog("Activity restarted");
    }


    /**
     * resume metodu
     * Activity görünür olduğunda ilk çağrılan metoddur.
     *Activity kullanıcıyla etkileşim halindedir.(Activity Running)
     *
     */
    @Override
    protected void onResume() {
        super.onResume();
        showLog("Activity resumed");
    }



    /**
     * pause metodu
     * Activity görünür durumdayken başka bir activity o activity'nin önüne geçiyorsa onPause
     *çağrılır.
     *bu metod çağrıldıktan sonra Activity destroy edilmez ama görünür değildir.
     *onPause metodunun çağrıldığı durumlar:
     *Home tuşuna basılması (onPause > onStop)
     *Arama gelmesi , alarmın çalması (sisteme ait interruptlar)
     *Aktif activity'nin önüne başka bir activity gelmesi
     *Whatsapp konuşma listelerinde bir kişi seçildiğinde konuşma listesi activityisi
     *onPause metodunu çağırır.
     *Mesajlar uygulamasında Tüm mesajlar ekranından bir mesajın seçilmesi
     *durumunda Tüm mesajlar ekranı onPause metodunu çağırır.
     *Settings menüsünde bir alt ayar menüsüne girilmesi.
     *Activity'nin yok edilmesi sırasında veri kayıplarını engellemek için yedekleme
     *işlemlerinin onPause()'da yapılması mantıklıdır. savedInstanceState metodu burada
     * çağrılır
     */
    @Override
    protected void onPause() {
        super.onPause();
        showLog("Activity paused");
    }


    /**
     * stop metodu
     *
     * Activity arka plana alınacaksa activity sırayla onPause > onStop > onDestroy
     metodlarını çağırır.
     */
    @Override
    protected void onStop() {
        super.onStop();
        showLog("Activity stopped");
    }

    /**
     * destroy metodu
     * Geri tuşuna basılırsa onPause > onStop > onDestroy çağrılır.
     *Screen orieantation'da ya da Arkaplan uygulamalar sonlandrıldığında çağrılır.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        showLog("Activity is bean destroyed!");
    }



    private void showLog(String text){

        Log.d(MAIN_ACTIVTY_TAG,text);
    }
}
