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
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class Post extends AppCompatActivity {
    String name;
    private TextView textView20;
    private Spinner spinner1;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private CheckBox checkBox1;
    private Button button1;
    private Button button2;




    private View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {

            String contentET1=editText1.getText().toString().trim();
            String contentET2=editText2.getText().toString().trim();
            String contentET3=editText3.getText().toString().trim();

            String contentRB=null;

            String contentSP=spinner1.getSelectedItem().toString();

            if(contentET1.matches(""))
                Toast.makeText(Post.this, "pls input correct number", Toast.LENGTH_SHORT).show();
            else if(contentET2.matches(""))
                Toast.makeText(Post.this, "pls input correct last name", Toast.LENGTH_SHORT).show();
            else if(contentET3.matches(""))
                Toast.makeText(Post.this, "pls input correct first name", Toast.LENGTH_SHORT).show();
            else{
                if((checkBox1.isChecked())){
                    String filename = "product.txt";
                    String fileContents = name+","+contentET1+"," +contentET2+","+contentET3+","+contentSP+ "\n";
                    FileOutputStream outputStream; //allow a file to be opened for writing
                    try {
                        outputStream = openFileOutput(filename, MODE_APPEND);
                        outputStream.write(fileContents.getBytes());
                        outputStream.close();
                        Toast.makeText(Post.this, "post correctly", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    finish();
                }//end of submit condition
                else
                    Toast.makeText(Post.this, "pls circle the checkbox to ensure posting", Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_post);

        Intent intent=getIntent();
        name=intent.getStringExtra("name");
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
        textView20=findViewById(R.id.textView20);
        textView20.setText(name);

        checkBox1=findViewById(R.id.checkBox1);

        button1.setOnClickListener(onClickListener);
        button2=findViewById(R.id.button2);

        button2.setOnClickListener(onClickListener2);




    }
}
