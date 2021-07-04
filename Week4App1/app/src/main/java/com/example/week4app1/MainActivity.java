package com.example.week4app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = (Button) findViewById(R.id.button);
        TextView tv1 = (TextView) findViewById(R.id.tv1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
                Sensor acc = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                if (acc!=null)
                {
                    tv1.setText("present");
                }
                else{
                    tv1.setText("absent");
                }
            }
        });
    }
}