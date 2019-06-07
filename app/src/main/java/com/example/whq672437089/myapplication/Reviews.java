package com.example.whq672437089.myapplication;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reviews extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        //find pixel density for later use
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        final double density = metrics.density;

        String user = "";
        try{
            FileInputStream fis = openFileInput("session.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            user = br.readLine();
            fis.close();
        }catch(IOException e){e.printStackTrace();}
        final String user1 = user;

        Button post = findViewById(R.id.btn_post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText content = findViewById(R.id.field_review);
                LinearLayout reviews = findViewById(R.id.view_reviews);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                params.leftMargin=(10);
                //posted by
                TextView poster = new TextView(v.getContext());
                poster.setLayoutParams(params);
                poster.setText("Posted by "+user1+"\n");
                poster.setTextColor(Color.BLACK);
                poster.setTextSize((int)(8*density));
                reviews.addView(poster);
                //review content
                TextView review = new TextView(v.getContext());
                review.setLayoutParams(params);
                review.setText(content.getText().toString());
                review.setTextColor(Color.BLACK);
                review.setTextSize((int)(6*density));
                reviews.addView(review);
                //draw a dividing line between items
                TableRow divider = new TableRow(v.getContext());
                View line = new View(v.getContext());
                line.setBackgroundColor(ContextCompat.getColor(v.getContext(), android.R.color.darker_gray));
                TableRow.LayoutParams lineparams = new TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                lineparams.height = (int) density;
                line.setLayoutParams(lineparams);
                reviews.addView(divider);
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
