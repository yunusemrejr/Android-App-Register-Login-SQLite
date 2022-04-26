package com.example.restorant;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    EditText kad, ksifre, ksifreyeniden;
    Button btnuyeol, btngiris, atla;
    VeriTabani VT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        VT = new VeriTabani(this);
        kad = (EditText)findViewById(R.id.kad);
        ksifre = (EditText)findViewById(R.id.ksifre);
        ksifreyeniden = (EditText)findViewById(R.id.ksifreyeniden);
        btngiris = (Button)findViewById(R.id.btngiris);
        btnuyeol = (Button)findViewById(R.id.btnuyeol);
        atla = (Button)findViewById(R.id.atla);





        atla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });





        btnuyeol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kadi = kad.getText().toString();
                String ksifresi = ksifre.getText().toString();
                String ksifresiyeniden = ksifreyeniden.getText().toString();

                if(kadi.equals("") || ksifresi.equals("") || ksifresiyeniden.equals("") || kadi.equals(" ") || ksifresi.equals(" ") || ksifresiyeniden.equals(" ") )
                    Toast.makeText(MainActivity.this, "Lütfen boş alan bırakmayınız", Toast.LENGTH_SHORT).show();


                else{


                    if(ksifresi.equals(ksifresiyeniden)){
                        Boolean kadkontrolet = VT.kadkontrol(kadi);

                        if (kadkontrolet) {
                            Toast.makeText(MainActivity.this, "Kullanıcı zaten mevcut, giriş yapmayı deneyin", Toast.LENGTH_SHORT).show();
                        }

                        else {
                            Boolean yapistir = VT.veriyapistir(kadi, ksifresi);

                            if(yapistir){
                                Toast.makeText(MainActivity.this, "Başarı ile kayıt olundu", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                                startActivity(intent);
                            }

                            else{
                                Toast.makeText(MainActivity.this, "Kayıt olma işlemi başarısız oldu", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    else{
                        Toast.makeText(MainActivity.this, "Şifreler uyuşmuyor", Toast.LENGTH_SHORT).show();
                    }
                } }
        });




        btngiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GirisYapActivity.class);
                startActivity(intent);
            }
        });
    }
}