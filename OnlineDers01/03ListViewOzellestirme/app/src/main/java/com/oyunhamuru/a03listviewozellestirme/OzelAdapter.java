package com.oyunhamuru.a03listviewozellestirme;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Bu Adapter classı ile listview verilerini bağlıyoruz
 * İçindeki metodları BaseAdapter class'ından kalıtım yolu ile
 * aldık.Biz sadece constructor/yapıcı metodu oluşturdul
 */

public class OzelAdapter extends BaseAdapter {


    //XML'i alıp View'a çevirecek inflater objesni oluşturduk
    private LayoutInflater mInflater;

    //Kişi tipimde mKisiListesi objesini oluşturduk
    private List<Kisi>  mKisiListesi;



    /**
     * Yapıcı metodumuz bu metod aracılığı ile
     objeyi ilk oluştururken değerler atıyoruz
     * @param activity bu parametresi hangi activity'den geldiğini
     * gösteriyor Yani bu listview hangi activity'de görünecek onu belirtiyor
     * @param kisiler bu parametre gösterilecek veri kümesi/listesi ni belirtir
     */
    public OzelAdapter(Activity activity, List<Kisi> kisiler) {

        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        //Yapıcı metod ile gelen activity aracılığı ile inflater objesine atama yapıyoruz
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        //Yapıcı metod ile gelen verileri mKisiListesi objesine atama yapıyoruz
        mKisiListesi = kisiler;
    }

    /**
     *
     * @return Kaç verinin mevcut olduğunu döndürür.
     */
    @Override
    public int getCount() {
        return mKisiListesi.size();
    }

    /**
     *
     * @param position Kişinin Listedeki pozisyonu
     * @return Geriye Kişi tipinde bir obje yollar
     */
    @Override
    public Object getItem(int position) {
        return mKisiListesi.get(position);
    }

    /**
     *
     * @param position Kişinin Listedeki pozisyonu
     * @return eğer verinin bir Idsi olsaydı o değeri yollardık
     * id değeri olmadığı için tekrardan listedeki index'ini yolluyoruz
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     *
     * @param position Kişinin Listedeki pozisyonu
     * @param convertView
     * @param parent
     * @return View tipinde bir obje döndürüyor
     * Tüm kişiler atana kadar tekrar tekrar çalışır
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View kisilerLayoutView;


        //Boş bir View oluşturuyoruz kisiler_layout.xml layoutundan
        kisilerLayoutView = mInflater.inflate(R.layout.kisiler_layout, null);

        /*
        Değer atayabilmek için kisiler_layout.xml dosyasının
         içindeki TextView oluşturduğumuz isimTextView nesnesine atıyoruz
         */
        TextView isimTextView =
                (TextView) kisilerLayoutView.findViewById(R.id.isimsoyisim);

        /*
        Değer atayabilmek için kisiler_layout.xml dosyasının
         içindeki imageviewi oluşturduğumuz cinsiyetImageView nesnesine atıyoruz
         */
        ImageView cinsiyetImageView =
                (ImageView) kisilerLayoutView.findViewById(R.id.simge);


        //position ile listedeki kişi objesini alıyoruz
        Kisi kisi = mKisiListesi.get(position);

        //Boş olan View'in içindeki textview'e kişinin ismini atıyoruz
        isimTextView.setText(kisi.getIsim());

        //Boş olan View'in içindeki imageview'e resim atıyoruz

        if (kisi.isKadinMi()) {
            //Kişi bayan ise bu kod ile female.png resmi atanır
            cinsiyetImageView.setImageResource(R.drawable.female);
        }
        else {
            //Kişi erkek ise bu kod ile male.png resmi atanır
            cinsiyetImageView.setImageResource(R.drawable.male);
        }

        //Doldurduğumuz View'i döndürüyoruz
        return kisilerLayoutView;
    }
}
