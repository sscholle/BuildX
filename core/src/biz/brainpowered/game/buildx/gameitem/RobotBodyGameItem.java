package biz.brainpowered.game.buildx.gameitem;

import biz.brainpowered.game.buildx.BuildX;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Sebnic on 2015/02/21.
 */
public class RobotBodyGameItem extends GameItem {

    public int movementRate;
    public int movementDirection;

    public RobotBodyGameItem(Texture texture, int x, int y){
        super(texture, x, y);
        movementRate = 1;
        movementDirection = 0;
    }

    public void update(float delta, SpriteBatch batch){
        // local stuff
        if (movementDirection == 0){
            setX(x+1);
            if (x >= BuildX.V_WIDTH - sprite.getWidth()) movementDirection = 1;
        }else{
            setX(x-1);
            if (x <= 0) movementDirection = 0;
        }
        super.update(delta, batch);
    }
}
