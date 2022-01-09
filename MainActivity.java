package com.example.sensormagnetic;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";

    private SensorManager sensorManager;

    private Sensor accelerometer, nGyro, nMagno, nLight, nPressure, nTemp, nHumi;

    TextView xValue, yValue, zValue, xGyroValue, yGyroValue, zGyroValue, xMagnoValue, yMagnoValue, zMagnoValue, light, pressure, temp, humi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xValue = (TextView) findViewById(R.id.xValue);
        yValue = (TextView) findViewById(R.id.yValue);
        zValue = (TextView) findViewById(R.id.zValue);

        xGyroValue = (TextView) findViewById(R.id.xGyroValue);
        yGyroValue = (TextView) findViewById(R.id.yGyroValue);
        zGyroValue = (TextView) findViewById(R.id.zGyroValue);

        xMagnoValue = (TextView) findViewById(R.id.xMagnoValue);
        yMagnoValue = (TextView) findViewById(R.id.yMagnoValue);
        zMagnoValue = (TextView) findViewById(R.id.zMagnoValue);

        light = (TextView) findViewById(R.id.light);
        pressure = (TextView) findViewById(R.id.pressure);
        temp = (TextView) findViewById(R.id.temp);
        humi = (TextView) findViewById(R.id.humi);

        Log.d(TAG, "onCreate: Initializing Sensor Services");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(accelerometer != null){
            sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d( TAG, "onCreate: Registered Accelerometer Listener");
        }else {
            xValue.setText("Accelerometer Not Supported");
            yValue.setText("Accelerometer Not Supported");
            zValue.setText("Accelerometer Not Supported");
        }

        nGyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(nGyro != null){
            sensorManager.registerListener(MainActivity.this, nGyro, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d( TAG, "onCreate: Registered Gyro Listener");
        }else {
            xGyroValue.setText("Gyro Not Supported");
            yGyroValue.setText("Gyro Not Supported");
            zGyroValue.setText("Gyro Not Supported");
        }

        nMagno = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if(nMagno != null){
            sensorManager.registerListener(MainActivity.this, nMagno, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d( TAG, "onCreate: Registered Magno Listener");
        }else {
            xMagnoValue.setText("Magno Not Supported");
            yMagnoValue.setText("Magno Not Supported");
            zMagnoValue.setText("Magno Not Supported");
        }

        nLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(nLight != null){
            sensorManager.registerListener(MainActivity.this, nLight, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d( TAG, "onCreate: Registered Light Listener");
        }else {
            light.setText("Light Not Supported");
        }

        nPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if(nPressure != null){
            sensorManager.registerListener(MainActivity.this, nPressure, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d( TAG, "onCreate: Registered Pressure Listener");
        }else {
            pressure.setText("Pressure Not Supported");
        }

        nTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if(nTemp != null){
            sensorManager.registerListener(MainActivity.this, nTemp, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d( TAG, "onCreate: Registered Temp Listener");
        }else {
            temp.setText("Pressure Not Supported");
        }

        nHumi = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if(nHumi != null){
            sensorManager.registerListener(MainActivity.this, nHumi, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d( TAG, "onCreate: Registered Humi Listener");
        }else {
            humi.setText("Humi Not Supported");
        }

    }


    @Override
    public void onAccuracyChanged (Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged (SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            Log.d(TAG, "onSensorChanged: X: " + sensorEvent.values[0] + "Y: " + sensorEvent.values[1] + "Z: "+ sensorEvent.values[2]);

            xValue.setText("xValue: " + sensorEvent.values[0]);
            yValue.setText("yValue: " + sensorEvent.values[1]);
            zValue.setText("zValue: " + sensorEvent.values[2]);
        } else if(sensor.getType() == Sensor.TYPE_GYROSCOPE){
            xGyroValue.setText("xGValue: " + sensorEvent.values[0]);
            yGyroValue.setText("yGValue: " + sensorEvent.values[1]);
            zGyroValue.setText("zGValue: " + sensorEvent.values[2]);
        } else if(sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            xMagnoValue.setText("xValue: " + sensorEvent.values[0]);
            yMagnoValue.setText("yValue: " + sensorEvent.values[1]);
            zMagnoValue.setText("zValue: " + sensorEvent.values[2]);
        } else if(sensor.getType() == Sensor.TYPE_LIGHT){
            light.setText("Light: " + sensorEvent.values[0]);
        } else if(sensor.getType() == Sensor.TYPE_PRESSURE){
            pressure.setText("Pressure: " + sensorEvent.values[0]);
        } else if(sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
            temp.setText("Temp: " + sensorEvent.values[8]);
        } else if(sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY){
            humi.setText("Humidity: " + sensorEvent.values[8]);
        }
    }
}