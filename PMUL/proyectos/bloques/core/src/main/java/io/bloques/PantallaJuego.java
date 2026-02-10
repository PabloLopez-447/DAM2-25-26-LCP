package io.bloques;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;


public class PantallaJuego extends Pantalla {
    boolean spawn = true;
    float tiempo = 0;

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 mundo = new Vector3(screenX, screenY, 0);
        juego.camara.unproject(mundo);
        float wx = mundo.x;
        float wy = mundo.y;

        for (Bloque b : Mundo.bloques) {
            if (b.contiene(wx, wy)) {
                if (!Mundo.haySeleccionado()) {
                    b.setSeleccionado(true);
                    Mundo.setBloque1(b);
                    break;
                }

                if (b != Mundo.getBloque1() && b.getNumero() == Mundo.getBloque1().getNumero()) {
                    Mundo.quitarBloque(b);
                    Mundo.quitarBloque(Mundo.getBloque1());
                    Mundo.getBloque1().setSeleccionado(false);
                    Mundo.setBloque1(null);
                } else {
                    Mundo.fin();
                }
                break;
            }
        }
        return true;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        if (spawn) Mundo.addFilaBloques();
        spawn = false;

        if (Mundo.filaNueva()) {
            Mundo.addFilaBloques();
//            Mundo.bloques.remove(Mundo.bloques.get(rnd.nextInt(Mundo.bloques.size())));
        }

        if (Mundo.fin) {
            juego.irAPantallaFin();
        }

        Mundo.actualizarBloques(delta);
        juego.sr.begin(ShapeRenderer.ShapeType.Line);
        juego.batch.begin();
        Mundo.dibujarBloques(juego.sr, juego.batch, juego.fuente, tiempo+=delta);
        juego.batch.end();
        juego.sr.end();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }
}
