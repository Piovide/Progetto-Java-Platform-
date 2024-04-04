package main;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import helpz.LoadSave;
import managers.TileManager;
import scenes.Editing;

@SuppressWarnings("serial")
public class Game extends JFrame implements Runnable {

	private GameScreen gameScreen;
	private Thread gameThread;

	private final double FPS_SET = 120.0;
	private final double UPS_SET = 60.0;

	// Classes
	private Render render;
	private Editing editing;

	private TileManager tileManager;

	public Game() {

		LoadSave.CreateFolder();

		createDefaultLevel();
		initClasses();		
		setBounds(getToolkit().getScreenSize().width/2 - 1920/2, getToolkit().getScreenSize().height/2 - 1080/2, getToolkit().getScreenSize().width, getToolkit().getScreenSize().height);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(new Color(219, 207, 117));
		setLocationRelativeTo(null);
		setTitle("LevelBuilder");
		add(gameScreen);
		pack();
		setVisible(true);
	}

	private void createDefaultLevel() {
		int[] arr = new int[300];
		for (int i = 0; i < arr.length; i++)
			arr[i] = 1;

		LoadSave.CreateLevel(arr);

	}

	private void initClasses() {
		tileManager = new TileManager();
		render = new Render(this);
		gameScreen = new GameScreen(this);
		editing = new Editing(this);
	}

	private void start() {
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

	public static void main(String[] args) {

		Game game = new Game();
		game.gameScreen.initInputs();
		game.start();

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

	public Editing getEditor() {
		return editing;
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public TileManager getTileManager() {
		return tileManager;
	}
	public void close() {
		dispose();
		System.exit(0);
	}

}