package io.moverPersonaje;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MoverPersonaje extends ApplicationAdapter {
    private SpriteBatch batch;
    private Personaje isaac;
    OrthographicCamera camara = new OrthographicCamera();

    @Override
    public void create() {
        batch = new SpriteBatch();
        isaac = new Personaje(140, 210, 0, 0);

        Gdx.input.setInputProcessor(new ProcesadorEntrada(isaac));
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        float delta = Gdx.graphics.getDeltaTime();
        isaac.actualizar(delta);
        batch.begin();
        isaac.dibujar(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override public void resize(int width, int height) {
        camara.setToOrtho(false, Mundo.ANCHO,Mundo.ALTO);
        camara.update();
        batch.setProjectionMatrix(camara.combined); // SpriteBatch
        //sr.setProjectionMatrix(camara.combined); // ShapeRenderer
    }
}
