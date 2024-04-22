package controller.helpz;

import java.awt.Color;
import java.util.HashMap;

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
//				public static final int RED_POTION = 0;
//				public static final int BLUE_POTION = 1;
//				public static final int BARREL = 2;
//				public static final int BOX = 3;
//				public static final int SPIKE = 4;
//				public static final int CANNON_LEFT = 5;
//				public static final int CANNON_RIGHT = 6;
//				public static final int TREE_ONE = 7;
//				public static final int TREE_TWO = 8;
//				public static final int TREE_THREE = 9;
				put(0, new Color(0, 0, 0)); // Red Pot
				put(1, new Color(0, 0, 1)); // Blue Pot
				put(2, new Color(0, 0, 2)); // Box
				put(3, new Color(0, 0, 3)); // Barrel
				put(4, new Color(0, 0, 4)); // Spike
				put(5, new Color(0, 0, 5)); // Cannon Left
				put(6, new Color(0, 0, 6)); // Cannon Right
				put(7, new Color(0, 0, 7)); // Tree one
				put(8, new Color(0, 0, 8)); // Tree two
				put(9, new Color(0, 0, 9)); // Tree Three
				put(10, new Color(128, 0, 128)); // Magenta scuro
				put(11, new Color(0, 128, 128)); // Ciano scuro
				put(12, new Color(255, 128, 0)); // Arancione
				put(13, new Color(255, 0, 128)); // Rosa
				put(14, new Color(128, 255, 0)); // Verde chiaro
				put(15, new Color(0, 255, 128)); // Verde acqua
				put(16, new Color(0, 128, 255)); // Blu chiaro
				put(17, new Color(128, 0, 255)); // Viola
				put(18, new Color(255, 128, 128)); // Rosa chiaro
				put(19, new Color(128, 255, 128)); // Verde pallido
				put(20, new Color(128, 128, 255)); // Blu pallido
				put(21, new Color(255, 255, 128)); // Giallo pallido
				put(22, new Color(255, 128, 255)); // Rosa pallido
				put(23, new Color(128, 255, 255)); // Azzurro
				put(24, new Color(255, 255, 255)); // Bianco
				put(25, new Color(192, 192, 192)); // Grigio chiaro
				put(26, new Color(128, 128, 128)); // Grigio medio
				put(27, new Color(64, 64, 64)); // Grigio scuro
				put(28, new Color(255, 215, 0)); // Oro
				put(29, new Color(255, 192, 203)); // Rosa chiaro
				put(30, new Color(0, 0, 0)); // Nero
				put(31, new Color(255, 165, 0)); // Arancione scuro
				put(32, new Color(218, 112, 214)); // Viola chiaro
				put(33, new Color(0, 0, 139)); // Blu scuro
				put(34, new Color(0, 128, 0)); // Verde scuro
				put(35, new Color(255, 105, 180)); // Rosa pallido
				put(36, new Color(46, 139, 87)); // Verde mare
				put(37, new Color(139, 69, 19)); // Marrone
				put(38, new Color(189, 183, 107)); // Cachi
				put(39, new Color(106, 90, 205)); // Blu reale
				put(40, new Color(255, 20, 147)); // Rosa brillante
				put(41, new Color(255, 69, 0)); // Arancione
				put(42, new Color(75, 0, 130)); // Indaco
				put(43, new Color(255, 182, 193)); // Rosa chiaro
				put(44, new Color(154, 205, 50)); // Verde primavera
				put(45, new Color(139, 0, 139)); // Viola
				put(46, new Color(255, 127, 80)); // Rosso corallo
				put(47, new Color(240, 255, 240)); // Verde pallido
				put(48, new Color(240, 248, 255)); // Azzurro pallido
				put(49, new Color(152, 251, 152)); // Verde pallido
				put(50, new Color(205, 92, 92)); // Rosso indiano
				put(51, new Color(250, 128, 114)); // Rosso corallo
			}
		};

		@SuppressWarnings("rawtypes")
		public HashMap getColor() {
			return numeriColori;
		}
	}
	

}
