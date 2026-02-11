package io.github.dedo;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Bala {
    float x, y, ancho, alto;
    float velocidad;
    Rectangle hBox;

    public Bala(float x, float y, float ancho, float alto, float velocidad) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
        hBox = new Rectangle(x, y, ancho, alto);
    }

    public void actualizar(float delta) {
        x += velocidad * delta;
        hBox.setPosition(x, y);
    }

    public void dibujar(ShapeRenderer sr) {
        sr.rect(x, y, ancho, alto);
    }

    public boolean isFueraDelMundo(){
        return x > Mundo.ANCHO;
    }
}
