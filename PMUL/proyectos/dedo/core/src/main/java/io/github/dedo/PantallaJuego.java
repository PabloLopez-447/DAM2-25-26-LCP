package io.github.dedo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class PantallaJuego extends Pantalla {

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.S:
                if (Gdx.input.isKeyPressed(Input.Keys.W)) Mundo.dedo.arriba();
                else Mundo.dedo.parar();
                break;
            case Input.Keys.W:
                if (Gdx.input.isKeyPressed(Input.Keys.S)) Mundo.dedo.abajo();
                else Mundo.dedo.parar();
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                Mundo.dedo.arriba();
                break;

            case Input.Keys.S:
                Mundo.dedo.abajo();
                break;
            case Input.Keys.SPACE:
                Mundo.disparar();
                break;
        }
        return true;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1f);
        Mundo.actualizar(delta);
        if (Mundo.fin) {
            if (Mundo.puntos > juego.prefs.getInteger("puntos", 0)) {
                juego.prefs.putInteger("puntos", Mundo.puntos);
                juego.prefs.flush();
            }
            juego.irAPantallaInicio();
        }
        juego.sr.begin(ShapeRenderer.ShapeType.Line);
        juego.batch.begin();
        Mundo.dibujar(juego.sr, juego.batch, juego.font);
        juego.batch.end();
        juego.sr.end();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void hide() {
        Mundo.reset();
    }
}
