package hu.csanysoft.donto.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import hu.csanysoft.donto.Donto;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.MyScreen;

public class GameScreen extends MyScreen {

    GameStage stage;
    ControlStage controlStage;
    InputMultiplexer inputMultiplexer;

    public GameScreen(Donto game, boolean newGame) {
        super(game);
        stage = new GameStage(game, newGame);
        controlStage = new ControlStage(game, stage);
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(controlStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
    }


    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act(delta);
        stage.draw();
        controlStage.act();
        controlStage.draw();
    }


    @Override
    public void init() {
        setBackGroundColor(0,0,0);
    }

    @Override
    public void resume() {
        super.resume();
    }

}
