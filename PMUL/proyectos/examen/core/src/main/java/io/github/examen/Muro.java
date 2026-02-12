package io.github.examen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Muro {
    float x, y;
    float ancho, alto;
    Rectangle hitBox;
    Texture image;

    public Muro(float x, float y, float ancho, float alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.hitBox = new Rectangle(x, y, ancho, alto);
        this.image = new Texture("obstaculo.png");
    }

    public void dibujar(SpriteBatch batch) {
        batch.draw(image, x, y, ancho, alto);
    }
}
