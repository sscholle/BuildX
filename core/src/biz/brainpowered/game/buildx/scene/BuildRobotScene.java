package biz.brainpowered.game.buildx.scene;

import biz.brainpowered.game.buildx.BuildX;
import biz.brainpowered.game.buildx.Core;
import biz.brainpowered.game.buildx.asset.Assets;
import biz.brainpowered.game.buildx.gameitem.GameItem;
import biz.brainpowered.game.buildx.gameitem.RobotBodyGameItem;
import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Sebnic on 2015/02/14.
 */
public class BuildRobotScene extends Scene {

    public BuildRobotScene(){
        super(5.0f);
        this.batch = Core.batcher;
        bmFont = Assets.font;
        verbNoun = "BUILD ROBOT";
    }

    public void reset(){
        super.reset();
    }

    public void setup(){
        super.setup();
        reset();
        stack = new Stack<GameItem>();
        drawables = new ArrayList<GameItem>();
        stack.push(new GameItem(Assets.robotHead, (int)(Math.random() * (BuildX.V_WIDTH - Assets.robotHead.getWidth())), 40));
        drawables.add(stack.peek());
        stack.push(new RobotBodyGameItem(Assets.robotBody, 20, 9));
        drawables.add(stack.peek());
        stack.push(new GameItem(Assets.ground, 0, 0));
        drawables.add(stack.peek());

        connect(stack.pop(), stack.pop());
        currentGameItem = stack.pop();
        currentGameItem.setConnecting(true);

        //Gdx.input.setInputProcessor(currentGameItem);

        state = PRE_RUN;
    }

    public void update(float delta){

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

        elapsedTime += Gdx.graphics.getDeltaTime();
    }
}
