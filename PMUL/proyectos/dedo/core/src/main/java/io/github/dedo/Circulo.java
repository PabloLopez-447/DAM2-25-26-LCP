package io.github.dedo;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Circulo extends Enemigo{
    public Circulo(float x, float y, float ancho, float alto, float velocidad, int numero) {
        super(x, y, ancho, alto, velocidad, numero);
    }

    @Override
    public void dibujar(ShapeRenderer sr, SpriteBatch batch, BitmapFont font) {
        sr.circle(x, y, ancho);
        font.draw(batch, Integer.toString(numero), x + (ancho / 2), y + (alto / 2));
    }
}
