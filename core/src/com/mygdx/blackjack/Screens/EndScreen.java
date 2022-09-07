package com.mygdx.blackjack.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.blackjack.Blackjack;
import com.mygdx.blackjack.Functions.Utilities;
import com.mygdx.blackjack.Objects.Card;
import com.mygdx.blackjack.Objects.Player;

import java.util.List;

public class EndScreen implements Screen {
    final Blackjack game;
    final Stage stage;
    OrthographicCamera camera;

    public EndScreen(final Blackjack game){
        this.game = game;
        stage = new Stage(new ScreenViewport());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

    }

    public void displayDeck(Player p, Boolean dealer){
        List<Card> pDeck = p.getDeck();
        int counter = 0;
        for (Card card : pDeck){
            Image cardImage = card.getCardImage();
            cardImage.setWidth(125);
            cardImage.setHeight(182);
            cardImage.setX(cardImage.getWidth() * 3 * counter / 2);
            if (dealer){
                cardImage.setY(400);
            }
            else{
                cardImage.setY(100);
            }

            stage.addActor(cardImage);
            counter++;
        }
    }
    @Override
    public void show() {
        String endMessage = "";
        if (game.p1.getTotal() > 21) {
            endMessage = "You bust!";
        }
        else if (game.p1.getTotal() == 21){
            if (game.p1.getDeck().size() == 2){
            endMessage = "Blackjack!";
            }
            else{
                endMessage="21! You win!";
            }
        } else if (game.p2.getTotal() > 21) {
            endMessage = "Dealer bust!";

        } else if (game.p1.getTotal() > game.p2.getTotal()) {
            endMessage = "You win!";

        }
        else if (game.p1.getTotal() == game.p2.getTotal()){
            endMessage = "Draw!";
        }
        else{
            endMessage = "You lose!";
        }
        String labelText = (endMessage + "  Player score = " + game.p1.getTotal()
                + " Dealer score = " + game.p2.getTotal());
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();
        Label result = new Label(labelText, style);
        result.setFontScale(2.0f);

        result.setX(0);
        result.setY(stage.getHeight() - 1.5f * result.getHeight());
        stage.addActor(result);
        result.getColor().a=0;
        result.addAction(Actions.fadeIn(0.75f));

        displayDeck(game.p1, false);
        displayDeck(game.p2, true);



        Actor exitButton = Utilities.createButton("exit.png", "exit_hover.png");
        exitButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent ev, float x, float y, int pointer, int button) {
                dispose();

            }

            @Override
            public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                return true;
            }

        });
       // exitButton.setPosition(
       //         Gdx.graphics.getWidth()  - exitButton.getWidth(),
       //         Gdx.graphics.getHeight() / 5f - exitButton.getHeight());

       //
        // ##]]]]]]stage.addActor(exitButton);

        Actor menuButton = Utilities.createButton("menu.png", "menu_hover.png");
        menuButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent ev, float x, float y, int pointer, int button) {
                game.setScreen(new MainMenuScreen(game));

            }

            @Override
            public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                return true;
            }

        });
       // menuButton.setPosition(
       //         Gdx.graphics.getWidth()  - exitButton.getWidth(),
       //         Gdx.graphics.getHeight() / 3f);

       // stage.addActor(menuButton);
        Actor resetButton = Utilities.createButton("reset.png", "reset_hover.png");
        resetButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent ev, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game));

            }

            @Override
            public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                return true;
            }

        });
       // resetButton.setPosition(
       //         Gdx.graphics.getWidth()  - resetButton.getWidth(),
       //         Gdx.graphics.getHeight()  - resetButton.getHeight());

       // stage.addActor(resetButton);

        Table table = new Table();
        //table.setFillParent(true);
        stage.addActor(table);
        table.setPosition(0.85f*Gdx.graphics.getWidth(), 0.5f*Gdx.graphics.getHeight());
        table.add(resetButton);
        table.row().padTop(100);
        table.add(menuButton);
        table.row().padTop(100);
        table.add(exitButton);

        Gdx.input.setInputProcessor(stage);

    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0.5f, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        stage.act();
        stage.draw();
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        game.batch.dispose();
        Gdx.app.exit();
    }
}
