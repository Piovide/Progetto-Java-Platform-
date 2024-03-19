package objects;

import java.awt.image.BufferedImage;

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
		return tileType > 20;
	}

	public int getId() {
		return id;
	}

}
