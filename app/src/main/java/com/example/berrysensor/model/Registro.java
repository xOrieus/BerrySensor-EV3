package com.example.berrysensor.model;

import java.util.Date;

public class Registro {
    private Date instante;
    private float lectura;
    private Sensor sensor;

    public Registro() {}

    public Registro(Date instante, float lectura) {
        this.instante = instante;
        this.lectura = lectura;
    }

    public Date getInstante() {
        return instante;
    }

    public void setInstante(Date instante) {
        this.instante = instante;
    }

    public float getLectura() {
        return lectura;
    }

    public void setLectura(float lectura) {
        this.lectura = lectura;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}

