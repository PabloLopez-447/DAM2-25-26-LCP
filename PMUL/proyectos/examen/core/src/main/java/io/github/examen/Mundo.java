package io.github.examen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class Mundo {
    public static final int ANCHO = 640;
    public static final int ALTO = 480;
    public static final int OFFSET_BARRA = 60;
    public static final int VELOCIDAD = 200;
    public static final int TAMANO = 40;
    private static float TIEMPO_MIN_ENTRE_COCHES = 1f;
    private static float TIEMPO_MAX_ENTRE_COCHES = 2f;
    static float TIEMPO_INICIAL = 11f;
    private static float TIEMPO_SEMAFORO = 3f;
    private static float TIEMPO_BONUS = 5f;
    private static Random random = new Random();
    static Semaforo semaforo = new Semaforo(ANCHO - TAMANO, ALTO - TAMANO, TAMANO, TAMANO);
    static Array<Coche> coches = new Array<>();
    static Array<Muro> muros = new Array<>();
    public static float TiempoTotalDeJuego, stateTime, stateTimeProximoCoche, stateTimeProximoCambioSemaforo, tiempoRestante = TIEMPO_INICIAL;
    static int vehiculosEscapados;
    static boolean fin = false;
    static boolean pausa = false;


    public static void creaCoche() {
        crearCoche(0, random.nextFloat(ALTO - OFFSET_BARRA - TAMANO), TAMANO, TAMANO);
        stateTimeProximoCoche = stateTime + getRandomProximoObjeto();
    }

    public static void eliminar(Coche coche) {
        coches.removeValue(coche, true);
    }

    public static float getRandomProximoObjeto() {
        return TIEMPO_MIN_ENTRE_COCHES + random.nextFloat() * (TIEMPO_MAX_ENTRE_COCHES - TIEMPO_MIN_ENTRE_COCHES);
    }

    public static void camBiarSemaforo() {
        switch (semaforo.getEstado()) {
            case ROJO:
                semaforo.amarillo();
                break;
            case AMARILLO:
                semaforo.verde();
                break;
            case VERDE:
                semaforo.rojo();
                break;
        }
    }

    public static void tocar(float x, float y) {
        for (int i = 0; i < coches.size; i++) {
            if (coches.get(i).contiene(x, y)) {
                if (coches.get(i).getEstado() == Coche.Estado.ADELANTE) {
                    coches.get(i).parar();
                } else {
                    coches.get(i).avanzar();
                }
                return;
            }
        }
        crearMuro(x, y);
    }

    public static void crearMuro(float x, float y) {
        muros.add(new Muro(x, y, TAMANO, TAMANO));
    }

    public static void actualizar(float delta) {
        TiempoTotalDeJuego += delta;
        tiempoRestante -= delta;
        stateTime += delta;

        if (tiempoRestante <= 0) fin();

        if (stateTime > stateTimeProximoCoche) Mundo.creaCoche();
        if (stateTime > stateTimeProximoCambioSemaforo) {
            camBiarSemaforo();
            stateTimeProximoCambioSemaforo += TIEMPO_SEMAFORO;
        }


        for (int i = 0; i < coches.size; i++) {
            coches.get(i).actualizar(delta);
            if (coches.get(i).getX() > ANCHO - coches.get(i).getAncho()) {
                if (semaforo.getEstado() != Semaforo.EstadoSemaforo.ROJO) {
                    vehiculosEscapados++;
                    tiempoRestante += TIEMPO_BONUS;
                    eliminar(coches.get(i));
                    return;
                } else {
                    fin();
                    return;
                }
            }

            for (int j = 0; j < muros.size; j++) {
                if (Intersector.overlaps(coches.get(i).hitbox, muros.get(j).hitBox)) {
                    eliminar(coches.get(i));
                    return;
                }
            }

        }
    }

    public static void dibujar(SpriteBatch batch, BitmapFont font) {
        semaforo.dibujar(batch);
        font.draw(batch, "Tiempo: " + (int) tiempoRestante, 10, ALTO - 10);
        font.draw(batch, "Vehiculos escapados: " + vehiculosEscapados, ANCHO / 2F, ALTO - 10);

        for (int i = 0; i < coches.size; i++) {
            coches.get(i).dibujar(batch);
        }

        for (int i = 0; i < muros.size; i++) {
            muros.get(i).dibujar(batch);
        }
    }

    public static void crearCoche(float x, float y, float ancho, float alto) {
        coches.add(new Coche(x, y, ancho, alto, VELOCIDAD));
    }

    public static void fin() {
        fin = true;
    }

    public static void pausar() {
        pausa = !pausa;
    }

    public static void reset() {
        fin = false;
        semaforo.verde();
        coches.clear();
        muros.clear();
        vehiculosEscapados = 0;
        stateTime = 0;
        stateTimeProximoCambioSemaforo = 0;
        stateTimeProximoCoche = 0;
        tiempoRestante = TIEMPO_INICIAL;
    }

}
