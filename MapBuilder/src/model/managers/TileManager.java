package model.managers;

import static controller.helpz.Constants.Tiles.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import controller.helpz.ImgFix;
import controller.helpz.LoadSave;
import model.objects.Tile;

public class TileManager {

	private int id = 0;
	
	private static final int TILE_SIZE = 32;
	
	public Tile WATER_BOTTOM,
		        WATER_TOP,
		        GRASS,
		        CANNON,
		        SPIKES,
		        BARRELS,
		        BOX,
		        POTION_BLUE,
		        POTION_RED,
		        OUTSIDE_TERRAIN,
		        OUTSIDE_PILLAR,
		        OUTSIDE_CORNER,
		        OUTSIDE_FLOWTING,
		        SHIP,
		        TREES,
		        GOMMA;

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
	public ArrayList<Tile> outside_corner = new ArrayList<>();
	public ArrayList<Tile> outside_floating = new ArrayList<>();
	public ArrayList<Tile> ship = new ArrayList<>();
	public ArrayList<Tile> trees = new ArrayList<>();
	

	public TileManager() {
		createTiles();

	}
 	
	private void createTiles() {		

		// Water bottom
		water_bottom.add(WATER_BOTTOM = new Tile(getSprite(0, 0, LoadSave.WATER_BOTTOM), id++, WATER_BOTTOM_TILE));
		
		// Water top
		water_top.add(WATER_TOP = new Tile(getAniSprites(0, 0, 4, LoadSave.WATER_TOP), id++, WATER_TOP_TILE));
		
		// Grass
		for (int x = 0; x < 3; x++)
			grass.add(GRASS = new Tile(getSprite(x, 0, LoadSave.GRASS_ATLAS, 21, 32), id++, GRASS_TILE));
		
		// Cannon Right
		cannon.add(CANNON = new Tile(ImgFix.getMirroredImage(getSprite(0, 0, LoadSave.CANNON_ATLAS, 40, 26)), id++, CANNON_TILE));
		
		// Cannon Left
		cannon.add(CANNON = new Tile(getSprite(0, 0, LoadSave.CANNON_ATLAS, 40, 26), id++, CANNON_TILE));
		
		// Spikes
		spikes.add(SPIKES = new Tile(getSprite(0, 0, LoadSave.TRAP_ATLAS), id++, SPIKES_TILE));
		
		// Barrels
		barrels.add(BARRELS = new Tile(getSprite(0, 0, LoadSave.CONTAINER_ATLAS, 35, 30), id++, BARRELS_TILE));
		
		//box
		barrels.add(BOX = new Tile(getSprite(0, 1, LoadSave.CONTAINER_ATLAS, 35, 30), id++, BARRELS_TILE));
		
		// Potions blue
		potions.add(POTION_BLUE = new Tile(getAniSprites(0, 0, 7, LoadSave.POTION_ATLAS, 12, 16), id++, POTIONS_TILE));
		
		// Potions red
		potions.add(POTION_RED = new Tile(getAniSprites(0, 1, 7, LoadSave.POTION_ATLAS, 12, 16), id++, POTIONS_TILE));
	
		// Outside terrain
		for(int x = 0; x < 3; x++)
			for(int y = 0; y < 3; y++)
				outside_terrain.add(OUTSIDE_TERRAIN = new Tile(getSprite(x, y, LoadSave.LEVEL_ATLAS), id++, OUTSIDE_TERRAIN_TILE));
		
		// Outside pillar
		for(int x = 3; x < 4; x++)
			for(int y = 0; y < 3; y++)
				outside_pillar.add(OUTSIDE_PILLAR = new Tile(getSprite(x, y, LoadSave.LEVEL_ATLAS), id++, OUTSIDE_PILLAR_TILE));
		
		// Outside corner
		for (int x = 4; x < 12; x++)
			for (int y = 0; y < 4; y++)
				if(x != 11 && y ==0)
					outside_corner.add(OUTSIDE_CORNER = new Tile(getSprite(x, y, LoadSave.LEVEL_ATLAS), id++, OUTSIDE_CORNER_TILE));
		
		// Outside floating
		for(int x = 0; x < 4; x++)
			for(int y = 0; y < 2; y++)
				outside_floating.add(OUTSIDE_FLOWTING = new Tile(getSprite(x, y, LoadSave.LEVEL_ATLAS), id++, OUTSIDE_FLOWTING_TILE));
		
		// Ship
		for (int y = 0; y < 2; y++)
			for (int x = 0; x < 2; x++)
				ship.add(SHIP = new Tile(getSprite(x, y, LoadSave.SHIP, 39, 36), id++, SHIP_TILE));
		
		// Trees
		for (int y = 0; y < 4; y++)
			for (int x = 0; x < 2; x++)
				trees.add(TREES = new Tile(getSprite(x, y, LoadSave.TREE_ONE_ATLAS, 20, 23), id++, TREES_TILE));
		
		tiles.addAll(water_bottom);
		tiles.addAll(water_top);
		tiles.addAll(grass);
		tiles.addAll(cannon);
		tiles.addAll(spikes);
		tiles.addAll(barrels);
		tiles.addAll(potions);
		tiles.addAll(outside_terrain);
		tiles.addAll(outside_pillar);
		tiles.addAll(outside_corner);
		tiles.addAll(outside_floating);
		tiles.addAll(ship);
		tiles.addAll(trees);
		tiles.add(GOMMA = new Tile(getSprite(11, 0, LoadSave.LEVEL_ATLAS), id, GOMMA_TILE));
		
		System.out.println("Tiles created: " + id);
	}

