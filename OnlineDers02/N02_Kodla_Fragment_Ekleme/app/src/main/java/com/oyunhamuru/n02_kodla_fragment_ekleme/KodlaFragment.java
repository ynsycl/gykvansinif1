package com.oyunhamuru.n02_kodla_fragment_ekleme;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Oluşturduğumuz BaslikFragment classına kalıtım yolu ile Fragment classının özelliklerini
 * atadık.
 */


public class KodlaFragment extends Fragment {

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
        View view=inflater.inflate(R.layout.fragment_kodlaekleme, container, false);
        return view;
    }
}
