package hu.csanysoft.donto.Actors;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.Global.Globals;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

import static com.badlogic.gdx.math.MathUtils.random;

public class WhiteBloodCell extends OneSpriteAnimatedActor {

    private Robot robot;
    float x = 0, y = 0;
    float gotox = 0, gotoy = 0;
    float xspeed, yspeed;

    float maxSpeed = .4f;

    public WhiteBloodCell(float x, float y, Robot robot) {
        super(Assets.manager.get(Assets.EXPLOSION_ATLAS));
        setSize(150,150);
        setPosition(x,y);
        this.robot = robot;
        addBaseCollisionCircleShape();
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        x = getX()+getWidth()/2;
        y = getY()+getHeight()/2;
        gotox = robot.getX()+robot.getWidth()/2;
        gotoy = robot.getY()+robot.getHeight()/2;

        float xcomp = gotox - x;
        float ycomp = gotoy - y;
        xspeed = xcomp/30 > 0 ? xcomp/30 > maxSpeed ? maxSpeed : xcomp/30 : xcomp/30 < -maxSpeed ? -maxSpeed : xcomp/30;
        yspeed = ycomp/30 > 0 ? ycomp/30 > maxSpeed ? maxSpeed : ycomp/30 : ycomp/30 < -maxSpeed ? -maxSpeed : ycomp/30;
        setX(getX()+xspeed);
        setY(getY()+yspeed);
    }
}
