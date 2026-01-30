package io.bloques;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {
    //    private SpriteBatch batch;
    private ShapeRenderer sr;
    boolean spawn = true;
    OrthographicCamera camara = new OrthographicCamera();

    @Override
    public void create() {
//        batch = new SpriteBatch();
        sr = new ShapeRenderer();
        camara.setToOrtho(false, Mundo.ANCHO, Mundo.ALTO);
        camara.update();
//        batch.setProjectionMatrix(camara.combined); // SpriteBatch
        sr.setProjectionMatrix(camara.combined); // ShapeRenderer
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
//        batch.begin();
//        batch.draw(image, 140, 210);
//        batch.end();
        if (spawn) Mundo.addFilaBloques();
        spawn = false;

        if (Mundo.filaNueva()) {
            Mundo.addFilaBloques();
        }

        Mundo.actualizarBloques(Gdx.graphics.getDeltaTime());
        sr.begin(ShapeRenderer.ShapeType.Filled);
        Mundo.dibujarBloques(sr);
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
//        batch.setProjectionMatrix(camara.combined); // SpriteBatch
        sr.setProjectionMatrix(camara.combined); // ShapeRenderer
    }
}
