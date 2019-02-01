package hu.csanysoft.donto.Actors;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import hu.csanysoft.donto.Game.GameStage;
import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.Global.Globals;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.AnimatedOffsetSprite;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.MultiSpriteActor;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.MyRectangle;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.ShapeType;

public class Virus extends MultiSpriteActor {

    float dest[];
    GameStage gameStage;
    AnimatedOffsetSprite tailSprite;

    public Virus() {
        super(64,64);
        dest = new float[2];
        setSize(64, 64);
        newDest();
        addBaseCollisionRectangleShape();
        tailSprite = new AnimatedOffsetSprite(Assets.manager.get(Assets.VIRUSTAIL_ATLAS), 22, -50, 20, 50);
        addSprite(tailSprite);
        tailSprite.setFps(10);
        addCollisionShape("farok", new MyRectangle(20, 50, 22, -50, 32, 32));
    }

    public void newDest() {
        dest[0] = Globals.random.nextInt(Globals.WORLD_WIDTH - (int)getWidth()) + Globals.random.nextFloat();
        dest[1] = Globals.random.nextInt(Globals.WORLD_HEIGHT - (int)getHeight()) + Globals.random.nextFloat();
    }

    public void die() {
        setVisible(false);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        float gotox = dest[0];
        float gotoy = dest[1];
        float x = getX() + getWidth() / 2;
        float y = getY() + getHeight() / 2;
        float xcomp = gotox - x;
        float ycomp = gotoy - y;
        float xspeed = xcomp / 50 > 0 ? xcomp / 50 > 1f ? 1f : xcomp / 50 : xcomp / 50 < -1f ? -1f : xcomp / 50;
        float yspeed = ycomp / 50 > 0 ? ycomp / 50 > 1f ? 1f : ycomp / 50 : ycomp / 50 < -1f ? -1f : ycomp / 50;
        setX(getX()+ xspeed);
        setY(getY()+ yspeed);
        if(Math.abs(xcomp) < 1 && Math.abs(ycomp) < 1) newDest();
        setRotation((float) ((Math.atan2 (xcomp, -(ycomp))*180.0d/Math.PI)+180));
    }
}
