package io.github.bender;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer sr;
    OrthographicCamera camara = new OrthographicCamera();
//    private Texture image;

    @Override
    public void create() {
        batch = new SpriteBatch();
        sr = new ShapeRenderer();
        font = new BitmapFont();
        font.getData().setScale(2f);
        camara.setToOrtho(false, Mundo.ANCHO, Mundo.ALTO);
        camara.update();
//        image = new Texture("libgdx.png");
        batch.setProjectionMatrix(camara.combined); // SpriteBatch
        sr.setProjectionMatrix(camara.combined); // ShapeRenderer

        Gdx.input.setInputProcessor(new ProcesadorEntrada());
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        float delta = Gdx.graphics.getDeltaTime();
        Mundo.stateTime += delta;
        if (Mundo.stateTime > Mundo.stateTimeProximoObjeto) Mundo.creaObjeto();
        Mundo.actualizar(delta);

        sr.begin(ShapeRenderer.ShapeType.Line);
        batch.begin();
//        batch.draw(image, 140, 210);
        Mundo.dibujar(sr, font, batch);
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
