package com.example.whq672437089.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Product extends AppCompatActivity {

    String name;
    private TextView textView31;
    private TextView textView32;
    private TextView textView33;
    private TextView textView34;

    private Button button31;
    private Button button32;
    private Button button33;

    private int counter=1;
    private int length;//used to count the number of lines in the file

    private View.OnClickListener onClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v){

            String line=null;
            try {
                FileInputStream fis = openFileInput("product.txt");
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br=new BufferedReader(isr);

                if (length==0){
                    Toast.makeText(Product.this, "Record is Empty", Toast.LENGTH_SHORT).show();
                    br.close();
                }
                else{
                    int i=0;
                    String[] output=null;
                    //if click previous
                    if(v.equals(button31)){
                        counter--;
                        if(counter<1){
                            counter=(int)length;
                        }
                    }

                    //if click next
                    if(v.equals(button32)){
                        counter++;
                        if(counter>length){
                            counter=1;
                        }
                    }
                    while ((line = br.readLine()) != null) {
                        output=line.split(",");
                        if(output[0].matches(name)){
                            i++;
                            if(i==counter){
                                break;
                            }//if the crrent line is what we need
                        }
                    }



                    textView31.setText(output[1]);
                    textView32.setText(output[2]);
                    textView33.setText(output[3]);
                    textView34.setText(output[4]);

                }//end of else of test length
                br.close();
            }//end of try
            catch (Exception e){
                e.printStackTrace();
            }//end of catch

        }
    };

    private View.OnClickListener onClickListener2 = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Intent intent=getIntent();
        name=intent.getStringExtra("name");

        textView31 = findViewById(R.id.textView31);
        textView32 = findViewById(R.id.textView32);
        textView33 = findViewById(R.id.textView33);
        textView34 = findViewById(R.id.textView34);

        button31 = findViewById(R.id.button31);
        button32 = findViewById(R.id.button32);
        button33 = findViewById(R.id.button33);

        button31.setOnClickListener(onClickListener);
        button32.setOnClickListener(onClickListener);
        button33.setOnClickListener(onClickListener2);

        FileInputStream fis = null;
        length = 0;
        try {
            fis = openFileInput("product.txt");
            InputStreamReader isr2 = new InputStreamReader(fis);
            BufferedReader countline = new BufferedReader(isr2);
            String line2 = null;
            while ((line2 = countline.readLine()) != null) {
                String[] output = line2.split(",");
                if(output[0].matches(name)){
                    length++;
                    if (length == 1) {
                        textView31.setText(output[1]);
                        textView32.setText(output[2]);
                        textView33.setText(output[3]);
                        textView34.setText(output[4]);
                    }
                }
            }
            //count the length of line at first
            countline.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (length == 0) {
            Toast.makeText(Product.this, "Product record is Empty", Toast.LENGTH_SHORT).show();
        }
    }
}
