package io.github.bender;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class ProcesadorEntrada extends InputAdapter {

    Personaje personaje;

    public ProcesadorEntrada(Personaje personaje) {
        this.personaje = personaje;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (personaje == null) return false;
        switch (keycode) {
            case Input.Keys.LEFT:
            case Input.Keys.RIGHT:
                personaje.estado = Personaje.Estado.PARADO;
                Gdx.app.debug("ProcesadorEntrada", "keyUp -> PARADO");
                break;
        }
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (personaje == null) return false;
        switch (keycode) {
            case Input.Keys.LEFT:
                personaje.estado = Personaje.Estado.IZQUIERDA;
                Gdx.app.debug("ProcesadorEntrada", "keyDown -> IZQUIERDA");
                break;

            case Input.Keys.RIGHT:
                personaje.estado = Personaje.Estado.DERECHA;
                Gdx.app.debug("ProcesadorEntrada", "keyDown -> DERECHA");
                break;
        }
        return true;
    }
}
