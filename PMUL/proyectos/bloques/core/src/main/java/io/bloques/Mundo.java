package io.bloques;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;

public class Mundo {
    public static final int ANCHO = 640;
    public static final int ALTO = 480;
    public static final int POSICION_SPAWN_BLOQUES = -120;
    public static final int NUM_BLOQUES_X_FILA = 5;
    public static final int VELOCIDAD_BLOQUES = 70;

    static List<Bloque> bloques = new ArrayList<>();
    private static Bloque bloque1;

    public static void dibujarBloques(ShapeRenderer sr, SpriteBatch batch, BitmapFont fuente) {
        for (Bloque bloque : bloques) {
            bloque.dibujar(sr, batch, fuente);
        }
    }

    public static void actualizarBloques(float delta) {
        for (Bloque bloque : bloques) {
            bloque.actualizar(delta);
            if (bloque.getY() + bloque.getAlto() >= ALTO) {
                System.out.println("Has perdido");
                Gdx.app.exit();
                return;
            }
        }
    }

    public static void addFilaBloques() {
        for (int i = 0; i < NUM_BLOQUES_X_FILA; i++) {
            float x = (i * (float) ANCHO / NUM_BLOQUES_X_FILA);
            bloques.add(new Bloque(x, POSICION_SPAWN_BLOQUES, (float) ANCHO / NUM_BLOQUES_X_FILA, (float) ANCHO / NUM_BLOQUES_X_FILA, VELOCIDAD_BLOQUES));
        }
    }

    public static boolean filaNueva() {
        return bloques.get(bloques.size() - 1).getY() > bloques.get(bloques.size() - 1).getAncho() + POSICION_SPAWN_BLOQUES;
    }

    public static void quitarBloque(Bloque bloque) {
        bloques.remove(bloque);
    }

    public static void setBloque1(Bloque bloque1) {
        Mundo.bloque1 = bloque1;
    }

    public static Bloque getBloque1() {
        return bloque1;
    }

    public static boolean haySeleccionado() {
        return bloque1 != null;
    }
}
