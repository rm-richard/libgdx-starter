package com.rmrichard.libgdx.example;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.rmrichard.libgdx.base.BaseActor;
import com.rmrichard.libgdx.base.BaseGame;
import com.rmrichard.libgdx.base.BaseScreen;

public class ExampleLevel extends BaseScreen {

    private BaseActor background;
    private BaseActor itWorksLabel;

    public ExampleLevel(BaseGame game) {
        super(game);
    }

    @Override
    public void create() {
        background = new BaseActor();
        background.setTexture(new Texture(Gdx.files.internal("assets/background.png")));
        background.setPosition(0, 0);

        itWorksLabel = new BaseActor();
        itWorksLabel.setTexture(new Texture(Gdx.files.internal("assets/it-works.png")));
        itWorksLabel.setPosition(65, 230);
        itWorksLabel.setOrigin(340, 75);

        Action textPulse = Actions.forever(
            Actions.parallel(
                Actions.sequence(
                    Actions.scaleTo(0.75f, 0.75f, 1),
                    Actions.scaleTo(1, 1, 1)
                ),
                Actions.sequence(
                    Actions.rotateBy(15, 0.5f),
                    Actions.rotateBy(-30, 1),
                    Actions.rotateBy(15, 0.5f)
                )
            )
        );
        itWorksLabel.addAction(textPulse);

        mainStage.addActor(background);
        uiStage.addActor(itWorksLabel);
    }

    @Override
    public void update(float dt) {

    }
}
