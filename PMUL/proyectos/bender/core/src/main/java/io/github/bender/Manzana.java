package io.github.bender;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Manzana extends Objeto {
    public Manzana(float x, float ancho, float alto) {
        super(x, ancho, alto);
    }

    public void actualizar(float delta) {
        y -= velocidad * delta;
        hitbox.setPosition(x, y);
    }

    public void dibujar(ShapeRenderer sr) {
        sr.rect(x, y, ancho, alto, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN);
    }
}
