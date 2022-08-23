package com.mygdx.blackjack.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.blackjack.Blackjack;

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
