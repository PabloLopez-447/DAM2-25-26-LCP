package io.github.bender;

import com.badlogic.gdx.utils.Array;

import static com.badlogic.gdx.math.MathUtils.random;

import java.util.ArrayList;
import java.util.List;

public class Mundo {
    public static final int ANCHO = 800;
    public static final int ALTO = 600;
    private static float TIEMPO_MIN_ENTRE_OBJETOS = 0.3f;
    private static float TIEMPO_MAX_ENTRE_OBJETOS = 0.6f;
    static Personaje personaje = new Personaje(0, 0, 100, 100);
    static Array<Objeto> objetos = new Array<>();
    public static float TiempoTotalDeJuego, stateTime, stateTimeProximoObjeto;


    public static void creaObjeto() {
         objetos.add(new Objeto(random.nextInt(ANCHO), 20, 20));
    }

        public static float getRandomProximoObjeto () {
            return TIEMPO_MIN_ENTRE_OBJETOS + random.nextFloat() * (TIEMPO_MAX_ENTRE_OBJETOS - TIEMPO_MIN_ENTRE_OBJETOS);
        }


    }
