package model.managers;

import static controller.helpz.Constants.Tiles.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import controller.helpz.ImgFix;
import controller.helpz.LoadSave;
import model.objects.Tile;
/**
 * questa classe crea gli array list associati ai tiles in qui vengono memorizzate le informazioni riguardanti i tiles 
 */
public class TileManager {

	private int id = 0;

	private static final int TILE_SIZE = 32;

	public Tile WATER_BOTTOM, WATER_TOP, GRASS, CANNON, SPIKES, BARRELS, BOX, POTION_BLUE, POTION_RED, OUTSIDE_TERRAIN,
			OUTSIDE_PILLAR, OUTSIDE_FLOWTING, SHIP, TREES, GOMMA, CRABBY, PINKSTAR, SHARK, PLAYER;

	private BufferedImage atlas;

	public ArrayList<Tile> tiles = new ArrayList<>();

	public ArrayList<Tile> water_bottom = new ArrayList<>();
	public ArrayList<Tile> water_top = new ArrayList<>();
	public ArrayList<Tile> grass = new ArrayList<>();
	public ArrayList<Tile> cannon = new ArrayList<>();
	public ArrayList<Tile> spikes = new ArrayList<>();
	public ArrayList<Tile> barrels = new ArrayList<>();
	public ArrayList<Tile> potions = new ArrayList<>();
	public ArrayList<Tile> outside_terrain = new ArrayList<>();
	public ArrayList<Tile> outside_pillar = new ArrayList<>();
	public ArrayList<Tile> outside_floating = new ArrayList<>();
	public ArrayList<Tile> ship = new ArrayList<>();
	public ArrayList<Tile> trees = new ArrayList<>();
	public ArrayList<Tile> entities = new ArrayList<>();

