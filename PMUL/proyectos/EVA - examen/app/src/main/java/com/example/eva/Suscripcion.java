package com.example.eva;

public class Suscripcion {
    int codSuscripcion;
    int codBar;
    int codCanal;
    String nombreCanal;
    int nVisualizaciones;
    int precioBase;
    int precioPorVisualizacion;

    public Suscripcion(int codSuscripcion, int codBar, int codCanal, String nombreCanal, int precioBase, int precioPorVisualizacion, int nVisualizaciones) {
        this.codSuscripcion = codSuscripcion;
        this.codBar = codBar;
        this.codCanal = codCanal;
        this.nombreCanal = nombreCanal;
        this.precioBase = precioBase;
        this.precioPorVisualizacion = precioPorVisualizacion;
        this.nVisualizaciones = nVisualizaciones;
    }

    @Override
    public String toString() {
        return nombreCanal + ": " + nVisualizaciones + " visualizaciones, precio: " + (precioBase + precioPorVisualizacion * nVisualizaciones) + "€";
    }
}
