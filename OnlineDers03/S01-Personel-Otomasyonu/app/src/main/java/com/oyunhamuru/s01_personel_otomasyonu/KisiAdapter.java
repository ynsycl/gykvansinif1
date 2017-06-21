package com.oyunhamuru.s01_personel_otomasyonu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.oyunhamuru.s01_personel_otomasyonu.data.Kisi;

import java.util.List;

/**
 * Oluşturduğumuz KisiAdapter class'a extends ile BaseAdapter i kalıtıyoruz
 * Ve sonra zorunlu metodlarını implement ediyoruz.
 *
 */

public class KisiAdapter extends BaseAdapter {

    //Res>layout>kisi_list_layout.xml dosyasına erişmemimizi sağlar
    private LayoutInflater mInflater;

    //Kişilerin tutulacağı arraylist objesi
    private List<Kisi> kisiListesi;

    /**
     *Yapılandırıcı metod bu metod obje oluştururken direk değişkenleride
     * atamamızı sağlar. örneğin =
     * KisiaAdapter adapter=new KisiAdapter(Activity ismi, gösterilecek veri);
     * şeklinde objeyi oluşturabiliriz.
     */

    public KisiAdapter(Activity activity, List<Kisi> kisiler){

        //Oluşturduğumuz layoutinflater objesine yapılandırıcı metoddan gelen
        // activity yardımı ile layout_inflater_service
        //servisini atama yapıyruz
        this.mInflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

       //yapılandırıcı metoddan gelen veriyi oluşturduğumuz kisiListesi arraylist objesine
        //atama yapıyoruz
        this.kisiListesi=kisiler;

    }


    /**
     * kisiListesindeki veri sayısını döndürür
     * @return
     */
    @Override
    public int getCount() {
        return kisiListesi.size();
    }

    /**
     * pozisyon parametresi ile kisiListesindeki kişi nesnesini bulup döndürür
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return kisiListesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        //Kişi bean classından bir obje oluşturuyoruz
        //position parametresini index olarak kullanım
        //bu indexteki kişi nesnesini döndürüyoruz
        Kisi kisi=kisiListesi.get(position);

        return kisi.getId();
    }

    /**
     * Listview de gösterilecek verileri kisi_list_layout.xml'e atayıp
     * listview objesine ekleme yapar
     * @param position kisiListesindeki bir nesnenin indexini ifade eder
     * @param convertView Eğer mümkünde yeniden kullanmak için eski view
     * @param parent viewlerin ekleneceği parent
     * @return geriye view nesnesi döner
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //oluşturduğumuz mInflater layout_inflater yardımı ile layout dosyasından
        //listview'de gösterilecek layout'u alıyoruz
        View view=mInflater.inflate(R.layout.kisi_list_layout,null);

        //Aldığımız layouttaki gösrel objeleri kod tarafında kullanmak için eşleştirme
        //yapıyoruz
        TextView isimTextView=(TextView) view.findViewById(R.id.tv_ad_list);
        TextView soyadTextView=(TextView)view.findViewById(R.id.tv_soyad_list);

        //position değişkeni ile kullanacağımız kişi nesnesini
        //Kişi listesinden alıyoruz
        Kisi kisi=kisiListesi.get(position);

        //Kişi nesnesindeki değerleri view'in textview nesnelerine atama yapıyoruz
        isimTextView.setText(kisi.getAd());
        soyadTextView.setText(kisi.getSoyad());

        //oluşturulan görünümü/view'i parentine ekliyoruz
        //Bu işlem kisiListesindeki tüm elemanlar bitene kadar devam eder
        return view;
    }
}
