package managers;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helpz.ImgFix;
import helpz.LoadSave;
import objects.Tile;
import static helpz.Constants.Tiles.*;
import static helpz.Constants.Buttons.*;

public class TileManager {

	private int id = 0;
	
	private static final int TILE_SIZE = 32;
	
	public Tile SPROUT, 
				GRASS_N_PEABBLES, 
				LADDERS,
				WIND_GRAY,
				WIND_WHITE,
				DOORS,
				TERRAIN,
				WATER,
				FLAWERS,
				OTHER_TERRAINS,
				FLOATING_ISLAND,
				SPIKES,
				INFO,
				ARROWS,
				BIG_TREE,
				MOON,
				SUN,
				STARS,
				CLOUDS,
				DOUBLE_CLOUDS;

	private BufferedImage atlas;
	
	
	public ArrayList<Tile> tiles = new ArrayList<>();
	public ArrayList<Tile> winds = new ArrayList<>();
	public ArrayList<Tile> grassNPeabbles = new ArrayList<>();
	public ArrayList<Tile> ladders = new ArrayList<>();
	public ArrayList<Tile> doors = new ArrayList<>();
	public ArrayList<Tile> terrains = new ArrayList<>();
	public ArrayList<Tile> flawers = new ArrayList<>();
	public ArrayList<Tile> other_terrains = new ArrayList<>();
	public ArrayList<Tile> spikes = new ArrayList<>();
	public ArrayList<Tile> arrows = new ArrayList<>();
	public ArrayList<Tile> big_trees = new ArrayList<>();
	public ArrayList<Tile> moon = new ArrayList<>();
	public ArrayList<Tile> sun = new ArrayList<>();
	public ArrayList<Tile> clouds = new ArrayList<>();
	public ArrayList<Tile> double_clouds = new ArrayList<>();
	

	public TileManager() {

		loadAtalas();
		createTiles();

	}

