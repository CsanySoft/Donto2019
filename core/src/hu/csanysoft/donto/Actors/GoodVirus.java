package hu.csanysoft.donto.Actors;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import hu.csanysoft.donto.Global.Assets;

public class GoodVirus extends Virus {
    public GoodVirus() {
        super(Assets.manager.get(Assets.MOSQUITO_TEXTURE));
    }
}
