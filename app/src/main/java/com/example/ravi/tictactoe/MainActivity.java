package com.example.ravi.tictactoe;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static android.R.attr.onClick;
import static android.R.attr.tag;

public class MainActivity extends AppCompatActivity {

    ImageView myImageView,iv1,iv2,iv3,iv4,iv5,iv6,iv7,iv8,iv9;
    int myPlayer = 0;    // 0 for player 1 and 1 for player 2
    RadioButton r1,r2;

    // Setting the default gamestate as 11
    int[] myGameState = {11,11,11,11,11,11,11,11,11};

    RelativeLayout relativeLayout;
    boolean draw = true;
    int winNo = 0;

    // Case when 3 images are stars in a row, column or diagnol
    boolean starCase = false;
    int p = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (RelativeLayout)findViewById(R.id.layout);

        // Setting the imagevies to the respective stars of the Tic Tac Toe
        iv1 = (ImageView)findViewById(R.id.iv1);
        iv2 = (ImageView)findViewById(R.id.iv2);
        iv3 = (ImageView)findViewById(R.id.iv3);
        iv4 = (ImageView)findViewById(R.id.iv4);
        iv5 = (ImageView)findViewById(R.id.iv5);
        iv6 = (ImageView)findViewById(R.id.iv6);
        iv7 = (ImageView)findViewById(R.id.iv7);
        iv8 = (ImageView)findViewById(R.id.iv8);
        iv9 = (ImageView)findViewById(R.id.iv9);

        // Radio button that tells which player turn is right now
        r1 = (RadioButton)findViewById(R.id.radioButton3);
        r2 = (RadioButton)findViewById(R.id.radioButton4);

