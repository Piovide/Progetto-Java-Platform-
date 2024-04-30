package view.main;

import java.awt.Color;

import javax.swing.JFrame;

import model.managers.TileManager;
import view.scenes.Editing;

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

	private void initClasses() {
		tileManager = new TileManager();
		render = new Render(this);
		gameScreen = new GameScreen(this);
		editing = new Editing(this);
	}

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
	public Render getRender() {
		return render;
	}

	public Editing getEditing() {
		return editing;
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public static TileManager getTileManager() {
		return tileManager;
	}
	public void close() {
		dispose();
		System.exit(0);
	}

	public static int getSCALE() {
		return SCALE;
	}
	
	

}