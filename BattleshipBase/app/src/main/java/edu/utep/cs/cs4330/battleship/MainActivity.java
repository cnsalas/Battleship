package edu.utep.cs.cs4330.battleship;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Board board;

    //Set for easy access to change number of shots number / game over indicator
    private BoardView boardView;
    private TextView tv;
    private final int boardSize = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.numberOfShots);

        board = new Board(boardSize);
        boardView = (BoardView) findViewById(R.id.boardView);
        boardView.setBoard(board);
        boardView.addBoardTouchListener(new BoardView.BoardTouchListener() {
            @Override
            public void onTouch(int x, int y) {
                toast(String.format("Touched: %d, %d", x, y));

                //Delay post handler used to update NumberOfShots TextView
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        if(!board.isGameOver())
                        {
                            tv.setText("Number of Shots: " + board.getNumberOfShots());
                        }
                        else
                        {
                            tv.setText("Game Over");
                        }
                    }
                }, 100);
            }
        });
    }


    /**
     * Used to create a new game
     * @param view View passed by layout on button push
     */
    public void newGame(View view)
    {
        board = new Board(boardSize);
        boardView.setBoard(board);
        tv.setText("Number of Shots: " + board.getNumberOfShots());
    }



    /** Show a toast message. */
    protected void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
