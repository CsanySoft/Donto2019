package hu.csanysoft.donto.Actors;

import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.MultiSpriteActor;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OffsetSprite;

public class Robot extends MultiSpriteActor {

    OffsetSprite body;

    public Robot(float width, float height) {
        super(width, height);
        body = new OffsetSprite(Assets.manager.get(Assets.EXIT), 0, 0, 50, 50);
        addSprite(body);
        addBaseCollisionRectangleShape();
    }
}
