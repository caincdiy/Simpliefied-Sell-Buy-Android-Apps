package com.example.whq672437089.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class signinAsCourier extends AppCompatActivity {

    //Button
    private Button Sign;

    //Intent
    private Intent intent;

    //inputs
    private EditText name;
    private EditText password;
    private EditText phone;
    private CheckBox Sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_as_courier);

        Sign = findViewById(R.id.BT);

        name = findViewById(R.id.IP);
        password = findViewById(R.id.IP1);
        phone = findViewById(R.id.IP2);

        Sure = findViewById(R.id.CB1);

        Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if (Sure.isChecked()){
                    intent = new Intent(signinAsCourier.this,loginAsCourier.class);
                    startActivity(intent);
                }

            }
        });

    }
}
