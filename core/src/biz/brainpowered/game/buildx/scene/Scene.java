package biz.brainpowered.game.buildx.scene;

import biz.brainpowered.game.buildx.gameitem.GameItem;
import com.badlogic.gdx.Gdx;

import java.util.Vector;

/**
 * Created by Sebnic on 2015/02/14.
 */
public class Scene {
    protected Vector <GameItem> gameItems;
    protected GameItem currentGameItem;
    protected boolean isRunning = false;
    protected boolean isFinished = false;
    protected boolean didWin = false;

    public Scene(){
        isRunning = false;
        isFinished = false;
        didWin = false;
        System.out.println("scene constructed");
    }

    public void checkConnections(){}

    public void preConnect(Vector<GameItem> gameItems){}

    public void setup(){
        Gdx.input.setInputProcessor(currentGameItem);

        isRunning = true;
    }

    public void update(float delta){}

    public void win(){}

    public void loose(){}

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
