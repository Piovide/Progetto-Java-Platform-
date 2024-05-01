package model.objects;

import static controller.helpz.Constants.Buttons.BTN_SHIP;
import static controller.helpz.Constants.Buttons.BTN_TREE;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
/**
 * questa classe contiene i metodi per gestire i tiles 
 */
public class Tile {

	private BufferedImage[] sprite;
	private int id, tileType, tileBtnConst;
	/**
	 * metodo costruttore 
	 * @param sprite
	 * @param id
	 * @param tileType
	 */
	public Tile(BufferedImage sprite, int id, int tileType) {
		this.sprite = new BufferedImage[1];
		this.sprite[0] = sprite;
		this.id = id;
		this.tileType = tileType;
		this.tileBtnConst = -1;
	}
	/**
	 * metodo costruttore 
	 * @param sprite
	 * @param id
	 * @param tileType
	 */
	public Tile(BufferedImage[] sprite, int id, int tileType) {
		this.sprite = sprite;
		this.id = id;
		this.tileType = tileType;
		this.tileBtnConst = -1;
	}
	/**
	 * questo metodo restituisce il tipo di tile 
	 * @return int 
	 */
	public int getTileType() {
		return tileType;
	}
	/**
	 * questo metodo restituisce il frame dell animazione associato all indice 
	 * @param animationIndex
	 * @return BufferedImage 
	 */
	public BufferedImage getSprite(int animationIndex) {
		return sprite[animationIndex];
	}
	/**
	 * questo medoto restituisce il frame iniziale di un animazione 
	 * @return BufferedImage
	 */
	public BufferedImage getSprite() {
		return sprite[0];
	}
	/**
	 * questo metodo restituisce la lunghezza in frame dello sprite 
	 * @return int
	 */
	public int getSpriteLenght() {
		return sprite.length;
	}
	/**
	 * questo metodo restituisce un boolean in base alla lunghezza di sprite 
	 * @return boolean
	 */
	public boolean isAnimation() {
		return sprite.length > 1;
	}
	/**
	 * questo metodo restituisce il tipo di tile (oggetto,blocco,entita) 
	 * @return int 
	 */
	public int getType() {
		switch(id) {
			//Blocks
			case 0,1,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27:
				return 0;
			//Objects
			case 2,3,4,5,6,7,8,9,10,11,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42:
				return 1;
			//Entities
			case 43,44,45,46:
				return 2;
		}
		return -1;
	}
	/**
	 * questo metodo restituisce un bolean true se tileBtnConst e associato ad un tile multiplo 
	 * @return
	 */
	public boolean isMultiple() {
		return tileBtnConst == BTN_SHIP || tileBtnConst == BTN_TREE;
	}
	
	/**
	 * questo metodo restituisce le dimensioni dell tile multiplo 
	 * @param tileType
	 * @return Dimension 
	 */
	public Dimension getmultipleBounds(int tileType) {
		int w = 0;
		int h = 0;
		
		if(tileBtnConst == BTN_SHIP) {
			w = 2;
			h = 2;
		}else if(tileBtnConst == BTN_TREE) {
			w = 1;
			h = 3;
		}
		
		
		Dimension dim = new Dimension(w,h);
		return dim;
	}
	/**
	 * questo metodo restituisce l'id del tile 
	 * @return int 
	 */
	public int getId() {
		//System.out.println(id);
		return id;
	}
	/**
	 * questo metodo restituisce tileBtnConst
	 * @return int 
	 */
	public int gettileBtnConst() {
		return tileBtnConst;
	}
	/**
	 * questo metodo assegna a tileBtnConst il valore in ingersso 
	 * @param tileBtnConst
	 */
	public void settileBtnConst(int tileBtnConst) {
		this.tileBtnConst = tileBtnConst;
	}
	
	@Override
	public String toString() {
		return "Tile [id=" + id + ", tileType=" + tileType + "]";
	}

}
