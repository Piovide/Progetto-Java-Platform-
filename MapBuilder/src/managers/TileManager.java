package managers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helpz.ImgFix;
import helpz.LoadSave;
import objects.Tile;
import static helpz.Constants.Tiles.*;

public class TileManager {

	public Tile GRASS, ROAD_LR, ROAD_TB, ROAD_B_TO_R, ROAD_L_TO_B, ROAD_L_TO_T, ROAD_T_TO_R, BL_WATER_CORNER, TL_WATER_CORNER, TR_WATER_CORNER, BR_WATER_CORNER, T_WATER, R_WATER, B_WATER,
			L_WATER, TL_ISLE, TR_ISLE, BR_ISLE, BL_ISLE;
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
				BIG_CLOUDS,
				SMALL_CLOUDS,
				DOUBLE_CLOUDS;

	private BufferedImage atlas;
	
	private static final int TILE_SIZE = 32;
	
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
	public ArrayList<Tile> big_clouds = new ArrayList<>();
	public ArrayList<Tile> small_clouds = new ArrayList<>();
	public ArrayList<Tile> double_clouds = new ArrayList<>();
	
	
	
	
	public ArrayList<Tile> roadsS = new ArrayList<>();
	public ArrayList<Tile> roadsC = new ArrayList<>();
	public ArrayList<Tile> corners = new ArrayList<>();
	public ArrayList<Tile> beaches = new ArrayList<>();
	public ArrayList<Tile> islands = new ArrayList<>();

	public TileManager() {

		loadAtalas();
		createTiles();

	}

	private void createTiles() {

		int id = 0;
		
	    // Sprout animation
	    tiles.add(SPROUT = new Tile(getAniSprites(0, 0, 4), id++, SPROUT_TILE));

	    // Water animation
	    winds.add(WATER = new Tile(getAniSprites(0, 6, 4), id++, WATER_TILE));

	    // Wind Gray animation
	    winds.add(WIND_GRAY = new Tile(ImgFix.getTrasparentImage(getAniSprites(0, 3, 5)), id++, WIND_GRAY_TILE));

	    // Wind White animation
	    tiles.add(WIND_WHITE = new Tile(ImgFix.getTrasparentImage(getAniSprites(0, 4, 5)), id++, WIND_WHITE_TILE));

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
		terrains.add(TERRAIN = new Tile(ImgFix.getTrasparentImage(getSprite(2, 5)), id++, TERRAIN_TILE));
		terrains.add(TERRAIN = new Tile(ImgFix.getTrasparentImage(getSprite(3, 5)), id++, TERRAIN_TILE));
		
		// Flawers
	    for (int i = 0; i < 4; i++) {
	        flawers.add(new Tile(ImgFix.getTrasparentImage(getSprite(i, 7)), id++, FLAWERS_TILE));
	    }
		
	    // Other terrains
	    for (int i = 0; i < 6; i++) {
	        other_terrains.add(new Tile(ImgFix.getTrasparentImage(getSprite(i, 8)), id++, OTHER_TERRAINS_TILE));
	    }
	    
	    islands.add(new Tile(ImgFix.getTrasparentImage(getSprite(0,9)), id++, FLOATING_ISLAND_TILE));
	    spikes.add(new Tile(ImgFix.getTrasparentImage(getSprite(1,9)), id++, SPIKES_TILE));
	    spikes.add(new Tile(ImgFix.getTrasparentImage(getSprite(2,9)), id++, SPIKES_TILE));
	    
		
		
		
		tiles.addAll(grassNPeabbles);
		tiles.addAll(ladders);
		tiles.addAll(doors);
		tiles.addAll(terrains);
		tiles.addAll(flawers);
		tiles.addAll(other_terrains);
		tiles.addAll(islands);
		tiles.addAll(spikes);
	}

	private void loadAtalas() {
		atlas = LoadSave.getSpriteAtlas();
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
		return atlas.getSubimage(xCord * TILE_SIZE, yCord * TILE_SIZE, 32, 32);
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

	public ArrayList<Tile> getSmall_clouds() {
		return small_clouds;
	}

	public ArrayList<Tile> getDouble_clouds() {
		return double_clouds;
	}

	public ArrayList<Tile> getCorners() {
		return corners;
	}

	public ArrayList<Tile> getBeaches() {
		return beaches;
	}

	public ArrayList<Tile> getIslands() {
		return islands;
	}

	public ArrayList<Tile> getWind() {
		return winds;
	}


}
