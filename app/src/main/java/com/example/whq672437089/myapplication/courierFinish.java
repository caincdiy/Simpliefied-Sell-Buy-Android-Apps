package com.example.whq672437089.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class courierFinish extends AppCompatActivity {

    TextView a,b,c,d;
    Button done;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courier_finish);

        a=findViewById(R.id.TW1);
        b=findViewById(R.id.Dest1);
        c=findViewById(R.id.OV1);
        d=findViewById(R.id.TV1);

        //get d
        intent = getIntent();
        String s1[]= intent.getStringArrayExtra("s1");
        String s2[]= intent.getStringArrayExtra("s2");
        String s3 = intent.getStringExtra("s3");
        String s4 = intent.getStringExtra("s4");
        String s5 = intent.getStringExtra("s5");

        //set data
        a.setText("Total Weight: "+s4);
        b.setText("Destination: "+s5);
        c.setText("Order Value: "+s3);

        String temp = "";

        for (int i=0; i<s1.length;i++){
            temp += s1[i]+"x"+s2[i]+"\n";
        }

        d.setText(temp);

        done=findViewById(R.id.Done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(courierFinish.this, listOfOrders.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
