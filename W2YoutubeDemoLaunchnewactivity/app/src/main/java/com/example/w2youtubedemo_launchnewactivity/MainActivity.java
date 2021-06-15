package com.example.w2youtubedemo_launchnewactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

        //launch new activity
        public void launchApp(View par1){
            Intent i = new Intent( this, SettingActivity.class);
            startActivity(i);

    }
}