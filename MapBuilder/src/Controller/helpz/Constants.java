package controller.helpz;

import static controller.helpz.Constants.Tiles.SHIP_TILE;

import java.awt.Color;
import java.util.HashMap;

import model.objects.Tile;
import view.main.Game;

public class Constants {
	
	public static class ObjectConstants {

		public static final int RED_POTION = 0;
		public static final int BLUE_POTION = 1;
		public static final int BARREL = 2;
		public static final int BOX = 3;
		public static final int SPIKE = 4;
		public static final int CANNON_LEFT = 5;
		public static final int CANNON_RIGHT = 6;
		public static final int TREE_ONE = 7;
		public static final int TREE_TWO = 8;
		public static final int TREE_THREE = 9;

		public static final int RED_POTION_VALUE = 15;
		public static final int BLUE_POTION_VALUE = 10;

		public static final int CONTAINER_WIDTH_DEFAULT = 40;
		public static final int CONTAINER_HEIGHT_DEFAULT = 30;
		public static final int CONTAINER_WIDTH = (int) (Game.getSCALE() * CONTAINER_WIDTH_DEFAULT);
		public static final int CONTAINER_HEIGHT = (int) (Game.getSCALE() * CONTAINER_HEIGHT_DEFAULT);

		public static final int POTION_WIDTH_DEFAULT = 12;
		public static final int POTION_HEIGHT_DEFAULT = 16;
		public static final int POTION_WIDTH = (int) (Game.getSCALE() * POTION_WIDTH_DEFAULT);
		public static final int POTION_HEIGHT = (int) (Game.getSCALE() * POTION_HEIGHT_DEFAULT);

		public static final int SPIKE_WIDTH_DEFAULT = 32;
		public static final int SPIKE_HEIGHT_DEFAULT = 32;
		public static final int SPIKE_WIDTH = (int) (Game.getSCALE() * SPIKE_WIDTH_DEFAULT);
		public static final int SPIKE_HEIGHT = (int) (Game.getSCALE() * SPIKE_HEIGHT_DEFAULT);

		public static final int CANNON_WIDTH_DEFAULT = 40;
		public static final int CANNON_HEIGHT_DEFAULT = 26;
		public static final int CANNON_WIDTH = (int) (CANNON_WIDTH_DEFAULT * Game.getSCALE());
		public static final int CANNON_HEIGHT = (int) (CANNON_HEIGHT_DEFAULT * Game.getSCALE());

		public static int GetSpriteAmount(int object_type) {
			switch (object_type) {
			case RED_POTION, BLUE_POTION:
				return 7;
			case BARREL, BOX:
				return 8;
			case CANNON_LEFT, CANNON_RIGHT:
				return 7;
			}
			return 1;
		}

		public static int GetTreeOffsetX(int treeType) {
			switch (treeType) {
			case TREE_ONE:
				return (Game.getSCALE() / 2) - (GetTreeWidth(treeType) / 2);
			case TREE_TWO:
				return (int) (Game.getSCALE() / 2.5f);
			case TREE_THREE:
				return (int) (Game.getSCALE() / 1.65f);
			}

			return 0;
		}

		public static int GetTreeOffsetY(int treeType) {

			switch (treeType) {
			case TREE_ONE:
				return -GetTreeHeight(treeType) + Game.getSCALE() * 2;
			case TREE_TWO, TREE_THREE:
				return -GetTreeHeight(treeType) + (int) (Game.getSCALE() / 1.25f);
			}
			return 0;

		}

		public static int GetTreeWidth(int treeType) {
			switch (treeType) {
			case TREE_ONE:
				return (int) (39 * Game.getSCALE());
			case TREE_TWO:
				return (int) (62 * Game.getSCALE());
			case TREE_THREE:
				return -(int) (62 * Game.getSCALE());

			}
			return 0;
		}

		public static int GetTreeHeight(int treeType) {
			switch (treeType) {
			case TREE_ONE:
				return (int) (int) (92 * Game.getSCALE());
			case TREE_TWO, TREE_THREE:
				return (int) (54 * Game.getSCALE());

			}
			return 0;
		}
	}
	
	public static class Tiles {
		
		public static final int TILE_SIZE = 32;
		
