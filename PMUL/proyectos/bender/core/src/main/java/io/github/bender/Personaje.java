package io.github.bender;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Personaje {

    public enum Estado {IZQUIERDA, DERECHA, PARADO}

    float x;
    float y;
    float ancho;
    float alto;
    float velocidad;
    Estado estado = Estado.PARADO;
    Rectangle pibe;

    public Personaje(float x, float y, float ancho, float alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = 200;
        pibe = new Rectangle(x, y, ancho, alto);
    }

    public void actualizar(float delta) {
        switch (estado){
            case IZQUIERDA:
                x -= velocidad * delta;
                break;
            case DERECHA:
                x += velocidad * delta;
                break;
            case PARADO:
                // no hacer nada
                break;
        }
        pibe.setPosition(x, y);
    }

    public void dibujar(ShapeRenderer sr){
        sr.rect(x, y, ancho, alto);

    }

}
