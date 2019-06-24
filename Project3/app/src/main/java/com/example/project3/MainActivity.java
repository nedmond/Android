package com.example.project3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int goal = 0;
    int goalb = 0;
    int shots = 0;
    int shotsb = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void incrementGoal(View view) {
        goal = goal + 1;
        display(goal);

    }

    public void decrementGoal(View view) {
        goal = goal - 1;
        display(goal);
    }

    public void display(int goal) {


        TextView goalText = (TextView) findViewById(R.id.goal_text_a);

        goalText.setText("" + goal);
    }

    public void displayB(int goalb) {
        TextView goalTextB = (TextView) findViewById(R.id.goal_text_b);
        goalTextB.setText("" + goalb);
    }

    public void incrementGoalB(View view) {
        goalb = goalb + 1;
        displayB(goalb);
    }

    public void decrementGoalB(View view) {
        goalb = goalb - 1;
        displayB(goalb);
    }

    public void incrementShots(View view) {
        shots = shots + 1;
        displayShots(shots);

    }

    public void decrementShots(View view) {
        shots = shots - 1;
        displayShots(shots);
    }

    public void displayShots(int shots) {


        TextView shotText = (TextView) findViewById(R.id.shots_text_a);

        shotText.setText("" + shots);
    }

    public void incrementShotsB(View view) {
        shotsb = shotsb + 1;
        displayShotsB(shotsb);

    }

    public void decrementShotsB(View view) {
        shotsb = shotsb - 1;
        displayShotsB(shotsb);
    }

    public void displayShotsB(int shotsb) {


        TextView shotTextB = (TextView) findViewById(R.id.shots_text_b);

        shotTextB.setText("" + shotsb);
    }
}

