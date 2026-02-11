package io.github.dedo;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public abstract class Enemigo {
    float x, y, ancho, alto;
    float velocidad;
    int numero;
    Rectangle hBox;

    public Enemigo(float x, float y, float ancho, float alto, float velocidad, int numero){
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
        this.numero = numero;
        hBox = new Rectangle(x, y, ancho, alto);
    }

    public void actualizar(float delta){
        x -= velocidad * delta;
        hBox.setPosition(x, y);
    }
    public boolean isFueraDelMundo(){
        return x + ancho < 0;
    }

    public abstract void dibujar(ShapeRenderer sr, SpriteBatch batch, BitmapFont font);

}
