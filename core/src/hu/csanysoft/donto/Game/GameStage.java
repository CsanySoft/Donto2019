package hu.csanysoft.donto.Game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.viewport.StretchViewport;

import hu.csanysoft.donto.Actors.Robot;
import hu.csanysoft.donto.Actors.BadVirus;
import hu.csanysoft.donto.Actors.Virus;
import hu.csanysoft.donto.Global.Globals;
import hu.csanysoft.donto.Donto;

import hu.csanysoft.donto.MyBaseClasses.Scene2D.MyStage;


public class GameStage extends MyStage {

    public boolean left = false, right = false, forward = false;
    Robot robot;



    public GameStage(Donto game) {
        super(new StretchViewport(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT, new OrthographicCamera(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT)), new SpriteBatch(), game);

        addActor(new BadVirus());
    }


    @Override
    public void init() {
        robot = new Robot(50,50);
        addActor(robot);
        robot.setPositionCenterOfActorToCenterOfViewport();

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        //KARAKTER MOZGÁSA
        if(forward || Gdx.input.isKeyPressed(Input.Keys.UP)){
            double rotation = Math.tan(robot.getRotation());
            robot.moveBy((float)Math.cos(rotation), (float)Math.sin(rotation));
        }
        if(left  || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            robot.rotateBy(5);
        }
        if(right  || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            robot.rotateBy(-5);
        }
        //KARAKTER MOZGÁSA VÉGE

    }

    @Override
    public boolean keyDown(int keyCode) {
        return super.keyDown(keyCode);
    }

    @Override
    public boolean keyUp(int keyCode) {

        return super.keyUp(keyCode);
    }

    @Override
    public void dispose() {
        super.dispose();
    }



}
