package hu.csanysoft.donto.Actors;


import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.MyActor;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

public class Spash extends OneSpriteAnimatedActor {


    public Spash(MyActor a) {
        super(Assets.manager.get(Assets.SPLASH_ATLAS));
        long f = Assets.manager.get(Assets.SPLASH_SOUND).play();
        setPosition(a.getX(), a.getY());
        setSize(a.getWidth(), a.getHeight());
        Assets.manager.get(Assets.SPLASH_SOUND).setVolume(f, 0.2f);
        setFps(20);
    }

    @Override
    protected void repeated() {
        if (getStage() != null)
            getStage().getActors().removeValue(this, true);
        super.repeated();
    }
}
