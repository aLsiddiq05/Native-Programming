package com.example.xyzsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager senseMan;
    private Sensor accelerometerSensor, gyroscopeSensor, magnetometerSensor;
    private TextView accelerometerTextView, gyroscopeTextView, magnetometerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accelerometerTextView = findViewById(R.id.accelerometerTextView);
        gyroscopeTextView = findViewById(R.id.gyroscopeTextView);
        magnetometerTextView = findViewById(R.id.magnetometerTextView);

        senseMan = (SensorManager) getSystemService(SENSOR_SERVICE);

        accelerometerSensor = senseMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscopeSensor = senseMan.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        magnetometerSensor = senseMan.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        registerSensor(accelerometerSensor, "Accelerometer sensor found", "Accelerometer sensor not found");
        registerSensor(gyroscopeSensor, "Gyroscope sensor found", "Gyroscope sensor not found");
        registerSensor(magnetometerSensor, "Magnetometer sensor found", "Magnetometer sensor not found");
    }

    private void registerSensor(Sensor sensor, String successMessage, String errorMessage) {
        if (sensor != null) {
            senseMan.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            Toast.makeText(this, successMessage, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelerometerTextView.setText(String.format("Accelerometer\nX: %s\nY: %s\nZ: %s", event.values[0], event.values[1], event.values[2]));
        } else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            gyroscopeTextView.setText(String.format("Gyroscope\nX: %s\nY: %s\nZ: %s", event.values[0], event.values[1], event.values[2]));
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            magnetometerTextView.setText(String.format("Magnetometer\nX: %s\nY: %s\nZ: %s", event.values[0], event.values[1], event.values[2]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes
    }

    @Override
    protected void onResume() {
        super.onResume();
        senseMan.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        senseMan.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        senseMan.registerListener(this, magnetometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        senseMan.unregisterListener(this);
    }
}
