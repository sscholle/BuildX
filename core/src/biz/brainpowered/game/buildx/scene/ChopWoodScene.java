package biz.brainpowered.game.buildx.scene;

import biz.brainpowered.game.buildx.BuildX;
import biz.brainpowered.game.buildx.Core;
import biz.brainpowered.game.buildx.asset.Assets;
import biz.brainpowered.game.buildx.gameitem.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Sebastian on 2015/03/01.
 */
public class ChopWoodScene extends GameScene {

    private float chopProgress;
    private ProgressBar progressBar;
    private HatchetGameItem hatchet;
    private boolean didChop = false;
    private float scaleChopped = 0.0f;
    private float elapsedChopTime;
    private float chopTime = 0.5f;
    public ChopWoodScene(){
        super(5.0f);
        this.batch = Core.batcher;
        bmFont = Assets.font;
        verbNoun = "CHOP WOOD";
        //connectionDistanceX = 5;
        chopProgress = 0.5f; // scale
        elapsedChopTime = 0.0f;
    }

    public void reset(){
        super.reset();
    }

    public void setup(){
        super.setup();
        reset();

        stack.push(new GameItem(Assets.wood, BuildX.V_WIDTH / 2 - Assets.wood.getWidth() / 2, 0));
        drawables.add(stack.peek());

        hatchet = new HatchetGameItem(Assets.hatchet, 0, chopProgress);
        stack.push(hatchet);
        drawables.add(stack.peek());
        //stack.peek().setConnectionPoint(0, new Vector2(stack.peek().getSprite().getWidth() / 2 + 14, 0.0f));

        // todo: make custom game item (ProgressBar)
        progressBar = new ProgressBar(Assets.progressBarOutline, Assets.progressBar, BuildX.V_WIDTH - 10, BuildX.V_HEIGHT - 20, chopProgress);
        stack.push(progressBar);
        drawables.add(stack.pop());

        //progressBar = ...

        receivingItem = stack.pop();// pop nail
        currentGameItem = stack.pop();
        currentGameItem.setConnecting(true);

        state = PRE_RUN;
        didChop = false;
    }

    public void checkConnections(){
        // do not check connections
    }

    public void update(float delta){
        super.update(delta);

        if (state == RUN){
            if(didChop){
                // chop anim
                elapsedChopTime += delta;
                hatchet.setPositionByScale((chopTime) - elapsedChopTime);
                if (elapsedChopTime >= chopTime && scaleChopped >= 0.66){
                    win();
                } else if (elapsedChopTime >= chopTime && scaleChopped < 0.66){
                    looseText = "BAD SWING";
                    loose();
                }

            }else {
                hatchet.setPositionByScale(progressBar.getScaleY());
            }
            // check win condition based on chop progress
            if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && didChop != true){
                didChop = true;
                progressBar.setProgressing(false);
                scaleChopped = progressBar.getScaleY();
            }
        }

        //chopProgress = Math.abs((float)Math.sin(elapsedTime));
        //System.out.println("progress: "+chopProgress);
        elapsedTime += Gdx.graphics.getDeltaTime();
    }
}
