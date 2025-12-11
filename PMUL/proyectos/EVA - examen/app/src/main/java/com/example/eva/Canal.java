package com.example.eva;

public class Canal {
    int codCanal;
    String nombre;
    String visibilidad;
    int precioBase;
    int precioPorVista;

    public Canal(int codCanal, String nombre, String visibilidad, int precioBase, int precioPorVista) {
        this.codCanal = codCanal;
        this.nombre = nombre;
        this.visibilidad = visibilidad;
        this.precioBase = precioBase;
        this.precioPorVista = precioPorVista;
    }

    public Canal(int codCanal, String nombre, String visibilidad) {
        this.codCanal = codCanal;
        this.nombre = nombre;
        this.visibilidad = visibilidad;
    }

    public int getCodCanal() {
        return codCanal;
    }

    public void setCodCanal(int codCanal) {
        this.codCanal = codCanal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(String visibilidad) {
        this.visibilidad = visibilidad;
    }

    @Override
    public String toString() {
        return nombre + " (" + visibilidad + ")";
    }
}
