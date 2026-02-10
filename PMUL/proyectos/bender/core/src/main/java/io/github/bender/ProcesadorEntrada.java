package io.github.bender;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class ProcesadorEntrada extends InputAdapter {

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode) {
            case Input.Keys.D:
                if(Gdx.input.isKeyPressed(Input.Keys.A))
                    Mundo.personaje.izquierda();
                else
                    Mundo.personaje.parar(); break;
            case Input.Keys.A:
                if(Gdx.input.isKeyPressed(Input.Keys.D))
                    Mundo.personaje.derecha();
                else
                    Mundo.personaje.parar(); break;
            default: return false;
        }
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
                Mundo.personaje.estado = Personaje.Estado.IZQUIERDA;
                break;

            case Input.Keys.D:
                Mundo.personaje.estado = Personaje.Estado.DERECHA;
                break;
        }
        return true;
    }
}
