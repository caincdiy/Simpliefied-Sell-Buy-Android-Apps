package com.example.whq672437089.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SignUpPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        //initialize fields
        final EditText uname = findViewById(R.id.field_username);
        final EditText pass = findViewById(R.id.field_password);
        final EditText pass2 = findViewById(R.id.field_confpassword);
        final EditText fname = findViewById(R.id.field_firstname);
        final EditText lname = findViewById(R.id.field_lastname);

        final RadioGroup gender = findViewById(R.id.group_gender);
        final RadioButton male = findViewById(R.id.rb_male);
        final RadioButton female = findViewById(R.id.rb_female);

        Button create = findViewById(R.id.btn_create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check for validity
                if(uname.getText().toString().contains(",") || pass.getText().toString().contains(",") || fname.getText().toString().contains(",") || lname.getText().toString().contains(",")){
                    Toast.makeText(getApplicationContext(),"You may not use the character ',' in the fields",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!pass.getText().toString().equals(pass2.getText().toString())){
                    Toast.makeText(getApplicationContext(),"The passwords do not match, try again",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(uname.getText().length()==0){
                    Toast.makeText(getApplicationContext(),"Please enter a username",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(fname.getText().length()==0){
                    Toast.makeText(getApplicationContext(),"Please provide a first name",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(pass.getText().length()==0){
                    Toast.makeText(getApplicationContext(),"Please enter a password",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(lname.getText().length()==0){
                    Toast.makeText(getApplicationContext(),"Please provide a last name",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(gender.getCheckedRadioButtonId()==-1){
                    Toast.makeText(getApplicationContext(),"Please provide a gender",Toast.LENGTH_SHORT).show();
                    return;
                }
                //check if username conflicts with other accounts
                try {
                    FileInputStream fis = openFileInput("buyerdata.txt");
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    //dataLine represents one line from the file
                    String dataLine = "";
                    while((dataLine = br.readLine())!=null){
                        //split the line into its data fields and check against inputs
                        String[] fields = dataLine.split(",");
                        if(fields[0].equals(uname.getText())) {
                            Toast.makeText(getApplicationContext(),"That username is already taken, please try another",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    fis.close();
                }catch (IOException e){e.printStackTrace();}
                //save user data
                String fileinput = uname.getText()+","+pass.getText()+","+fname.getText()+","+lname.getText()+","+(gender.getCheckedRadioButtonId()==male.getId()?"Male":(gender.getCheckedRadioButtonId()==female.getId()?"Female":"NA"))+"\n";

                try {
                    FileOutputStream outputStream;
                    outputStream= openFileOutput("buyerdata.txt", Context.MODE_APPEND);
                    outputStream.write(fileinput.getBytes());
                    outputStream.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finish();

            }
        });

        Button back = findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigates back to previous screen
                finish();
            }
        });
    }
}
