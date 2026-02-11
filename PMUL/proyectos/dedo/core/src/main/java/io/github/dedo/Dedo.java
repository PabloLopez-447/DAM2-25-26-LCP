package io.github.dedo;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Dedo {
    public enum Estado{ARRIBA, ABAJO, PARAD0}
    float x, y, ancho, alto;
    float velocidad;
    Estado estado;
    Rectangle hBox;

    public Dedo(float x, float y, float ancho, float alto, float velocidad) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
        estado = Estado.PARAD0;
        hBox = new Rectangle(x, y, ancho, alto);
    }

    public void actualizar(float delta) {
        switch (estado){
            case PARAD0:
                return;
            case ARRIBA:
                y += velocidad * delta;
                float yMax = Mundo.ALTO - alto;
                if (y > yMax) y = yMax;
                break;
            case ABAJO:
                y -= velocidad * delta;
                if (y < 0) y = 0;
                break;
        }
        hBox.setPosition(x, y);
    }

    public void dibujar(ShapeRenderer sr) {
        sr.rect(x, y, ancho, alto);
    }

    public void parar(){
        estado = Estado.PARAD0;
    }

    public void arriba(){
        estado = Estado.ARRIBA;
    }

    public void abajo(){
        estado = Estado.ABAJO;
    }
}
