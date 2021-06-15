package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //method to disable all buttons with onClick disable, par1 is parameter aka button selected
    public void disable(View par1){

        //method 3, which only changes second button
        findViewById(R.id.disbutton2).setEnabled(false);
        ((Button)findViewById(R.id.disbutton2)).setText("new new disabled");

        /*
        // method 2
        //the next 4 line of code only disabled 1 button bc only 1 button is called for
        View v = findViewById(R.id.disbutton);
        v.setEnabled(false);
        Button b = (Button) v;
        b.setText("new disabled");
        */

        /* method 1
        par1.setEnabled(false);
        //tag for logcat is "success" to see the message given
        Log.d("success","Button disabled");
        //creating button object
        Button disb = (Button) par1;
        //sets text of button to disabled when clicks
        disb.setText("Disabled");
        */
    }
}