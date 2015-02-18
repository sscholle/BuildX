package biz.brainpowered.game.buildx;

import biz.brainpowered.game.buildx.asset.AssetLoader;
import biz.brainpowered.game.buildx.asset.Assets;
import biz.brainpowered.game.buildx.screen.MainMenuScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BuildX extends Game {
    // used by all screens - engine/manager composition
    private Core core;
    private AssetLoader loader;
    public static SpriteBatch batch;

    // define global static dimensions
    public static int V_WIDTH = 64;
    public static int V_HEIGHT = 64;

    static boolean isLoading = false;
    static boolean isRunning = false;

    @Override
    public void create () {
        isLoading = true;
        core.init();
        loader = core.assetLoader;
        batch = core.batcher;
        Settings.load();

        // todo: asset mapping/loading
        Assets.init();
        Assets.loader = loader;
        Assets.loadAssets();
    }

    @Override
    public void render() {
        if(isLoading && Assets.isInited){

            GL20 gl = Gdx.gl;
            gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            if (!Assets.isLoaded()) {
                loader.render();
            }else {
                isLoading = false;
                isRunning = true;
            }

        }else if(isRunning){
            if (getScreen() == null){
                System.out.println("set Main menu");
                //setScreen(new SplashScreen(this));
                setScreen(new MainMenuScreen(this));
            }
        }
        super.render();
    }
}
