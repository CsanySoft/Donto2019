package hu.csanysoft.donto.Actors;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OffsetSprite;

public class GoodVirus extends Virus {
    OffsetSprite body;

    public GoodVirus() {

        super();
        body = new OffsetSprite(Assets.manager.get(Assets.GOODVIRUS_TEXTURE), 0,0,64,64);
        addSprite(body);
    }
}
