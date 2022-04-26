package com.example.restorant;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class GirisYapActivity extends AppCompatActivity {
    EditText kad, ksifre;
    Button girisbutonu;
    VeriTabani vt;


    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giris_activity);
        kad = (EditText) findViewById(R.id.kullaniciadi);
        ksifre = (EditText) findViewById(R.id.ksifresi);
        girisbutonu = (Button) findViewById(R.id.girisbtn);
        vt = new VeriTabani(this);
        girisbutonu.setOnClickListener(new View.OnClickListener() {


            @Override


            public void onClick(View view) {

                String kullanici = kad.getText().toString();
                String kullanicisifresi = ksifre.getText().toString();

                if(kullanici.equals("")||kullanicisifresi.equals(""))
                    Toast.makeText(GirisYapActivity.this, "Lütfen boş alan bırakmayınız", Toast.LENGTH_SHORT).show();


                else{
                    Boolean kontrolsifre = vt.kadveksifrekontrol(kullanici, kullanicisifresi);

                    if(kontrolsifre==true){
                        Toast.makeText(GirisYapActivity.this, "Giriş başarıyla gerçekleşti", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(intent);

                    }

                    else{

                        Toast.makeText(GirisYapActivity.this, "Hatalı giriş bilgileri", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}