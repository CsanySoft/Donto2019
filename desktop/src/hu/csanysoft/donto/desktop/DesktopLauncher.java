package hu.csanysoft.donto.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import hu.csanysoft.donto.Donto;
import hu.csanysoft.donto.Global.Globals;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Globals.WORLD_WIDTH;
		config.height = Globals.WORLD_HEIGHT;
		new LwjglApplication(new Donto(), config);
	}
}
