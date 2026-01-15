package io.moverPersonaje;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Personaje {
    public enum estadoH {PARAO, IZQUIERDA, DERECHA}
    public enum estadoV {PARAO, ARRIBA, ABAJO}

    float x, y;
    float velH;
    float velV;
    estadoH estH = estadoH.PARAO;
    estadoV estV = estadoV.PARAO;

    private Texture image;

    public Personaje(float x, float y, float velH, float velV) {
        this.x = x;
        this.y = y;
        this.velH = velH;
        this.velV = velV;
        this.image = new Texture("isaacLeft.jpg");
    }

    public void dibujar(SpriteBatch batch) {

        if (estH == estadoH.IZQUIERDA) {
            image = new Texture("isaacLeft.jpg");
        }
        if (estH == estadoH.DERECHA) {
            image = new Texture("isaacRight.jpg");
        }

        batch.draw(image, x, y);

    }

    public void actualizar(float delta) {
        switch (estH) {
            case DERECHA:
                setVelH(200f);
                break;

            case IZQUIERDA:
                setVelH(-200f);
                break;
            case PARAO:
                setVelH(0f);
                break;
        }
        x += velH * delta;

        if (x < 0) {
            x = 0;
            estH = estadoH.PARAO;
        }

        float maxX = Mundo.ANCHO - image.getWidth();
        if (x > maxX) {
            x = maxX;
            estH = estadoH.PARAO;
        }

        switch (estV) {
            case ARRIBA:
                setVelV(200f);
                break;

            case ABAJO:
                setVelV(-200f);
                break;
            case PARAO:
                setVelV(0f);
                break;
        }
        y += velV * delta;

        if (y < 0) {
            y = 0;
            estV = estadoV.PARAO;
        }

        float maxXA = Mundo.ALTO - image.getHeight();
        if (y > maxXA) {
            y = maxXA;
            estV = estadoV.PARAO;
        }
    }

    public void setVelH(float velH) {
        this.velH = velH;
    }

    public float getVelH() {
        return velH;
    }

    public float getVelV() {
        return velV;
    }

    public void setVelV(float velV) {
        this.velV = velV;
    }
}
