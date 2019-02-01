package hu.csanysoft.donto.Actors;

import com.badlogic.gdx.graphics.Texture;

import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Pill extends OneSpriteStaticActor {
    public Pill(float x, float y) {
        super(Assets.manager.get(Assets.EXIT));
        setSize(100, 50);
        setPosition(x,y);
        addBaseCollisionRectangleShape();
    }
}
