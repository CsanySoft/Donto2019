package hu.csanysoft.donto.Game;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.viewport.StretchViewport;

import hu.csanysoft.donto.Global.Globals;
import hu.csanysoft.donto.Donto;

import hu.csanysoft.donto.MyBaseClasses.Scene2D.MyStage;


public class GameStage extends MyStage {




    public GameStage(Donto game) {
        super(new StretchViewport(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT, new OrthographicCamera(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT)), new SpriteBatch(), game);
    }


    @Override
    public void init() {


    }

    @Override
    public void act(float delta) {
        super.act(delta);
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
