package io.bloques;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.ArrayList;
import java.util.List;

public class ProcesadorEntrada extends InputAdapter {
    private final OrthographicCamera camara;

    public ProcesadorEntrada(OrthographicCamera camara) {
        this.camara = camara;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 mundo = new Vector3(screenX, screenY, 0);
        camara.unproject(mundo);
        float wx = mundo.x;
        float wy = mundo.y;

        List<Bloque> toRemove = new ArrayList<>();

        for (Bloque b : Mundo.bloques) {
            if (b.contiene(wx, wy)) {
                if (!Mundo.haySeleccionado()) {
                    b.setSeleccionado(true);
                    Mundo.setBloque1(b);
                    break;
                }

                if (b != Mundo.getBloque1() && b.getNumero() == Mundo.getBloque1().getNumero()) {
                    toRemove.add(b);
                    toRemove.add(Mundo.getBloque1());
                    Mundo.getBloque1().setSeleccionado(false);
                    Mundo.setBloque1(null);
                    break;
                }
            }
        }

        for (Bloque r : toRemove) {
            Mundo.quitarBloque(r);
        }

        return true;
    }
}