	/**
	 * metodo costruttore
	 */
	public TileManager() {
		createTiles();

	}
	/**
	 * questo metodo aggiunge tutti i tiles ai corrispettivi arraylist e in fine li aggiunge all arylist tiles 
	 */
	private void createTiles() {

		// Water bottom 0
		water_bottom.add(WATER_BOTTOM = new Tile(getSprite(0, 0, LoadSave.WATER_BOTTOM), id++, WATER_BOTTOM_TILE));

		// Water top 1
		water_top.add(WATER_TOP = new Tile(getAniSprites(0, 0, 4, LoadSave.WATER_TOP), id++, WATER_TOP_TILE));

		// Grass 2-4
		for (int x = 0; x < 3; x++)
			grass.add(GRASS = new Tile(getSprite(x, 0, LoadSave.GRASS_ATLAS, 22, 22), id++, GRASS_TILE));
		// Cannon Right 5
		cannon.add(CANNON = new Tile(ImgFix.getMirroredImage(getSprite(0, 0, LoadSave.CANNON_ATLAS, 40, 26)), id++,
				CANNON_TILE));

		// Cannon Left 6
		cannon.add(CANNON = new Tile(getSprite(0, 0, LoadSave.CANNON_ATLAS, 40, 26), id++, CANNON_TILE));

		// Spikes 7
		spikes.add(SPIKES = new Tile(getSprite(0, 0, LoadSave.TRAP_ATLAS), id++, SPIKES_TILE));

		// Barrels 8
		barrels.add(BARRELS = new Tile(getSprite(0, 0, LoadSave.CONTAINER_ATLAS, 35, 30), id++, BARRELS_TILE));

		// box 9
		barrels.add(BOX = new Tile(getSprite(0, 1, LoadSave.CONTAINER_ATLAS, 35, 30), id++, BARRELS_TILE));

		// Potion blue 10
		potions.add(POTION_BLUE = new Tile(getAniSprites(0, 0, 7, LoadSave.POTION_ATLAS, 12, 16), id++, POTIONS_TILE));
		
		// Potion red 11
		potions.add(POTION_RED = new Tile(getAniSprites(0, 1, 7, LoadSave.POTION_ATLAS, 12, 16), id++, POTIONS_TILE));


		// Outside terrain 12_20
		for (int y = 0; y < 3; y++)
			for (int x = 0; x < 3; x++)
				outside_terrain.add(
						OUTSIDE_TERRAIN = new Tile(getSprite(x, y, LoadSave.LEVEL_ATLAS), id++, OUTSIDE_TERRAIN_TILE));

		// Outside pillar 21-23

		for (int y = 0; y < 3; y++)
			outside_pillar
					.add(OUTSIDE_PILLAR = new Tile(getSprite(3, y, LoadSave.LEVEL_ATLAS), id++, OUTSIDE_PILLAR_TILE));
		

		// Outside floating 24-27
		for (int x = 0; x < 4; x++)
				outside_floating.add(OUTSIDE_FLOWTING = new Tile(getSprite(x, 3, LoadSave.LEVEL_ATLAS), id++,
						OUTSIDE_FLOWTING_TILE));
		
		// Ship 28-31
		for (int y = 0; y < 2; y++)
			for (int x = 0; x < 2; x++)
				ship.add(SHIP = new Tile(getSprite(x, y, LoadSave.SHIP, 39, 36), id++, SHIP_TILE));

		
		// Trees 32-34
		for (int y = 0; y < 3; y++)
			for (int x = 0; x < 1; x++)
				trees.add(TREES = new Tile(getSprite(x, y, LoadSave.TREE_ONE_ATLAS, 39, 30), id++, TREES_TILE));
		//35-38
		for (int y = 0; y < 2; y++)
			for (int x = 0; x < 2; x++) {
				trees.add(TREES = new Tile(getSprite(x, y, LoadSave.TREE_TWO_ATLAS, 31, 27), id++, TREES_TILE));
		}
		//39-42
		for (int y = 0; y < 2; y++)
			for (int x = 0; x < 2; x++)
				trees.add(TREES = new Tile(ImgFix.getMirroredImage(getSprite(x, y, LoadSave.TREE_TWO_ATLAS, 31, 27)), id++, TREES_TILE));
		

		// Crabs 43
		entities.add(CRABBY = new Tile(getAniSprites(0, 0, 9, LoadSave.CRABBY_SPRITE, 72, 29), id++, CRABBY_TILE));

		// Pinkstars 44
		entities.add(PINKSTAR = new Tile(getAniSprites(0, 0, 8, LoadSave.PINKSTAR_ATLAS, 34, 29), id++, PINKSTAR_TILE));

		// Sharks 45
		entities.add(SHARK = new Tile(getAniSprites(0, 0, 8, LoadSave.SHARK_ATLAS, 34, 28), id++, SHARK_TILE));

		// Player 46
		entities.add(PLAYER = new Tile(getAniSprites(0, 0, 5, LoadSave.PLAYER_ATLAS, 64, 32), id++, PLAYER_TILE));

		tiles.addAll(water_bottom);
		tiles.addAll(water_top);
		tiles.addAll(grass);
		tiles.addAll(cannon);
		tiles.addAll(spikes);
		tiles.addAll(barrels);
		tiles.addAll(potions);
		tiles.addAll(outside_terrain);
		tiles.addAll(outside_pillar);
		tiles.addAll(outside_floating);
		tiles.addAll(ship);
		tiles.addAll(trees);
		tiles.addAll(entities);

		// Gomma 47
		tiles.add(GOMMA = new Tile(getSprite(11, 0, LoadSave.LEVEL_ATLAS), id, GOMMA_TILE));

//		System.out.println("Tiles created: " + id);
	}

	/**
	 * questo metodo assegna ad atlas il png associato alla stringa di input 
	 * @param path
	 */
	private void loadAtalas(String path) {
		atlas = LoadSave.getSpriteAtlas(path);
	}
	/**
	 * questo metodo restituisce la dimensione -1 dell' araylist tiles 
	 * @return Tile 
	 */
	public Tile getGomma() {
		return tiles.get(tiles.size() - 1);
	}
	/**
	 * questo metodo restituisce il tile associato all' id di input 
	 * @param id
	 * @return Tile
	 */
	public Tile getTile(int id) {
		return tiles.get(id);
	}
	/**
	 * questo metodo restituisce lo sprite associato all' id di input 
	 * @param id
	 * @return BufferedImage 
	 */
	public BufferedImage getSprite(int id) {
		return tiles.get(id).getSprite();
	}
	/**
	 * questo metodo restituisce lo sprite associato all' id di input nell momento indicato dalindice 
	 * usato per gli oggetti e le entita animate 
	 * @param id
	 * @param animationIndex
	 * @return BufferedImage 
	 */
	public BufferedImage getAniSprite(int id, int animationIndex) {
		return tiles.get(id).getSprite(animationIndex);
	}
	
