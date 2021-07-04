package com.example.week5app2;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.view.WindowManager;
import android.widget.Toast;
import java.util.Date;


import java.io.FileOutputStream;
import java.util.Arrays;

public class DataService extends Service implements SensorEventListener {
    private final String LOG_TAG ="MainActivity";
    private SensorManager sm;
    private Sensor m,g,a,sensor;

/*    Handler handler;
    int interval = 1000;
    boolean flag = false; */

    public float[] aval;
    String filename;
    WindowManager windowService;
    public FileOutputStream fos;
    long oldval = 0;

    //interval can be changed and the larger the number, the less data be written
    long interval = 300;

 /*   private final Runnable processSensors = new Runnable() {
        @Override
        public void run() {
            // Do work with the sensor values.

            flag = true;
            // The Runnable is posted to run again here:
           handler.postDelayed(this, interval);
        }
    }; */

    public DataService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    //create sensor manager and get accelerator sensor
    public void onCreate() {
        Toast.makeText(this, "Invoke background service onCreate method.", Toast.LENGTH_LONG).show();
        super.onCreate();
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        a = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }


    @Override
    //register accelerometer sensor
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent!=null&& intent.getExtras()!=null)
        {
            //filename from front end called into backend using "FN" code
            filename = intent.getStringExtra("FN");
            try{
                fos=openFileOutput(filename,MODE_APPEND);
                fos.write(("Start button pushed at: "+new Date()).getBytes());
                fos.write("\n".getBytes());
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
 //           handler.post(processSensors);

            Toast.makeText(this, "Invoke background service onStartCommand method.", Toast.LENGTH_LONG).show();
            sm.registerListener(this,a,SensorManager.SENSOR_DELAY_NORMAL);

        }
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    //unregister sensor and stop service
    public void onDestroy() {

        super.onDestroy();
        Toast.makeText(this, "Invoke background service onDestroy method.", Toast.LENGTH_LONG).show();
        try{
            fos=openFileOutput(filename,MODE_APPEND);
            fos.write(("Stop button pushed at: "+new Date()).getBytes());
            fos.write("\n".getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
 //       handler.removeCallbacks(processSensors);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        windowService = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int rotation = windowService.getDefaultDisplay().getRotation();
        long tslong =  System.currentTimeMillis();

        //takes time by millisecond
       // String ts = tslong.toString();

        sensor = event.sensor;

        int i = sensor.getType();

        if(i == Sensor.TYPE_ACCELEROMETER)
        {
            aval = event.values;}

        String x  = "Time: "+ tslong+", Accelerometer: "+Arrays.toString(aval)+", Orientation: "+rotation ;

        writemsg(tslong, x);

    }

    @Override

    //getting the accelerometer readings
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    //write reading to file
    public void writemsg(long tsval, String msg)
    {
        if ((tsval-oldval)==tsval||(tsval-oldval>=interval)){
            try{
                fos=openFileOutput(filename,MODE_APPEND);

                fos.write(msg.getBytes());
                fos.write("\n".getBytes());
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        oldval=tsval;
        }}}