package biz.brainpowered.game.buildx.desktop;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import biz.brainpowered.game.buildx.BuildX;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "BuildX";
        config.width = BuildX.V_WIDTH * 8;
        config.height = BuildX.V_HEIGHT * 8;
		new LwjglApplication(new BuildX(), config);
	}
}
