package com.oyunhamuru.n03_fragmentler_arasi_veri_gonderme;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Oluşturduğumuz FragmentB classına kalıtım yolu ile Fragment classının özelliklerini
 * atadık.
 */


public class FragmentB extends Fragment {

    TextView sonuc;
    String veri;

    /**
     * Fragment classından gelen onCreateView metodunu override ettik
     *Bu metod ile layout klasöründeki fragment_baslik.xml layoutu
     * ile BaslikFragment class'ımızı eşleştiriyoruz
     * @param inflater paramtresi yardımı ile bir View objesi oluşturuyoruz
     * @param container parametresi fragment'in gösterileceği yer
     * @param savedInstanceState parametresi anlık veri tutmamızı sağlar
     * @return fragmenti görsel olarak görmemizi sağlayan view objesini döndürüyoruz
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        //inflater yardımı ile fragment'in layoutunu bir view objesine atayıp
        //görsel olarak oluşturulmasını sağlıyoruz.
       View view=inflater.inflate(R.layout.fragment_b,container,false);

        //Bundle kontrol ediyoruz eğer içinde veri varsa
        //bu veriyi değişkenkerden kaybolan değerlere atama yapıyoruz
        if(savedInstanceState!=null){
            veri=savedInstanceState.getString("verikey");
            TextView textView=(TextView)view.findViewById(R.id.tv_sonuc);
            textView.setText(veri);
        }

        return view;
    }

    /**
     * MainActivty activity'sin onCreate metodu çalışıp sonlandıktan
     * sonra aşağıdaki metod çalışır.
     * Fragmentteki nesneler aşağıdaki metodun içinde oluşturulur.
     * @param savedInstanceState= anlık veri tutar
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sonuc=(TextView)getActivity().findViewById(R.id.tv_sonuc);
    }

    /**
     * FragmentA'dan gelen veriyi FragmentB deki textview'e atayan metod
     * @param gelenVeri paramtresi FragmentA'dan gelen string veri
     */
    public void yaziyiDegistir(String gelenVeri){
        this.veri=gelenVeri;
        sonuc.setText(veri);

    }


    /**
     * Anlık veri tutan metod
     * bu metod ile veri kaybını önleriz
     * @param outState=parametresi verinin katılacağı nesne
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("verikey",veri);
    }
}
