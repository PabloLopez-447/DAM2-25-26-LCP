package io.moverPersonaje;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class ProcesadorEntrada extends InputAdapter {

    private Personaje isaac;
    private int dirH = 0;
    private int dirV = 0;

    public ProcesadorEntrada(Personaje isaac) {
        this.isaac = isaac;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A) dirH--;
        if (keycode == Input.Keys.D) dirH++;
        if (keycode == Input.Keys.W) dirV++;
        if (keycode == Input.Keys.S) dirV--;
        aplicar();
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A) dirH++;
        if (keycode == Input.Keys.D) dirH--;
        if (keycode == Input.Keys.W) dirV--;
        if (keycode == Input.Keys.S) dirV++;
        aplicar();
        return true;
    }

    private void aplicar() {
        if (dirH < 0) isaac.estH = Personaje.estadoH.IZQUIERDA;
        else if (dirH > 0) isaac.estH = Personaje.estadoH.DERECHA;
        else isaac.estH = Personaje.estadoH.PARAO;

        if (dirV < 0) isaac.estV = Personaje.estadoV.ABAJO;
        else if (dirV > 0) isaac.estV = Personaje.estadoV.ARRIBA;
        else isaac.estV = Personaje.estadoV.PARAO;
    }
}
