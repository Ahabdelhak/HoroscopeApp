package com.example.webserv;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by ah_abdelhak on 11/2/2019.
 */

public class Splash_Screen extends AppCompatActivity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash_screen);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Splash_Screen.this,Sign_choosen_activity.class);
                Splash_Screen.this.startActivity(mainIntent);
                Splash_Screen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}