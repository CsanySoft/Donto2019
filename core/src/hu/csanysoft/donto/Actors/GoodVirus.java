package hu.csanysoft.donto.Actors;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.Global.Globals;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OffsetSprite;

public class GoodVirus extends Virus {
    OffsetSprite body;

    public GoodVirus() {
        super();
        body = new OffsetSprite(Assets.manager.get(Assets.GOODVIRUS_TEXTURE), 0,0,64,64);
        addSprite(body);
        setPosition(Globals.random.nextInt(Globals.WORLD_WIDTH / 2 - (int)getWidth() - 100) + Globals.WORLD_WIDTH / 2 + Globals.random.nextFloat() + 100, Globals.random.nextInt(Globals.WORLD_HEIGHT - (int)getHeight()) + Globals.random.nextFloat());
    }
}
