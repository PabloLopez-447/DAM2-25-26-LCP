package io.github.examen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends Game {
    public SpriteBatch batch;
    public BitmapFont font;
    public ShapeRenderer sr;
    OrthographicCamera camara = new OrthographicCamera();
    Preferences prefs;
    public static Pantalla[] pantallas = {new PantallaInicio(), new PantallaJuego()};

    @Override
    public void create() {
        batch = new SpriteBatch();
        sr = new ShapeRenderer();
        font = new BitmapFont();
        font.getData().setScale(2f);
        camara.setToOrtho(false, Mundo.ANCHO, Mundo.ALTO);
        camara.update();
        batch.setProjectionMatrix(camara.combined);
        sr.setProjectionMatrix(camara.combined);
        prefs = Gdx.app.getPreferences("Examen");
        Pantalla.setJuego(this);
        setScreen(pantallas[0]);
    }

    @Override
    public void resize(int width, int height) {
        camara.setToOrtho(false, Mundo.ANCHO, Mundo.ALTO);
        camara.update();
        batch.setProjectionMatrix(camara.combined);
        sr.setProjectionMatrix(camara.combined);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public void irAPantallaInicio(){
        setScreen(pantallas[0]);
    }

    public void irAPantallaJuego(){
        setScreen(pantallas[1]);
    }
}