	/**
	 * questo metodo restituisce un array di sprite che verranno trasformati in un animazione 
	 * @param xCord
	 * @param yCord
	 * @param nAni
	 * @param path
	 * @return BufferedImage[]
	 */
	private BufferedImage[] getAniSprites(int xCord, int yCord, int nAni, String path) {
		BufferedImage[] arr = new BufferedImage[nAni];
		for (int i = 0; i < nAni; i++) {
			arr[i] = getSprite(xCord + i, yCord, path);
		}

		return arr;

	}

	/**
	 * questo metodo restituisce un array di sprite (con grandezza diversa da 32x32) che verranno trasformati in un animazione 
	 * @param xCord
	 * @param yCord
	 * @param nAni
	 * @param path
	 * @param w
	 * @param h
	 * @return BufferedImage[]
	 */
	private BufferedImage[] getAniSprites(int xCord, int yCord, int nAni, String path, int w, int h) {
		BufferedImage[] arr = new BufferedImage[nAni];
		for (int i = 0; i < nAni; i++) {
			arr[i] = getSprite(xCord + i, yCord, path, w, h);
		}

		return arr;
	}
	/**
	 * questo metodo restituisce uno sprite (con grandezza diversa da 32x32)
	 * @param xCord
	 * @param yCord
	 * @param path
	 * @return BufferedImage 
	 */
	private BufferedImage getSprite(int xCord, int yCord, String path) {
		loadAtalas(path);
		return atlas.getSubimage(xCord * TILE_SIZE, yCord * TILE_SIZE, TILE_SIZE, TILE_SIZE);
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @param path
	 * @param w
	 * @param h
	 * @return BufferedImage
	 */
	private BufferedImage getSprite(int x, int y, String path, int w, int h) {
		loadAtalas(path);
		return atlas.getSubimage(x * w, y * h, w, h);
	}
	/**
	 * questo metodo restituisce un boolean qualora lo sprite associato all id faccia parte di un animazione 
	 * @param spriteID
	 * @return Boolean
	 */
	public boolean isSpriteAnimation(int spriteID) {
		return tiles.get(spriteID).isAnimation();
	}
	/**
	 * questo metodo restituisce l'arraylist tiles
	 * @return arrayList
	 */
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	/**
	 * questo metodo restituisce l'arraylist water_bottom
	 * @return arrayList
	 */
	public ArrayList<Tile> getWater_bottom() {
		return water_bottom;
	}
	/**
	 * questo metodo restituisce l'arraylist water_top
	 * @return arrayList
	 */
	public ArrayList<Tile> getWater_top() {
		return water_top;
	}
	/**
	 * questo metodo restituisce l'arraylist grass
	 * @return arrayList
	 */
	public ArrayList<Tile> getGrass() {
		return grass;
	}
	/**
	 * questo metodo restituisce l'arraylist cannon
	 * @return arrayList
	 */
	public ArrayList<Tile> getCannon() {
		return cannon;
	}
	/**
	 * questo metodo restituisce l'arraylist spikes
	 * @return arrayList
	 */
	public ArrayList<Tile> getSpikes() {
		return spikes;
	}
	/**
	 * questo metodo restituisce l'arraylist barrels
	 * @return arrayList
	 */
	public ArrayList<Tile> getBarrels() {
		return barrels;
	}
	/**
	 * questo metodo restituisce l'arraylist potions
	 * @return arrayList
	 */
	public ArrayList<Tile> getPotions() {
		return potions;
	}
	/**
	 * questo metodo restituisce l'arraylist outside_terrain
	 * @return arrayList
	 */
	public ArrayList<Tile> getOutside_terrain() {
		return outside_terrain;
	}
	/**
	 * questo metodo restituisce l'arraylist outside_pillar
	 * @return arrayList
	 */
	public ArrayList<Tile> getOutside_pillar() {
		return outside_pillar;
	}
	/**
	 * questo metodo restituisce l'arraylist outside_floating
	 * @return arrayList
	 */
	public ArrayList<Tile> getOutside_floating() {
		return outside_floating;
	}
	/**
	 * questo metodo restituisce l'arraylist ship
	 * @return arrayList
	 */
	public ArrayList<Tile> getShip() {
		return ship;
	}
	/**
	 * questo metodo restituisce l'arraylist trees
	 * @return arrayList
	 */
	public ArrayList<Tile> getTrees() {
		return trees;
	}
	/**
	 * questo metodo restituisce l'arraylist entities
	 * @return arrayList
	 */
	public ArrayList<Tile> getEntities() {
		return entities;
	}
	/**
	 * questo metodo restituisce la dimensione di tiles 
	 * @return
	 */
	public int getTilesLenght() {
		return tiles.size();
	}

}
