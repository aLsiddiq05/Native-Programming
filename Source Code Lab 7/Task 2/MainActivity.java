package com.example.specificsensor;

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
    private Sensor lightSensor, proximitySensor, humiditySensor;
    private TextView lightTextView, proximityTextView, humidityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lightTextView = findViewById(R.id.lightTextView);
        proximityTextView = findViewById(R.id.proximityTextView);
        humidityTextView = findViewById(R.id.humidityTextView);

        senseMan = (SensorManager) getSystemService(SENSOR_SERVICE);

        lightSensor = senseMan.getDefaultSensor(Sensor.TYPE_LIGHT);
        proximitySensor = senseMan.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        humiditySensor = senseMan.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        registerSensor(lightSensor, "Light sensor found", "Light sensor not found");
        registerSensor(proximitySensor, "Proximity sensor found", "Proximity sensor not found");
        registerSensor(humiditySensor, "Humidity sensor found", "Humidity sensor not found");
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
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            lightTextView.setText(String.format("Light level: %s", event.values[0]));
        } else if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            proximityTextView.setText(String.format("Proximity: %s", event.values[0]));
        } else if (event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            humidityTextView.setText(String.format("Humidity: %s", event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes
    }

    @Override
    protected void onResume() {
        super.onResume();
        senseMan.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        senseMan.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        senseMan.registerListener(this, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        senseMan.unregisterListener(this);
    }
}


