package controller.ui;

import java.awt.Graphics;
import java.awt.Rectangle;

import controller.helpz.ImgFix;
import controller.helpz.LoadSave;
/**
 * questa classe contiene i metodi per creare il bottone 
 */
public class MyButton {

	public int x, y, width, height, id, btnConst;
	private String text;
	private Rectangle bounds;
	private boolean mouseOver, mousePressed;

	// For tile buttons
	/**
	 * metodo costruttore 
	 * @param text
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param id
	 */
	public MyButton(String text, int x, int y, int width, int height, int id) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		initBounds();
	}
	/**
	 * questo metodo imposta i limiti dell bottone 
	 */
	private void initBounds() {
		this.bounds = new Rectangle(x, y, width, height);
	}
	/**
	 * questo metodo si occpua di disegnare il corpo e i bordi dell bottone 
	 * @param g
	 */
	public void draw(Graphics g) {
		// Body
		drawBody(g);

		// Border
		drawBorder(g);
	}
	/**
	 * questo metodo si occpua di disegnare i bordi dell bottone 
	 * @param g
	 */
	private void drawBorder(Graphics g) {
		if (mousePressed) {
			if (id <= 104)
				g.drawImage(LoadSave.getSpriteAtlas(LoadSave.BTN_EDITING).getSubimage(160, 28 * (id - 100), 80, 28), x,
						y, width, height, null);
			else {
				if (id == 105) {
					g.drawImage(LoadSave.getSpriteAtlas(LoadSave.BTN_ARROWS).getSubimage(112, 0, 56, 56), x, y, width,
							height, null);
				} else if (id == 106) {
					g.drawImage(
							ImgFix.getMirroredImage(
									LoadSave.getSpriteAtlas(LoadSave.BTN_ARROWS).getSubimage(112, 0, 56, 56)),
							x, y, width, height, null);
				}
			}
		}
	}
	/**
	 * questo metodo si occupa di disegnare il corpo dell bottone 
	 * @param g
	 */
	private void drawBody(Graphics g) {
		if (mouseOver) {
			if(id <= 104)
				g.drawImage(LoadSave.getSpriteAtlas(LoadSave.BTN_EDITING).getSubimage(80, 28 * (id - 100), 80, 28), x, y, width, height, null);
			else {
				if (id == 105) {
					g.drawImage(LoadSave.getSpriteAtlas(LoadSave.BTN_ARROWS).getSubimage(56, 0, 56, 56), x, y, width, height, null);
				} else if (id == 106) {
					g.drawImage(ImgFix.getMirroredImage(LoadSave.getSpriteAtlas(LoadSave.BTN_ARROWS).getSubimage(56, 0, 56, 56)),
							x, y, width, height, null);
				}
				}
		}else {
			if (id <= 104)
				g.drawImage(LoadSave.getSpriteAtlas(LoadSave.BTN_EDITING).getSubimage(0, 28 * (id - 100), 80, 28), x, y,
						width, height, null);
			else {
				if (id == 105)
					g.drawImage(LoadSave.getSpriteAtlas(LoadSave.BTN_ARROWS).getSubimage(0, 0, 56, 56), x, y, width, height, null);
				else if (id == 106)
					g.drawImage(ImgFix.getMirroredImage(LoadSave.getSpriteAtlas(LoadSave.BTN_ARROWS).getSubimage(0, 0, 56, 56)), x, y, width, height, null);
		
			}
		}
	}
	/**
	 * questo metodo ritorna un valore in base alla posizione dell mouse sui bottoni 
	 * @param x
	 * @param y
	 * @return
	 */
	public int mouseOver(int x, int y) {
		if (bounds.contains(x, y)) {
			mouseOver = true;
			return this.id;
		} else {
			mouseOver = false;
		}
		return -1;
	}

	@Override
	public String toString() {
		return "MyButton [name= "+text +" x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", id=" + id + ", text="
				+ text + ", bounds=" + bounds + ", mouseOver=" + mouseOver + ", mousePressed=" + mousePressed + "]";
	}
	/**
	 * questo metodo resetta a false le variabili boleane 
	 */
	public void resetBooleans() {
		this.mouseOver = false;
		this.mousePressed = false;
	}
	/**
	 * questo metodo imposta il valore booleano di mousePressed
	 * @param mousePressed
	 */
	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
	/**
	 * questo metodo imposta il valore booleano di mouseOver
	 * @param mouseOver
	 */
	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}
	/**
	 * questo metodo restituisce il booleano mouseOver
	 * @return boolean 
	 */
	public boolean isMouseOver() {
		return mouseOver;
	}
	/**
	 * questo metodo restituisce il booleano mousePressed
	 * @return boolean 
	 */
	public boolean isMousePressed() {
		return mousePressed;
	}
	/**
	 * questo metodo restituisce le dimensioni dell bottone 
	 * @return Rectangle 
	 */
	public Rectangle getBounds() {
		return bounds;
	}
	/**
	 * questo metodo restituisce l'id 
	 * @return int 
	 */
	public int getId() {
		return id;
	}
	/**
	 * questo metodo restituisce btnConst 
	 * @return int 
	 */
	public int getBtnConst() {
		return btnConst;
	}
	/**
	 * questo metodo imposta btnConst
	 * @param btnConst
	 */
	public void setBtnConst(int btnConst) {
		this.btnConst = btnConst;
	}

}
