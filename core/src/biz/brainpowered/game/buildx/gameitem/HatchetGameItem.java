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
public class HatchetGameItem extends GameItem {

    public int movementRate;
    public int movementDirection;

    private int yMin;
    private int yMax;

    private boolean isHammering = false;
    private int hammeringMovement = -2;

    private int actionKey = Input.Keys.SPACE;

    private int rangeY;

    public HatchetGameItem(Texture texture, int x, float startingYPosScale){
        super(texture, x, 0);

        movementRate = 1;
        movementDirection = 0;

        yMin = 0 + Assets.wood.getHeight() - 4;
        yMax = BuildX.V_HEIGHT - texture.getHeight();
        rangeY = yMax - yMin;
        setPositionByScale(startingYPosScale);
    }

    public void setPositionByScale(float scale){
        setY(yMin + rangeY * scale);
    }

    public void setHammering(boolean hammering){
        isHammering = hammering;
    }

    public void update(float delta, SpriteBatch batch){
        // local stuff
        if (isHammering) {
            setY(y + hammeringMovement);
            if (y <= yMin) {
                isHammering = false;
                hammeringMovement = 0;
            }
        }
        sprite.draw(batch);
        // dont update from keys just draw
        //super.update(delta, batch);
    }
}
