package com.example.kokin.sharedfiles;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import java.io.*;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private CheckBox checkbox1;
    private CheckBox checkBox2;

    private EditText editText2; // do zapisów długich znaków!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        checkbox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);

        // zapisanie z editTexta do Internal Storage -> wewnetrzna pamieć!
        editText2 = (EditText) findViewById(R.id.editText2); // zapisanie długich znaków!
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences dane = getSharedPreferences("dane", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = dane.edit();
        editor.putString("editText", editText.getText().toString());
        editor.putBoolean("checkbox1", checkbox1.isChecked());
        editor.putBoolean("checkbox2", checkBox2.isChecked());
        editor.putBoolean("radioButton1", radioButton1.isChecked());
        editor.putBoolean("radioButton2", radioButton2.isChecked());

        editor.commit();


        // zapisanie do Internal Storage z editTexta!
        File file;  // przechowuje tylko informację o pliku! ścieżka, nazwa itd
        FileOutputStream fos = null; //do operacji na pliku!
        try {
            file = new File(getCacheDir(), "MyCache"); //getCacheDir -> ścieżka dostepu do pamieci
            Log.v("file CacheDir ", getCacheDir().toString()); //nie mam kontroli nad tym jak się plik nazywa i gdzie sie zapisuje (ale w obrebie app
            fos = new FileOutputStream(file);// do zapisu strumienia danych // bajtów!
            fos.write(editText2.getText().toString().getBytes());
//            fos.close();  --> przeniesione do finally -> zeby zawsze, mimo, że wywali wyjątek!
        } catch (Exception ex) {
//            Log.d() // na konsoli Android bedzie info d= debug, e=error, v = ???
            Log.e("my app", ex.getMessage());

        } finally { //za każdym razem czy wyjątek czy nie to żeby wykonała się ta sekcja!
            try {
                if (fos != null) { // musi być pewnośc że obiekt nie jest nullem!
                    fos.close(); //
                }

            } catch (Exception ex2) { //ex gdy obiekt pliku został utworzony ale powstał wyjatek na etapie zamykania
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences dane = getSharedPreferences("dane", Context.MODE_PRIVATE);
        String daneString = dane.getString("editText", ""); //wartosc domyślna przy pierwszym uruchomieniu.
        editText.setText(daneString);
        checkbox1.setChecked(dane.getBoolean("checkbox1", true));
        checkBox2.setChecked(dane.getBoolean("checkbox2", false));
        radioButton1.setChecked(dane.getBoolean("radioButton1", false));
        radioButton2.setChecked(dane.getBoolean("radioButton2", false));

        //odczytywanie z InternalStorage

        BufferedReader br = null;
        File file = null;
        String line = null;
        StringBuilder sb = new StringBuilder();
        try {
            file = new File(getCacheDir(), "MyCache"); // nie mam kontroli nad tym jak się plik nazywa i gdzie sie zapisuje (ale w obrebie app
            Log.v("read InternalStorage", getCacheDir().toString());
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (Exception ex) {
//            Log.d() // na konsoli Android bedzie info d= debug, e=error, v = ???
            Log.e("my app", ex.getMessage());

        } finally { //za każdym razem czy wyjątek czy nie to żeby wykonała się ta sekcja!
            try {
                if (br != null) { // musi być pewnośc że obiekt nie jest nullem!
                    br.close(); //
                }

            } catch (Exception ex2) { //ex gdy obiekt pliku został utworzony ale powstał wyjatek na etapie zamykania
                Log.e("MyApp", ex2.getMessage());
            }

        }

        editText2.setText(sb.toString());

    }


}
