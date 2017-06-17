package com.oyunhamuru.s05_gorsel_bilesenler_checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CheckBox javaCheckBox,androidCheckBox,csharpCheckBox;
    Button secimButonu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nesneleriOlustur();



    }

    private void nesneleriOlustur() {
        javaCheckBox=(CheckBox)findViewById(R.id.cb_java);
        androidCheckBox=(CheckBox)findViewById(R.id.cb_android);
        csharpCheckBox=(CheckBox)findViewById(R.id.cb_csharp);
        secimButonu=(Button)findViewById(R.id.btn_sec);
        secimButonu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_sec){

            int secilenSayisi=0;
            String result="";
            result+=("Seçilen ögeler:");

            /**
             *  checkbox'ın seçili olup olmadığını kontrol ediyoruz
             *  Eğer seçilmişse text değerini alıyoruz
             */

            if(javaCheckBox.isChecked()){
                result+="\n"+javaCheckBox.getText();
                secilenSayisi+=1;
            }
            if(androidCheckBox.isChecked()){
                result+="\n"+androidCheckBox.getText();
                secilenSayisi+=1;
            }
            if(csharpCheckBox.isChecked()){
                result+="\n"+csharpCheckBox.getText();
                secilenSayisi+=1;
            }
            result+="\nToplam: "+secilenSayisi+" öge seçildi";
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        }
    }
}
