package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MyButton {

	public int x, y, width, height, id, btnConst;
	private String text;
	private Rectangle bounds;
	private boolean mouseOver, mousePressed;

	// For normal Buttons
	public MyButton(String text, int x, int y, int width, int height) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = -1;

		initBounds();
	}

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

		// Text
		drawText(g);
	}

	private void drawBorder(Graphics g) {

		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
		if (mousePressed) {
			g.drawRect(x + 1, y + 1, width - 2, height - 2);
			g.drawRect(x + 2, y + 2, width - 4, height - 4);
		}

	}

	private void drawBody(Graphics g) {
		if (mouseOver)
			g.setColor(Color.gray);
		else
			g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);

	}

	@Override
	public String toString() {
		return "MyButton [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", id=" + id + ", text="
				+ text + ", bounds=" + bounds + ", mouseOver=" + mouseOver + ", mousePressed=" + mousePressed + "]";
	}

	private void drawText(Graphics g) {
		int w = g.getFontMetrics().stringWidth(text);
		int h = g.getFontMetrics().getHeight();
		g.drawString(text, x - w / 2 + width / 2, y + h / 2 + height / 2 - 2);

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
