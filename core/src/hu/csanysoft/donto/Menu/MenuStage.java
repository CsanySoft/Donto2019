package hu.csanysoft.donto.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanysoft.donto.Game.GameScreen;
import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.Global.Globals;
import hu.csanysoft.donto.Donto;
import hu.csanysoft.donto.MyBaseClasses.MyTextButton;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.MyStage;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class MenuStage extends MyStage {

    public MenuStage(Viewport viewport, Batch batch, Donto game) {
        super(viewport, batch, game);
        Gdx.input.setCatchBackKey(true);
        setDebugAll(Globals.DEBUG_ALL);
    }

    public void init() {
        /* OneSpriteStaticActor logo = new OneSpriteStaticActor(Assets.manager.get(Assets.LOGO)){
            @Override
            public void act(float delta) {
                super.act(delta);
                setRotation((float) (Math.sin(elapsedTime)*10));
            }
        }; */
        MyTextButton start = new MyTextButton("Start"){
            @Override
            public void init() {
                super.init();
                addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        return true;
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        game.setScreen(new GameScreen(game), true);
                        super.touchUp(event, x, y, pointer, button);
                    }
                });
            }
        };

        MyTextButton exit = new MyTextButton("Exit"){
            @Override
            public void init() {
                super.init();
                addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        return true;
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        System.exit(0);
                        super.touchUp(event, x, y, pointer, button);
                    }
                });
            }
        };

      /*  OneSpriteStaticActor tutorial = new OneSpriteStaticActor(Assets.manager.get(Assets.GAMEOVER_TEXTURE)){
            @Override
            public void init() {
                super.init();
                addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        setTexture(Assets.manager.get(Assets.GAMEOVER_TEXTURE));
                        return true;
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        game.setScreen(new SelectScreen(game),true);
                        super.touchUp(event, x, y, pointer, button);
                    }
                });
            }
        }; */

       // addActor(logo);
       // addActor(spiral);
        // addActor(tutorial);
        addActor(start);
        addActor(exit);

        //start.magnify(2);
        //tutorial.magnify(2);
        //exit.magnify(2);
       // spiral.magnify(2);

        start.setPosition(Globals.WORLD_WIDTH/2-start.getWidth()/2, 600);
        exit.setPosition(Globals.WORLD_WIDTH/2-start.getWidth()/2, 600);
        //tutorial.setPositionCenterOfActorToCenterOfViewport();
        //logo.setPositionCenterOfActorToCenterOfViewport();

       // tutorial.changePosition(+400, +200);
        exit.changePosition(0, -200);
        //logo.changePosition(-200, +200);

       // spiral.setZIndex(10);
       // spiral.setOrigintoCenter();
       // spiral.setPositionCenterOfActorToCenterOfViewport();
       // spiral.setZIndex(0);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
            game.setScreenBackByStackPop();
        }
        return false;
    }

}