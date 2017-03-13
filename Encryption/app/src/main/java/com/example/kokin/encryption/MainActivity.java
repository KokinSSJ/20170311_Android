package com.example.kokin.encryption;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private enum ALGOS {
        RSA(0), CEZAR(1), PLECAK(2);

        private final int id;

        ALGOS(int id) {
            this.id = id;

        }
    }

    private EditText editText1, editText2, editText3;
    private TextView textView;
    private MyAlgorithm algo;
    private ListView listView;
    private ALGOS selectedAlgo;

    private class ListClickHandel implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectedAlgo = ALGOS.values()[position];
            textView.setText("Wybrany algorytm: " + selectedAlgo.name());
        }
    }

    private class ListAdapter extends ArrayAdapter<String> {

        public ListAdapter(Activity activity, java.util.List<String> list) {
            super(activity, R.layout.list_item_layout, list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            String name = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, viewGroup, false);
            }
            TextView textView = (TextView) convertView.findViewById(R.id.textView);

            textView.setText(name);

            return convertView;


        }
    }

    private void initWidgets() {
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        textView = (TextView) findViewById(R.id.textView);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new ListClickHandel());

    }

    private void initObjects() {
//        algo = new MyAlgorithmImpl(); // dowolny, stary
        algo = new Adapter(); // nowy kod ->

        List<String> algoList = new ArrayList<>();
        algoList.add(ALGOS.RSA.name());
        algoList.add(ALGOS.CEZAR.name());
        algoList.add(ALGOS.PLECAK.name());
        ListAdapter listAdapter = new ListAdapter(this, algoList);
        listView.setAdapter(listAdapter);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        initObjects();



    }

    public void clickEncrypt(View view) {

        if (selectedAlgo.equals(ALGOS.CEZAR)) {
            editText3.setText(algo.encrypt(editText1.getText().toString(), editText2.getText().toString()));
        } else {
            editText3.setText("Algorytm nie zaimplementowany");
        }

    }
}
