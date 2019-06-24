package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class numbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
ArrayList <String> words =new ArrayList<String>();
int i;
for(i=0; i<=100; i++ ) {
    words.add("one" + i);
}
//        words.add("Two");
//        words.add("Three");
//        words.add("Four");
//       words.add("Five");
//        words.add("Six");
//        words.add("Seven");
//        words.add("Eight");
//        words.add("Nine");
//        words.add("Ten");

        LinearLayout rootView= (LinearLayout) findViewById(R.id.bestView);

        int index=0;
        while(index<words.size()) {
            TextView wordView = new TextView(this);
            wordView.setText(words.get(index));
            wordView.setTextSize(16);
            rootView.addView(wordView);
            index +=1;

        }
        for(index=0; index<words.size(); index++){
            TextView wordView = new TextView(this);
            wordView.setText(words.get(index));
            wordView.setTextSize(16);
            rootView.addView(wordView);
        }


    }

}
