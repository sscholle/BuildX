package biz.brainpowered.game.buildx.gameitem;

import biz.brainpowered.game.buildx.BuildX;
import biz.brainpowered.game.buildx.asset.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Sebastian on 2015/03/01.
 */
public class HammerGameItem extends GameItem {

    public int movementRate;
    public int movementDirection;

    private int yMin;
    private int yMax;

    private boolean isHammering = false;
    private int hammeringMovement = -2;

    private int actionKey = Input.Keys.SPACE;

    public HammerGameItem(Texture texture, int x, int y){
        super(texture, x, y);
        movementRate = 1;
        movementDirection = 0;

        yMin = 0 + Assets.nail.getHeight();
        yMax = BuildX.V_HEIGHT - (int)sprite.getHeight();
    }

    public void update(float delta, SpriteBatch batch){
        // local stuff
        if (isHammering) {
            setY(y + hammeringMovement);
            if (y <= yMin) hammeringMovement = 2;
            if (y >= yMax) {
                hammeringMovement = -2;
                isHammering = false;
            }
        } else {
            if(Gdx.input.isKeyPressed(actionKey)){
                isHammering = true;
            }

            if (movementDirection == 0){
                setX(x+1);
                if (x >= BuildX.V_WIDTH - sprite.getWidth()) movementDirection = 1;
            }else{
                setX(x-1);
                if (x <= -28) movementDirection = 0;
            }
        }

        sprite.draw(batch);
        // dont update from keys just draw
        //super.update(delta, batch);
    }
}
