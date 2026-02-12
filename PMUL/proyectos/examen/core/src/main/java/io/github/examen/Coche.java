package io.github.examen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Coche {

    public enum Estado {ADELANTE, PARADO}

    float x, y;
    float ancho, alto;
    float velocidad;
    Texture image;
    Estado estado;
    Rectangle hitbox;

    public Coche(float x, float y, float ancho, float alto, float velocidad) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
        this.image = new Texture("moto.png");
        this.estado = Estado.ADELANTE;
        this.hitbox = new Rectangle(x, y, ancho, alto);
    }

    public void actualizar(float delta) {
        switch (estado) {
            case PARADO:
                return;
            case ADELANTE:
                x += velocidad * delta;
                break;
        }
        hitbox.setPosition(x, y);
    }

    public void dibujar(SpriteBatch batch) {
        batch.draw(image, x, y, ancho, alto);
    }

    public boolean contiene(float x, float y) {
        return hitbox.contains(x, y);
    }

    public void parar() {
        estado = Estado.PARADO;
    }

    public void avanzar() {
        estado = Estado.ADELANTE;
    }

    public float getX() {
        return x;
    }

    public float getAncho() {
        return ancho;
    }

    public Estado getEstado() {
        return estado;
    }
}
