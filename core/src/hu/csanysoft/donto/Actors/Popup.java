package hu.csanysoft.donto.Actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import hu.csanysoft.donto.Donto;
import hu.csanysoft.donto.MyBaseClasses.UI.MyLabel;

public class Popup extends MyLabel {
    public Popup(int points, int x, int y) {
        super(points > 0 ? "+"+points*10 : ""+points*10, points > 0 ? Donto.getColorLabelStyle(Color.GREEN) : Donto.getColorLabelStyle(Color.RED));
        setPosition(x,y);
    }

    public Popup(String text, int x, int y) {
        super(text,Donto.getColorLabelStyle(Color.YELLOW));
        setPosition(x,y);
    }
    public Popup(String text, int x, int y, Color color) {
        super(text,Donto.getColorLabelStyle(color));
        setPosition(x,y);
        scaleBy(0.5f);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        moveBy(0,1);
        if(elapsedtime > 1) addAction(Actions.alpha(0, 1));
        if(elapsedtime > 2) getStage().getActors().removeValue(this, true);
    }
}