	private void createTiles() {		
	    // Sprout animation
	    tiles.add(SPROUT = new Tile(getAniSprites(0, 0, 4), id++, SPROUT_TILE));

	    // Water animation
	    tiles.add(WATER = new Tile(getAniSprites(0, 6, 4), id++, WATER_TILE));

	    // Wind Gray animation
	    tiles.add(WIND_GRAY = new Tile((getAniSprites(0, 3, 5)), id++, WIND_GRAY_TILE));

	    //Floating island
	    tiles.add(FLOATING_ISLAND = new Tile(getSprite(0,9), id++, FLOATING_ISLAND_TILE));
	    
	    // Wind White animation
//	    tiles.add(WIND_WHITE = new Tile(ImgFix.getTrasparentImage(getAniSprites(0, 4, 5)), id++, WIND_WHITE_TILE));
	    
	    
	    // Grass and pebbles
	    for (int i = 0; i < 6; i++) {
	        grassNPeabbles.add(new Tile(ImgFix.getTrasparentImage(getSprite(i, 1)), id++, GRASS_N_PEABBLES_TILE));
	    }
	    
	    // Ladders
	    for (int i = 0; i < 6; i++) {
	        ladders.add(new Tile(ImgFix.getTrasparentImage(getSprite(i, 2)), id++, LADDERS_TILE));
	    }
	    
	    // Doors
		doors.add(DOORS = new Tile(ImgFix.getTrasparentImage(getSprite(0, 5)), id++, DOORS_TILE)); 
		doors.add(DOORS = new Tile(ImgFix.getTrasparentImage(getSprite(4, 5)), id++, DOORS_TILE)); 
		
		// Terrains
		terrains.add(TERRAIN = new Tile(ImgFix.getTrasparentImage(getSprite(1, 5)), id++, TERRAIN_TILE));

		// Flawers
	    for (int i = 0; i < 4; i++) {
	        flawers.add(FLAWERS = new Tile(ImgFix.getTrasparentImage(getSprite(i, 7)), id++, FLAWERS_TILE));
	    }
		
	    // Other terrains
	    for (int i = 0; i < 6; i++) {
	        other_terrains.add(OTHER_TERRAINS = new Tile(ImgFix.getTrasparentImage(getSprite(i, 8)), id++, OTHER_TERRAINS_TILE));
	    }
	    
	    // Spikes
	    for (int i = 1; i < 3; i++) {
	    	spikes.add(SPIKES = new Tile(ImgFix.getTrasparentImage(getSprite(i,9)), id++, SPIKES_TILE));
	    	spikes.add(SPIKES = new Tile(ImgFix.getMirroredImage(getSprite(i,9)), id++, SPIKES_TILE));
	    }   
	    
	    // Sun
	    for (int h = 0; h < 2; h++) {
        	for (int w = 0; w < 4; w++) {
        		SUN = new Tile(getSprite(w,  22 + h), id++, SUN_TILE);
        		SUN.setBtnConst(BTN_SUN);
        		sun.add(SUN);
        	}
	    }
	    // Moon
	    for (int h = 0; h < 2; h++) {
	    	for (int w = 0; w < 4; w++) {
	    		MOON = new Tile(getSprite(w,  24 + h), id++, MOON_TILE);
	    		MOON.setBtnConst(BTN_MOON);
	    		moon.add(MOON);
	    	}
	    }
	    
	    // CLOUDS
	    for (int h = 0; h < 2; h++) {
	    	for (int w = 0; w < 4; w++) {
	    		CLOUDS = new Tile(getSprite(w,  20 + h), id++, CLOUDS_TILE);
	    		CLOUDS.setBtnConst(BTN_CLOUDS);
	    		clouds.add(CLOUDS);
	    	}
	    }
	    
	    // DOUBLE CLOUDS
	    for (int h = 0; h < 2; h++) {
	    	for (int w = 0; w < 3; w++) {
	    		DOUBLE_CLOUDS = new Tile(getSprite(w,  26 + h), id++, DOUBLE_CLOUDS_TILE);
	    		DOUBLE_CLOUDS.setBtnConst(BTN_DOUBLE_CLOUDS);
	    		double_clouds.add(DOUBLE_CLOUDS);
	    	}
	    }
	    
	    // BIG TREE
	    for (int w = 0; w < 4; w++) {
	    	for (int h = 0; h < 3; h++) {
	    		BIG_TREE = new Tile(getSprite(w,  12 + h), id++, TREES_TILE);
	    		BIG_TREE.setBtnConst(BTN_BIG_TREE);
	    		big_trees.add(BIG_TREE);
	    	}
	    }
	    
		tiles.addAll(grassNPeabbles);
		tiles.addAll(ladders);
		tiles.addAll(doors);
		tiles.addAll(terrains);
		tiles.addAll(flawers);
		tiles.addAll(other_terrains);
		tiles.addAll(spikes);
		tiles.addAll(sun);
		tiles.addAll(moon);
		tiles.addAll(clouds);
		tiles.addAll(double_clouds);
		tiles.addAll(big_trees);
		
		
		tiles.add(new Tile(ImgFix.getTrasparentImage(getSprite(5, 0)), id++, id++));
		
		tiles.add(new Tile(getAniSprites(0, 28, 6), id+1, id+1));
		System.out.println("Tiles created: " + id);
	}

	private void loadAtalas() {
		atlas = LoadSave.getSpriteAtlas();
	}
	
	public Tile getGomma() {
		return tiles.get(tiles.size()-2);
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

	private BufferedImage[] getAniSprites(int xCord, int yCord, int nAni) {
		BufferedImage[] arr = new BufferedImage[nAni];
		for (int i = 0; i < nAni; i++) {
			arr[i] = getSprite(xCord + i, yCord);
		}

		return arr;

	}

	private BufferedImage getSprite(int xCord, int yCord) {
		return atlas.getSubimage(xCord * TILE_SIZE, yCord * TILE_SIZE, TILE_SIZE, TILE_SIZE);
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

	public ArrayList<Tile> getGrassNPeabbles() {
		return grassNPeabbles;
	}

	public ArrayList<Tile> getLadders() {
		return ladders;
	}

	public ArrayList<Tile> getDoors() {
		return doors;
	}

	public ArrayList<Tile> getTerrains() {
		return terrains;
	}

	public ArrayList<Tile> getFlawers() {
		return flawers;
	}

	public ArrayList<Tile> getOther_terrains() {
		return other_terrains;
	}

	public ArrayList<Tile> getSpikes() {
		return spikes;
	}

	public ArrayList<Tile> getArrows() {
		return arrows;
	}

	public ArrayList<Tile> getBig_trees() {
		return big_trees;
	}

	public ArrayList<Tile> getMoon() {
		return moon;
	}

	public ArrayList<Tile> getSun() {
		return sun;
	}

	public ArrayList<Tile> getDouble_clouds() {
		return double_clouds;
	}
	
	public ArrayList<Tile> getWind() {
		return winds;
	}

	public int getTilesLenght() {
		return tiles.size();
	}

	public ArrayList<Tile> getClouds() {
		return clouds;
	}


}
