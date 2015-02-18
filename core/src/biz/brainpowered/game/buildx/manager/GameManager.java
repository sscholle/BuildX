package biz.brainpowered.game.buildx.manager;

import biz.brainpowered.game.buildx.scene.DemoScene;
import biz.brainpowered.game.buildx.scene.Scene;
import com.badlogic.gdx.Game;

import java.util.Vector;

/**
 * Created by Sebnic on 2015/02/14.
 */
public class GameManager {
    private Game game;
    private Vector<Scene> scenes;
    private Scene currentScene;
    private int level;
    private int seconds;

    public GameManager(){
        scenes = new Vector<Scene>();
    }

    public void init(){
        scenes.add(new DemoScene());

        currentScene = scenes.firstElement();
        currentScene.setup();

    }

    public void update(float delta){
        if(currentScene.isRunning()) {
            currentScene.update(delta);
        }else if (currentScene.isFinished()){
            if (currentScene.didWin()){
                // switch to win scene
            }else{
                // switch to loose scene
            }
        }
    }
}
