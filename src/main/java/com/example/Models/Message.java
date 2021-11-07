package com.example.Models;


import java.time.LocalDateTime;
import java.util.Date;

public class Message {


    private LocalDateTime fechaGeneracion;

    private long idDispositivo;

    private float temperatura;

    private float humedad;

    public Message() {
    }

    public Message(LocalDateTime fechaGeneracion, long idDispositivo, float temperatura, float humedad) {
        this.fechaGeneracion = fechaGeneracion;
        this.idDispositivo = idDispositivo;
        this.temperatura = temperatura;
        this.humedad = humedad;
    }

    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDateTime fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public long getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(long idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float getHumedad() {
        return humedad;
    }

    public void setHumedad(float humedad) {
        this.humedad = humedad;
    }

    @Override
    public String toString() {
        return "Message{" +
                ", fechaGeneracion=" + fechaGeneracion +
                ", idDispositivo=" + idDispositivo +
                ", temperatura=" + temperatura +
                ", humedad=" + humedad +
                '}';
    }
}
