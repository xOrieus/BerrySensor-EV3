package com.example.berrysensor.model;

public class Sensor {
    private String nombre;
    private String descripcion;
    private float ideal;

    private TipoSensor tipoSensor;
    private Ubicacion ubicacion;

    public Sensor() {}


public Sensor(String nombre, String descripcion, float ideal, TipoSensor tipoSensor, Ubicacion ubicacion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ideal = ideal;
        this.tipoSensor = tipoSensor;
        this.ubicacion = ubicacion;
}

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getIdeal() {
        return ideal;
    }

    public void setIdeal(float ideal) {
        this.ideal = ideal;
    }

    public TipoSensor getTipoSensor() {
        return tipoSensor;
    }

    public void setTipoSensor(TipoSensor tipoSensor) {
        this.tipoSensor = tipoSensor;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}