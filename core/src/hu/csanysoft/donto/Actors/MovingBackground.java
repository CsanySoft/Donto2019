package hu.csanysoft.donto.Actors;

import com.badlogic.gdx.graphics.Texture;

import hu.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class MovingBackground extends OneSpriteStaticActor {

    float width, height, move;

    public MovingBackground(Texture texture, float width, float height, float x, float y, float move) {
        super(texture);
        setSize(width, height);
        setPosition(x, y);
        this.width = width;
        this.height = height;
        this.move = move;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setWidth(width + (float)Math.sin(elapsedTime)*move);
        setHeight(height + (float)Math.cos(elapsedTime)*move);
    }
}
