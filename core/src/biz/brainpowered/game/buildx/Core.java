package biz.brainpowered.game.buildx;

import biz.brainpowered.game.buildx.asset.AssetLoader;
import biz.brainpowered.game.buildx.manager.GameManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Sebnic on 2015/01/02.
 */
public class Core {
    public static AssetLoader assetLoader;
    public static SpriteBatch batcher;
    public static TimelineManager timelineManager;
    public static GameManager gameManager;

    public static void init () {
        assetLoader = new AssetLoader();
        batcher = new SpriteBatch();
        timelineManager = new TimelineManager();
        gameManager = new GameManager(1); // todo: param?
    }
}