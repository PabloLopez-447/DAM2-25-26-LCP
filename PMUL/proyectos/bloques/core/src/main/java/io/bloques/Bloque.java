package io.bloques;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Bloque {
    final static Random rnd = new Random();
    private float x, y;
    private float ancho, alto;
    private float velocidad;
    private int numero;
    private Rectangle bloque;

    public Bloque(float x, float y, float ancho, float alto, float velocidad) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
        this.numero = rnd.nextInt(5);
        this.bloque = new Rectangle(x, y, ancho, alto);
    }
    public float getX() {return x;}
    public void setX(float x) {this.x = x;}
    public float getY() {return y;}
    public void setY(float y) {this.y = y;}
    public float getAncho() {return ancho;}

    public void dibujar(ShapeRenderer sr, SpriteBatch batch, BitmapFont fuente){
        sr.setColor(Color.RED);
        sr.rect(x + 0.5f, y + 0.5f, ancho - 1f, alto - 1f);
        fuente.draw(batch, String.valueOf(numero), x + ancho / 2, y + alto / 2);
    }


    public void actualizar(float delta){
        y += velocidad*delta;
    }

}
