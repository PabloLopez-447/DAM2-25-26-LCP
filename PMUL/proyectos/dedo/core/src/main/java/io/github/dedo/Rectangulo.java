package io.github.dedo;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Rectangulo extends Enemigo{
    public Rectangulo(float x, float y, float ancho, float alto, float velocidad, int numero) {
        super(x, y, ancho, alto, velocidad, numero);
    }

    @Override
    public void dibujar(ShapeRenderer sr, SpriteBatch batch, BitmapFont font) {
        sr.rect(x, y, ancho, alto);
        font.draw(batch, Integer.toString(numero), x + ancho / 2, y + alto / 2);
    }
}
