package com.example.eva;

public class Cliente {
    String nombre, apellidos, nif;
    boolean VIP;
    double latitud, longitud;

    public Cliente(String nombre, String apellidos, String nif, boolean VIP, double latitud, double longitud) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.VIP = VIP;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setVIP(boolean VIP) {
        this.VIP = VIP;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNif() {
        return nif;
    }

    public boolean isVIP() {
        return VIP;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }
}
