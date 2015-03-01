package biz.brainpowered.game.buildx.scene;

import biz.brainpowered.game.buildx.BuildX;
import biz.brainpowered.game.buildx.Core;
import biz.brainpowered.game.buildx.asset.Assets;
import biz.brainpowered.game.buildx.gameitem.GameItem;
import biz.brainpowered.game.buildx.gameitem.RobotBodyGameItem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Sebnic on 2015/02/14.
 */
public class BuildRobotScene extends GameScene {

    public BuildRobotScene(){
        super(5.0f);
        this.batch = Core.batcher;
        bmFont = Assets.font;
        verbNoun = "BUILD ROBOT";
        backgroundColour = Color.valueOf("FFAEC9");
    }

    public void reset(){
        super.reset();
    }

    public void setup(){
        super.setup();
        reset();
        stack.push(new GameItem(Assets.robotHead, randomYPos(Assets.robotHead), 40));
        drawables.add(stack.peek());
        stack.push(new RobotBodyGameItem(Assets.robotBody, 20, 9));
        drawables.add(stack.peek());
        stack.push(new GameItem(Assets.ground, 0, 0));
        drawables.add(stack.peek());

        connect(stack.pop(), stack.pop());
        currentGameItem = stack.pop();
        currentGameItem.setConnecting(true);

        state = PRE_RUN;
    }

    public void update(float delta){
        super.update(delta);
        elapsedTime += Gdx.graphics.getDeltaTime();
    }
}
