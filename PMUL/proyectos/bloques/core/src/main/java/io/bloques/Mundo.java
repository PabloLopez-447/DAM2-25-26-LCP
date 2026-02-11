package io.bloques;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

public class Mundo {
    public static final int ANCHO = 640;
    public static final int ALTO = 800;
    public static final int POSICION_SPAWN_BLOQUES = -120;
    public static final int NUM_BLOQUES_X_FILA = 5;
    public static final int VELOCIDAD_BLOQUES = 50;
    static Array<Bloque> bloques = new Array<>();
    private static Bloque bloque1;
    public static boolean fin = false;



    public static void dibujarBloques(ShapeRenderer sr, SpriteBatch batch, BitmapFont fuente, float tiempo) {
        for (Bloque bloque : bloques) {
            bloque.dibujar(sr, batch, fuente);
        }
        fuente.draw(batch, "Tiempo: " + String.valueOf((int)tiempo), 0, ALTO);
    }

    public static void actualizarBloques(float delta) {
        for (Bloque bloque : bloques) {
            bloque.actualizar(delta);
            if (bloque.getY() + bloque.getAlto() >= ALTO) {
                fin();
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
        return bloques.get(bloques.size - 1).getY() > bloques.get(bloques.size - 1).getAncho() + POSICION_SPAWN_BLOQUES;
    }

    public static void fin(){
        fin = true;
    }

    public static void quitarBloque(Bloque bloque) {
        bloques.removeValue(bloque, true);
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
