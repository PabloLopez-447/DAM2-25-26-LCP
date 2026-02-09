package io.github.bender;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private ShapeRenderer sr;
    OrthographicCamera camara = new OrthographicCamera();
    Personaje personaje;
//    private Texture image;

    @Override
    public void create() {
        batch = new SpriteBatch();
        sr = new ShapeRenderer();
        camara.setToOrtho(false, Mundo.ANCHO, Mundo.ALTO);
        camara.update();
//        image = new Texture("libgdx.png");
        batch.setProjectionMatrix(camara.combined); // SpriteBatch
        sr.setProjectionMatrix(camara.combined); // ShapeRenderer
        personaje = new Personaje(0, 0, 100, 100);
        Gdx.input.setInputProcessor(new ProcesadorEntrada(personaje));
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        personaje.actualizar(Gdx.graphics.getDeltaTime());
        sr.begin(ShapeRenderer.ShapeType.Line);
        batch.begin();
//        batch.draw(image, 140, 210);
        personaje.dibujar(sr);
        batch.end();
        sr.end();
    }

    @Override
    public void resize(int width, int height) {
        camara.setToOrtho(false, Mundo.ANCHO, Mundo.ALTO);
        camara.update();
        batch.setProjectionMatrix(camara.combined); // SpriteBatch
        sr.setProjectionMatrix(camara.combined); // ShapeRenderer
    }

    @Override
    public void dispose() {
        batch.dispose();
//        image.dispose();
    }


}
