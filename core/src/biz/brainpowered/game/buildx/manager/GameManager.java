package biz.brainpowered.game.buildx.manager;

import biz.brainpowered.game.buildx.scene.BuildHouseScene;
import biz.brainpowered.game.buildx.scene.BuildRobotScene;
import biz.brainpowered.game.buildx.scene.Scene;
import biz.brainpowered.game.buildx.scene.WinScene;
import com.badlogic.gdx.Game;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Sebnic on 2015/02/14.
 */
public class GameManager {
    private Game game;
    private ArrayList<Scene> gameScenes;
    private Scene currentScene;
    private WinScene winScene;
    private WinScene looseScene; // to create loose scene
    private int level;
    private int seconds;
    private int wins;
    private int losses;
    private float totalTime;

    public GameManager(int firstLevel){
        setLevel(firstLevel);
        wins = 0;
        losses = 0;
        totalTime = 0.0f;
    }

    public void init(){
        winScene = new WinScene();
        looseScene = new WinScene();

        gameScenes = new ArrayList<Scene>();
        gameScenes.add(new BuildHouseScene());
        gameScenes.add(new BuildRobotScene());
//        gameScenes.add(new BuildDamScene());
        currentScene = getNextGameScene();
        currentScene.setup();
    }

    public void setLevel(int level) {
        this.level = level;
        switch(level){
            case 1:
                seconds = 5;
                break;
            case 2:
                seconds = 4;
                break;
            case 3:
                seconds = 3;
                break;
        }
    }

    public Scene getNextGameScene(){

        return gameScenes.get((int)(Math.random() * gameScenes.size()));
    }

    public void update(float delta){

        if(currentScene.isRunning()) {
            currentScene.update(delta);

        }else if (currentScene.isFinished()){

            if(winScene == currentScene || looseScene == currentScene){
                //win scene is fin - next game scene
                currentScene = getNextGameScene();
                currentScene.setup();

            }else{
                // game scene
                if (currentScene.didWin()){
                    wins += 1;
                    // switch to win scene
                    winScene.setup();
                    winScene.setWins(wins);
                    winScene.setLosses(losses);
                    currentScene = winScene;

                }else{
                    losses += 1;
                    if (losses == 3){
                        //game over screen
                    }else{

                        // switch to loose scene
                        looseScene.setup();
                        looseScene.setWins(wins);
                        looseScene.setLosses(losses);
                        currentScene = looseScene;
                    }
                }
            }
        }
    }
}
