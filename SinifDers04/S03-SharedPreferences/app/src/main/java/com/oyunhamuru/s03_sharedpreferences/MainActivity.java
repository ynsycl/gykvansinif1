package com.oyunhamuru.s03_sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText veriEditText;
    private Button kaydetButton;

    SharedPreferences sharedPreferences;

    public static final String PREF_NAME="myPrefName";
    public static final String VERI_KEY="veriKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nesneleriOlustur();

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        if(sharedPreferences.contains(VERI_KEY)){
            veriEditText.setText(sharedPreferences.getString(VERI_KEY,""));

        }
    }

    private void nesneleriOlustur() {
        veriEditText=(EditText) findViewById(R.id.et_veri);
        kaydetButton=(Button) findViewById(R.id.btn_kaydet);
        kaydetButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String veri=veriEditText.getText().toString();

        if (veri.isEmpty()||veri.trim().equals("")){
            Toast.makeText(getApplicationContext(),"Birşey yazmalısınız",Toast.LENGTH_SHORT).show();
        }
        else {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString(VERI_KEY,veri);
            editor.commit();

            Toast.makeText(getApplicationContext(),"Veri başarıyla kaydedildi",Toast.LENGTH_SHORT).show();
        }


    }
}
