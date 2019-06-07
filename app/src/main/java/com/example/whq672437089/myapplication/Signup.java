package com.example.whq672437089.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileOutputStream;

public class Signup extends AppCompatActivity {
    private Spinner spinner1;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private Button button1;
    private CheckBox checkBox;



    private View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {

            String contentET1=editText1.getText().toString().trim();
            String contentET2=editText2.getText().toString().trim();
            String contentET3=editText3.getText().toString().trim();
            String contentET4=editText4.getText().toString().trim();

            String contentRB=null;

            String contentSP=spinner1.getSelectedItem().toString();

            if(radioButton1.isChecked())
                contentRB=radioButton1.getText().toString();
            else if(radioButton2.isChecked()){
                contentRB=radioButton2.getText().toString();
            }


            if((contentET3.length()!=10))
                Toast.makeText(Signup.this, "pls input correct phone number", Toast.LENGTH_SHORT).show();
            else if(contentET2.matches(""))
                Toast.makeText(Signup.this, "pls input password", Toast.LENGTH_SHORT).show();
            else if(contentET1.matches(""))
                Toast.makeText(Signup.this, "pls input name", Toast.LENGTH_SHORT).show();
            else if(contentET4.matches(""))
                Toast.makeText(Signup.this, "pls input farm address", Toast.LENGTH_SHORT).show();
            else if(!checkBox.isChecked())
                Toast.makeText(Signup.this, "pls circle the checkbox to ensure", Toast.LENGTH_SHORT).show();
            else{
                if((radioButton1.isChecked()||radioButton2.isChecked())){
                    String filename = "seller.txt";
                    String fileContents = contentET1+"," +contentET2+","+contentET3+","+contentET4+","+contentSP+","+contentRB+ "\n";
                    FileOutputStream outputStream; //allow a file to be opened for writing
                    try {
                        outputStream = openFileOutput(filename, MODE_APPEND);
                        outputStream.write(fileContents.getBytes());
                        outputStream.close();
                        Toast.makeText(Signup.this, "Sign Up Correctly", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    Intent intent=new Intent(Signup.this,Login.class);
                    startActivity(intent);
                    finish();
                }//end of submit condition
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        spinner1=findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.subjects, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter);

        button1=findViewById(R.id.button21);

        editText1=findViewById(R.id.editText21);
        editText2=findViewById(R.id.editText22);
        editText3=findViewById(R.id.editText23);
        editText4=findViewById(R.id.editText24);

        radioButton1=findViewById(R.id.radioButton1);
        radioButton2=findViewById(R.id.radioButton2);

        button1.setOnClickListener(onClickListener);
        checkBox=findViewById(R.id.checkBox);


    }
}
