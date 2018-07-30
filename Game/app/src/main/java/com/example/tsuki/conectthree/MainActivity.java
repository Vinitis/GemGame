package com.example.tsuki.conectthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    GridLayout board;
    TextView playerCounter;
    ImageButton replay;
    int activePlayer = 0;
    boolean gameActive = true;
    int[] gameBoard = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerCounter = (TextView) findViewById(R.id.textView);
        replay = (ImageButton) findViewById(R.id.replay);
        board = (GridLayout) findViewById(R.id.board);
    }

    public void appearOn (View view){

        ImageView counter = (ImageView) view;
        System.out.println(counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameBoard[tappedCounter] == 2 && gameActive) {

            gameBoard[tappedCounter] = activePlayer;

            if (activePlayer == 0) {
                playerCounter.setText("Player 2");
                counter.setImageResource(R.drawable.purple);
                counter.animate().alpha(1).rotation(360f).setDuration(500);
                activePlayer = 1;
            } else {
                playerCounter.setText("Player 1");
                counter.setImageResource(R.drawable.green);
                counter.animate().alpha(1).rotation(360f).setDuration(500);
                activePlayer = 0;

            }

            for (int[] winningPosition : winningPositions) {
                if (gameBoard[winningPosition[0]] == gameBoard[winningPosition[1]] &&
                        gameBoard[winningPosition[1]] == gameBoard[winningPosition[2]] &&
                        gameBoard[winningPosition[0]] != 2){

                    //Winning
                    gameActive = false;
                    playerCounter.setText("Player " + (gameBoard[winningPosition[0]] + 1) + " won! ");
                    replay.setClickable(true);
                    replay.animate().rotation(360f).alpha(0.8f).setDuration(500);

                } else {

                    boolean gameIsOver = true;

                    for (int counterState : gameBoard){
                        if (counterState == 2) {
                            gameIsOver = false;
                        }
                    }

                    if (gameIsOver){
                        gameActive = false;
                        playerCounter.setText("Draw!");
                        replay.setClickable(true);
                        replay.animate().rotation(360f).alpha(0.8f).setDuration(500);
                    }
                }

            }
        }
    }

    public void playAgain (View view){

        activePlayer = 0;
        playerCounter.setText("Player 1");
        gameActive = true;

        for (int i = 0; i < gameBoard.length; i++ ) {
            gameBoard[i] = 2;
        }

        for (int i = 0; i < board.getChildCount(); i++){
            ((ImageView) board.getChildAt(i)).setImageResource(0);
        }

    }
}
