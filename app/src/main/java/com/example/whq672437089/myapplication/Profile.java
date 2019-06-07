package com.example.whq672437089.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Profile extends AppCompatActivity {
    String name;
    private TextView textView31;
    private TextView textView32;
    private TextView textView33;
    private TextView textView34;

    private Button button31;
    private Button button32;
    private Button button33;
    private Button button34;

    private EditText editText1;



    private View.OnClickListener onClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v){
            Intent intent=new Intent(Profile.this,Post.class);
            intent.putExtra("name",name);
            startActivity(intent);
        }
    };

    private View.OnClickListener onClickListener1 = new View.OnClickListener(){

        @Override
        public void onClick(View v){
            Intent intent=new Intent(Profile.this,Product.class);
            intent.putExtra("name",name);
            startActivity(intent);
        }
    };

    private View.OnClickListener onClickListener2 = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent=new Intent(Profile.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    };

    private View.OnClickListener onClickListener3 = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            String productName=editText1.getText().toString().trim();
            String description=null;
            String price=null;
            String category=null;
            boolean valid=false;

            FileInputStream fis = null;
            try {
                fis = openFileInput("product.txt");
                InputStreamReader isr2 = new InputStreamReader(fis);
                BufferedReader countline = new BufferedReader(isr2);
                String line2 = null;
                while ((line2 = countline.readLine()) != null) {
                    String[] output = line2.split(",");
                    if(output[0].matches(name)&&output[1].matches(productName)){
                            description=output[2];
                            price=output[3];
                            category=output[4];
                            valid=true;
                    }
                }
                //count the length of line at first
                countline.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(valid){
                Intent intent=new Intent(Profile.this,Remove.class);
                intent.putExtra("name",name);
                intent.putExtra("productName",productName);
                intent.putExtra("description",description);
                intent.putExtra("price",price);
                intent.putExtra("category",category);
                startActivity(intent);
            }else{
                Toast.makeText(Profile.this, "Not found this product", Toast.LENGTH_SHORT).show();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textView31=findViewById(R.id.textView31);
        textView32=findViewById(R.id.textView32);
        textView33=findViewById(R.id.textView33);
        textView34=findViewById(R.id.textView34);

        button31=findViewById(R.id.button31);
        button32=findViewById(R.id.button32);
        button33=findViewById(R.id.button33);
        button34=findViewById(R.id.button34);

        button31.setOnClickListener(onClickListener);
        button32.setOnClickListener(onClickListener1);
        button33.setOnClickListener(onClickListener2);
        button34.setOnClickListener(onClickListener3);

        editText1=findViewById(R.id.editText1);

        Intent intent=getIntent();
        name=intent.getStringExtra("name");

        FileInputStream fis = null;
        try {
            fis = openFileInput("seller.txt");
            InputStreamReader isr2 = new InputStreamReader(fis);
            BufferedReader countline = new BufferedReader(isr2);
            String line2=null;
            while ((line2=countline.readLine()) != null) {
                    String[] output=line2.split(",");
                    if(output[0].matches(name)){
                        textView31.setText(output[0]);
                        textView32.setText(output[2]);
                        textView33.setText(output[3]);
                        textView34.setText(output[5]);
                    }
            }
            //count the length of line at first
            countline.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
