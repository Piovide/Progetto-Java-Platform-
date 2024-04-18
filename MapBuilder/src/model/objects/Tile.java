package model.objects;

import static controller.helpz.Constants.Tiles.*;
import static controller.helpz.Constants.Buttons.*;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

public class Tile {

	private BufferedImage[] sprite;
	private int id, tileType, tileBtnConst;

	public Tile(BufferedImage sprite, int id, int tileType) {
		this.sprite = new BufferedImage[1];
		this.sprite[0] = sprite;
		this.id = id;
		this.tileType = tileType;
		this.tileBtnConst = -1;
	}

	public Tile(BufferedImage[] sprite, int id, int tileType) {
		this.sprite = sprite;
		this.id = id;
		this.tileType = tileType;
		this.tileBtnConst = -1;
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
	
	
	public boolean isMultiple() {
		return tileBtnConst == BTN_SHIP || tileBtnConst == BTN_TREE;
	}
	
	public Dimension getmultipleBounds(int tileType) {
		int w = 0;
		int h = 0;
		
		if(tileBtnConst == BTN_SHIP) {
			w = 2;
			h = 2;
		}else if(tileBtnConst == BTN_TREE) {
			w = 2;
			h = 4;
		}
		
		
		Dimension dim = new Dimension(w,h);
		return dim;
	}

	public int getId() {
		return id;
	}

	public int gettileBtnConst() {
		return tileBtnConst;
	}

	public void settileBtnConst(int tileBtnConst) {
		this.tileBtnConst = tileBtnConst;
	}
	
	@Override
	public String toString() {
		return "Tile [id=" + id + ", tileType=" + tileType + "]";
	}

}
