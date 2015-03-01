package biz.brainpowered.game.buildx.scene;

import biz.brainpowered.game.buildx.BuildX;
import biz.brainpowered.game.buildx.gameitem.GameItem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Sebastian on 2015/03/01.
 */
public class GameScene extends Scene {
    protected int connectionDistanceX;
    protected int connectionDistanceY;

    public GameScene(float time) {
        super(time);
        connectionDistanceX = 1;
        connectionDistanceY = 1;
        backgroundColour = Color.WHITE;
    }

    public void setup(){
        super.setup();
        stack = new Stack<GameItem>();
        drawables = new ArrayList<GameItem>();
    }

    protected void checkConnections(){
        //reception point
        float xR = receivingItem.getReceptionPoints().firstElement().x + receivingItem.getX();
        float yR = receivingItem.getReceptionPoints().firstElement().y + receivingItem.getY();

        //get connection point of connector - then check distance
        float xC = currentGameItem.getConnectionPoints().firstElement().x + currentGameItem.getX();
        float yC = currentGameItem.getConnectionPoints().firstElement().y + currentGameItem.getY();

        if(Math.abs(xR - xC) <=connectionDistanceX && Math.abs(yR - yC) <=connectionDistanceY){
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

    public void win(){
        didWin = true;
        state = END;
        System.out.println("WIN");
    }

    public void loose(){
        state = END;
        System.out.println("LOOSE");
    }

    public void update(float delta) {
        super.update(delta);
        GL20 gl = Gdx.gl;
        gl.glClearColor(backgroundColour.r, backgroundColour.g, backgroundColour.b, 0.0f);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(state == PRE_RUN){
            if(preRun >= elapsedTime) {
                drawVerb();
            }else {
                state = RUN;
            }

        }else if(state == RUN){
            checkConnections();
            for (int x = 0; x < drawables.size(); x++){
                drawables.get(x).update(delta, batch);
            }
            drawTimer();
            if (elapsedRunTime >= runTime)
                loose();
            elapsedRunTime += Gdx.graphics.getDeltaTime();

        }else if(state == END){
            drawWinState();
            if(elapsedEndTime >= endTime){
                state = FINISHED;
            }
            elapsedEndTime += Gdx.graphics.getDeltaTime();
        }
    }

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
}
