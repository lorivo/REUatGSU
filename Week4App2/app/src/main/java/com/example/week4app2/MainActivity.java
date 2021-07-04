package com.example.week4app2;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.SensorEvent;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    Sensor acc;
    SensorManager sm;
    TextView tv;
    boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sm = (SensorManager)getSystemService(SENSOR_SERVICE);
                acc = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                tv = (TextView)findViewById(R.id.tv1);
                if(acc==null)
                {
                    tv.setText("error in sensor");

                }
                else
                {
                    sm.registerListener(MainActivity.this, acc, SensorManager.SENSOR_DELAY_NORMAL);
                    flag = true;
                }
            }
        });


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (flag ==true)
        {
            float vals[] = sensorEvent.values;
            float x = vals[0];
            float y = vals[1];
            float z = vals[2];
            String msg = "Time: "+String.valueOf(sensorEvent.timestamp)+"\n"+"Values: "+String.valueOf(x)+", "+String.valueOf(y)+", "+String.valueOf(z);
            //Toast.makeText(MainActivity.this,"flag:true",Toast.LENGTH_LONG).show();
            tv.setText(msg);}
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    @Override
    protected void onStop()

    {
        super.onStop();
        sm.unregisterListener(this);
    }
}