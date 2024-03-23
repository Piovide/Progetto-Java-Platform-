package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static main.GameStates.*;

import main.Game;
import main.GameStates;

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
	    System.out.println("Key pressed: " + e.getKeyCode());
	    if (GameStates.gameState == EDIT) {
	        System.out.println("Sending key event to editor" + game.getEditor());
	        game.getEditor().keyPressed(e);
	    }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
