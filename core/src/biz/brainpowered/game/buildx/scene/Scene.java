package biz.brainpowered.game.buildx.scene;

import biz.brainpowered.game.buildx.BuildX;
import biz.brainpowered.game.buildx.gameitem.GameItem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

/**
 * Created by Sebnic on 2015/02/14.
 */
public class Scene {
    protected SpriteBatch batch;
    protected BitmapFont bmFont;

    protected Stack<GameItem> stack;
    protected ArrayList<GameItem> drawables;
    protected GameItem receivingItem;
    protected GameItem currentGameItem;


    protected float preRun = 1.0f;
    protected float runTime;
    protected float endTime = 1.0f;

    protected int state = 0;
    protected int PRE_RUN = 0;
    protected int RUN = 1;
    protected int END = 2;
    protected int FINISHED = 3;

    protected boolean didWin = false;

    protected String verbNoun = "BUILD X";
    protected String action = "GO!";
    protected String winText = "GOOD JOB!";
    protected String looseText = "TOO SLOW!";

    protected float elapsedTime;
    protected float elapsedRunTime;
    protected float elapsedEndTime;


    public Scene(float time){
        runTime = time;// + verbFadeTime;
        System.out.println("scene constructed");
    }

    public void setup(){
        didWin = false;
    }

    public int randomYPos(Texture asset){
        return (int) (Math.random() * (BuildX.V_WIDTH - asset.getWidth()));
    }

    public void reset(){
        elapsedTime = 0.0f;
        elapsedRunTime = 0.0f;
        elapsedEndTime = 0.0f;
    }

    public void update(float delta){}

    public boolean isRunning(){
        return state == PRE_RUN || state == RUN || state == END;
    }

    public boolean isFinished(){
        return state == FINISHED;
    }

    public boolean didWin(){
        return didWin;
    }
}
