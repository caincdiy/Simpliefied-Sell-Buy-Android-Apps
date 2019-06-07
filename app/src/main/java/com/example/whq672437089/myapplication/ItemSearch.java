package com.example.whq672437089.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ItemSearch extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search);

        //get in session user
        String user = "";
        try{
            FileInputStream fis = openFileInput("session.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            user = br.readLine();
            fis.close();
        }catch(IOException e){e.printStackTrace();}

        //initialize
        final EditText searchtext = findViewById(R.id.field_search);
        //the title changes based on who is logged in
        TextView title = findViewById(R.id.txt_title);
        title.setText("Welcome "+user+", what are you looking for today?");

        Button search = findViewById(R.id.btn_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //take the search term and start item list
                Intent intent = new Intent(v.getContext(),ItemList.class);
                intent.putExtra("search",searchtext.getText().toString());
                startActivity(intent);
            }
        });

        Button back = findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Clicking on popular items puts them in your search bar
        final TextView pop1 = findViewById(R.id.txt_popitem1);
        pop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchtext.setText(pop1.getText());
            }
        });
        final TextView pop2 = findViewById(R.id.txt_popitem2);
        pop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchtext.setText(pop2.getText());
            }
        });
        final TextView pop3 = findViewById(R.id.txt_popitem3);
        pop3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchtext.setText(pop3.getText());
            }
        });
        final TextView pop4 = findViewById(R.id.txt_popitem4);
        pop4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchtext.setText(pop4.getText());
            }
        });
        final TextView pop5 = findViewById(R.id.txt_popitem5);
        pop5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchtext.setText(pop5.getText());
            }
        });
        final TextView pop6 = findViewById(R.id.txt_popitem6);
        pop6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchtext.setText(pop6.getText());
            }
        });
    }

}
