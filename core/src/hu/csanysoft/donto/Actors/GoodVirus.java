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
        setY(Globals.random.nextInt(GameStage.WORLD_BOUND_Y - (int)getHeight()) + Globals.random.nextFloat());
        if(Globals.random.nextBoolean()) {
            setX(Globals.random(GameStage.WORLD_BOUND_X/ 7,GameStage.WORLD_BOUND_X / 7 * 2));
        }
        else if (Globals.random.nextBoolean()) {
            setX(Globals.random(GameStage.WORLD_BOUND_X / 7 * 4,GameStage.WORLD_BOUND_X / 7 * 5));
        } else {
            setX(Globals.random(GameStage.WORLD_BOUND_X / 7 * 6 ,GameStage.WORLD_BOUND_X / 7 * 7));
        }
    }
}
