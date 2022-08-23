package com.mygdx.blackjack.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
import com.mygdx.blackjack.Objects.Card;
import com.mygdx.blackjack.Objects.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameScreen implements Screen {
    final Blackjack game;
    final Stage stage;
    OrthographicCamera camera;
    private int counter = 0;
    public List<int[]> picked;
    public Player p1;
    public Player p2;
    public boolean gameover = false;

    enum Turn{
        PLAYER,
        DEALER
    }

    public Turn turn;
    public GameScreen (final Blackjack game){
        this.game = game;

        stage = new Stage(new ScreenViewport());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        picked = new ArrayList<>();
        p1 = new Player(picked);
        p2 = new Player(picked);
        setup(p1, p2);
        turn = Turn.PLAYER;
        playerLoop(p1);


    }

    public void playerLoop(Player p1){
        if (gameover == false) {
            // player turn
            if (turn == Turn.PLAYER){
                if (p1.getTotal() >= 21){

                    gameover = true;
                }
            }
        }
        else{

            System.out.println("gameover" + p1.getTotal() + p2.getTotal());
            game.p1Score = p1.getTotal();
            game.p2Score = p2.getTotal();


        }
    }

    public void dealerLoop(Player p2){
        // dealer turn
        while (gameover == false){
            if  (p2.getTotal() < 16){
                p2.addCard(picked);
                displayDeck(p2, true);
                try{

                    Thread.sleep(200);
                }catch(InterruptedException ex){
                    //do stuff
                }

            }
            else{
                try{
                    Thread.sleep(2000);
                }catch(InterruptedException ex){
                    //do stuff
                }
                gameover = true;
                System.out.println("gameover" + p1.getTotal() + p2.getTotal());

            }
        }
        game.p1Score = p1.getTotal();
        game.p2Score = p2.getTotal();

    }


    public void setup1(Player p1, Player p2){

        List<Card> p1Deck = p1.getDeck();
        int counter = p1Deck.size() - 2;
        for (Card card : p1Deck){
            Image cardImage = card.getCardImage();
            cardImage.setWidth(125);
            cardImage.setHeight(182);
            cardImage.setX(cardImage.getWidth() * 3 * counter / 2);
            cardImage.setY(100);
            stage.addActor(cardImage);
            counter++;
        }


        List<Card> p2Deck = p2.getDeck();
        counter = p2Deck.size() - 2;
        for (Card card : p2Deck){
            Image cardImage = card.getCardImage();
            cardImage.setWidth(125);
            cardImage.setHeight(182);
            cardImage.setX(cardImage.getWidth() * 3 * counter / 2);
            cardImage.setY(400);
            stage.addActor(cardImage);
            counter++;
        }
    }

    public void setup(Player p1, Player p2){
        displayDeck(p1, false);
        displayDeck(p2, true);
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
    public void addCard1(int counter){
        Random rand = new Random();
        int suit = rand.nextInt(3);
        int number = rand.nextInt(12);

        Card card = new Card(suit, number);

        Image cardImage = card.getCardImage();
        cardImage.setWidth(125);
        cardImage.setHeight(182);
        cardImage.setX(cardImage.getWidth() * 3 * counter / 2);
        cardImage.setY(100);

        stage.addActor(cardImage);
    }

    public void addCard(){
        p1.addCard(picked);
        displayDeck(p1, false);
        playerLoop(p1);
    }

    public void endGame(Stage stage){
        String winner = "";
        if (p1.getTotal() < 21){
            if (p2.getTotal() > 21){
                winner = "player";
            } else if (p2.getTotal() < p1.getTotal()) {

                winner = "player";
            }

        } else if (p2.getTotal() > 21) {
            winner = "no one";

        }
        else{
            winner = "dealer";
        }
        String labelText = ("The winner is " + winner + "\nplayer score = " + p1.getTotal()
                + "\n dealer score is " + p2.getTotal());
        Label.LabelStyle style = new Label.LabelStyle();
        //style.font = new BitmapFont(Gdx.files.internal("MachineScript.ttf"));
        Label result = new Label(labelText, style);
        stage.addActor(result);
    }


    @Override
    public void show() {
        Texture addButtonTexture = new Texture("addCard.png");
        Texture addButtonActiveTexture = new Texture("addCardActive.png");
        TextureRegion addButtonRegion = new TextureRegion(addButtonTexture);
        TextureRegionDrawable addButtonDraw = new TextureRegionDrawable(addButtonRegion);
        TextureRegion addButtonRegionActive = new TextureRegion(addButtonActiveTexture);
        TextureRegionDrawable  addButtonDrawActive = new TextureRegionDrawable(addButtonRegionActive);
        ImageButton.ImageButtonStyle addStyle = new ImageButton.ImageButtonStyle();
        addStyle.up = addButtonDraw;
        addStyle.over = addButtonDrawActive;
        Actor addButton = new ImageButton(addStyle); //Set the button up
        addButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent ev, float x, float y, int pointer, int button) {
              // addCard(counter);
              // counter += 1;
                addCard();
                playerLoop(p1);
            }

            @Override
            public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                return true;
            }

        });

        addButton.setPosition(
                Gdx.graphics.getWidth() - addButton.getWidth(),
                Gdx.graphics.getHeight() - addButton.getHeight());
        stage.addActor(addButton);

        Texture exitTexture = new Texture("exit.png");
        Texture exitTextureActive = new Texture("exit_hover.png");
        TextureRegion exitRegion = new TextureRegion(exitTexture);
        TextureRegion exitRegionActive = new TextureRegion(exitTextureActive);
        TextureRegionDrawable exitDraw = new TextureRegionDrawable(exitRegion);
        TextureRegionDrawable exitDrawActive = new TextureRegionDrawable(exitRegionActive);

        ImageButton.ImageButtonStyle exitStyle = new ImageButton.ImageButtonStyle();
        exitStyle.up = exitDraw;
        exitStyle.over = exitDrawActive;

        Actor exitButton = new ImageButton(exitStyle); //Set the button up

        exitButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent ev, float x, float y, int pointer, int button) {
                dispose();
                // game.setScreen(new EndScreen(game));
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

        Texture changeTexture = new Texture("exit.png");
        Texture changeTextureActive = new Texture("exit_hover.png");
        TextureRegion changeRegion = new TextureRegion(changeTexture);
        TextureRegion changeRegionActive = new TextureRegion(changeTextureActive);
        TextureRegionDrawable changeDraw = new TextureRegionDrawable(changeRegion);
        TextureRegionDrawable changeDrawActive = new TextureRegionDrawable(changeRegionActive);
        ImageButton.ImageButtonStyle changeStyle = new ImageButton.ImageButtonStyle();
        changeStyle.up = changeDraw;
        changeStyle.over = changeDrawActive;
        Actor changeButton = new ImageButton(changeStyle); //Set the button up
        changeButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent ev, float x, float y, int pointer, int button) {

               turn = Turn.DEALER;
               dealerLoop(p2);
            }

            @Override
            public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                return true;
            }

        });

        changeButton.setPosition(
                Gdx.graphics.getWidth()  - changeButton.getWidth(),
                Gdx.graphics.getHeight() / 2f - changeButton.getHeight());


        stage.addActor(changeButton);

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        game.batch.dispose();
        Gdx.app.exit();
    }

    @Override
    public void pause() {

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

        if (gameover == true){

            game.setScreen(new EndScreen(game));
        }


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {

    }
}
