package hu.csanysoft.donto.Actors;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import hu.csanysoft.donto.Game.GameStage;
import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.Global.Globals;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.AnimatedOffsetSprite;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OffsetSprite;

public class BadVirus extends Virus {

    public boolean needsABetterWeaponToDestroy ;

    OffsetSprite body;

    public BadVirus() {
        super();
        if(gameStage.level >= 5) needsABetterWeaponToDestroy = Globals.random(0, 20) < 4;
        if(needsABetterWeaponToDestroy) addSprite(new AnimatedOffsetSprite(Assets.manager.get(Assets.SHIELD_ATLAS), 0, 0, 75,75));
        body = new OffsetSprite(Assets.manager.get(Assets.BADVIRUS_TEXTURE), 0,0,64,64);
        addSprite(body);
        setY(Globals.random.nextInt(GameStage.WORLD_BOUND_Y - (int)getHeight()) + Globals.random.nextFloat());
        if(Globals.random.nextBoolean()) {
            setX(Globals.random(0,GameStage.WORLD_BOUND_X / 7));
        }
        else if (Globals.random.nextBoolean()) {
            setX(Globals.random(GameStage.WORLD_BOUND_X / 7 * 2,GameStage.WORLD_BOUND_X / 7 * 3));
        } else {
            setX(Globals.random(GameStage.WORLD_BOUND_X / 7 * 5 ,GameStage.WORLD_BOUND_X / 7 * 6));
        }
    }
}
