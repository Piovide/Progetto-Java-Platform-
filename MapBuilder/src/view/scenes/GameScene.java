package view.scenes;

import java.awt.image.BufferedImage;

import view.main.Game;

public class GameScene {

	protected Game game;
	public int animationIndex;
	protected int ANIMATION_SPEED = 15;
	protected int tick;
	/**
	 * metodo costruttore 
	 * @param game
	 */
	public GameScene(Game game) {
		this.game = game;
	}
	/**
	 * questo metodo restituisce l'oggetto game 
	 * @return Game 
	 */
	public Game getGame() {
		return game;
	}
	/**
	 * questo metodo restituisce un booleano true qualora l'id inserito sia associato ad un animazione  
	 * @param spriteID
	 * @return boolean 
	 */
	protected boolean isAnimation(int spriteID) {
		return game.getTileManager().isSpriteAnimation(spriteID);
	}
	/**
	 * questo metodo si occpua di far scorrere le animazioni con i vari stadi 
	 */
	protected void updateTick() {
		tick++;
		if (tick >= ANIMATION_SPEED) {
			tick = 0;
			animationIndex++;
			if (animationIndex >= 4)
				animationIndex = 0;
		}
	}
	/**
	 * questo metodo restituisce lo sprite associato all id 
	 * @param spriteID
	 * @return BufferedImage
	 */
	protected BufferedImage getSprite(int spriteID) {
		return game.getTileManager().getSprite(spriteID);
	}
	/**
	 * questo metodo restituisce lo sprite associato all id e lo stato dell animazione indicato dall indice 
	 * @param spriteID
	 * @return BufferedImage
	 */
	protected BufferedImage getSprite(int spriteID, int animationIndex) {
		return game.getTileManager().getAniSprite(spriteID, animationIndex);
	}

}
