package io.github.examen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Semaforo {
    public enum EstadoSemaforo {ROJO, AMARILLO, VERDE}

    float x, y;
    float ancho, alto;
    Texture rojo, amarillo, verde;
    EstadoSemaforo estado;

    public Semaforo(float x, float y, float ancho, float alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.rojo = new Texture("rojo.jpg");
        this.amarillo = new Texture("amarillo.jpg");
        this.verde = new Texture("verde.jpg");
        this.estado = EstadoSemaforo.VERDE;
    }

    public void dibujar(SpriteBatch batch) {
        switch (estado) {
            case ROJO:
                batch.draw(rojo, x, y, ancho, alto);
                break;
            case AMARILLO:
                batch.draw(amarillo, x, y, ancho, alto);
                break;
            case VERDE:
                batch.draw(verde, x, y, ancho, alto);
                break;
        }
    }

    public void rojo() {
        estado = EstadoSemaforo.ROJO;
    }

    public void amarillo() {
        estado = EstadoSemaforo.AMARILLO;
    }

    public void verde() {
        estado = EstadoSemaforo.VERDE;
    }

    public EstadoSemaforo getEstado() {
        return estado;
    }
}
