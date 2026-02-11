package io.github.dedo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.ScreenUtils;

public class PantallaInicio extends Pantalla {


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1f);
        juego.batch.begin();
        juego.font.draw(juego.batch, "Record: " + juego.prefs.getInteger("puntos", 0), Mundo.ANCHO / 2f, Mundo.ALTO / 2f);
        juego.batch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.J:
                juego.irAPantallaJuego();
                return true;
            case Input.Keys.ESCAPE:
                Gdx.app.exit();
                return true;
        }
        return false;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }
}
