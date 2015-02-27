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

    protected void checkConnections(){
        //reception point
        float xR = receivingItem.getReceptionPoints().firstElement().x + receivingItem.getX();
        float yR = receivingItem.getReceptionPoints().firstElement().y + receivingItem.getY();

        //get connection point of connector - then check distance
        float xC = currentGameItem.getConnectionPoints().firstElement().x + currentGameItem.getX();
        float yC = currentGameItem.getConnectionPoints().firstElement().y + currentGameItem.getY();

        if(Math.abs(xR - xC) <=1 && Math.abs(yR - yC) <=1){
            connect(receivingItem, currentGameItem);
            if(stack.size() == 0){
                win();
            }
        }
    }

    protected void connect(GameItem receptor, GameItem connector){
        float xDest = receptor.getReceptionPoints().firstElement().x + receptor.getX();
        float yDest = receptor.getReceptionPoints().firstElement().y + receptor.getY();
        connector.setX(xDest - connector.getConnectionPoints().firstElement().x);
        connector.setY(yDest - connector.getConnectionPoints().firstElement().y);
        connector.setReceiving(true);
        receivingItem = connector; // connector becomes the receptor
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

    public void drawVerb(){
        float stringLength = bmFont.getBounds(verbNoun).width;
        float stringHeight = bmFont.getBounds(verbNoun).height;
        bmFont.setColor(new Color(1f, 1f, 1f, 1.0f));
        bmFont.draw(batch, verbNoun, (BuildX.V_WIDTH / 2) - stringLength / 2, (BuildX.V_HEIGHT / 2 + stringHeight / 2));
    }

    public void drawTimer(){
        String text = ""+(runTime-elapsedRunTime);
        if(text.length() > 4) text = text.substring(0, 4);
        bmFont.setColor(new Color(1f, 1f, 1f, 1.0f));
        bmFont.draw(batch, text, 1, (BuildX.V_HEIGHT - 1));
    }

    public void drawWinState(){
        String text = (didWin) ? winText: looseText ;
        float stringLength = bmFont.getBounds(text).width;
        float stringHeight = bmFont.getBounds(text).height;
        bmFont.setColor(new Color(1f, 1f, 1f, 1.0f));
        bmFont.draw(batch, text, (BuildX.V_WIDTH / 2) - stringLength / 2, BuildX.V_HEIGHT / 2 + stringHeight / 2);
    }

    public void win(){
        didWin = true;
        state = END;
        System.out.println("WIN");
    }

    public void loose(){
        state = END;
        System.out.println("LOOSE");
    }

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
