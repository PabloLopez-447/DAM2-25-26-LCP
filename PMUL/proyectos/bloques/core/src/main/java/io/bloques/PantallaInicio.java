package io.bloques;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class PantallaInicio extends Pantalla {

    Texture fondo = new Texture("Fondo inicio.png");
    Texture boton = new Texture("Boton jugar.png");

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // convertir coordenada Y de pantalla (origen arriba) a Y del mundo (origen abajo)
        float worldY = Mundo.ALTO - screenY;

        float bx = Mundo.ANCHO / 2f - boton.getWidth() / 2f;
        float by = Mundo.ALTO / 2f - boton.getHeight() / 2f - 200f;
        Rectangle botonJugar = new Rectangle(bx, by, boton.getWidth(), boton.getHeight());

        if (botonJugar.contains(screenX, worldY)) {
            juego.irAPantallaJuego();
        }
        return true;
    }

    @Override
    public void render(float delta) {
        juego.batch.begin();
        juego.batch.draw(fondo, 0, 0, Mundo.ANCHO, Mundo.ALTO);
        juego.batch.draw(boton, Mundo.ANCHO / 2f - boton.getWidth() / 2f, Mundo.ALTO / 2f - boton.getHeight() / 2f - 200f);
        juego.batch.end();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }
}
