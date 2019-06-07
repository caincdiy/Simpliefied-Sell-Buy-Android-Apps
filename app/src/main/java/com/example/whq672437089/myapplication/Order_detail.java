package com.example.whq672437089.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Order_detail extends AppCompatActivity {
    Intent intent1;

    TextView a;
    TextView b;
    TextView c;
    TextView d;

    Button accept;

    String [] s1;
    String [] s2;
    String s3,s4,s5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        //find all views
        a=findViewById(R.id.TWeight);
        b=findViewById(R.id.Dest1);
        c=findViewById(R.id.OV1);
        d=findViewById(R.id.textView10);

        //get data from intent
        intent1 = getIntent();
        s1 = intent1.getStringArrayExtra("items");
        s2 = intent1.getStringArrayExtra("quantity");
        s3 = intent1.getStringExtra("totValue");
        s4 = intent1.getStringExtra("totWeight");
        s5 = intent1.getStringExtra("dest");

        //set data
        a.setText("Total Weight: "+s4);
        b.setText("Destination: "+s5);
        c.setText("Order Value: "+s3);

        String temp = "";

        for (int i=0; i<s1.length;i++){
            temp += s1[i]+"x"+s2[i]+"\n";
        }

        d.setText(temp);

        accept = findViewById(R.id.Done);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               intent1 = new Intent(Order_detail.this,courierFinish.class);

                intent1.putExtra("s1",s1);
                intent1.putExtra("s2",s2);
                intent1.putExtra("s3",s3);
                intent1.putExtra("s4",s4);
                intent1.putExtra("s5",s5);

                startActivity(intent1);
                finish();
            }
        });
    }
}
