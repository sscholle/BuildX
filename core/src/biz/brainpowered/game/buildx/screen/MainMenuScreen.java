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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenuScreen implements Screen {
    SpriteBatch batch;
    Game g;
    int appWidth;
    int appHeight;
    OrthographicCamera camera;
    private BitmapFont bmFont;
    GameManager gameManager;

    public int state;
    public int MENU = 0;
    public int GAME = 1;

    Rectangle soundBounds;
    Rectangle playBounds;
    Rectangle exitBounds;
    Rectangle highscoresBounds;
    Rectangle helpBounds;
    Vector3 touchPoint;

    int screenWidth;
    int screenHeight;

    public MainMenuScreen(Game g){
        state = MENU;
        screenWidth = BuildX.V_WIDTH;
        screenHeight = BuildX.V_HEIGHT;

        soundBounds = new Rectangle(0, 0, 64, 64);
        playBounds = new Rectangle(screenWidth / 2 - 10, screenHeight - 25, 20, 7);
        exitBounds = new Rectangle(screenWidth / 2 - 8, screenHeight - 34, 16, 7);
        highscoresBounds = new Rectangle(160 - 150, 200 - 18, 300, 36);
        helpBounds = new Rectangle(160 - 150, 200 - 18 - 36, 300, 36);
        touchPoint = new Vector3();

        create();
        this.g=g;

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

        bmFont = Assets.font;

        gameManager = Core.gameManager;
        gameManager.init();
    }

    public void update () {
        if (state == MENU && Gdx.input.justTouched()) {
            camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (playBounds.contains(touchPoint.x, touchPoint.y)) {
                //Assets.playSound(Assets.clickSound);
                state = GAME;
                return;
            }
            if (exitBounds.contains(touchPoint.x, touchPoint.y)) {
                //Assets.playSound(Assets.clickSound);
                Gdx.app.exit();
                return;
            }
//            if (highscoresBounds.contains(touchPoint.x, touchPoint.y)) {
//                Assets.playSound(Assets.clickSound);
//                game.setScreen(new HighscoresScreen(game));
//                return;
//            }
//            if (helpBounds.contains(touchPoint.x, touchPoint.y)) {
//                Assets.playSound(Assets.clickSound);
//                game.setScreen(new HelpScreen(game));
//                return;
//            }
//            if (soundBounds.contains(touchPoint.x, touchPoint.y)) {
//                Assets.playSound(Assets.clickSound);
//                Settings.soundEnabled = !Settings.soundEnabled;
//                if (Settings.soundEnabled)
//                    Assets.music.play();
//                else
//                    Assets.music.pause();
//            }
        }
    }

    public void render (float delta) {
        update();
        draw(delta);
    }

    public void draw(float delta){
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        // batch.disableBlending();
        if (state == MENU){
            batch.draw(Assets.menu, 0, 0);

        }else if(state == GAME){
            gameManager.update(delta);
        }
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
        g.dispose();
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