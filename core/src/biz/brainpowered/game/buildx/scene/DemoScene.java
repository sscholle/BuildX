package biz.brainpowered.game.buildx.scene;

import biz.brainpowered.game.buildx.BuildX;
import biz.brainpowered.game.buildx.Core;
import biz.brainpowered.game.buildx.asset.Assets;
import biz.brainpowered.game.buildx.gameitem.GameItem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.*;

/**
 * Created by Sebnic on 2015/02/14.
 */
public class DemoScene extends Scene {
    String verbNoun = "BUILD HOUSE";
    String action = "GO!";
    String winText = "WIN!";
    String looseText = "LOOSE!";

    float elapsedTime;
    float elapsedRunTime;
    float elapsedEndTime;

    public DemoScene(){
        super(5.0f);
        this.batch = Core.batcher;
        bmFont = Assets.font;
        elapsedTime = 0.0f;
        elapsedRunTime = 0.0f;
        elapsedEndTime = 0.0f;
    }

    public void reset(){
        elapsedTime = 0.0f;
        elapsedRunTime = 0.0f;
        elapsedEndTime = 0.0f;
    }

    public void setup(){
        super.setup();
        reset();
        stack = new Stack<GameItem>();
        drawables = new ArrayList<GameItem>();
        stack.push(new GameItem(Assets.roof, 15, 40));
        drawables.add(stack.peek());
        stack.push(new GameItem(Assets.walls, 20, 9));
        drawables.add(stack.peek());
        stack.push(new GameItem(Assets.ground, 0, 0));
        drawables.add(stack.peek());

        connect(stack.pop(), stack.pop());
        currentGameItem = stack.pop();
        currentGameItem.setConnecting(true);

        Gdx.input.setInputProcessor(currentGameItem);

        state = PRE_RUN;
    }

    public void update(float delta){

        if(state == PRE_RUN){
            if(preRun >= elapsedTime) {
                float stringLength = bmFont.getBounds(verbNoun).width;
                float stringHeight = bmFont.getBounds(verbNoun).height;
                bmFont.setColor(new Color(1f, 1f, 1f, 1.0f));
                bmFont.draw(batch, verbNoun, (BuildX.V_WIDTH / 2) - stringLength / 2, (BuildX.V_HEIGHT / 2 + stringHeight / 2));
            }else {
                state = RUN;
            }

        }else if(state == RUN){
            checkConnections();
            for (int x = 0; x < drawables.size(); x++){
                drawables.get(x).update(delta, batch);
            }

            String text = ""+(runTime-elapsedRunTime);
            if(text.length() > 4) text = text.substring(0,4);

            bmFont.setColor(new Color(1f, 1f, 1f, 1.0f));
            bmFont.draw(batch, text, 1, (BuildX.V_HEIGHT - 1));

            if (elapsedRunTime >= runTime)
                loose();

            elapsedRunTime += Gdx.graphics.getDeltaTime();

        }else if(state == END){

            String text = (didWin) ? winText: looseText ;
            float stringLength = bmFont.getBounds(text).width;
            float stringHeight = bmFont.getBounds(text).height;
            bmFont.setColor(new Color(1f, 1f, 1f, 1.0f));
            bmFont.draw(batch, text, (BuildX.V_WIDTH / 2) - stringLength / 2, BuildX.V_HEIGHT / 2 + stringHeight / 2);

            if(elapsedEndTime >= endTime){
                state = FINISHED;
            }

            elapsedEndTime += Gdx.graphics.getDeltaTime();
        }

        elapsedTime += Gdx.graphics.getDeltaTime();
    }
}
