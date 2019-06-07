package com.example.whq672437089.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SignInPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);

        //sample products
        String fileinput = "Apple,Just a regular apple,apple,0.50,0\nAlso an apple,Also a regular apple,apple,0.60,0\nPear,A juicy pear soon to be ripe,pear,0.90,0\nBeef Steak,A raw beef steak,beef_steak,12.50,0\nGround Beef,Raw ground up beef,ground_beef,10.00,0\nTomato,A freshly grown tomato,tomato,1.10,0\nOrange,Juicy oranges straight off the tree,orange,1.30,0\nPotato,What a delightful tuber,potato,0.75,0";
        try {
            FileOutputStream outputStream;
            outputStream= openFileOutput("items.txt", Context.MODE_PRIVATE);
            outputStream.write(fileinput.getBytes());
            outputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        final EditText uname = findViewById(R.id.field_username);
        final EditText pass = findViewById(R.id.field_password);

        Button join = findViewById(R.id.btn_join);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),SignUpPage.class);
                startActivity(intent);
            }
        });

        Button login = findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //grab user data from file
                try {
                    //make an empty file if none exist
                    FileOutputStream o = openFileOutput("buyerdata.txt", Context.MODE_APPEND);
                    o.close();
                    FileInputStream fis = openFileInput("buyerdata.txt");
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    //dataLine represents one line from the file
                    String dataLine = "";
                    Log.d("debug","b4 while");
                    while((dataLine = br.readLine())!=null){
                        //split the line into its data fields and check against inputs
                        String[] fields = dataLine.split(",");
                        if(fields[0].equals(uname.getText().toString()) && fields[1].equals(pass.getText().toString())) {
                            Log.d("debug","find match");
                            Intent intent = new Intent(v.getContext(),ItemSearch.class);
                            fis.close();
                            //log in the user by putting his/her name in session
                            String fileinput = uname.getText().toString()+"\n";
                            FileOutputStream outputStream;
                            outputStream= openFileOutput("session.txt",Context.MODE_PRIVATE);
                            outputStream.write(fileinput.getBytes());
                            outputStream.close();
                            startActivity(intent);
                        }
                    }
                    Toast.makeText(getApplicationContext(),"Username and password do not match the records, try again or make an account.",Toast.LENGTH_SHORT).show();
                    fis.close();
                }catch (IOException e){e.printStackTrace();}

            }
        });
    }
}
