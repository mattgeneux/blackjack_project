package com.mygdx.blackjack;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.blackjack.Objects.Player;
import com.mygdx.blackjack.Screens.MainMenuScreen;


public class Blackjack extends Game {

	public SpriteBatch batch;
	public BitmapFont font;
	public int p1Score;
	public int p2Score;
	public Player p1;
	public Player p2;

	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont(); // use libGDX's default Arial font
		this.setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render(); // important!

	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
