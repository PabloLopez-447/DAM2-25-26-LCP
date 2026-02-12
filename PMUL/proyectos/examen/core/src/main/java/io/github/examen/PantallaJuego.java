package io.github.examen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class PantallaJuego extends Pantalla {

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 coordsMundo = juego.camara.unproject(new Vector3(screenX, screenY, 0));
        Mundo.tocar(coordsMundo.x, coordsMundo.y);
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.P:
                Mundo.pausar();
                return true;
            case Input.Keys.ESCAPE:
                Gdx.app.exit();
                return true;
        }
        return false;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.15f, 1f);
        Mundo.actualizar(delta);

        if (Mundo.pausa) {
            juego.irApantallaPausa();
        }

        if (Mundo.fin) {
            if (juego.prefs.getInteger("Record" + (int) Mundo.TIEMPO_INICIAL, -1) < Mundo.vehiculosEscapados) {
                juego.prefs.putInteger("Record" + (int) Mundo.TIEMPO_INICIAL, Mundo.vehiculosEscapados);
                juego.prefs.flush();
            }
            juego.irAPantallaInicio();
        }

        juego.batch.begin();
        Mundo.dibujar(juego.batch, juego.font);
        juego.batch.end();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void hide() {
        if (Mundo.fin) {
            Mundo.reset();
        }
    }
}
