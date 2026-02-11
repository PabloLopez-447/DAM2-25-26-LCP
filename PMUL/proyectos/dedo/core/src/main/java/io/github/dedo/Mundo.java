package io.github.dedo;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class Mundo {
    public static final int ANCHO = 640;
    public static final int ALTO = 480;
    public static final int VELOCIDAD = 200;
    public static final int BALAS_MAX = 7;
    public static final int VIDAS_INICIALES = 3;
    public static Random random = new Random();

    static Dedo dedo = new Dedo(0, 0, 40, 40, VELOCIDAD);
    static Array<Bala> balas = new Array<>();
    static Array<Enemigo> enemigos = new Array<>();
    static int vidas = VIDAS_INICIALES;
    static int puntos = 0;
    static boolean fin = false;
    public static float stateTime, stateTimeProximoEnemigo;

    public static void actualizar(float delta) {
        stateTime += delta;
        if (stateTime > stateTimeProximoEnemigo) {
            crearEnemigos();
        }
        dedo.actualizar(delta);
        for (int i = 0; i < balas.size; i++) {
            balas.get(i).actualizar(delta);
            if (balas.get(i).isFueraDelMundo()) {
                balas.removeIndex(i);
                break;
            }
            for (int j = 0; j < enemigos.size; j++) {
                if (Intersector.overlaps(balas.get(i).hBox, enemigos.get(j).hBox)) {
                    balas.removeIndex(i);
                    enemigos.get(j).numero--;
                    if (enemigos.get(j).numero == 0) {
                        enemigos.removeIndex(j);
                        puntos++;
                    }
                    break;
                }
            }
        }
        for (int i = 0; i < enemigos.size; i++) {
            enemigos.get(i).actualizar(delta);
            if (enemigos.get(i).isFueraDelMundo()) {
                enemigos.removeIndex(i);
                break;
            }
            if (Intersector.overlaps(dedo.hBox, enemigos.get(i).hBox)) {
                vidas--;
                enemigos.removeIndex(i);
                break;
            }
        }
        if (vidas <= 0) {
            fin();
        }
    }

    public static void dibujar(ShapeRenderer sr, SpriteBatch batch, BitmapFont font) {
        dedo.dibujar(sr);
        for (int i = 0; i < balas.size; i++) {
            balas.get(i).dibujar(sr);
        }
        for (int i = 0; i < enemigos.size; i++) {
            enemigos.get(i).dibujar(sr, batch, font);
        }
    }

    public static void disparar() {
        if (balas.size < BALAS_MAX) {
            balas.add(new Bala(dedo.x + dedo.ancho, dedo.y + dedo.alto / 2, 10, 10, VELOCIDAD));
        }
    }

    public static void crearEnemigos() {
        stateTime = 0;
        if (random.nextBoolean()) {
            enemigos.add(new Rectangulo(ANCHO, random.nextInt(ALTO), 40, 40, VELOCIDAD, random.nextInt(5) + 1 ));
        } else {
            enemigos.add(new Circulo(ANCHO, random.nextInt(ALTO), 20, 20, VELOCIDAD, random.nextInt(5) + 1));
        }
        stateTimeProximoEnemigo = random.nextInt(3) + 1;
    }

    public static void reset(){
        dedo.x = 0;
        dedo.y = 0;
        balas.clear();
        enemigos.clear();
        stateTime = 0;
        stateTimeProximoEnemigo = random.nextInt(3) + 1;
        vidas = VIDAS_INICIALES;
        puntos = 0;
        fin = false;
    }

    public static void fin(){
        fin = true;
    }
}
