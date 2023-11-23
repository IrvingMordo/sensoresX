package com.example.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private SensorEventListener accelerometerEventListener;
    private TextView tvValues;
    private long x, y, z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvValues = findViewById(R.id.tv_values);
        sensorManager = (SensorManager)  getSystemService(SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        accelerometerEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                x = Math.round(Math.toDegrees(sensorEvent.values[0])); //toDegrees convierte un ángulo de radianes a grados
                y = Math.round(Math.toDegrees(sensorEvent.values[1])); //math.round Redondea los numeros
                z = Math.round(Math.toDegrees(sensorEvent.values[2])); //.values indica la posición del ángulo.
                tvValues.setText(x+"::"+y+"::"+z);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        sensorManager.registerListener(accelerometerEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL); //Sirve para vincular un escuchador con un sensor

    }
}
