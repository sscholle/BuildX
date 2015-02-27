package biz.brainpowered.game.buildx.gameitem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

/**
 * Created by Sebnic on 2015/02/14.
 */
public class GameItem{
    protected Vector<Vector2> connectionPoints;
    protected Vector<Vector2> receptionPoints;
    protected Sprite sprite;
    protected Animation animation;
    protected boolean isConnecting;
    protected boolean isReceiving;
    protected float x;
    protected float y;
    protected float elapsedTime;
    protected float lastInputCheck;

    public GameItem(Texture texture, int x, int y){
        sprite = new Sprite(texture);
        sprite.setX(x);
        sprite.setY(y);
        this.x = x;
        this.y = y;

        connectionPoints = new Vector<Vector2>();
        receptionPoints = new Vector<Vector2>();

        connectionPoints.add(new Vector2(texture.getWidth()/2, 0.0f)); //bottom center
        receptionPoints.add(new Vector2(texture.getWidth()/2, texture.getHeight())); //top center

        isConnecting = false;
        isReceiving = false;

        elapsedTime = 0;
        lastInputCheck = 0;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        sprite.setX(x);
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        sprite.setY(y);
        this.y = y;
    }

    public Vector<Vector2> getReceptionPoints() {
        return receptionPoints;
    }

    public Vector<Vector2> getConnectionPoints() {
        return connectionPoints;
    }

    public void setConnecting(boolean isConnecting) {
        this.isReceiving = false;
        this.isConnecting = isConnecting;
    }

    public boolean isConnecting() {
        return isConnecting;
    }

    public void setReceiving(boolean isReceiving) {
        this.isConnecting = false;
        this.isReceiving = isReceiving;
    }

    public boolean isReceiving() {
        return isReceiving;
    }

    public void update(float delta, SpriteBatch batch){
        if(isConnecting)checkInput();
        sprite.draw(batch);
    }

    public void checkInput()
    {
        if ((lastInputCheck + 0.033f) < (elapsedTime))
        {
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){

                setX(x-1);
            }
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){

                setX(x+1);
            }
            if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){

                setY(y - 1);
            }
            if(Gdx.input.isKeyPressed(Input.Keys.UP)){

                setY(y + 1);
            }
            lastInputCheck = elapsedTime;
        }
        elapsedTime += Gdx.graphics.getDeltaTime();
    }
}
