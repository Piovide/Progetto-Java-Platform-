package view.main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import controller.inputs.MyKeyboardListener;
import controller.inputs.MyMouseListener;

@SuppressWarnings("serial")
public class GameScreen extends JPanel {

	private Game game;
	private Dimension size;

	private MyMouseListener myMouseListener;
	private MyKeyboardListener keyboardListener;

	public GameScreen(Game game) {
		this.game = game;
		setPanelSize();
	}

	public void initInputs(Game game) {
		myMouseListener = new MyMouseListener(game);
		keyboardListener = new MyKeyboardListener(game);

		addKeyListener(keyboardListener);
		addMouseListener(myMouseListener);
		addMouseMotionListener(myMouseListener);

		requestFocus();
	}

	private void setPanelSize() {
		size = new Dimension(1920, 1080);
		
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		game.getRender().render(g);

	}
	public MyKeyboardListener getKeyboardListener() {
		return keyboardListener;
	}


}