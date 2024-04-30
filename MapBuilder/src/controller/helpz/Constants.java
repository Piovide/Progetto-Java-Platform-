package controller.helpz;

import java.awt.Color;
import java.util.ArrayList;
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
		public static final int GOMMA_TILE = 47;

	}

	public static class Buttons {
		public static final int BTN_SHIP = 0;
		public static final int BTN_TREE = 1;

	}

	public static class IdColori {

//		protected static HashMap<Integer, Color> numeriColori = new HashMap<Integer, Color>() {
//			{	
//
//				int id=0;
//				put(id++, new Color(48, 0, 0)); // water bottom 0
//				put(id++, new Color(11, 0, 0)); // water top 1
//				//Objects
//				for(int i=0;i<2;i++) {
//					put(id++, new Color(0, 0, i));//grass 2 3
//				}
//				put(id++, new Color(0, 0, 6)); // cannon right 4 
//				put(id++, new Color(0, 0, 5)); // cannon left 5
//				put(id++, new Color(0, 0, 4)); // spike 6
//				put(id++, new Color(0, 0, 2)); // barrel 7
//				put(id++, new Color(0, 0, 3)); // box 8
//				put(id++, new Color(0, 0, 1)); // potion blue 9
//				put(id++, new Color(0, 0, 0)); // potion red 10
//				
//				//Block 1
//				put(id++, new Color(0, 0, 0)); // outside terrain 1 11
//				put(id++, new Color(1, 0, 0)); // outside terrain 2 12
//				put(id++, new Color(2, 0, 0)); // outside terrain 3 13
//				put(id++, new Color(12, 0, 0)); // outside terrain 4 14
//				put(id++, new Color(13, 0, 0)); // outside terrain 5 15
//				put(id++, new Color(14, 0, 0)); // outside terrain 6 16
//				put(id++, new Color(24, 0, 0)); // outside terrain 7 17
//				put(id++, new Color(25, 0, 0)); // outside terrain 8 18
//				put(id++, new Color(26, 0, 0)); // outside terrain 9 19
//				
//				//Block 2
//				put(id++, new Color(3, 0, 0)); // outside pillar 1 20
//				put(id++, new Color(15, 0, 0)); // outside pillar 2 21
//				put(id++, new Color(27, 0, 0)); // outside pillar 3 22
//				
//				//Block 3
//				
//				put(id++, new Color(36, 0, 0)); // outside corner 1 23
//				put(id++, new Color(37, 0, 0)); // outside corner 2 24
//				put(id++, new Color(38, 0, 0)); // outside corner 3 25
//				put(id++, new Color(39, 0, 0)); // outside corner single 26
//				
//				//ship
//				for (int y = 0; y < 2; y++)
//					for (int x = 0; x < 2; x++)
//						put(id++, new Color(255,255,255)); // Barca 27 28 29 30
//				
//				
//				//alberi
//				for (int y = 0; y < 4; y++)
//					for (int x = 0; x < 2; x++)
//						put(id++, new Color(0,0,7));//albero dritto 
//				 
//				for (int y = 0; y < 4; y++)
//					for (int x = 0; x < 2; x++)
//						put(id++, new Color(0,0,8)); //albero piegato verso destra
//
//				
//				//Entities
//				put(id++, new Color(0,0,0));//Granchietto
//				put(id++, new Color(0,1,0));//stella
//				put(id++, new Color(0,2,0));//bestia di satana 
//				put(id++, new Color(0,100,0));//giocatore
//				
//				//Gomma
//				put(id++, new Color(52, 52, 52));
//				
//			}
//		};
//
//		@SuppressWarnings("rawtypes")
//		public HashMap getColor() {
//			return numeriColori;
//		}
		public static ArrayList<Color> getIdColori() {
			ArrayList<Color> numeriColori = new ArrayList<Color>(43);

			int gomma = Constants.Tiles.GOMMA_TILE;

			numeriColori.add(new Color(49, gomma, gomma)); // water bottom 0
			numeriColori.add(new Color(48, gomma, gomma)); // water top 1
			// Objects
			numeriColori.add(new Color(gomma, gomma, 118));// grass 2
			numeriColori.add(new Color(gomma, gomma, 119));// grass 3
			numeriColori.add(new Color(gomma, gomma, 120)); // grass 4

			numeriColori.add(new Color(gomma, gomma, 6)); // cannon right 5
			numeriColori.add(new Color(gomma, gomma, 5)); // cannon left 6
			numeriColori.add(new Color(gomma, gomma, 4)); // spike 7
			numeriColori.add(new Color(gomma, gomma, 3)); // barrel 8
			numeriColori.add(new Color(gomma, gomma, 2)); // box 9
			numeriColori.add(new Color(gomma, gomma, 1)); // potion blue 10
			numeriColori.add(new Color(gomma, gomma, 0)); // potion red 11

			// Block 1
			numeriColori.add(new Color(0, gomma, gomma)); // outside terrain 1 12
			numeriColori.add(new Color(1, gomma, gomma)); // outside terrain 2 13
			numeriColori.add(new Color(2, gomma, gomma)); // outside terrain 3 14
			numeriColori.add(new Color(12, gomma, gomma)); // outside terrain 4 15
			numeriColori.add(new Color(13, gomma, gomma)); // outside terrain 5 16
			numeriColori.add(new Color(14, gomma, gomma)); // outside terrain 6 17
			numeriColori.add(new Color(24, gomma, gomma)); // outside terrain 7 18
			numeriColori.add(new Color(25, gomma, gomma)); // outside terrain 8 19
			numeriColori.add(new Color(26, gomma, gomma)); // outside terrain 9 20

			// Block 2
			numeriColori.add(new Color(3, gomma, gomma)); // outside pillar 1 21
			numeriColori.add(new Color(15, gomma, gomma)); // outside pillar 2 22
			numeriColori.add(new Color(27, gomma, gomma)); // outside pillar 3 23

			// Block 3

			numeriColori.add(new Color(36, gomma, gomma)); // outside corner 1 24
			numeriColori.add(new Color(37, gomma, gomma)); // outside corner 2 25
			numeriColori.add(new Color(38, gomma, gomma)); // outside corner 3 26
			numeriColori.add(new Color(39, gomma, gomma)); // outside corner single 27

			// ship
			numeriColori.add(new Color(gomma, gomma, 50)); // Barca 28
			numeriColori.add(new Color(gomma, gomma, 51)); // Barca 29
			numeriColori.add(new Color(gomma, gomma, 52)); // Barca 30
			numeriColori.add(new Color(gomma, gomma, 53)); // Barca 31

			// alberi
			numeriColori.add(new Color(gomma, gomma, 11));// albero dritto 32
			numeriColori.add(new Color(gomma, gomma, 7)); // albero dritto 33
			numeriColori.add(new Color(gomma, gomma, 13));// albero dritto 34

			numeriColori.add(new Color(gomma, gomma, 14)); // albero piegato verso destra 35
			numeriColori.add(new Color(gomma, gomma, 15)); // albero piegato verso destra 36
			numeriColori.add(new Color(gomma, gomma, 8)); // albero piegato verso destra 37
			numeriColori.add(new Color(gomma, gomma, 16)); // albero piegato verso destra 38

			numeriColori.add(new Color(gomma, gomma, 17));// albero piegato verso sinistra 39
			numeriColori.add(new Color(gomma, gomma, 18));// albero piegato verso sinistra 40
			numeriColori.add(new Color(gomma, gomma, 9));// albero piegato verso sinistra 41
			numeriColori.add(new Color(gomma, gomma, 19));// albero piegato verso sinistra 42

			// Entities
			numeriColori.add(new Color(gomma, 0, gomma));// Granchietto 39
			numeriColori.add(new Color(gomma, 1, gomma));// stella 40
			numeriColori.add(new Color(gomma, 2, gomma));// shark 41
			numeriColori.add(new Color(gomma, 100, gomma));// giocatore 42

			// Gomma
			numeriColori.add(new Color(11, gomma, gomma)); // 43

			return numeriColori;
		};
	}

}
