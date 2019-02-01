package hu.csanysoft.donto.Actors;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import hu.csanysoft.donto.Global.Assets;

public class BadVirus extends Virus {
    public BadVirus() {
        super(Assets.manager.get(Assets.MOSQUITO_TEXTURE));
    }
}
