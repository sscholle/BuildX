package biz.brainpowered.game.buildx.scene;

import biz.brainpowered.game.buildx.Core;
import biz.brainpowered.game.buildx.asset.Assets;
import biz.brainpowered.game.buildx.gameitem.GameItem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Sebastian on 2015/03/01.
 */
public class BuildDamScene extends GameScene {

    /**
     * todo: add param for difficulty/time
     */
    public BuildDamScene(){
        super(5.0f);
        this.batch = Core.batcher;
        bmFont = Assets.font;
        verbNoun = "BUILD DAM";
        backgroundColour =  Color.valueOf("99D9EA");
    }

    public void reset(){
        super.reset();
    }

    public void setup(){
        super.setup();
        reset();
        stack.push(new GameItem(Assets.damCap, randomYPos(Assets.damCap), 40));
        drawables.add(stack.peek());
        stack.push(new GameItem(Assets.damWall, 20, 9));
        drawables.add(stack.peek());
        stack.peek().setReceptionPoint(0, new Vector2(stack.peek().getSprite().getWidth() / 2 + 3, stack.peek().getSprite().getHeight()));
        stack.push(new GameItem(Assets.damGround, 0, 0));
        drawables.add(stack.peek());
        stack.peek().setReceptionPoint(0, new Vector2(stack.peek().getSprite().getWidth() / 2 - 6, stack.peek().getSprite().getHeight() / 2 - 3));

        connect(stack.pop(), stack.pop());
        currentGameItem = stack.pop();
        currentGameItem.setConnecting(true);
        state = PRE_RUN;
    }

    /**
     * todo: implement separate update function (updateRun, updatePreRun, updateEnd)
     * @param delta delta time
     */
    public void update(float delta){
        super.update(delta);

        // for custom state actions
        if(state == PRE_RUN){

        }else if(state == RUN){

        }else if(state == END){

        }

        elapsedTime += Gdx.graphics.getDeltaTime();
    }
}
