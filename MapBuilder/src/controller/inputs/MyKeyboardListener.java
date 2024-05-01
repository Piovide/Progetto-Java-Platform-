package controller.inputs;

import static view.main.GameStates.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.main.Game;
import view.main.GameStates;
/**
 * questa classe gestice gli imput provenienti dalla tastiera
 */
public class MyKeyboardListener implements KeyListener {
	private Game game;
	
	/*
	 * metodo costruttore
	 */
	public MyKeyboardListener(Game game) {
		this.game = game;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/*
	 * 
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			game.dispose();
		}
		if (GameStates.gameState == EDIT) {
			game.getEditing().keyPressed(e);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
