package helpz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class LoadSave {

	public static String homePath = System.getProperty("user.home");
	public static String saveFolder = "Livelli";
	public static String levelFile = "level.txt";
	public static String filePath = homePath + File.separator + saveFolder + File.separator + levelFile;
	private static File lvlFile = new File(filePath);

	public static void CreateFolder() {
		File folder = new File(homePath + File.separator + saveFolder);
		if (!folder.exists())
			folder.mkdir();
	}
	public static void SaveLevel(int[][] levelData, String fileName) {
//        try (FileOutputStream fileOut = new FileOutputStream(fileName);
//             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
//            
//            objectOut.writeObject(levelData);
//            System.out.println("Livello salvato con successo!");
//
//        } catch (IOException e) {
//            System.out.println("Errore durante il salvataggio del livello: " + e.getMessage());
//        }
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
		File outputFile = new File("level.bmp");
		try {
		    ImageIO.write(image, "bmp", outputFile);
		    System.out.println("Immagine salvata con successo.");
		} catch (IOException e) {
		    System.out.println("Errore durante il salvataggio dell'immagine: " + e.getMessage());
		}

    }
	public static int[][] LoadLevelData(String fileName) {
		int[][] levelData = null;
	    try {
	        File file = new File(fileName);
	        BufferedImage image = ImageIO.read(file);
	        
	        int width = image.getWidth();
	        int height = image.getHeight();

			HashMap<Integer, Color> colorMap = Constants.IdColori.numeriColori;
	        levelData = new int[height][width];
	        
	        for (int y = 0; y < height; y++) {
	            for (int x = 0; x < width; x++) {
	                int rgb = image.getRGB(x, y);
	                levelData[y][x] = colorMap.entrySet().stream()
                            .filter(entry -> entry.getValue().getRGB() == rgb)
                            .map(entry -> entry.getKey())
                            .findFirst()
                            .orElse(0);
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
 
	public static void CreateLevel(int[] idArr) {
		if (lvlFile.exists()) {
			System.out.println("File: " + lvlFile + " already exists!");
			return;
		} else {
			try {
				lvlFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Adding start and end points to the new level.
			WriteToFile(idArr);
		}

	}

	private static void WriteToFile(int[] idArr) {
		try {
			PrintWriter pw = new PrintWriter(lvlFile);
			for (Integer i : idArr)
				pw.println(i);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void SaveLevel(int[][] idArr) {
		if (lvlFile.exists()) {
			WriteToFile(Utilz.TwoDto1DintArr(idArr));
		} else {
			System.out.println("File: " + lvlFile + " does not exists! ");
			return;
		}
	}

	private static ArrayList<Integer> ReadFromFile() {
	    ArrayList<Integer> list = new ArrayList<>();

	    try {
	        Scanner sc = new Scanner(lvlFile);

	        while (sc.hasNextLine()) {
	            String line = sc.nextLine();
	            int value = Integer.parseInt(line);
	            list.add(value);
	        }

	        sc.close();

	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }

	    return list;
	}


	public static int[][] GetLevelData() {
		if (lvlFile.exists()) {
			ArrayList<Integer> list = ReadFromFile();
			return Utilz.ArrayListTo2Dint(list, 33, 60);

		} else {
			System.out.println("File: " + lvlFile + " does not exists! ");
			return null;
		}

	}
}