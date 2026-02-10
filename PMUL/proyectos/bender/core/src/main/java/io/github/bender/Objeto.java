package io.github.bender;

import com.badlogic.gdx.math.Rectangle;

public class Objeto {
    float x;
    float y;
    float ancho;
    float alto;
    float velocidad;
    Rectangle hitbox;

    public Objeto(float x, float ancho, float alto){
        this.x = x;
        this.y = Mundo.ALTO;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = 200;
        hitbox = new Rectangle(x, y, ancho, alto);
    }

}
