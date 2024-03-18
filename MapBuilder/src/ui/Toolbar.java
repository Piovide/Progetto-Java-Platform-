package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import objects.Tile;
import scenes.Editing;

public class Toolbar extends Bar {
	private Editing editing;
	private MyButton bExit, bSave;
	private Tile selectedTile;
	private Map<MyButton, ArrayList<Tile>> map = new HashMap<MyButton, ArrayList<Tile>>();

	private MyButton bGrass, bWater, bWind, bTerrains, bGrassNPeabbles, bFlawers, bOtherTerrains, bSpikes, bDoors, bLadders;
	private MyButton currentButton;
	private int currentIndex = 0;
	private int y;

	public Toolbar(int x, int y, int width, int height, Editing editing) {
		super(x, y, width, height);
		this.editing = editing;
		this.y = y;
		initButtons();
	}

	private void initButtons() {
		
		bExit = new MyButton("Esci", 2, y+20, 100, 30);
		bSave = new MyButton("Save", 2, y+60, 100, 30);
		int w = 50;
		int h = 50;
		int xStart = 10;
		int yStart = y+100;
		int yOffset = (int) (w * 1.1f);
		int i = 0;

		bGrass = new MyButton("Sprout", xStart, yStart, w, h, i++);
		bWater = new MyButton("Water", xStart, yStart + yOffset, w, h, i++);
		
		// Get grassNPeabbles tiles
		initMapButton(bGrassNPeabbles, editing.getGame().getTileManager().getGrassNPeabbles(), xStart, yStart, yOffset, w, h, i++);
		// Get Wind tiles
		initMapButton(bWind, editing.getGame().getTileManager().getWind(), xStart, yStart, yOffset, w, h, i++);
		
		// Get Terrains tiles
		initMapButton(bTerrains, editing.getGame().getTileManager().getTerrains(), xStart, yStart, yOffset, w, h, i++);
		

		// Get ladders tiles
		initMapButton(bLadders, editing.getGame().getTileManager().getLadders(), xStart, yStart, yOffset, w, h, i++);

		// Get doors tiles
		initMapButton(bDoors, editing.getGame().getTileManager().getDoors(), xStart, yStart, yOffset, w, h, i++);

		// Get terrains tiles
		initMapButton(bTerrains, editing.getGame().getTileManager().getTerrains(), xStart, yStart, yOffset, w, h, i++);

		// Get flawers tiles
		initMapButton(bFlawers, editing.getGame().getTileManager().getFlawers(), xStart, yStart, yOffset, w, h, i++);

		// Get other_terrains tiles
		initMapButton(bOtherTerrains, editing.getGame().getTileManager().getOther_terrains(), xStart, yStart, yOffset, w, h, i++);

		// Get spikes tiles
		initMapButton(bSpikes, editing.getGame().getTileManager().getSpikes(), xStart, yStart, yOffset, w, h, i++);

	}

	private void initMapButton(MyButton b, ArrayList<Tile> list, int x, int y, int xOff, int w, int h, int id) {
		b = new MyButton("", x, y + xOff * id, w, h, id);
		map.put(b, list);
	}

	private void saveLevel() {
		editing.saveLevel();
	}

	public void rotateSprite() {

		currentIndex++;
		if (currentIndex >= map.get(currentButton).size())
			currentIndex = 0;
		selectedTile = map.get(currentButton).get(currentIndex);
		editing.setSelectedTile(selectedTile);

	}

	public void draw(Graphics g) {

		// Background
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, editing.getGame().getToolkit().getScreenSize().height);

		// Buttons
		drawButtons(g);
	}

	private void drawButtons(Graphics g) {
		bSave.draw(g);
		bExit.draw(g);
		drawNormalButton(g, bGrass);
		drawNormalButton(g, bWater);
		drawSelectedTile(g);
		drawMapButtons(g);

	}

	private void drawNormalButton(Graphics g, MyButton b) {
		g.drawImage(getButtImg(b.getId()), b.x, b.y, b.width, b.height, null);
		drawButtonFeedback(g, b);

	}

	private void drawMapButtons(Graphics g) {
		for (Map.Entry<MyButton, ArrayList<Tile>> entry : map.entrySet()) {
			MyButton b = entry.getKey();
			BufferedImage img = entry.getValue().get(0).getSprite();

			g.drawImage(img, b.x, b.y, b.width, b.height, null);
			drawButtonFeedback(g, b);
		}

	}

	

	private void drawSelectedTile(Graphics g) {

		if (selectedTile != null) {
			g.drawImage(selectedTile.getSprite(), 30, editing.getGame().getToolkit().getScreenSize().height -150, 100, 100, null);
			g.setColor(Color.black);
			g.drawRect(30, editing.getGame().getToolkit().getScreenSize().height -150, 100, 100);
		}

	}

	public BufferedImage getButtImg(int id) {
		return editing.getGame().getTileManager().getSprite(id);
	}

	public void mouseClicked(int x, int y) {
		if (bSave.getBounds().contains(x, y))
			saveLevel();
		else if (bExit.getBounds().contains(x, y))
			editing.getGame().close();
		else if (bWater.getBounds().contains(x, y)) {
			selectedTile = editing.getGame().getTileManager().getTile(bWater.getId());
			editing.setSelectedTile(selectedTile);
			return;
		} else if (bGrass.getBounds().contains(x, y)) {
			selectedTile = editing.getGame().getTileManager().getTile(bGrass.getId());
			editing.setSelectedTile(selectedTile);
			return;
		} else {
			for (MyButton b : map.keySet())
				if (b.getBounds().contains(x, y)) {
					selectedTile = map.get(b).get(0);
					editing.setSelectedTile(selectedTile);
					currentButton = b;
					currentIndex = 0;
					return;
				}
		}

	}

	public void mouseMoved(int x, int y) {
		bSave.setMouseOver(false);
		bExit.setMouseOver(false);
		bWater.setMouseOver(false);
		bGrass.setMouseOver(false);

		for (MyButton b : map.keySet())
			b.setMouseOver(false);
		if (bSave.getBounds().contains(x, y))
			bSave.setMouseOver(true);
		else if (bExit.getBounds().contains(x, y))
			bExit.setMouseOver(true);
		else if (bWater.getBounds().contains(x, y))
			bWater.setMouseOver(true);
		else if (bGrass.getBounds().contains(x, y))
			bGrass.setMouseOver(true);
		else {
			for (MyButton b : map.keySet())
				if (b.getBounds().contains(x, y)) {
					b.setMouseOver(true);
					return;
				}
		}

	}

	public void mousePressed(int x, int y) {
		if (bSave.getBounds().contains(x, y))
			bSave.setMousePressed(true);
		else if (bExit.getBounds().contains(x, y))
			bExit.setMousePressed(true);
		else if (bWater.getBounds().contains(x, y))
			bWater.setMousePressed(true);
		else if (bGrass.getBounds().contains(x, y))
			bGrass.setMousePressed(true);
		else {
			for (MyButton b : map.keySet())
				if (b.getBounds().contains(x, y)) {
					b.setMousePressed(true);
					return;
				}
		}
	}

	public void mouseReleased(int x, int y) {
		bSave.resetBooleans();
		bExit.resetBooleans();
		bGrass.resetBooleans();
		bWater.resetBooleans();
		for (MyButton b : map.keySet())
			b.resetBooleans();

	}

}
