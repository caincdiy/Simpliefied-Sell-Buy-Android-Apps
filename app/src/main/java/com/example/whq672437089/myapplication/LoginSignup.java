package com.example.whq672437089.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginSignup extends AppCompatActivity {

    private Button button21;
    private Button button22;
    private Button button23;

    private View.OnClickListener onClickListener1 = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(LoginSignup.this,Login.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener onClickListener2 = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(LoginSignup.this,Signup.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener onClickListener3 = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(LoginSignup.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        button21=findViewById(R.id.button21);
        button22=findViewById(R.id.button22);
        button23=findViewById(R.id.button23);


        button21.setOnClickListener(onClickListener1);
        button22.setOnClickListener(onClickListener2);
        button23.setOnClickListener(onClickListener3);
    }
}
