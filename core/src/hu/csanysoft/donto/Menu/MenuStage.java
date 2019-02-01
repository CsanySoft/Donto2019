package hu.csanysoft.donto.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanysoft.donto.Actors.BadVirus;
import hu.csanysoft.donto.Actors.GoodVirus;
import hu.csanysoft.donto.Actors.MovingBackground;
import hu.csanysoft.donto.Game.GameScreen;
import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.Global.Globals;
import hu.csanysoft.donto.Donto;
import hu.csanysoft.donto.MyBaseClasses.MyTextButton;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.MyStage;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanysoft.donto.MyBaseClasses.UI.MyButton;
import hu.csanysoft.donto.Tutorial.TutorialScreen;

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

        for(int i = 0; i < 10; i++) {
            addActor(new GoodVirus(), 20);
            addActor(new BadVirus(), 20);
        }

        addActor(new MovingBackground(Assets.manager.get(Assets.BACKGROUNDWATER_TEXTURE),Globals.WORLD_WIDTH + 600, Globals.WORLD_HEIGHT + 600, -500, -500, 100), 5);
        TextButton start = new MyButton("",game.btnStart());
        start.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game, false),true);
                super.touchUp(event, x, y, pointer, button);
            }
        });
        start.setSize(start.getWidth()/2, start.getHeight()/2);

        TextButton tutorial = new MyButton("", game.btnTutorial());
        tutorial.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new TutorialScreen(game),true);
                super.touchUp(event, x, y, pointer, button);
            }
        });
        tutorial.setSize(tutorial.getWidth()/2, tutorial.getHeight()/2);

        TextButton exit = new MyButton("", game.btnExit());
        exit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
            }
        });
        exit.setSize(exit.getWidth()/2, exit.getHeight()/2);


        addActor(tutorial, 10);
        addActor(start, 10);
        addActor(exit, 10);


        start.setPosition(Globals.WORLD_WIDTH/2-start.getWidth()/2, Globals.WORLD_HEIGHT - start.getHeight() - 100);
        tutorial.setPosition(Globals.WORLD_WIDTH/2-exit.getWidth()/2, start.getY() - 100);
        exit.setPosition(Globals.WORLD_WIDTH/2-exit.getWidth()/2, 100);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
            game.setScreenBackByStackPop();
        }
        return false;
    }

}
