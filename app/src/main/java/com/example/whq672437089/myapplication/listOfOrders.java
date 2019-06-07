package com.example.whq672437089.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class listOfOrders extends AppCompatActivity {

    Button order1;
    Button order2;
    Button order3;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_orders);

        order1 = findViewById(R.id.button);
        order2 = findViewById(R.id.btn_back);
        order3 = findViewById(R.id.button4);

        order1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(listOfOrders.this, Order_detail.class);

                String items[] = {"A","B","C","D"};
                String quantity[] = {"1","3","5","7"};
                String totValue = "10000";
                String totWeight = "100";
                String dest = "Vancouver";

                intent.putExtra("items",items);
                intent.putExtra("quantity",quantity);
                intent.putExtra("totValue",totValue);
                intent.putExtra("totWeight",totWeight);
                intent.putExtra("dest",dest);

                startActivity(intent);
            }
        });

        order2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(listOfOrders.this, Order_detail.class);

                //Make up some order info, and pass it to order detail
                String items[] = {"A","B","C"};
                String quantity[] = {"1","3","5"};
                String totValue = "233333";
                String totWeight = "1234";
                String dest = "Kelowna";

                intent.putExtra("items",items);
                intent.putExtra("quantity",quantity);
                intent.putExtra("totValue",totValue);
                intent.putExtra("totWeight",totWeight);
                intent.putExtra("dest",dest);

                startActivity(intent);
            }
        });

        order3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(listOfOrders.this, Order_detail.class);

                //Make up some order info, and pass it to order detail
                String items[] = {"A","B"};
                String quantity[] = {"1","3"};
                String totValue = "233333";
                String totWeight = "1234";
                String dest = "New York";

                intent.putExtra("items",items);
                intent.putExtra("quantity",quantity);
                intent.putExtra("totValue",totValue);
                intent.putExtra("totWeight",totWeight);
                intent.putExtra("dest",dest);

                startActivity(intent);
            }
        });

    }
}
