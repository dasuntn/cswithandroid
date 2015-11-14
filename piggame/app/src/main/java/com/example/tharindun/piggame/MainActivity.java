package com.example.tharindun.piggame;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
int newn,utot,ctot;
    int u=1;
    int c=0;
ImageView imageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageview= (ImageView) findViewById(R.id.imageView);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

    public void onroll(View view) {


        Random r = new Random();
        newn = r.nextInt(6)+1;

        Drawable drawable=getImage(newn);
        imageview.setImageDrawable(drawable);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                imageview.setImageDrawable(getResources().getDrawable(R.drawable.dice0));
            }
        }, 10000);


        if(utot>=20){
            Toast.makeText(MainActivity.this,"Winner is User 1",Toast.LENGTH_LONG).show();
        }
        if(ctot>=20){
            Toast.makeText(MainActivity.this,"winner is User 2",Toast.LENGTH_LONG).show();
        }

    }

    private Drawable getImage(int newn) {
        switch (newn){
            case 1:

            if(u==1){
                u=0;
                utot=0;
            }else{
                u=1;
                ctot=0;
            }
                Toast.makeText(MainActivity.this,"player change...",Toast.LENGTH_LONG).show();
            return getResources().getDrawable(R.drawable.dice1);

            case 2:

            if(u==1) utot +=newn;
            else {
                ctot +=newn;

            }return getResources().getDrawable(R.drawable.dice2);

            case 3:
                if(u==1) utot +=newn;
                else {
                    ctot +=newn;
                }
                return getResources().getDrawable(R.drawable.dice3);


            case 4:
                if(u==1) utot +=newn;
                else {
                    ctot +=newn;
                }
                return getResources().getDrawable(R.drawable.dice4);

            case 5:
                if(u==1) utot +=newn;
                else {
                    ctot +=newn;
                }
                return getResources().getDrawable(R.drawable.dice5);

            case 6:
                if(u==1) utot +=newn;
                else {
                    ctot +=newn;
                }
                return getResources().getDrawable(R.drawable.dice6);


        }
        return null;
    }


    public void onreset(View view) {
        utot=0;
        ctot=0;
        Toast.makeText(MainActivity.this,"Game reseted...",Toast.LENGTH_LONG).show();
    }

    public void onhold(View view) {

        if(u==1){
            u=0;
            Toast.makeText(MainActivity.this,"User 1 hold...",Toast.LENGTH_LONG).show();

        }else{
            u=1;
            Toast.makeText(MainActivity.this,"User 2 hold...",Toast.LENGTH_LONG).show();
        }
    }
}
