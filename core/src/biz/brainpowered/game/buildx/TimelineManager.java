package biz.brainpowered.game.buildx;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Sebnic on 2015/01/02.
 */
public class TimelineManager {
    public static TweenManager tweenManager;

    public TimelineManager() {
        tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
    }

    public static void simplePulse (Sprite sprite) {
        Timeline.createSequence()
                .push(Tween.set(sprite, SpriteAccessor.OPACITY).target(0f))
                .push(Tween.set(sprite, SpriteAccessor.SCALE).target(0f, 0f))
                .beginParallel()
                .push(Tween.to(sprite, SpriteAccessor.OPACITY, 0.3f).target(1f).ease(TweenEquations.easeInOutQuad))
                .push(Tween.to(sprite, SpriteAccessor.SCALE, 0.3f).target(1.3f, 1.3f).ease(TweenEquations.easeInOutQuad))
                .end()
                .push(Tween.to(sprite, SpriteAccessor.SCALE, 0.1f).target(1f, 1f).ease(TweenEquations.easeInOutQuad))
                .start(tweenManager);
    }
}
