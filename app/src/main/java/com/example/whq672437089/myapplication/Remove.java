package com.example.whq672437089.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class Remove extends AppCompatActivity {
    private TextView textView31;
    private TextView textView32;
    private TextView textView33;
    private TextView textView34;

    private CheckBox checkBox1;
    private Button button1;
    private Button button2;

    private View.OnClickListener onClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v){
            if(checkBox1.isChecked()){
                Toast.makeText(Remove.this, "Remove Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(Remove.this, "Pls circle checkbox to ensure operation", Toast.LENGTH_SHORT).show();
            }

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
        setContentView(R.layout.activity_remove);
        String productName=null;
        String description=null;
        String price=null;
        String category=null;
        Intent intent=getIntent();
        productName=intent.getStringExtra("productName");
        description=intent.getStringExtra("description");
        price=intent.getStringExtra("price");
        category=intent.getStringExtra("category");

        textView31 = findViewById(R.id.textView31);
        textView32 = findViewById(R.id.textView32);
        textView33 = findViewById(R.id.textView33);
        textView34 = findViewById(R.id.textView34);

        textView31.setText(productName);
        textView32.setText(description);
        textView33.setText(price);
        textView34.setText(category);

        checkBox1=findViewById(R.id.checkBox1);
        button1=findViewById(R.id.button1);

        button1.setOnClickListener(onClickListener);

        button1=findViewById(R.id.button2);

        button1.setOnClickListener(onClickListener2);

    }
}
