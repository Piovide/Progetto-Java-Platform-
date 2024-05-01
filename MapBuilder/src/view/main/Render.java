package view.main;

import java.awt.Graphics;
/**
 * questa classe si occupa dell rendering dell pannello 
 */
public class Render {

	private Game game;
	
	/**
	 * metodo costruttore 
	 * @param game
	 */
	public Render(Game game) {
		this.game = game;
	}
	/**
	 * questo metodo si occupa dell rendering di game 
	 * @param g
	 */
	public void render(Graphics g) {
		switch (GameStates.gameState) {
		case EDIT:
			game.getEditing().render(g);
			break;
		default:
			break;

		}

	}

}