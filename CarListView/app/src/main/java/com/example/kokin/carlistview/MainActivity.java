package com.example.kokin.carlistview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Car> cars;
    private ListView listView;
    private class ListAdapter extends ArrayAdapter<Car> {

        public ListAdapter(Activity context, List<Car> objects) {
            super(context, R.layout.cars_layout, objects);
        }

        @Override
        public View getView (int position, View contentView, ViewGroup parent){
            Car cars = getItem(position);
            if(contentView== null){ // po to zeby się dane od nowa nie renderowały
                contentView = LayoutInflater.from(getContext()).inflate(R.layout.cars_layout, parent, false );
            }


            TextView textViewId = (TextView) contentView.findViewById(R.id.textViewId);
            TextView textViewMade = (TextView) contentView.findViewById(R.id.textViewMade);
            CheckBox checkBox = (CheckBox) contentView.findViewById(R.id.checkbox);

            textViewId.setText(cars.getId().toString());
            textViewMade.setText(cars.getMade());
            checkBox.setChecked(cars.getUsed());
            checkBox.setEnabled(false);


            return contentView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initList();
    }


    private void initList(){
        cars = new LinkedList<>();
        cars.add(new Car("Ferrari", true));
        cars.add(new Car("Fiat", false));

        listView = (ListView) findViewById(R.id.listView);
        ListAdapter listAdapter = new ListAdapter(this, cars);
        listView.setAdapter(listAdapter);

    }

}
