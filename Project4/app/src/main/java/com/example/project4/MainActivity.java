package com.example.project4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int ScoreTeamA = 0;
    int ScoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void display(int pointer3) {
        TextView point3Text = (TextView) findViewById(R.id.scoreA);
        point3Text.setText("" + pointer3);
    }

    public void add3Pointer(View view) {

        ScoreTeamA = ScoreTeamA + 3;
        display(ScoreTeamA);
    }

    public void add2Pointer(View view) {

        ScoreTeamA = ScoreTeamA + 2;
        display(ScoreTeamA);
    }

    public void add1Pointer(View view) {
        ScoreTeamA = ScoreTeamA + 1;
        display(ScoreTeamA);
    }

    public void displayB(int ScoreTeamB) {
        TextView point3TextB = (TextView) findViewById(R.id.scoreB);
        point3TextB.setText(""+ScoreTeamB);
    }

    public void add3PointerB(View view) {

        ScoreTeamB = ScoreTeamB + 3;
        displayB(ScoreTeamB);
    }

    public void add2PointerB(View view) {

        ScoreTeamB = ScoreTeamB + 2;
        displayB(ScoreTeamB);
    }

    public void add1PointerB(View view) {
        ScoreTeamB = ScoreTeamB + 1;
        displayB(ScoreTeamB);
    }

    public void reset(View view) {
        ScoreTeamA = 0;
        ScoreTeamB = 0;
        display(ScoreTeamA);
        displayB(ScoreTeamB);
    }
}
