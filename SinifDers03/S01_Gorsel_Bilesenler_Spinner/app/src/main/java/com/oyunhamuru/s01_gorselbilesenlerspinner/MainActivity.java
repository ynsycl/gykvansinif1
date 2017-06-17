package com.oyunhamuru.s01_gorselbilesenlerspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner manuelSpinner;
    private Spinner kodlaSpinner;
    private Button  showSelectedItemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manuelSpinner=(Spinner) findViewById(R.id.spinner_country);
        kodlaSpinner=(Spinner)findViewById(R.id.spinner_country2);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.country_arrays));
        kodlaSpinner.setAdapter(adapter);

        showSelectedItemButton=(Button) findViewById(R.id.btn_show_selected_item);
        showSelectedItemButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_show_selected_item){
            String secilenler="Manuel veri eklenen spinnerden seçilen: "+manuelSpinner.getSelectedItem()+
                    "\nKodla veri eklenen spinnerden seçilen "+kodlaSpinner.getSelectedItem();

            Toast.makeText(getApplicationContext(),secilenler,Toast.LENGTH_SHORT).show();
        }
    }
}
