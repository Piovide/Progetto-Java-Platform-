package helpz;

import java.awt.Color;
import java.util.HashMap;

public class Constants {
	public static class Tiles {
		public static final int SPROUT_TILE = 0;
		public static final int GRASS_N_PEABBLES_TILE = 1;
		public static final int WIND_GRAY_TILE = 2;
		public static final int LADDERS_TILE = 3;
		public static final int WIND_WHITE_TILE = 4;
		public static final int DOORS_TILE = 5;
		public static final int TERRAIN_TILE = 6;
		public static final int WATER_TILE = 7;
		public static final int FLAWERS_TILE = 8;
		public static final int OTHER_TERRAINS_TILE = 9;
		public static final int FLOATING_ISLAND_TILE = 10;
		public static final int SPIKES_TILE = 11;
		public static final int INFO_TILE = 12;
		public static final int ARROWS_TILE = 13;
		public static final int TREES_TILE = 14;
		public static final int MOON_TILE = 15;
		public static final int SUN_TILE = 16;
		public static final int CLOUDS_TILE = 17;
		public static final int DOUBLE_CLOUDS_TILE = 18;
	}

	public static class Buttons {
		public static final int BTN_SUN = 0;
		public static final int BTN_MOON = 1;
		public static final int BTN_CLOUDS = 2;
		public static final int BTN_DOUBLE_CLOUDS = 3;
		public static final int BTN_BIG_TREE = 4;

	}

	public static class IdColori {
		@SuppressWarnings("serial")
		
		static HashMap<Integer, Color> numeriColori = new HashMap<Integer, Color>() {
			{
				put(0, new Color(255, 0, 0)); // Rosso
				put(1, new Color(0, 255, 0)); // Verde
				put(2, new Color(0, 0, 255)); // Blu
				put(3, new Color(255, 255, 0)); // Giallo
				put(4, new Color(255, 0, 255)); // Magenta
				put(5, new Color(0, 255, 255)); // Ciano
				put(6, new Color(128, 0, 0)); // Rosso scuro
				put(7, new Color(0, 128, 0)); // Verde scuro
				put(8, new Color(0, 0, 128)); // Blu scuro
				put(9, new Color(128, 128, 0)); // Giallo scuro
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
				put(52, new Color(0, 255, 255)); // Azzurro
				put(53, new Color(255, 20, 147)); // Rosa brillante
				put(54, new Color(219, 112, 147)); // Rosa
				put(55, new Color(65, 105, 225)); // Blu reale
				put(56, new Color(238, 130, 238)); // Violetto
				put(57, new Color(0, 255, 127)); // Verde primavera
				put(58, new Color(127, 255, 212)); // Acqua marina
				put(59, new Color(255, 228, 196)); // Carne
				put(60, new Color(255, 250, 240)); // Cipria
				put(61, new Color(244, 164, 96)); // Cachi
				put(62, new Color(210, 180, 140)); // Tan
				put(63, new Color(188, 143, 143)); // Marrone chiaro
				put(64, new Color(240, 230, 140)); // Khaki
				put(65, new Color(245, 245, 220)); // Beige
				put(66, new Color(255, 239, 213)); // PapayaWhip
				put(67, new Color(245, 255, 250)); // MintCream
				put(68, new Color(255, 228, 225)); // MistyRose
				put(69, new Color(240, 255, 255)); // Azure
				put(70, new Color(255, 255, 240)); // Ivory
				put(71, new Color(245, 245, 245)); // WhiteSmoke
				put(72, new Color(250, 240, 230)); // Linen
				put(73, new Color(253, 245, 230)); // OldLace
				put(74, new Color(255, 250, 250)); // Snow
				put(75, new Color(250, 235, 215)); // AntiqueWhite
				put(76, new Color(255, 250, 205)); // LemonChiffon
				put(77, new Color(255, 255, 224)); // LightGoldenRodYellow
				put(78, new Color(255, 228, 181)); // Moccasin
				put(79, new Color(255, 222, 173)); // NavajoWhite
				put(80, new Color(255, 248, 220)); // Cornsilk
				put(81, new Color(255, 228, 196)); // Bisque
				put(82, new Color(255, 239, 213)); // PapayaWhip
				put(83, new Color(245, 222, 179)); // Wheat
				put(84, new Color(244, 164, 96)); // SandyBrown
				put(85, new Color(210, 105, 30)); // Chocolate
				put(86, new Color(178, 34, 34)); // Firebrick
				put(87, new Color(165, 42, 42)); // Brown
				put(88, new Color(139, 69, 19)); // SaddleBrown
				put(89, new Color(128, 0, 0)); // Maroon
			}
		};

		@SuppressWarnings("rawtypes")
		public HashMap getColor() {
			return numeriColori;
		}
	}
	

}
