package com.example.berrysensor.datos;

import androidx.appcompat.app.AppCompatActivity;
import com.example.berrysensor.model.Sensor;
import com.example.berrysensor.model.TipoSensor;
import com.example.berrysensor.model.Ubicacion;

import java.util.ArrayList;
import java.util.List;

public class Repositorio extends AppCompatActivity {
    private static Repositorio instance = null;
    public List<Sensor> sensores;
    public List<TipoSensor> tipos;
    public List<Ubicacion> ubicaciones;

    protected Repositorio() {
        sensores = new ArrayList<>();
        tipos = new ArrayList<>();
        ubicaciones = new ArrayList<>();

        tipos.add(new TipoSensor("Temperatura"));
        tipos.add(new TipoSensor("Humedad"));

        ubicaciones.add(new Ubicacion("Invernadero Norte","Invernadero de la zona Norte"));
        ubicaciones.add(new Ubicacion("Invernadero Sur","Invernadero de la zona Sur"));
        ubicaciones.add(new Ubicacion("Invernadero Flores","Invernadero de Flores"));

        sensores.add(new Sensor("Sensor Temp01","Sensor de Temperatura",21.4f,tipos.get(0), ubicaciones.get(0)));
        sensores.add(new Sensor("Sensor Hum01","Sensor de Humedad",62.5f,tipos.get(1),ubicaciones.get(0)));
    }

    public static synchronized Repositorio getInstance() {
        if (instance == null) {
            instance = new Repositorio();
        }
        return instance;
    }
}
