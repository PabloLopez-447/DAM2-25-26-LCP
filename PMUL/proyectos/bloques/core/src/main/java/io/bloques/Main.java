package io.bloques;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends Game {
    public SpriteBatch batch;
    public ShapeRenderer sr;
    BitmapFont fuente;
    Random rnd = new Random();
    OrthographicCamera camara = new OrthographicCamera();

    @Override
    public void create() {
        batch = new SpriteBatch();
        sr = new ShapeRenderer();
        fuente = new BitmapFont();
        camara.setToOrtho(false, Mundo.ANCHO, Mundo.ALTO);
        camara.update();
        batch.setProjectionMatrix(camara.combined); // SpriteBatch
        sr.setProjectionMatrix(camara.combined); // ShapeRenderer
        Pantalla.setJuego(this);
        irAPantallaInicio();
    }

    @Override
    public void resize(int width, int height) {
        camara.setToOrtho(false, Mundo.ANCHO, Mundo.ALTO);
        camara.update();
        batch.setProjectionMatrix(camara.combined); // SpriteBatch
        sr.setProjectionMatrix(camara.combined); // ShapeRenderer
    }

    public void irAPantallaInicio() { setScreen(new PantallaInicio()); }
    public void irAPantallaJuego() { setScreen(new PantallaJuego());}
    public void irAPantallaFin() { setScreen(new PantallaFin());}
    }
