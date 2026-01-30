package io.bloques;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;

public class Mundo {
    public static final int ANCHO = 640;
    public static final int ALTO = 480;
    public static final int NUM_BLOQUES_X_FILA = 5;

    static List<Bloque> bloques = new ArrayList<>();

    public static void dibujarBloques(ShapeRenderer sr) {
        for (Bloque bloque : bloques) {
            bloque.dibujar(sr);
        }
    }
    public static void actualizarBloques(float delta) {
        for (Bloque bloque : bloques) {
            bloque.actualizar(delta);
        }
    }

    public static void addFilaBloques(){
        for (int i = 0; i < NUM_BLOQUES_X_FILA; i++) {
            float x = i * (float) ANCHO / NUM_BLOQUES_X_FILA;
            bloques.add(new Bloque(x,-120, (float) ANCHO / NUM_BLOQUES_X_FILA, (float) ANCHO / NUM_BLOQUES_X_FILA, 70));
        }
    }

    public static boolean filaNueva(){
        return bloques.get(bloques.size()-1).getY() > bloques.get(bloques.size()-1).getAncho()-120;
    }


}