		public static final int WATER_BOTTOM_TILE = 0;
		public static final int WATER_TOP_TILE = 1;
		public static final int GRASS_TILE = 2;
		public static final int CANNON_TILE = 3;
		public static final int SPIKES_TILE = 4;
		public static final int BARRELS_TILE = 5;
		public static final int POTIONS_TILE = 6;
		public static final int OUTSIDE_TERRAIN_TILE = 7;
		public static final int OUTSIDE_PILLAR_TILE = 8;
		public static final int OUTSIDE_CORNER_TILE = 9;
		public static final int OUTSIDE_FLOWTING_TILE = 10;
		public static final int SHIP_TILE = 11;
		public static final int TREES_TILE = 12;
		public static final int CRABBY_TILE = 13;
		public static final int PINKSTAR_TILE = 14;
		public static final int SHARK_TILE = 15;
		public static final int PLAYER_TILE = 16;
		public static final int GOMMA_TILE = -1;
		
	}

	public static class Buttons {
		public static final int BTN_SHIP = 0;
		public static final int BTN_TREE = 1;

	}

	public static class IdColori {
		@SuppressWarnings("serial")
		
		static HashMap<Integer, Color> numeriColori = new HashMap<Integer, Color>() {
			{
//				// Water bottom
//				water_bottom.add(WATER_BOTTOM = new Tile(getSprite(0, 0, LoadSave.WATER_BOTTOM), id++, WATER_BOTTOM_TILE));
//
//				// Water top
//				water_top.add(WATER_TOP = new Tile(getAniSprites(0, 0, 4, LoadSave.WATER_TOP), id++, WATER_TOP_TILE));
//
//				// Grass
//				for (int x = 0; x < 3; x++)
//					grass.add(GRASS = new Tile(getSprite(x, 0, LoadSave.GRASS_ATLAS, 21, 32), id++, GRASS_TILE));
//
//				// Cannon Right
//				cannon.add(CANNON = new Tile(ImgFix.getMirroredImage(getSprite(0, 0, LoadSave.CANNON_ATLAS, 40, 26)), id++,
//						CANNON_TILE));
//
//				// Cannon Left
//				cannon.add(CANNON = new Tile(getSprite(0, 0, LoadSave.CANNON_ATLAS, 40, 26), id++, CANNON_TILE));
//
//				// Spikes
//				spikes.add(SPIKES = new Tile(getSprite(0, 0, LoadSave.TRAP_ATLAS), id++, SPIKES_TILE));
//
//				// Barrels
//				barrels.add(BARRELS = new Tile(getSprite(0, 0, LoadSave.CONTAINER_ATLAS, 35, 30), id++, BARRELS_TILE));
//
//				// box
//				barrels.add(BOX = new Tile(getSprite(0, 1, LoadSave.CONTAINER_ATLAS, 35, 30), id++, BARRELS_TILE));
//
//				// Potions blue
//				potions.add(POTION_BLUE = new Tile(getAniSprites(0, 0, 7, LoadSave.POTION_ATLAS, 12, 16), id++, POTIONS_TILE));
//
//				// Potions red
//				potions.add(POTION_RED = new Tile(getAniSprites(0, 1, 7, LoadSave.POTION_ATLAS, 12, 16), id++, POTIONS_TILE));
//				
//				// Outside terrain
//				for (int x = 0; x < 3; x++)
//					for (int y = 0; y < 3; y++)
//						outside_terrain.add(
//								OUTSIDE_TERRAIN = new Tile(getSprite(x, y, LoadSave.LEVEL_ATLAS), id++, OUTSIDE_TERRAIN_TILE));
//
//				// Outside pillar
//				for (int x = 3; x < 4; x++)
//					for (int y = 0; y < 3; y++)
//						outside_pillar.add(
//								OUTSIDE_PILLAR = new Tile(getSprite(x, y, LoadSave.LEVEL_ATLAS), id++, OUTSIDE_PILLAR_TILE));
//
//				// Outside corner
//				for (int x = 4; x < 12; x++)
//					for (int y = 0; y < 4; y++)
//						if (x != 11 && y == 0)
//							outside_corner.add(OUTSIDE_CORNER = new Tile(getSprite(x, y, LoadSave.LEVEL_ATLAS), id++,
//									OUTSIDE_CORNER_TILE));
//
//				// Outside floating
//				for (int x = 0; x < 4; x++)
//					for (int y = 0; y < 2; y++)
//						outside_floating.add(OUTSIDE_FLOWTING = new Tile(getSprite(x, y, LoadSave.LEVEL_ATLAS), id++,
//								OUTSIDE_FLOWTING_TILE));
//
//				// Ship
//				for (int y = 0; y < 2; y++)
//					for (int x = 0; x < 2; x++)
//						ship.add(SHIP = new Tile(getSprite(x, y, LoadSave.SHIP, 39, 36), id++, SHIP_TILE));
//
//				// Trees
//				for (int y = 0; y < 4; y++)
//					for (int x = 0; x < 2; x++)
//						trees.add(TREES = new Tile(getSprite(x, y, LoadSave.TREE_ONE_ATLAS, 20, 23), id++, TREES_TILE));
//
//				for (int y = 0; y < 2; y++)
//					for (int x = 0; x < 2; x++)
//						trees.add(TREES = new Tile(getSprite(x, y, LoadSave.TREE_TWO_ATLAS, 31, 27), id++, TREES_TILE));
//
//				// Crabbies
//				entities.add(CRABBY = new Tile(getAniSprites(0, 0, 9, LoadSave.CRABBY_SPRITE, 72, 32), id++, CRABBY_TILE));
//
//				// Pinkstars
//				entities.add(PINKSTAR = new Tile(getAniSprites(0, 0, 8, LoadSave.PINKSTAR_ATLAS, 34, 30), id++, PINKSTAR_TILE));
//
//				// Sharks
//				entities.add(SHARK = new Tile(getAniSprites(0, 0, 8, LoadSave.SHARK_ATLAS, 34, 30), id++, SHARK_TILE));
//				
//				// Player
//				entities.add(PLAYER = new Tile(getAniSprites(0, 0, 5, LoadSave.PLAYER_ATLAS, 60, 40), id++, PLAYER_TILE));
				
				

				int id=0;
				put(id++, new Color(48, 0, 0)); // water bottom
				put(id++, new Color(11, 0, 0)); // water top
				//Objects
				for(int i=0;i<2;i++) {
					put(id++, new Color(0, 0, i));//grass
				}
				put(id++, new Color(0, 0, 6)); // cannon right
				put(id++, new Color(0, 0, 5)); // cannon left
				put(id++, new Color(0, 0, 4)); // spike
				put(id++, new Color(0, 0, 2)); // barrel
				put(id++, new Color(0, 0, 3)); // box
				put(id++, new Color(0, 0, 1)); // potion blue
				put(id++, new Color(0, 0, 0)); // potion red
				
				
				//Block 1
				put(id++, new Color(0, 0, 0)); // outside terrain 1 
				put(id++, new Color(1, 0, 0)); // outside terrain 2 
				put(id++, new Color(2, 0, 0)); // outside terrain 3 
				put(id++, new Color(12, 0, 0)); // outside terrain 4  
				put(id++, new Color(13, 0, 0)); // outside terrain 5 
				put(id++, new Color(14, 0, 0)); // outside terrain 6  
				put(id++, new Color(24, 0, 0)); // outside terrain 7 
				put(id++, new Color(25, 0, 0)); // outside terrain 8
				put(id++, new Color(26, 0, 0)); // outside terrain 9
				
				//Block 2
				put(id++, new Color(3, 0, 0)); // outside pillar 1
				put(id++, new Color(15, 0, 0)); // outside pillar 2
				put(id++, new Color(27, 0, 0)); // outside pillar 3
				
				//Block 3
				
				put(id++, new Color(36, 0, 0)); // outside corner 1
				put(id++, new Color(37, 0, 0)); // outside corner 2
				put(id++, new Color(38, 0, 0)); // outside corner 3
				put(id++, new Color(39, 0, 0)); // outside corner single
				
				//ship
				for (int y = 0; y < 2; y++)
					for (int x = 0; x < 2; x++)
						put(id++, new Color(255,255,255));//albero dritto 
				
				
				//alberi
				for (int y = 0; y < 4; y++)
					for (int x = 0; x < 2; x++)
						put(id++, new Color(0,0,7));//albero dritto 
				
				for (int y = 0; y < 4; y++)
					for (int x = 0; x < 2; x++)
						put(id++, new Color(0,0,8)); //albero piegato verso destra
				
				for (int y = 0; y < 4; y++)
					for (int x = 0; x < 2; x++)
						put(id++, new Color(0,0,9)); //albero piegato verso sinistra
			
				
				
				
				//Entities
				put(id++, new Color(0,0,0));//Granchietto
				put(id++, new Color(0,1,0));//stella
				put(id++, new Color(0,2,0));//bestia di satana 
				put(id++, new Color(0,100,0));//giocatore
				
				//Gomma
				put(id++, new Color(52, 52, 52));
				
			}
		};

		@SuppressWarnings("rawtypes")
		public HashMap getColor() {
			return numeriColori;
		}
	}
	

}
