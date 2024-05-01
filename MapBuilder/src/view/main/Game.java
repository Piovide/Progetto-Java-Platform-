package view.main;

import java.awt.Color;

import javax.swing.JFrame;

import model.managers.TileManager;
import view.scenes.Editing;


/**
 * questa classe gestice i thread e iniziallizza le classi e la finestra 
 */
@SuppressWarnings("serial")
public class Game extends JFrame implements Runnable {

	protected GameScreen gameScreen;
	private Thread gameThread;

	private final double FPS_SET = 120.0;
	private final double UPS_SET = 60.0;
	
	private final static int SCALE = 1;
	
	
	// Classes
	private Render render;
	private Editing editing;

	private static TileManager tileManager;
	/**
	 * metodo cotruttore 
	 */
	public Game() {
		initClasses();
		gameScreen.initInputs(this);
		setBounds(getToolkit().getScreenSize().width/2 - 1920/2, getToolkit().getScreenSize().height/2 - 1080/2, getToolkit().getScreenSize().width, getToolkit().getScreenSize().height);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(new Color(219, 207, 117));
		setLocationRelativeTo(null);
		setTitle("LevelBuilder");
		add(gameScreen);
		addKeyListener(gameScreen.getKeyboardListener());
		pack();
		setVisible(true);
	}
	/**
	 * questo metodo inizializza le classi 
	 */
	private void initClasses() {
		tileManager = new TileManager();
		render = new Render(this);
		gameScreen = new GameScreen(this);
		editing = new Editing(this);
	}
	/**
	 * questo metodo fa iniziare il thread 
	 */
	public void start() {
		gameThread = new Thread(this) {
		};

		gameThread.start();
	}

	private void updateGame() {
		switch (GameStates.gameState) {
		case EDIT:
			editing.update();
			break;
		default:
			break;
		}
	}

	@SuppressWarnings("unused")
	@Override
	/**
	 * questo metodo appartiene alla classe runnable 
	 */
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis();

		int frames = 0;
		int updates = 0;

		long now;

		while (true) {
			now = System.nanoTime();

			// Render
			if (now - lastFrame >= timePerFrame) {
				repaint();
				lastFrame = now;
				frames++;
			}

			// Update
			if (now - lastUpdate >= timePerUpdate) {			
				updateGame();
				lastUpdate = now;
				updates++;
			}

		}

	}

	// Getters and setters
	/**
	 * questo metodo restituisce l'oggetto render 
	 * @return Render 
	 */
	public Render getRender() {
		return render;
	}
	/**
	 * questo metodo restituisce l'oggetto editing 
	 * @return Editing 
	 */
	public Editing getEditing() {
		return editing;
	}
	
	/**
	 * questo metodo restituisce l'oggetto gameScreen 
	 * @return gameScreen 
	 */
	public GameScreen getGameScreen() {
		return gameScreen;
	}
	/**
	 * vquesto metodo restituisce l'oggetto tileManager 
	 * @return tileManager
	 */
	public static TileManager getTileManager() {
		return tileManager;
	}
	/**
	 * questo emtodo chiude la finestra e il pannello 
	 */
	public void close() {
		dispose();
		System.exit(0);
	}
	/**
	 * questo metodo restituisce l'int scale 
	 * @return int 
	 */
	public static int getSCALE() {
		return SCALE;
	}
	
	

}