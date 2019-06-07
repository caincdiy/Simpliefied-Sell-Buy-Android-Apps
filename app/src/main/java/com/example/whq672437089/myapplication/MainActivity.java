package com.example.whq672437089.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button11;

    //button and intent for jumping to courier part
    private Button button13;
    Intent intentC;

    private View.OnClickListener onClickListener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, LoginSignup.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//click to enter seller signup/login page
        button11 = findViewById(R.id.button11);
        button11.setOnClickListener(onClickListener1);

        //buyer signup/login
        Button buyer = findViewById(R.id.btn_buyer);
        buyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignInPage.class);
                startActivity(intent);
            }
        });
        //click to login as courier
        button13 = findViewById(R.id.button13);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentC = new Intent(MainActivity.this, loginAsCourier.class);
                startActivity(intentC);
                finish();
            }
        });

    }
}