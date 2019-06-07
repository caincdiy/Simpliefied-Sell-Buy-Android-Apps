package com.example.whq672437089.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Checkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        //find pixel density for later use
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        double density = metrics.density;

        //initialize
        TextView totalprice = findViewById(R.id.txt_totalprice);
        TableLayout tl = findViewById(R.id.items);
        TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT,1f);
        TableRow.LayoutParams rowParams1 = new TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT,0.5f);
        TableRow.LayoutParams rowParams2 = new TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,0.3f);
        TableRow.LayoutParams rowParams3 = new TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,0.2f);
        rowParams1.gravity = Gravity.LEFT;
        rowParams2.gravity = Gravity.CENTER;
        rowParams3.gravity = Gravity.CENTER;

        double total = 0;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("session.txt")));
            String dataLine = "";
            while ((dataLine = br.readLine()) != null) {
                String[] fields = dataLine.split(",");
                if(fields.length==3 && !fields[2].equals("0")) {
                    String itemname = fields[0];
                    Double itemprice = Double.parseDouble(fields[1]);
                    String itemquantity = fields[2];
                    total += itemprice * Integer.parseInt(itemquantity);

                    //display item in scrollview table
                    TableRow row = new TableRow(this);
                    row.setLayoutParams(rowParams);

                    //name
                    final TextView name = new TextView(this);
                    name.setLayoutParams(rowParams1);
                    name.setText(itemname);
                    name.setTextColor(Color.BLACK);
                    name.setTextSize((int) (9 * density));

                    row.addView(name);

                    //price
                    final TextView price = new TextView(this);
                    price.setLayoutParams(rowParams2);
                    price.setText("$" + String.format("%.2f", itemprice * Integer.parseInt(itemquantity)));
                    price.setTextColor(Color.parseColor("#00AA00"));
                    price.setTextSize((int) (7 * density));
                    price.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    row.addView(price);

                    //quantity
                    final TextView quantity = new TextView(this);
                    quantity.setLayoutParams(rowParams3);
                    quantity.setText(itemquantity);
                    quantity.setTextColor(Color.BLACK);
                    quantity.setTextSize((int) (7 * density));
                    quantity.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    row.addView(quantity);

                    tl.addView(row);

                    //draw a dividing line between items
                    TableRow divider = new TableRow(this);
                    View line = new View(this);
                    line.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
                    TableRow.LayoutParams lineparams = new TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                    lineparams.height = (int) density;
                    line.setLayoutParams(lineparams);
                    divider.addView(line);
                    tl.addView(divider);


                }
                }

            br.close();
        }catch(IOException e){e.printStackTrace();}

        //set total price
        totalprice.setText("$"+String.format("%.2f",total));

        Button placeorder = findViewById(R.id.btn_placeorder);
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("session.txt")));
                    String user = br.readLine();
                    FileOutputStream clearcart = openFileOutput("session.txt", Context.MODE_PRIVATE);
                    clearcart.write(user.getBytes());
                    br.close();
                    clearcart.close();
                }catch(IOException e){e.printStackTrace();}

                Toast.makeText(getApplicationContext(),"Your Order has been placed",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(v.getContext(),ItemSearch.class);
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
}
