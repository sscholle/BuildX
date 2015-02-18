package biz.brainpowered.game.buildx.scene;

import biz.brainpowered.game.buildx.BuildX;
import biz.brainpowered.game.buildx.Core;
import biz.brainpowered.game.buildx.asset.Assets;
import biz.brainpowered.game.buildx.gameitem.GameItem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Vector;

/**
 * Created by Sebnic on 2015/02/14.
 */
public class DemoScene extends Scene {
    private SpriteBatch batch;
    // scene game items
    GameItem roof;
    GameItem ground;
    GameItem walls;
    private BitmapFont bmFont;

    public DemoScene(){
        super();
        this.batch = Core.batcher;
    }

    public void checkConnections(){

    }

    public void preConnect(Vector<GameItem> gameItems){

    }

    public void setup(){
        super.setup();

        ground = new GameItem(Assets.ground, 0, 0);
        walls = new GameItem(Assets.walls, 20, 4);
        roof = new GameItem(Assets.roof, 15, 40);

        bmFont = Assets.font;
    }


    public void update(float delta){
        //anything else - do stuff
        //currentGameItem.update(delta, batch);


        ground.update(delta, batch);
        walls.update(delta, batch);
        roof.update(delta, batch);

        String text = "BUILD HOUSE";
        float stringLength = bmFont.getBounds(text).width;
        bmFont.setColor(new Color(1f, 1f, 1f, 1.0f));
        bmFont.draw(batch, text, (BuildX.V_WIDTH / 2) - stringLength/2, (BuildX.V_WIDTH / 2));
    }

    public void win(){

    }

    public void loose(){

    }

    public boolean isRunning(){
        return isRunning;
    }

    public boolean isFinished(){
        return isFinished;
    }

    public boolean didWin(){
        return didWin;
    }
}
