package com.example.week5app1;


import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.Arrays;

public class DataService extends Service implements SensorEventListener {
    private final String LOG_TAG ="MainActivity";
    private SensorManager sm;
    private Sensor m,g,a,sensor;
    //public float[] mval;
    //public float[] gval;
    public float[] aval;
    //public float[] grval;

    public String fileName = "demo1.csv";
    public FileOutputStream fos;


    public DataService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        Toast.makeText(this, "Invoke background service onCreate method.", Toast.LENGTH_LONG).show();
        super.onCreate();
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        //m = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        //g = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        a = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //gr = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);



    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Invoke background service onStartCommand method.", Toast.LENGTH_LONG).show();
        //sm.registerListener(this,m,SensorManager.SENSOR_DELAY_NORMAL);
        //sm.registerListener(this,g,SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this,a,SensorManager.SENSOR_DELAY_NORMAL);
        //sm.registerListener(this,gr,SensorManager.SENSOR_DELAY_NORMAL);
        return super.onStartCommand(intent, flags, startId);



    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Invoke background service onDestroy method.", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Long tslong =  System.currentTimeMillis();
        String ts = tslong.toString();

        sensor = event.sensor;

        int i = sensor.getType();
       /* if(i==Sensor.TYPE_MAGNETIC_FIELD)
        {
            mval = event.values;}

        else if(i== Sensor.TYPE_GYROSCOPE)
        {
            gval = event.values;}*/


        if(i == Sensor.TYPE_ACCELEROMETER)
        {
            aval = event.values;}

        String x  = Arrays.toString(aval);

        //String x  = Arrays.toString(mval)+","+Arrays.toString(gval)+","+Arrays.toString(aval);
        writemsg(x);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public void writemsg(String msg)
    {
        try{
            fos=openFileOutput(fileName,MODE_APPEND);
            fos.write(msg.getBytes());
            fos.write("\n".getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
