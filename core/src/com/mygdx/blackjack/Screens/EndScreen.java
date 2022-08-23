package com.mygdx.blackjack.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
        String winner = "";
        if (game.p1Score < 21){
            if (game.p2Score > 21){
                winner = "player";
            } else if (game.p2Score < game.p1Score) {

                winner = "player";
            }

        } else if (game.p2Score > 21) {
            winner = "no one";

        }
        else{
            winner = "dealer";
        }
        String labelText = ("The winner is " + winner + "\nplayer score = " + game.p1Score
                + "\n dealer score is " + game.p2Score);
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();
        Label result = new Label(labelText, style);
        stage.addActor(result);

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
        exitButton.setPosition(
                Gdx.graphics.getWidth()  - exitButton.getWidth(),
                Gdx.graphics.getHeight() / 5f - exitButton.getHeight());

        stage.addActor(exitButton);

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
        menuButton.setPosition(
                Gdx.graphics.getWidth()  - exitButton.getWidth(),
                Gdx.graphics.getHeight() / 3f - exitButton.getHeight());

        stage.addActor(menuButton);

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
        resetButton.setPosition(
                Gdx.graphics.getWidth()  - resetButton.getWidth(),
                Gdx.graphics.getHeight() / 2f - resetButton.getHeight());

        stage.addActor(resetButton);



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
