package controller.ui;

import java.awt.Graphics;
import java.awt.Rectangle;

import controller.helpz.ImgFix;
import controller.helpz.LoadSave;

public class MyButton {

	public int x, y, width, height, id, btnConst;
	private String text;
	private Rectangle bounds;
	private boolean mouseOver, mousePressed;

	// For tile buttons
	public MyButton(String text, int x, int y, int width, int height, int id) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		initBounds();
	}

	private void initBounds() {
		this.bounds = new Rectangle(x, y, width, height);
	}

	public void draw(Graphics g) {
		// Body
		drawBody(g);

		// Border
		drawBorder(g);
	}

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

	public void resetBooleans() {
		this.mouseOver = false;
		this.mousePressed = false;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public int getId() {
		return id;
	}

	public int getBtnConst() {
		return btnConst;
	}

	public void setBtnConst(int btnConst) {
		this.btnConst = btnConst;
	}

}
