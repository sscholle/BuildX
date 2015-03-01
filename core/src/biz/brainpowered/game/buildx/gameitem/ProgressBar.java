package biz.brainpowered.game.buildx.gameitem;

        import biz.brainpowered.game.buildx.BuildX;
        import biz.brainpowered.game.buildx.asset.Assets;
        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Input;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.Sprite;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Sebastian on 2015/03/01.
 */
public class ProgressBar extends GameItem {

    private float scaleY;
    private Sprite bar;
    private boolean isProgressing = true;
    private float scaleModifier = -1.0f;

    private boolean isChopping = false;

    public int movementRate;
    public int movementDirection;

    private int yMin;
    private int yMax;

    private boolean isHammering = false;
    private int hammeringMovement = -2;

    private int actionKey = Input.Keys.SPACE;

    public ProgressBar(Texture outline, Texture bar, int x, int y, float initialScale){
        super(outline, x, y);
        this.bar = new Sprite(bar);
        this.bar.setX(x+1);
        this.bar.setY(y + 1);
        this.bar.setOrigin(0.0f, 0.0f);
        scaleY = initialScale;
    }

    public void setProgressing(boolean progressing){
        isProgressing = progressing;
    }

    public float getScaleY(){
        return scaleY;
    }

    public void update(float delta, SpriteBatch batch){
        // local stuff
        if (isProgressing){
            scaleY += scaleModifier / 30;
            bar.setScale(1.0f, scaleY);
            if (scaleY <= 0.0f) scaleModifier = 1.0f;
            if (scaleY >= 1.0f) scaleModifier = -1.0f;

            bar.setColor(1.0f - scaleY, scaleY, 0.0f, 1.0f);
        }

        bar.draw(batch);
        sprite.draw(batch);
        // dont update from keys just draw
        //super.update(delta, batch);
    }
}
