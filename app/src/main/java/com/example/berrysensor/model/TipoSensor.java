package com.example.berrysensor.model;

public class TipoSensor {
    private String nombre;

    public TipoSensor() {}

    public TipoSensor(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
