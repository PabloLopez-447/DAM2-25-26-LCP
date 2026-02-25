package UD2.Sockets.Ejercicios.examen;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombre;
    private boolean online;
    private int puntuacion;
    private List<Pregunta> preguntas = new ArrayList<>();
    public Usuario(String nombre) {
        this.nombre = nombre;
        login();
    }
    public String getNombre() { return nombre; }
    public boolean estaONLINE () { return online; };
    public void login() {
        online=true;
    }
    public void logout() {
        online=false;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void aumentarPuntuacion(){
        puntuacion++;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", puntuacion=" + puntuacion +
                '}';
    }
}
