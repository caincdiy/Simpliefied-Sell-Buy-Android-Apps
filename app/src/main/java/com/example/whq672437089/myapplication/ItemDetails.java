package com.example.whq672437089.myapplication;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        //find pixel density for later use
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        double density = metrics.density;

        ImageView img = findViewById(R.id.img_itempic);
        TextView name = findViewById(R.id.txt_itemname);
        TextView desc = findViewById(R.id.txt_itemdesc);
        TextView price = findViewById(R.id.txt_itemprice);

        //set values
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if(b!=null) {
            img.setImageResource(getResources().getIdentifier(b.getString("pic"), "drawable", getPackageName()));
            name.setText(b.getString("name"));
            desc.setText(b.getString("desc"));
            price.setText(b.getString("price"));
        }

        //buttons
        Button comments = findViewById(R.id.btn_comments);
        comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Reviews.class);
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
