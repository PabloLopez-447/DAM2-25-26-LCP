package io.bloques;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class PantallaFin extends Pantalla {
    Texture fondo = new Texture("Fin.png");

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Gdx.app.exit();
        return true;
    }

    @Override
    public void render(float delta) {
        juego.batch.begin();
        juego.batch.draw(fondo, 0, 0, Mundo.ANCHO, Mundo.ALTO);
        juego.batch.end();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }
}
