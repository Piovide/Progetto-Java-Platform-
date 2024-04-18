package controller.inputs;

import static view.main.GameStates.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.main.Game;
import view.main.GameStates;

public class KeyboardListener implements KeyListener {
	private Game game;

	public KeyboardListener(Game game) {
		this.game = game;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
//	    if (GameStates.gameState == EDIT) {
//	        game.getEditor().keyPressed(e);
//	    }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (GameStates.gameState == EDIT) {
	        game.getEditor().keyPressed(e);
	    }
	}

}
