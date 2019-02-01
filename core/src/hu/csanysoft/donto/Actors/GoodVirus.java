package hu.csanysoft.donto.Actors;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import hu.csanysoft.donto.Game.GameStage;
import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.Global.Globals;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OffsetSprite;

public class GoodVirus extends Virus {
    OffsetSprite body;

    public GoodVirus() {
        super();
        body = new OffsetSprite(Assets.manager.get(Assets.GOODVIRUS_TEXTURE), 0,0,64,64);
        addSprite(body);
        setPosition(Globals.random.nextInt(GameStage.WORLD_BOUND_X / 2 - (int)getWidth() - 100) + GameStage.WORLD_BOUND_X / 2 + Globals.random.nextFloat() + 100, Globals.random.nextInt(GameStage.WORLD_BOUND_Y - (int)getHeight()) + Globals.random.nextFloat());
    }
}
