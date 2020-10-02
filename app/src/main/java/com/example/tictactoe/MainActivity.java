package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.datatype.Duration;

public class MainActivity extends AppCompatActivity {

    int[] game = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8}, {2,4,6}};
    int activePlayer = 0;

    boolean gameActive = true;
    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        Log.i("info", counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (!gameActive){
            return;
        }
        if (game[tappedCounter] != 2){
            Toast.makeText(this,"invalid space!", Toast.LENGTH_SHORT).show();
            return;
        }

        game[tappedCounter] = activePlayer;

        counter.setTranslationY(-1500);
        if (activePlayer == 0){
            counter.setImageResource(R.drawable.ximg);
            activePlayer = 1;
        }else {
            counter.setImageResource(R.drawable.oimg);
            activePlayer = 0;
        }
        counter.animate().translationYBy(1500).setDuration(300);

        for (int[] winningPos : winningPositions){
            if (game[winningPos[0]] == game[winningPos[1]] && game[winningPos[1]]==game[winningPos[2]] && game[winningPos[0]]!= 2){
                //someone has won
                String winner = "'O'";
                if (activePlayer ==1){
                    winner = "'X'";
                }
                gameActive = false;

                Button playAgain = (Button) findViewById(R.id.playAgain);
                TextView text = (TextView) findViewById(R.id.winnerTextView);

                String message = winner + " is the winner!";
                text.setText(message);
                text.setVisibility(View.VISIBLE);
                playAgain.setVisibility(View.VISIBLE);

            }
        }

        boolean isTie = true;
        for (int element : game) {
            if (element == 2) {
                isTie = false;
            }
        }
        if (isTie){
            Button playAgain = (Button) findViewById(R.id.playAgain);
            TextView text = (TextView) findViewById(R.id.winnerTextView);
            text.setText("It's a tie!");
            text.setVisibility(View.VISIBLE);
            playAgain.setVisibility(View.VISIBLE);
        }
    }

    public void resetGame(View view){
        Button playAgain = (Button) findViewById(R.id.playAgain);
        TextView text = (TextView) findViewById(R.id.winnerTextView);

        GridLayout layout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i=0; i<layout.getChildCount(); i++){
            ImageView child = (ImageView) layout.getChildAt(i);
            child.setImageDrawable(null);
        }
        activePlayer = 0;
        gameActive = true;

        for (int i=0; i<game.length; i++){
            game[i] = 2;
        }
        text.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}