package io.bloques;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private ShapeRenderer sr;
    BitmapFont fuente;
    Random rnd = new Random();
    boolean spawn = true;
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
        Gdx.input.setInputProcessor(new ProcesadorEntrada(camara));
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        if (spawn) Mundo.addFilaBloques();
        spawn = false;

        if (Mundo.filaNueva()) {
            Mundo.addFilaBloques();
//            Mundo.bloques.remove(Mundo.bloques.get(rnd.nextInt(Mundo.bloques.size())));
        }

        Mundo.actualizarBloques(Gdx.graphics.getDeltaTime());
        sr.begin(ShapeRenderer.ShapeType.Line);
        batch.begin();
        Mundo.dibujarBloques(sr, batch, fuente);
        batch.end();
        sr.end();
    }

//    @Override
//    public void dispose() {
//        batch.dispose();
//        image.dispose();
//    }

    @Override
    public void resize(int width, int height) {
        camara.setToOrtho(false, Mundo.ANCHO, Mundo.ALTO);
        camara.update();
        batch.setProjectionMatrix(camara.combined); // SpriteBatch
        sr.setProjectionMatrix(camara.combined); // ShapeRenderer
    }
}
