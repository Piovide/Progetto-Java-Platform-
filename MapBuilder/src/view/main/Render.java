package view.main;

import java.awt.Graphics;

public class Render {

	private Game game;

	public Render(Game game) {
		this.game = game;
	}

	public void render(Graphics g) {
		switch (GameStates.gameState) {
		case EDIT:
			game.getEditor().render(g);
			break;
		default:
			break;

		}

	}

}