        // By default the first radio button is checked for the player 1 turn
        r1.setChecked(true);

    }


    // Called when user taps any star
    public void imageTapped(View view)
    {
        myImageView = (ImageView)view;

        // To get the location of the star, we use the imageTag to identify them
        int imageTag = Integer.parseInt(view.getTag().toString());


        // By default the game state is 11, so if the game state is 11, we can edit it otherwise not
        if(myGameState[imageTag]==11)
        {
            // changing the game state so that it becomes un-editable
            myGameState[imageTag] = myPlayer;

            // By sefault the first player gets cros as the sign , so checks which player it is
            if(myPlayer==0)  // For player 1
            {
                // Changing images and the radio buttons

                //myImageView.animate().alpha(0).setDuration(3000);
                myImageView.setImageResource(R.drawable.cross);
                //myImageView.animate().alpha(0).setDuration(1000);
                r2.setChecked(true);
                r1.setChecked(false);
                myPlayer = 1;
            }
            else // For player 2
            {
                //myImageView.animate().alpha(0).setDuration(3000);
                myImageView.setImageResource(R.drawable.circle);
                //myImageView.animate().alpha(0).setDuration(1000);
                r1.setChecked(true);
                r2.setChecked(false);
                myPlayer = 0;
            }

            // Every time the images change, conditions for winning are checked and only called once 5 images are changed

            winNo++;
            if(winNo >= 5 && checkStarCase())  // Minimum 5 moves are required to win in least time
            {
               winCheck();
            }

            //Log.i("gamestate","game state is " + myGameState[6]);
        }
        else // When user taps any un-editable image
        {
            Toast.makeText(this,"Position filled already",Toast.LENGTH_SHORT).show();
        }

    }


    // Restart game
    public void playAgain(View view)
    {
        alertPlayAgin();
    }

    public void alertPlayAgin()
    {
        myPlayer = 0;  // resetting player to 0

        for(int i = 0;i < myGameState.length;i++)    // resetting game state
        {
            myGameState[i] = 11;
        }

        /*for(int j = 0;j < relativeLayout.getChildCount();j++)
        {
            View x = relativeLayout.getChildAt(j);
            if(Integer.parseInt(x.getTag().toString()) == 0 || Integer.parseInt(x.getTag().toString()) == 1 || Integer.parseInt(x.getTag().toString()) == 2 || Integer.parseInt(x.getTag().toString()) == 3 || Integer.parseInt(x.getTag().toString()) == 4 || Integer.parseInt(x.getTag().toString()) == 5 || Integer.parseInt(x.getTag().toString()) == 6 || Integer.parseInt(x.getTag().toString()) == 7 ||Integer.parseInt(x.getTag().toString()) == 8)
            {
                ((ImageView)relativeLayout.getChildAt(j)).setImageResource(R.drawable.blue);
            }

        }*/

        iv1.setImageResource(R.drawable.blue);   // setting all images to original blue one
        iv2.setImageResource(R.drawable.blue);
        iv3.setImageResource(R.drawable.blue);
        iv4.setImageResource(R.drawable.blue);
        iv5.setImageResource(R.drawable.blue);
        iv6.setImageResource(R.drawable.blue);
        iv7.setImageResource(R.drawable.blue);
        iv8.setImageResource(R.drawable.blue);
        iv9.setImageResource(R.drawable.blue);

        r1.setChecked(true);                     // setting the radio buttons to original state
        r2.setChecked(false);

        draw = true;                            // Setting all values to default
        winNo = 0;
        starCase = false;
        p = 1;
    }


    // Checking the winning conditions
    public void winCheck()
    {

        // Checking all the possible combinations for winning a game
        // That is 3 rows, 3 columns and 2 diagnols

        if(myGameState[0] == myGameState[4] && myGameState[0] == myGameState[8])
        {
            //Toast.makeText(this,"Player wins 1",Toast.LENGTH_SHORT).show();
            win();
            return;
        }

        if(myGameState[2] == myGameState[4] && myGameState[2] == myGameState[6])
        {
            //Toast.makeText(this,"Player wins 2",Toast.LENGTH_SHORT).show();
            win();
            return;
        }

        for (int i = 0; i < 3; i++)
        {
            if(myGameState[i] == myGameState[i+3] && myGameState[i] == myGameState[i+6])
            {
                //Toast.makeText(this,"Player wins 3",Toast.LENGTH_SHORT).show();
                win();
                return;
            }
        }

        if(myGameState[0] == myGameState[1] && myGameState[0] == myGameState[2])
        {
            //Toast.makeText(this,"Player wins 4",Toast.LENGTH_SHORT).show();
            win();
            return;
        }

        if(myGameState[3] == myGameState[4] && myGameState[3] == myGameState[5])
        {
            //Toast.makeText(this,"Player wins 5",Toast.LENGTH_SHORT).show();
            win();
            return;
        }

        if(myGameState[6] == myGameState[7] && myGameState[6] == myGameState[8])
        {
            //Toast.makeText(this,"Player wins 6",Toast.LENGTH_SHORT).show();
            win();
            return;
        }

        // Check for draw
        for (int j = 0; j<9; j++)
        {
            if(myGameState[j] == 11)
            {
                draw = false;
            }

        }

        if (draw)
        {
            //Toast.makeText(this,"Match Draw",Toast.LENGTH_SHORT).show();
            draw();
        }

        draw = true;

    }

    // Cheks if the there are no 3 consecutive stars present un-touched
    public boolean checkStarCase()
    {
        // Checking all possible star combinations ma be remaining

        if (myGameState[0] != 11 || myGameState[3] != 11 || myGameState[6] != 11)
        {
            starCase = true;
        }
        else
        {
            starCase = false;
            return starCase;
        }

        if (myGameState[1] != 11 || myGameState[4] != 11 || myGameState[7] != 11)
        {
            starCase = true;
        }
        else
        {
            starCase = false;
            return starCase;
        }

        if (myGameState[2] != 11 || myGameState[5] != 11 || myGameState[8] != 11)
        {
            starCase = true;
        }
        else
        {
            starCase = false;
            return starCase;
        }

        if (myGameState[0] != 11 || myGameState[1] != 11 || myGameState[2] != 11)
        {
            starCase = true;
        }
        else
        {
            starCase = false;
            return starCase;
        }

        if (myGameState[3] != 11 || myGameState[4] != 11 || myGameState[5] != 11)
        {
            starCase = true;
        }
        else
        {
            starCase = false;
            return starCase;
        }

        if (myGameState[6] != 11 || myGameState[7] != 11 || myGameState[8] != 11)
        {
            starCase = true;
        }
        else
        {
            starCase = false;
            return starCase;
        }

       return starCase;
    }

    // When a player wins
    public void win()
    {

        // Getting which player won
        if (r1.isChecked())
        {
            p = 2;
        }

        // Launching an alert dialog to show who has won
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Player " + p + " wins");
        alert.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertPlayAgin();
            }
        });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    // If match draws
    public void draw()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Match Drawn");
        alert.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertPlayAgin();
            }
        });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }


}
