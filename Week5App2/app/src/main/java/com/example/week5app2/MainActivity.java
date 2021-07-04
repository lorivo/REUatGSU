package com.example.week5app2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import java.util.Timer;
import java.util.TimerTask;


import androidx.appcompat.app.AppCompatActivity;

import com.example.week5app2.R;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    String filename;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //gets user input to a usable variable
        EditText textfile = findViewById((R.id.filen));

        Button start = findViewById(R.id.startbtn);



        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //makes variable into a string
                filename=textfile.getText().toString();

                Toast.makeText(getApplicationContext(),"Start button pressed",Toast.LENGTH_LONG).show();
                Intent startservice = new Intent(MainActivity.this, com.example.week5app2.DataService.class);

                //method used to allow filename to be used in the backend
                startservice.putExtra("FN",filename);
                startService(startservice);

            }
        });

        Button stop = findViewById(R.id.stopbtn);
        stop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Toast.makeText(getApplicationContext(),"Stop button pressed",Toast.LENGTH_LONG).show();
                stopService(new Intent(getApplicationContext(), DataService.class));

            }
        });

    }
}