package com.google.engedu.ghost;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class GhostActivity extends ActionBarActivity {
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private GhostDictionary dictionary;
    private boolean userTurn = false;
    private Random random = new Random();
    SimpleDictionary sd ;
    String input="";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost);

        InputStream in = null;
        String f="words.txt";


        try {
            in=(getAssets().open(f));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            sd=new SimpleDictionary(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        onStart(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
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

    private void computerTurn() {
        TextView label = (TextView) findViewById(R.id.gameStatus);
        TextView text = (TextView) findViewById(R.id.ghostText);
        int tag=0;
        if(input.length()>=4){
            if(sd.isWord(input)) {
                label.setText("valid word... COMPUTER WINS!");
                tag=1;
            }
        }
        if(tag==0) {
            String repre = sd.getAnyWordStartingWith(input);
            if (repre == null) {
                label.setText("Challenge...there is no such a word starting with " + input + "  COMPUTER WINS!");
            } else {
                input = repre.substring(0,input.length()+1);
                text.setText(input);
                label.setText(repre);

            }


            // Do computer turn stuff then make it the user's turn again
            userTurn = true;
            //label.setText(USER_TURN);
        }
    }

    /**
     * Handler for the "Reset" button.
     * Randomly determines whether the game starts with a user turn or a computer turn.
     * @param view
     * @return true
     */
    public boolean onStart(View view) {
        userTurn = random.nextBoolean();
        TextView text = (TextView) findViewById(R.id.ghostText);
        text.setText("");
        TextView label = (TextView) findViewById(R.id.gameStatus);
        if (userTurn) {
            label.setText(USER_TURN);
        } else {
            label.setText(COMPUTER_TURN);
            computerTurn();
        }
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        TextView text = (TextView) findViewById(R.id.ghostText);
        TextView label = (TextView) findViewById(R.id.gameStatus);
        char ch= (char) event.getUnicodeChar();
        input=input.concat(""+ch);
        text.setText(input);
        computerTurn();

      /* if(sd.isWord(input)==true){

            label.setText("valid word");

        }else{
            label.setText("invalid word");
        }*/
        return super.onKeyUp(keyCode, event);
    }

    public void onclickchallenge(View view) {
        TextView label = (TextView) findViewById(R.id.gameStatus);
        TextView text = (TextView) findViewById(R.id.ghostText);
        int tag=0;
        if(input.length()>=4){
            if(sd.isWord(input))
                label.setText("valid word... USER WINS!");
                tag=1;
        }
        if(tag==0){
        String repre=sd.getAnyWordStartingWith(input);
        if(repre==null){
            label.setText("There is no such a word starting with "+input+"  User WINS!" );
        }else {
            label.setText("There is a a word starting with " + input + " that is " + repre + "  COMPUTER WINS!");

        }
        }
    }
}
