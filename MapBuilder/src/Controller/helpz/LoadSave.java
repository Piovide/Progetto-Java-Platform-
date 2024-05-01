package controller.helpz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import view.scenes.Editing;

public class LoadSave {

	public static final String PLAYER_ATLAS = "player_sprites.png";
	public static final String LEVEL_ATLAS = "outside_sprites.png";
	public static final String CRABBY_SPRITE = "crabby_sprite.png";
	public static final String POTION_ATLAS = "potions_sprites.png";
	public static final String CONTAINER_ATLAS = "objects_sprites.png";
	public static final String TRAP_ATLAS = "trap_atlas.png";
	public static final String CANNON_ATLAS = "cannon_atlas.png";
	public static final String PINKSTAR_ATLAS = "pinkstar_atlas.png";
	public static final String SHARK_ATLAS = "shark_atlas.png";
	public static final String GRASS_ATLAS = "grass_atlas.png";
	public static final String TREE_ONE_ATLAS = "tree_one_atlas.png";
	public static final String TREE_TWO_ATLAS = "tree_two_atlas.png";
	public static final String TREE_THREE_ATLAS = "tree_three_atlas.png";
	public static final String WATER_TOP = "water_atlas_animation.png";
	public static final String WATER_BOTTOM = "water.png";
	public static final String SHIP = "ship.png";
	public static final String BTN_EDITING = "btn_editing.png";
	public static final String BTN_ARROWS = "arrows.png";

	private static final String fileName = "level.png";

	public static void SaveLevel(int[][] lvlBlocks, int[][] lvlEntities, int[][] lvlObjects, String fileName) {
		int width = lvlBlocks[0].length;
		int height = lvlBlocks.length;

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Color[][] matrix = new Color[width][height];
		ArrayList<Color> colorMap = Constants.IdColori.getIdColori();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int r = colorMap.get(lvlBlocks[i][j]).getRed();
				int g = colorMap.get(lvlEntities[i][j]).getGreen();
				int b = colorMap.get(lvlObjects[i][j]).getBlue();
				
				matrix[j][i] = new Color(r, g, b); // Corretta l'assegnazione di coordinate
				image.setRGB(j, i, matrix[j][i].getRGB()); // Corretta l'assegnazione di coordinate
			}
		}

		// Salva l'immagine su disco
		File outputFile = new File("level.png");
		try {
			ImageIO.write(image, "png", outputFile);
			System.out.println("Immagine salvata con successo.");
		} catch (IOException e) {
			System.out.println("Errore durante il salvataggio dell'immagine: " + e.getMessage());
		}

	}

	public static void LoadLevelData(int[][] RED, int[][] GREEN, int[][] BLUE, Editing editing) {
		int[][] RedData = RED;
		int[][] GreenData = GREEN;
		int[][] BlueData = BLUE;
		int gomma = Constants.Tiles.GOMMA_TILE;

		try {
			File file = new File(fileName);
			if (!file.exists()) {
				RedData = new int[14][90];
				GreenData = new int[14][90];
				BlueData = new int[14][90];
				System.out.println("File Creato: level.png");
				for (int y = 0; y < RedData.length; y++) {
					for (int x = 0; x < RedData[0].length; x++) {
						RedData[y][x] = gomma;
						GreenData[y][x] = gomma;
						BlueData[y][x] = gomma;
					}
				}
				editing.setLvlBlocks(RedData);
				editing.setLvlEntities(GreenData);
				editing.setLvlObjects(BlueData);

			} else {
				BufferedImage image = ImageIO.read(file);

				int width = image.getWidth();
				int height = image.getHeight();
				RedData = new int[height][width];
				GreenData = new int[height][width];
				BlueData = new int[height][width];
				ArrayList<Color> colorMap = Constants.IdColori.getIdColori();

				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++) {
						Color c = new Color(image.getRGB(x, y));
						int red = c.getRed();
						int green = c.getGreen();
						int blue = c.getBlue();
						
							RedData[y][x] = colorMap.indexOf(new Color(red, gomma, gomma));
							GreenData[y][x] = colorMap.indexOf(new Color(gomma, green, gomma));
							BlueData[y][x] = colorMap.indexOf(new Color(gomma, gomma, blue));
							
							if (RedData[y][x] == -1) {
								RedData[y][x] = gomma;
							}
							if (GreenData[y][x] == -1) {
								GreenData[y][x] = gomma;
							}
							if (BlueData[y][x] == -1) {
								BlueData[y][x] = gomma;
							}
						
					}
				}
				editing.setLvlBlocks(RedData);
				editing.setLvlEntities(GreenData);
				editing.setLvlObjects(BlueData);
			}
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