	private void loadAtalas(String path) {
		atlas = LoadSave.getSpriteAtlas(path);
	}
	
	public Tile getGomma() {
		return tiles.get(tiles.size()-1);
	}
	
	public Tile getTile(int id) {
		return tiles.get(id);
	}

	public BufferedImage getSprite(int id) {
		return tiles.get(id).getSprite();
	}

	public BufferedImage getAniSprite(int id, int animationIndex) {
		return tiles.get(id).getSprite(animationIndex);
	}

	private BufferedImage[] getAniSprites(int xCord, int yCord, int nAni, String path) {
		BufferedImage[] arr = new BufferedImage[nAni];
		for (int i = 0; i < nAni; i++) {
			arr[i] = getSprite(xCord + i, yCord, path);
		}

		return arr;

	}
	private BufferedImage[] getAniSprites(int xCord, int yCord, int nAni, String path, int w, int h) {
		BufferedImage[] arr = new BufferedImage[nAni];
		for (int i = 0; i < nAni; i++) {
			arr[i] = getSprite(xCord + i, yCord, path, w, h);
		}

		return arr;
	}

	private BufferedImage getSprite(int xCord, int yCord, String path) {
		loadAtalas(path);
		return atlas.getSubimage(xCord * TILE_SIZE, yCord * TILE_SIZE, TILE_SIZE, TILE_SIZE);
	}
	
	private BufferedImage getSprite(int x, int y, String path, int w, int h) {
		loadAtalas(path);
		return atlas.getSubimage(x * w, y * h, w, h);
	}

	public boolean isSpriteAnimation(int spriteID) {
		return tiles.get(spriteID).isAnimation();
	}

	public int[][] getTypeArr() {
		int[][] idArr = LoadSave.GetLevelData();
		int[][] typeArr = new int[idArr.length][idArr[0].length];

		for (int j = 0; j < idArr.length; j++) {
			for (int i = 0; i < idArr[j].length; i++) {
				int id = idArr[j][i];
				typeArr[j][i] = tiles.get(id).getTileType();
			}
		}

		return typeArr;

	}

	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	
	public ArrayList<Tile> getWater_bottom() {
		return water_bottom;
	}

	public ArrayList<Tile> getWater_top() {
		return water_top;
	}

	public ArrayList<Tile> getGrass() {
		return grass;
	}

	public ArrayList<Tile> getCannon() {
		return cannon;
	}

	public ArrayList<Tile> getSpikes() {
		return spikes;
	}

	public ArrayList<Tile> getBarrels() {
		return barrels;
	}

	public ArrayList<Tile> getPotions() {
		return potions;
	}

	public ArrayList<Tile> getOutside_terrain() {
		return outside_terrain;
	}

	public ArrayList<Tile> getOutside_pillar() {
		return outside_pillar;
	}

	public ArrayList<Tile> getOutside_corner() {
		return outside_corner;
	}

	public ArrayList<Tile> getOutside_floating() {
		return outside_floating;
	}

	public ArrayList<Tile> getShip() {
		return ship;
	}

	public ArrayList<Tile> getTrees() {
		return trees;
	}
	
	public int getTilesLenght() {
		return tiles.size();
	}



}
