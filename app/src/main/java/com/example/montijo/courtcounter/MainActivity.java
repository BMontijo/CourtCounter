package com.example.montijo.courtcounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // variables to keep track of scores of teams
    private int scoreHome = 0;
    private int scoreVisitor = 0;

    // variables to hold team names
    private EditText homeTeam;
    private EditText visitorTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // intantiate variables
        homeTeam = (EditText) findViewById(R.id.home_team);
        visitorTeam = (EditText) findViewById(R.id.visitor_team);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Increase the score for Team A by 1 point.
     */
    public void addOneForTeamA(View v) {
        scoreHome++;
        displayForTeamA(scoreHome);
    }

    /**
     * Increase the score for Team A by 2 points.
     */
    public void addTwoForTeamA(View v) {
        scoreHome = scoreHome +2;
        displayForTeamA(scoreHome);
    }

    /**
     * Increase the score for Team A by 3 points.
     */
    public void addThreeForTeamA(View v) {
        scoreHome = scoreHome + 3;
        displayForTeamA(scoreHome);
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Increase the score for Team B by 1 point.
     */
    public void addOneForTeamB(View v) {
        scoreVisitor++;
        displayForTeamB(scoreVisitor);
    }

    /**
     * Increase the score for Team B by 2 points.
     */
    public void addTwoForTeamB(View v) {
        scoreVisitor = scoreVisitor +2;
        displayForTeamB(scoreVisitor);
    }

    /**
     * Increase the score for Team B by 3 points.
     */
    public void addThreeForTeamB(View v) {
        scoreVisitor = scoreVisitor + 3;
        displayForTeamB(scoreVisitor);
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    public void resetScore(View v) {
        // reset score variables
        scoreHome = 0;
        scoreVisitor = 0;

        //update score displays
        displayForTeamA(scoreHome);
        displayForTeamB(scoreVisitor);

        // reset team name variables
        homeTeam.setText("");
        visitorTeam.setText("");
    }

    public void shareScores (View v) {
        // string for home team share
        String shareHomeTeam = homeTeam.getText().toString();
        // string for visitor team share
        String shareVisitorTeam = visitorTeam.getText().toString();
        // check if home team name was entered
        if (shareHomeTeam.equals("")) {
            // no name entered - assign generic
            shareHomeTeam = getString(R.string.generic_home);
        }
        // check if visitor team name was entered
        if (shareVisitorTeam.equals("")) {
            // no name entered - assign generic
            shareVisitorTeam = getString(R.string.generic_visitor);
        }
        // start the body of share message
        String body = getString(R.string.share_subject);
        // add team names and scores to share message
        body = body + "\n" + String.format("%s\n\n", getString(R.string.share_body1, shareHomeTeam, scoreHome));
        body = body + getString(R.string.share_body2, shareVisitorTeam, scoreVisitor) + "\n\n";
        // create intent for email
        Intent shareScore = new Intent(Intent.ACTION_SEND);
        shareScore.setType("text/plain");
        shareScore.putExtra(Intent.EXTRA_TEXT, body);
        // make sure app installed to handle intent
        if (shareScore.resolveActivity(getPackageManager()) != null) {
            // start sharing intent
            startActivity(shareScore);
        } else {
            // present toast stating error
            Toast.makeText(MainActivity.this, R.string.share_error, Toast.LENGTH_SHORT).show();
        }
    }
}
