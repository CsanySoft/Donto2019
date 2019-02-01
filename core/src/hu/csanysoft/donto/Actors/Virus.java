package hu.csanysoft.donto.Actors;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import hu.csanysoft.donto.Game.GameStage;
import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.Global.Globals;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

public class Virus extends OneSpriteAnimatedActor {

    float dest[];
    GameStage gameStage;

    public Virus(TextureAtlas textureAtlas) {
        super(textureAtlas);
        dest = new float[2];
        setSize(64, 64);
        newDest();
        addBaseCollisionRectangleShape();
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
        float xspeed = xcomp / 100 > 0 ? xcomp / 100 > 0.5f ? 0.5f : xcomp / 100 : xcomp / 100 < -0.5f ? -0.5f : xcomp / 100;
        float yspeed = ycomp / 100 > 0 ? ycomp / 100 > 0.5f ? 0.5f : ycomp / 100 : ycomp / 100 < -0.5f ? -0.5f : ycomp / 100;
        setX(getX()+ xspeed);
        setY(getY()+ yspeed);
        if(Math.abs(xcomp) < 1 && Math.abs(ycomp) < 1) newDest();
        setRotation((float) ((Math.atan2 (xcomp, -(ycomp))*180.0d/Math.PI)+180));
    }
}
