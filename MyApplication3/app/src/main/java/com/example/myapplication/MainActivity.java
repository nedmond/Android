package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String category[] = {"Numbers", "Family members", "Colors", "Phrases"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //find a view that shows
        TextView numbers = (TextView) findViewById(R.id.numbers);
        // set a click listener to that view

        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent numbersIntent = new Intent(MainActivity.this, numbersActivity.class);
                startActivity(numbersIntent);
            }
        });
        //find a view that shows
        TextView family = (TextView) findViewById(R.id.family_members);
        // set a click listener to that view

       family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent familyIntent = new Intent(MainActivity.this, familyMembersActivity.class);
                startActivity(familyIntent);
            }
        });

        //find a view that shows
        TextView colors = (TextView) findViewById(R.id.colors);
        // set a click listener to that view

        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent colorsIntent = new Intent(MainActivity.this, colorsActivity.class);
                startActivity(colorsIntent);
            }
        });

        //find a view to show phrases
        TextView phrases = (TextView) findViewById(R.id.phrases);

        //se a click listener
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phrasesIntent = new Intent(MainActivity.this, phrasesActivity.class);
                startActivity(phrasesIntent);
            }


        });

    }
}