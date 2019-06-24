package com.example.project5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
int quantity =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    private void display(int quantity){

        TextView t = (TextView)findViewById(R.id.text);
        t.setText(""+quantity);
    }
    private void increment(View v){
        quantity=quantity+1;
        display(quantity);
    }
    private void decrement(View v){
        quantity=quantity-1;
        display(quantity);
    }

}
