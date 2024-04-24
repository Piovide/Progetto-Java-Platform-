package controller.helpz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import javax.imageio.ImageIO;

import view.scenes.Editing;

public class LoadSave {

	public static final String PLAYER_ATLAS = "player_sprites.png";
	public static final String LEVEL_ATLAS = "outside_sprites.png";
	public static final String MENU_BUTTONS = "button_atlas.png";
	public static final String MENU_BACKGROUND = "menu_background.png";
	public static final String PAUSE_BACKGROUND = "pause_menu.png";
	public static final String SOUND_BUTTONS = "sound_button.png";
	public static final String URM_BUTTONS = "urm_buttons.png";
	public static final String VOLUME_BUTTONS = "volume_buttons.png";
	public static final String MENU_BACKGROUND_IMG = "background_menu.png";
	public static final String PLAYING_BG_IMG = "playing_bg_img.png";
	public static final String BIG_CLOUDS = "big_clouds.png";
	public static final String SMALL_CLOUDS = "small_clouds.png";
	public static final String CRABBY_SPRITE = "crabby_sprite.png";
	public static final String STATUS_BAR = "health_power_bar.png";
	public static final String COMPLETED_IMG = "completed_sprite.png";
	public static final String POTION_ATLAS = "potions_sprites.png";
	public static final String CONTAINER_ATLAS = "objects_sprites.png";
	public static final String TRAP_ATLAS = "trap_atlas.png";
	public static final String CANNON_ATLAS = "cannon_atlas.png";
	public static final String CANNON_BALL = "ball.png";
	public static final String DEATH_SCREEN = "death_screen.png";
	public static final String OPTIONS_MENU = "options_background.png";
	public static final String PINKSTAR_ATLAS = "pinkstar_atlas.png";
	public static final String QUESTION_ATLAS = "question_atlas.png";
	public static final String EXCLAMATION_ATLAS = "exclamation_atlas.png";
	public static final String SHARK_ATLAS = "shark_atlas.png";
	public static final String CREDITS = "credits_list.png";
	public static final String GRASS_ATLAS = "grass_atlas.png";
	public static final String TREE_ONE_ATLAS = "tree_one_atlas.png";
	public static final String TREE_TWO_ATLAS = "tree_two_atlas.png";
	public static final String GAME_COMPLETED = "game_completed.png";
	public static final String RAIN_PARTICLE = "rain_particle.png";
	public static final String WATER_TOP = "water_atlas_animation.png";
	public static final String WATER_BOTTOM = "water.png";
	public static final String SHIP = "ship.png";

	private static final String fileName = "level.png";

	public static void SaveLevel(int[][] levelData, String fileName) {
		int width = levelData[0].length;
		int height = levelData.length;

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Color[][] matrix = new Color[width][height];
		HashMap<Integer, Color> colorMap = Constants.IdColori.numeriColori;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				matrix[j][i] = colorMap.get(levelData[i][j]); // Corretta l'assegnazione di coordinate
			}
		}

		// Imposta i colori della BufferedImage utilizzando i valori della matrice
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				image.setRGB(j, i, matrix[j][i].getRGB()); // Corretta l'assegnazione di coordinate
			}
		}

		// Salva l'immagine su disco
		File outputFile = new File("level.png");
		try {
			ImageIO.write(image, "bmp", outputFile);
			System.out.println("Immagine salvata con successo.");
		} catch (IOException e) {
			System.out.println("Errore durante il salvataggio dell'immagine: " + e.getMessage());
		}

	}

	public static void LoadLevelData(int[][] RED, int[][] GREEN, int[][] BLUE, Editing editing) {
		int[][] RedData = RED;
		int[][] GreenData = GREEN;
		int[][] BlueData = BLUE;

		try {
			File file = new File(fileName);
			if (!file.exists()) {
				RedData = new int[14][90];
				System.out.println("File Creato: level.png");
				for (int y = 0; y < RedData.length; y++) {
					for (int x = 0; x < RedData[0].length; x++) {
						RedData[y][x] = 255;
						GreenData[y][x] = 255;
						BlueData[y][x] = 255;
					}
				}

			} else {
				BufferedImage image = ImageIO.read(file);

				int width = image.getWidth();
				int height = image.getHeight();
				RedData = new int[height][width];
				GreenData = new int[height][width];
				BlueData = new int[height][width];
				HashMap<Integer, Color> colorMap = Constants.IdColori.numeriColori;

				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++) {
						Color c = new Color(image.getRGB(x, y));
						int red = c.getRed();
						int green = c.getGreen();
						int blue = c.getBlue();
						
						RedData[y][x] = colorMap.entrySet().stream()
								.filter(entry -> entry.getValue().getRed() == red)
								.map(entry -> entry.getKey()).findFirst().orElse(0);
						
						GreenData[y][x] = colorMap.entrySet().stream()
								.filter(entry -> entry.getValue().getGreen() == green)
								.map(entry -> entry.getKey()).findFirst().orElse(0);
						
						BlueData[y][x] = colorMap.entrySet().stream()
								.filter(entry -> entry.getValue().getBlue() == blue)
								.map(entry -> entry.getKey()).findFirst().orElse(0);
					}
				}
			}
			editing.setLvlBlocks(RedData);
			editing.setLvlEntities(GreenData);
			editing.setLvlObjects(BlueData);

		} catch (IOException e) {
			System.out.println("Errore durante il caricamento del livello da BMP: " + e.getMessage());
		}
	}

	public static BufferedImage getSpriteAtlas(String path) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getClassLoader().getResourceAsStream(path);

		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
}