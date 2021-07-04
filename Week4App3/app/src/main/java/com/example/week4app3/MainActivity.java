package com.example.week4app3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    Button str;
    TextView strmsg;
    SensorManager sm;
    Sensor acc;

    //
    String filename ="accvalues.txt";
    boolean flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        str = (Button)findViewById(R.id.sb);
        strmsg = (TextView)findViewById(R.id.tv1);
        str.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sm = (SensorManager)getSystemService(SENSOR_SERVICE);
                acc = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                if(acc!=null) {
                    sm.registerListener(MainActivity.this, acc, SensorManager.SENSOR_DELAY_NORMAL);
                    flag = true;
                }
                else
                {
                   // Toast.makeText(MainActivity.this,"missing sensor",Toast.LENGTH_LONG).show();

                }

            }
        });

    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(flag==true)
        {

            float val[] = sensorEvent.values;
            long time = sensorEvent.timestamp;
            String msg = "Time: "+String.valueOf(time)+"\n"+"Values: "+String.valueOf(val[0])+", "+String.valueOf(val[1])+", "+String.valueOf(val[2]);
            writemsg(msg);
           // Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();
            strmsg.setText(msg);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void writemsg(String msg)
    {
        try {
            File dir = new File(MainActivity.this.getFilesDir(),"week4");
            if(!dir.exists())
            {
                dir.mkdir();
            }
            File f1 = new File(dir,filename);
            FileWriter fw = new FileWriter(f1,true);
            fw.append(msg+"\n");
            fw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }



}