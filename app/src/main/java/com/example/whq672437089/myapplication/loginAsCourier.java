package com.example.whq672437089.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginAsCourier extends AppCompatActivity {

    //buttons:
    private Button login;
    private Button toSign;
    private Button Return;

    //Intents
    private Intent intent1;
    private Intent intent2;
    private Intent intent3;

    //fields
    private EditText name;
    private EditText pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_as_courier);

    login = findViewById(R.id.LoginAsCourier);
    toSign = findViewById(R.id.toSignIn);
    Return = findViewById(R.id.Return);

    name = findViewById(R.id.NameInput);
    pw = findViewById(R.id.pwInput);

    //click log in
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input1 = name.getText().toString();
                String input2 = pw.getText().toString();

                boolean valid = true;
                String error = "";

                if(input1.equals("") || input2.equals("")){
                    valid = false;
                    error = "Name or password is empty";
                }

                if (false){
                    //check if the login information can be found in file
                    valid =false;
                    error = "incorrect login name or password";
                }

                //if valid, jump to next activity
                if (valid){
                    intent1 = new Intent(loginAsCourier.this, listOfOrders.class);
                    startActivity(intent1);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();
                }



            }
        });

    //click sign in
        toSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent2 = new Intent(loginAsCourier.this,signinAsCourier.class);
                startActivity(intent2);
                finish();
            }
        });

    //click Return
        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent3 = new Intent(loginAsCourier.this,MainActivity.class);
                startActivity(intent3);
                finish();
            }
        });


    }
}
