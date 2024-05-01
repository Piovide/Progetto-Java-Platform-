package view.main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import controller.inputs.MyKeyboardListener;
import controller.inputs.MyMouseListener;

@SuppressWarnings("serial")
/**
 * questa classe e il panneello 
 */
public class GameScreen extends JPanel {

	private Game game;
	private Dimension size;

	private MyMouseListener myMouseListener;
	private MyKeyboardListener keyboardListener;
	/**
	 * metodo costuttore 
	 * @param game
	 */
	public GameScreen(Game game) {
		this.game = game;
		setPanelSize();
	}
	/**
	 * questo metodo assegna i listener 
	 * @param game
	 */
	public void initInputs(Game game) {
		myMouseListener = new MyMouseListener(game);
		keyboardListener = new MyKeyboardListener(game);

		addKeyListener(keyboardListener);
		addMouseListener(myMouseListener);
		addMouseMotionListener(myMouseListener);

		requestFocus();
	}
	/**
	 * questo metodo imposta le dimensioni dell pannello 
	 */
	private void setPanelSize() {
		size = new Dimension(1920, 1080);
		
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);

	}
	/**
	 * questo metodo dipinge sull pannello grapich 
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		game.getRender().render(g);

	}
	/**
	 * questo metodo ritorna l'oggetto keyboardListener
	 * @return keyboardListener
	 */
	public MyKeyboardListener getKeyboardListener() {
		return keyboardListener;
	}


}