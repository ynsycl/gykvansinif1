package com.oyunhamuru.n03_fragmentler_arasi_veri_gonderme;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Oluşturduğumuz FragmentA classına kalıtım yolu ile Fragment classının özelliklerini
 * atadık.
 */



public class FragmentA extends Fragment {

    Button banaTikla;
    int counter=0;
    VeriTasiyici veriTasiyici;

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

        //Bundle kontrol ediyoruz eğer içinde veri varsa
        //bu veriyi değişkenkerden kaybolan değerlere atama yapıyoruz
        if(savedInstanceState!=null){
            counter=savedInstanceState.getInt("counter",0);
        }else {
            counter=0;

        }

        //inflater yardımı ile fragment'in layoutunu bir view objesine atayıp
        //görsel olarak oluşturulmasını sağlıyoruz.
       View view=inflater.inflate(R.layout.fragment_a,container,false);
        return view;
    }

    /**
     * MainActivity'inin onCreate metodu çalışıp sonlandıktan sonra
     * bu metod çalışacak
     * @param savedInstanceState veri kaybını önlemek için verilerin tutulduğu bundle nesnesi
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        banaTikla= (Button) getActivity().findViewById(R.id.btn_tikla);

        //Veri tranferi için veriTasiyici interface'inden bir obje oluşturuyoruz
        veriTasiyici=(VeriTasiyici)getActivity();

        banaTikla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;

                veriTasiyici.gonder("Butona "+counter+" kere tıklandı.");



            }
        });
    }

    /**
     * Anlık veri tutan metod
     * bu metod ile veri kaybını önleriz
     * @param outState=parametresi verinin katılacağı nesne
     */

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("counter",counter);
    }
}
