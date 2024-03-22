package objects;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import static helpz.Constants.Tiles.*;

public class Tile {

	private BufferedImage[] sprite;
	private int id, tileType;

	public Tile(BufferedImage sprite, int id, int tileType) {
		this.sprite = new BufferedImage[1];
		this.sprite[0] = sprite;
		this.id = id;
		this.tileType = tileType;
	}

	public Tile(BufferedImage[] sprite, int id, int tileType) {
		this.sprite = sprite;
		this.id = id;
		this.tileType = tileType;
	}

	public int getTileType() {
		return tileType;
	}

	public BufferedImage getSprite(int animationIndex) {
		return sprite[animationIndex];
	}

	public BufferedImage getSprite() {
		return sprite[0];
	}
	public int getSpriteLenght() {
		return sprite.length;
	}

	public boolean isAnimation() {
		return sprite.length > 1;
	}
	
	// DA CAMBIARE LE COSTANTI E METTERLE SOPRA IL VENTI
	public boolean isMultiple() {
		return tileType == SUN_TILE
			   || tileType == MOON_TILE 
			   || tileType == BIG_TREES_TILE 
			   || tileType == BIG_CLOUDS_TILE;
	}
	
	public Dimension getmultipleBounds(int tileType) {
		int w = 0;
		int h = 0;
		
		if(tileType == SUN_TILE || tileType == MOON_TILE) {
			w = 4;
			h = 2;
		}else if(tileType == BIG_TREES_TILE) {
			w = 3;
			h = 1;
		}else if(tileType == BIG_CLOUDS_TILE) {
			w = 2;
			h = 1;
		}
		
		
		Dimension dim = new Dimension(w,h);
		return dim;
	}

	public int getId() {
		return id;
	}

}
