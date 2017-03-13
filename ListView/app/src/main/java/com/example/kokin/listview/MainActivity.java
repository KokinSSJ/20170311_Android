package com.example.kokin.listview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contact> contacts;
    private ListView listView;

    private class ListAdapter extends ArrayAdapter<Contact> {

        public ListAdapter(Activity context, List<Contact> objects) {
            super(context, R.layout.list_item_layout, objects);
        }

        @Override
        public View getView (int position, View contentView, ViewGroup parent){
            Contact contact =getItem(position);
            if(contentView== null){ // po to zeby się dane od nowa nie renderowały
                contentView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false );
            }


            TextView textView = (TextView) contentView.findViewById(R.id.textView1);
            EditText editText = (EditText) contentView.findViewById(R.id.editText);

            textView.setText(contact.getName());
            editText.setText(contact.getPhone());


            return contentView;
        }
    }

    private void initList(){
        contacts = new LinkedList<>();
        contacts.add(new Contact("kasia", "1111"));
        contacts.add(new Contact("aisia", "2222"));

        listView = (ListView) findViewById(R.id.listView);
        ListAdapter listAdapter = new ListAdapter(this, contacts);
        listView.setAdapter(listAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();





    }
}
