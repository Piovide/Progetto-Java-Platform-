package helpz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class LoadSave {
	
	private static final String fileName = "../level.png";

	public static void SaveLevel(int[][] levelData) {

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
		File outputFile = new File(fileName);
		try {
			ImageIO.write(image, "bmp", outputFile);
			System.out.println("Immagine salvata con successo.");
		} catch (IOException e) {
			System.out.println("Errore durante il salvataggio dell'immagine: " + e.getMessage());
		}

	}


	public static int[][] LoadLevelData() {
		int[][] levelData = null;
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				levelData = new int[33][60];
				System.out.println("File Creato: level.png");
				for (int y = 0; y < 33; y++) {
					for (int x = 0; x < 60; x++) {
						levelData[y][x] = Constants.Tiles.GOMMA;
					}
				}

				return levelData;

			} else {
				BufferedImage image = ImageIO.read(file);

				int width = image.getWidth();
				int height = image.getHeight();	
				levelData = new int[height][width];
				HashMap<Integer, Color> colorMap = Constants.IdColori.numeriColori;

				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++) {
						int rgb = image.getRGB(x, y);
						levelData[y][x] = colorMap.entrySet().stream().filter(entry -> entry.getValue().getRGB() == rgb)
								.map(entry -> entry.getKey()).findFirst().orElse(0);
					}
				}
			}

		} catch (IOException e) {
			System.out.println("Errore durante il caricamento del livello da BMP: " + e.getMessage());
		}
		return levelData;
	}

	public static BufferedImage getSpriteAtlas() {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("Tiles.png");

		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

}
