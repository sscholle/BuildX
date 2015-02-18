package biz.brainpowered.game.buildx.screen;

import biz.brainpowered.game.buildx.BuildX;
import biz.brainpowered.game.buildx.Core;
import biz.brainpowered.game.buildx.gameitem.GameItem;
import biz.brainpowered.game.buildx.asset.Assets;
import biz.brainpowered.game.buildx.manager.GameManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenuScreen implements Screen {
    Skin skin;
    Stage stage;
    SpriteBatch batch;

    Game g;

    int appWidth;
    int appHeight;
    OrthographicCamera camera;

    private TextButton playButton, hiscoreButton, exitButton;

    private BitmapFont bmFont;
    //private String fontText = "Splash Screen";
    Viewport viewport;

GameManager gameManager;

    public MainMenuScreen(Game g){
        create();
        this.g=g;
        //todo: dispose previous screen
        //previousScreen.dispose();

        appWidth = BuildX.V_WIDTH;
        appHeight = BuildX.V_HEIGHT;
    }

    public MainMenuScreen(){
        create();
    }

    public void create(){
        Gdx.app.log("Main Menu Screen", "Constructor called");
        batch = Core.batcher;

        camera = new OrthographicCamera( BuildX.V_WIDTH, BuildX.V_HEIGHT);
        camera.position.set(BuildX.V_WIDTH / 2, BuildX.V_HEIGHT / 2, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // menu graphic

        // hit detect on regions
            // start game loop, etc


        bmFont = Assets.font;

        gameManager = Core.gameManager;
        gameManager.init();

    }

    public void render (float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

       // batch.disableBlending();
        batch.begin();
        gameManager.update(delta);
        batch.end();

    }

    @Override
    public void resize (int width, int height) {
        Gdx.app.log("Main Menu Screen", "resize called");
        System.out.println("size: "+width+" "+height);
        //stage.getViewport().update(width, height);
        //stage.setViewport(width, height, false);
    }

    @Override
    public void dispose () {
        Gdx.app.log("Main Menu Screen", "dispose called");

    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        Gdx.app.log("Main Menu Screen", "Show called");

    }

    @Override
    public void hide() {
        // TODO: disable Inputs, etc here
        Gdx.app.log("Main Menu Screen", "Hide called");

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        Gdx.app.log("Main Menu Screen", "pause called");

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        // todo: re-init textures???
        Gdx.app.log("Main Menu Screen", "resume called");
    }
}