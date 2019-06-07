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

public class Login extends AppCompatActivity {
    private EditText editText1;
    private EditText editText2;

    private Button button31;
    private Button button32;

    private int counter=1;
    private int length;//used to count the number of lines in the file

    private View.OnClickListener onClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v){
            String contentET1=editText1.getText().toString().trim();
            String contentET2=editText2.getText().toString().trim();
            boolean valid=false;
            String line=null;
            try {
                FileInputStream fis = openFileInput("seller.txt");
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br=new BufferedReader(isr);

                while ((line = br.readLine()) != null) {
                    String[] output=line.split(",");
                    if(contentET1.matches(output[0])&&contentET2.matches(output[1]))
                        valid=true;
                }
                br.close();
            }//end of try
            catch (Exception e){
                e.printStackTrace();
            }//end of catch

            if(valid){
                Intent intent=new Intent(Login.this,Profile.class);
                intent.putExtra("name",contentET1);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(Login.this, "name and password donot match", Toast.LENGTH_SHORT).show();
            }

        }
    };

    private View.OnClickListener onClickListener2 = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Login.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText1=findViewById(R.id.editText21);
        editText2=findViewById(R.id.editText22);

        button31=findViewById(R.id.button31);
        button32=findViewById(R.id.button32);


        button31.setOnClickListener(onClickListener);
        button32.setOnClickListener(onClickListener2);




    }
}
