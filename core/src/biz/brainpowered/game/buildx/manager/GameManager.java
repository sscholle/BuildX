package biz.brainpowered.game.buildx.manager;

import biz.brainpowered.game.buildx.scene.*;
import com.badlogic.gdx.Game;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Manages transition and scoring between Scenes (Games)
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

    private int lastGameIndex = -1;
    private int currentGameIndex = -1;

    public GameManager(int firstLevel){
        setLevel(firstLevel);
        wins = 0;
        losses = 0;
        totalTime = 0.0f;
    }

    /**
     * construct all scenes
     */
    public void init(){
        winScene = new WinScene();
        looseScene = new WinScene();

        gameScenes = new ArrayList<Scene>();
        gameScenes.add(new BuildHouseScene());
        gameScenes.add(new BuildRobotScene());
        gameScenes.add(new BuildDamScene());
        gameScenes.add(new HammerNailScene());
        gameScenes.add(new ChopWoodScene());
        currentScene = getNextGameScene();
        currentScene.setup();
    }

    /**
     * note: not currently used
     * @param level integer between 1 and 3
     */
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

    /**
     * randomly select and return a game Scene that isn't the same as last scene
     * note: requires a minimum of 2 gameScenes
     * @return Scene
     */
    public Scene getNextGameScene(){
        int nextGameIndex = 0;
        do{
            nextGameIndex = (int)(Math.random() * gameScenes.size());
            if(lastGameIndex != nextGameIndex) {
                currentGameIndex = nextGameIndex;
                lastGameIndex = currentGameIndex;
            }
        } while(lastGameIndex != currentGameIndex);
        return gameScenes.get(currentGameIndex);
    }

    /**
     * standard update function - manage scoring and scene transition
     * @param delta time delta
     */
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
