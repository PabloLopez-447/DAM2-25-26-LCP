package io.github.examen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.ScreenUtils;

public class PantallaPausa extends Pantalla {


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1f);
        juego.batch.begin();
        juego.font.draw(juego.batch, "PAUSA", Mundo.ANCHO / 2f, Mundo.ALTO / 2f);
        juego.font.draw(juego.batch, "Pulsa J para reanudar", Mundo.ANCHO / 2f, Mundo.ALTO / 2f - 100);
        juego.font.draw(juego.batch, "Pulsa Esc para salir", Mundo.ANCHO / 2f, Mundo.ALTO / 2f - 200);
        juego.batch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.J:
                Mundo.pausar();
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
