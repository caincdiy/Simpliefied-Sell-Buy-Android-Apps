package com.example.whq672437089.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class ItemList extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        //get in session user


        //find pixel density for later use
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        double density = metrics.density;

        //initialize
        final EditText searchtext = findViewById(R.id.field_search);
        final TextView cartval = findViewById(R.id.txt_carttotal);
        TableLayout tl = findViewById(R.id.items);

        //initialize cart value from session file
        try{
            BufferedReader initcartval = new BufferedReader(new InputStreamReader(openFileInput("session.txt")));
            String dataLine = "";
            double value = 0;
            while((dataLine = initcartval.readLine())!=null){
                String[] fields = dataLine.split(",");
                if(fields.length==3) {
                    value += Double.parseDouble(fields[1])*Double.parseDouble(fields[2]);
                }
            }
            cartval.setText("Cart Value:\n$"+String.format("%.2f",value));
        }catch(IOException e){e.printStackTrace();}




        String query = getIntent().getStringExtra("search");
        //display items that match the search
        try {

            FileInputStream fis = openFileInput("items.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            //dataLine represents one line from the file
            String dataLine = "";

            //set up the weights of the columns and align the items vertically
            TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT,1f);
            TableRow.LayoutParams rowParams1 = new TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,0.2f);
            TableRow.LayoutParams rowParams2 = new TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,0.3f);
            TableRow.LayoutParams rowParams3 = new TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,0.2f);
            TableRow.LayoutParams rowParams4 = new TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,0.1f);
            TableRow.LayoutParams rowParams5 = new TableRow.LayoutParams((int) (30 * density), TableRow.LayoutParams.WRAP_CONTENT,0.1f);
            rowParams.gravity = Gravity.CENTER;
            rowParams2.gravity = Gravity.CENTER;
            rowParams3.gravity = Gravity.CENTER;
            rowParams4.gravity = Gravity.CENTER;
            rowParams5.gravity = Gravity.CENTER;


            while((dataLine = br.readLine())!=null){
                //read/write session data
                //user in session info
                BufferedReader sesread = new BufferedReader(new InputStreamReader(openFileInput("session.txt")));
                final String user = sesread.readLine();
                //session output
                FileOutputStream seswrite = openFileOutput("session.txt", Context.MODE_APPEND);

                //split the line into its data fields and find search matches
                String[] fields = dataLine.split(",");

                if(fields[0].toLowerCase().contains(query.toLowerCase())){

                    final String itemname = fields[0];
                    final String itemdesc = fields[1];
                    final String picname = fields[2];
                    final Double itemprice = Double.parseDouble(fields[3]);

                    //display item in scrollview table
                    TableRow row = new TableRow(this);
                    row.setLayoutParams(rowParams);

                    //image
                    ImageView img = new ImageView(this);
                    img.setLayoutParams(rowParams1);
                    img.setImageResource(getResources().getIdentifier(picname, "drawable", getPackageName()));
                    img.setAdjustViewBounds(true);
                    img.setMaxHeight((int)(80*density));

                    row.addView(img);

                    //name
                    final TextView name = new TextView(this);
                    name.setLayoutParams(rowParams2);
                    name.setText(itemname);
                    name.setTextColor(Color.BLACK);
                    name.setTextSize((int)(8*density));
                    name.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    row.addView(name);

                    //price
                    final TextView price = new TextView(this);
                    price.setLayoutParams(rowParams3);
                    price.setText("$"+String.format("%.2f",itemprice));
                    price.setTextColor(Color.BLACK);
                    price.setTextSize((int)(6*density));
                    price.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    row.addView(price);

                    //amount in cart
                    final TextView cartamount = new TextView(this);
                    cartamount.setLayoutParams(rowParams4);
                    //get cart amount
                    String dataLine2="";
                    boolean infile = false;
                    while((dataLine2 = sesread.readLine())!=null && !infile){
                        String[] fields2 = dataLine2.split(",");
                        if(fields2[0].equals(itemname)){
                            //if the item is in file
                            infile = true;
                            cartamount.setText(String.valueOf(fields2[2]));
                        }
                    }
                    if(!infile){
                        cartamount.setText("0");
                        //write to file
                        seswrite.write((itemname+","+itemprice+",0\n").getBytes());
                    }

                    cartamount.setTextColor(Color.BLACK);
                    cartamount.setTextSize((int) (6 * density));
                    cartamount.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    //subtract from cart
                    Button sub = new Button(this);
                    sub.setLayoutParams(rowParams5);
                    sub.setText("-");
                    sub.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(cartamount.getText().toString().equals("0")){
                                return;
                            }
                            cartamount.setText(String.valueOf(Integer.parseInt(cartamount.getText().toString())-1));
                            cartval.setText("Cart Value:\n$"+String.format("%.2f",Double.parseDouble(cartval.getText().toString().substring(13))-itemprice));

                            try {
                                BufferedReader sesread = new BufferedReader(new InputStreamReader(openFileInput("session.txt")));
                                String dataLine2 = "";
                                while ((dataLine2 = sesread.readLine()) != null) {
                                    String[] fields2 = dataLine2.split(",");
                                    if (fields2[0].equals(name.getText().toString())) {
                                        replaceLine("session.txt",dataLine2,fields2[0]+","+fields2[1]+","+String.valueOf(Integer.parseInt(fields2[2])-1)+"\n");
                                    }
                                }
                            }catch(IOException e){e.printStackTrace();}
                        }
                    });

                    //add to cart
                    Button add = new Button(this);
                    add.setLayoutParams(rowParams5);
                    add.setText("+");
                    add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cartamount.setText(String.valueOf(Integer.parseInt(cartamount.getText().toString())+1));
                            cartval.setText("Cart Value:\n$"+String.format("%.2f",Double.parseDouble(cartval.getText().toString().substring(13))+itemprice));

                            try {
                                BufferedReader sesread = new BufferedReader(new InputStreamReader(openFileInput("session.txt")));
                                String dataLine2 = "";
                                while ((dataLine2 = sesread.readLine()) != null) {
                                    String[] fields2 = dataLine2.split(",");
                                    if (fields2[0].equals(name.getText().toString())) {
                                        replaceLine("session.txt",dataLine2,fields2[0]+","+fields2[1]+","+String.valueOf(Integer.parseInt(fields2[2])+1)+"\n");
                                    }
                                }
                            }catch(IOException e){e.printStackTrace();}
                        }
                    });


                    row.addView(sub);
                    row.addView(cartamount);
                    row.addView(add);

                    //add link to the item page
                    row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(v.getContext(),ItemDetails.class);
                            Bundle b = new Bundle();
                            b.putString("name",itemname);
                            b.putString("desc",itemdesc);
                            b.putString("pic",picname);
                            b.putString("price","$"+String.format("%.2f",itemprice));
                            intent.putExtras(b);
                            startActivity(intent);
                        }
                    });

                    tl.addView(row);

                    //draw a dividing line between items
                    TableRow divider = new TableRow(this);
                    View line = new View(this);
                    line.setBackgroundColor(ContextCompat.getColor(this,android.R.color.darker_gray));
                    TableRow.LayoutParams lineparams =  new TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,1f);
                    lineparams.height = (int)density;
                    line.setLayoutParams(lineparams);
                    divider.addView(line);
                    tl.addView(divider);


                    sesread.close();
                    seswrite.close();
                }

            }

            fis.close();
        }catch (IOException e){e.printStackTrace();}

        //for creating new searches within the activity
        Button search = findViewById(R.id.btn_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //take the search term and start item list
                Intent intent = new Intent(v.getContext(),ItemList.class);
                intent.putExtra("search",searchtext.getText().toString());
                startActivity(intent);
            }
        });

        //proceed to checkout
        Button checkout = findViewById(R.id.btn_checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Checkout.class);
                startActivity(intent);
            }
        });

        Button back = findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    //helper methods
    public void replaceLine(String filename, String line, String replacement){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(filename)));
            FileOutputStream writer = openFileOutput("tempfile.txt", Context.MODE_PRIVATE);

            String currentLine;

            //get file contents with new line
            while((currentLine = reader.readLine()) != null) {
                if(currentLine.equals(line)){
                    writer.write((replacement+"\n").getBytes());
                }else{
                    writer.write((currentLine+"\n").getBytes());
                }
            }
            writer.close();
            reader.close();

            //overwite
            BufferedReader readnew = new BufferedReader(new InputStreamReader(openFileInput("tempfile.txt")));
            FileOutputStream overwrite = openFileOutput(filename, Context.MODE_PRIVATE);

            while((currentLine = readnew.readLine()) != null) {
                overwrite.write((currentLine+"\n").getBytes());
            }
            readnew.close();
            overwrite.close();

        }catch(IOException e){e.printStackTrace();}
    }
}
