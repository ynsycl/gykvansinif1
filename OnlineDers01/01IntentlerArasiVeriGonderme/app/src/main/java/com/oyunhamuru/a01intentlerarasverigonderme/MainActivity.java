package com.oyunhamuru.a01intentlerarasverigonderme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*
    Uyarı intentler arası geri butonu için app>manifests>AndroidManifest.xml dosyasında
    işlem yaptık incele
     */

    //Layoutda oluşturduğumuz objeleri  oluşturuyoruz
    private EditText veriEditText;
    private Button  veriGonderButton;

    //Sabit bir değişken tanımladık karışıklığı önlemek için
    public static final String VERI_BASLIGI="veriBasligi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //veriEditText objesine activity_main.xml layoutundaki et_veri nesnesini atıyoruz
        veriEditText=(EditText) findViewById(R.id.et_veri);

        //veriGonderButton objesine activity_main.xml layoutundaki btn_intent_start
        //nesnesini atıyoruz
        veriGonderButton=(Button) findViewById(R.id.btn_intent_start);


        //Buton'a onclick Özelliğini atıyoruz
        veriGonderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //veriEditText içindeki veriyi veri değişkenine atıyoruz
                String veri=veriEditText.getText().toString();


                //veri değişkeninin boş olup olmadığını kontrol ediyoruz
                //Eğer boş ise if bloğunun içindeki uyarı mesajını kullanıcıya gösteriyoruz
                //veri değişkeni boş değilse else blogunun içindeki intentler arası
                //geçiş kodlarını çalıştırıyoruz
                if(veri.equals("") || veri.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Birşeyler yazınız",Toast.LENGTH_SHORT).show();
                }
                else {

                    /**
                     * Yeni intent objesi oluşturuyoruz
                     * MainActivity.this parametresi=içinde bulunan activity
                     *
                     * DetayActivity.class parametresi başlatılacak olan activity
                     */
                    Intent intent=new Intent(MainActivity.this,DetayActivity.class);
                    //Veri atama sadece primitive tipteki verileri atayabiliz
                    intent.putExtra(VERI_BASLIGI,veri);

                    //startActivity() ile diğer intenti başlatıyoruz
                    startActivity(intent);


                }
            }
        });



    }
}
