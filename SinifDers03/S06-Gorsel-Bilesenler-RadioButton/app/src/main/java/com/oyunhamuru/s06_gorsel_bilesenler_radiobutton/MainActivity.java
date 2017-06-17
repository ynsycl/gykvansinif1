package com.oyunhamuru.s06_gorsel_bilesenler_radiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup cinsiyetRadioGroup;

    //Sadece bir tane radioButton nesnesi tanımlıyoruz.
    //Çünkü bize lazım olan seçilmiş olan radioButton
    private RadioButton cinsiyetRadioButton;
    private Button secimiGosterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeVariables();
    }

    private void initializeVariables() {

        cinsiyetRadioGroup=(RadioGroup) findViewById(R.id.rg_cinsiyet);
        secimiGosterButton=(Button) findViewById(R.id.btn_secimi_goster);
        secimiGosterButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_secimi_goster){

            //RadioGroup'tan seçilen radioButonun id sini alıyoruz
            int selectedId = cinsiyetRadioGroup.getCheckedRadioButtonId();

            // id sini bildiğimiz radiobuttona kod tarafında erişebilmek için
            //nesnesini oluşturuyoruz
            cinsiyetRadioButton = (RadioButton) findViewById(selectedId);

            
            Toast.makeText(MainActivity.this,
                    cinsiyetRadioButton.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}
