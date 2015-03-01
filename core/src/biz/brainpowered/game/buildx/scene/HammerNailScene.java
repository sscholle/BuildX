package biz.brainpowered.game.buildx.scene;

import biz.brainpowered.game.buildx.Core;
import biz.brainpowered.game.buildx.asset.Assets;
import biz.brainpowered.game.buildx.gameitem.GameItem;
import biz.brainpowered.game.buildx.gameitem.HammerGameItem;
import biz.brainpowered.game.buildx.gameitem.RobotBodyGameItem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Sebastian on 2015/03/01.
 */
public class HammerNailScene extends GameScene {

    public HammerNailScene(){
        super(5.0f);
        this.batch = Core.batcher;
        bmFont = Assets.font;
        verbNoun = "HAMMER NAIL";
        connectionDistanceX = 5;
    }

    public void reset(){
        super.reset();
    }

    public void setup(){
        super.setup();
        reset();
        stack.push(new HammerGameItem(Assets.hammer, randomYPos(Assets.hammer), 40));
        drawables.add(stack.peek());
        stack.peek().setConnectionPoint(0, new Vector2(stack.peek().getSprite().getWidth() / 2 + 14, 0.0f));
        stack.push(new GameItem(Assets.nail, randomYPos(Assets.nail), 0));
        drawables.add(stack.peek());

        receivingItem = stack.pop();// pop nail
        currentGameItem = stack.pop();
        currentGameItem.setConnecting(true);

        state = PRE_RUN;
    }

    public void update(float delta){
        super.update(delta);
        elapsedTime += Gdx.graphics.getDeltaTime();
    }
}
