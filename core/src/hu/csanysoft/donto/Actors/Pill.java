package hu.csanysoft.donto.Actors;

import com.badlogic.gdx.graphics.Texture;

import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Pill extends OneSpriteStaticActor {
    public Pill(float x, float y, float rotation) {
        super(Assets.manager.get(Assets.ANTIPILL_TEXTURE));
        setSize(50, 100);
        setPosition(x,y);
        setRotation(rotation);
        addBaseCollisionRectangleShape();
    }
}
