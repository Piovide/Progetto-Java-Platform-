package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import helpz.LoadSave;
import objects.Tile;
import scenes.Editing;
import static helpz.Constants.Buttons.*;

public class Toolbar extends Bar {
	private Editing editing;
	private Tile selectedTile;
	private Map<MyButton, ArrayList<Tile>> map = new HashMap<MyButton, ArrayList<Tile>>();
	
	private ArrayList<MyButton> buttons = new ArrayList<MyButton>();
	
	// BOTTONI FUNZIONI
	private MyButton bExit, bSave, bGomma, bGrid, bDelete;

	// BOTTONI SINGOLI
	private MyButton bGrass, bWater, bWind, bFlowatingIsland, bSun, bMoon, bCloud, bDoubleCloud, bTrees;

	// BOTTONI MAP
	private MyButton bTerrains, bGrassNPeabbles, bFlowers, bOtherTerrains, bSpikes, bDoors, bLadders, bArrows;

	// BOTTONE SELEZIONATO
	private MyButton currentButton;

	// Varibili di servizio
	private double aniIndex = 0;
	private int currentIndex = 0;
	private int y;

	public Toolbar(int x, int y, int width, int height, Editing editing) {
		super(x, y, width, height);
		this.editing = editing;
		this.y = y;
		initButtons();
	}

	private void initButtons() {

		bExit = new MyButton("Esci", 12, y + 20, 100, 30);
		bSave = new MyButton("Salva", 12, y + 60, 100, 30);
		bGomma = new MyButton("Gomma", 12, y + 100, 100, 30);
		bGrid = new MyButton("Griglia", 12, y + 140, 100, 30);
		bDelete = new MyButton("Reset", 12, y + 180, 100, 30);
		
		int w = 50;
		int h = 50;
		int xStart = 10;
		int yStart = y + 220;
		int yOffset = (int) (w * 1.1f);
		int xOffset = (int) (w * 1.1f);
		int i = 0;

		bGrass = new MyButton("Sprout", xStart, yStart, w, h, i++);
		bWind = new MyButton("Wind", xStart, yStart + yOffset, w, h, i++);
		bWater = new MyButton("Water", xStart, yStart + yOffset * 2, w, h, i++);
		bFlowatingIsland = new MyButton("Island", xStart + xOffset, yStart, w, h, i++);

		// Get grassNPeabbles tiles
		initMapButton(bGrassNPeabbles, editing.getGame().getTileManager().getGrassNPeabbles(), xStart, yStart, yOffset, w, h, i++);
		buttons.add(bGrassNPeabbles);
		
		// Get Terrains tiles
		initMapButton(bTerrains, editing.getGame().getTileManager().getTerrains(), xStart, yStart, yOffset, w, h, i++);
		buttons.add(bTerrains);
		
		// Get ladders tiles
		initMapButton(bLadders, editing.getGame().getTileManager().getLadders(), xStart, yStart, yOffset, w, h, i++);
		buttons.add(bLadders);
		
		// Get doors tiles
		initMapButton(bDoors, editing.getGame().getTileManager().getDoors(), xStart, yStart, yOffset, w, h, i++);
		buttons.add(bDoors);
		
		// Get flowers tiles
		initMapButton(bFlowers, editing.getGame().getTileManager().getFlowers(), xStart, yStart, yOffset, w, h, i++);
		buttons.add(bFlowers);
		
		// Get other_terrains tiles
		initMapButton(bOtherTerrains, editing.getGame().getTileManager().getOther_terrains(), xStart, yStart, yOffset, w, h, i++);
		buttons.add(bOtherTerrains);
		
		// Get spikes tiles
		initMapButton(bSpikes, editing.getGame().getTileManager().getSpikes(), xStart, yStart, yOffset, w, h, i++);
		buttons.add(bSpikes);
		
		// Get sun tiles
		initMapButton(bSun, editing.getGame().getTileManager().getSun(), xStart + (int) (w * 1.1f),
				yStart + (int) (w * 1.1f), 0, w, h, i++, BTN_SUN);
		buttons.add(bSun);
		
		// Get moon tiles
		initMapButton(bMoon, editing.getGame().getTileManager().getMoon(), xStart + (int) (w * 1.1f),
				yStart + (int) (w * 1.1f) * 2, 0, w, h, i++, BTN_MOON);
		buttons.add(bMoon);
		
		// Get sun tiles
		initMapButton(bCloud, editing.getGame().getTileManager().getClouds(), xStart + (int) (w * 1.1f),
				yStart + (int) (w * 1.1f) * 3, 0, w, h, i++, BTN_CLOUDS);
		buttons.add(bCloud);
		
		// Get moon tiles
		initMapButton(bDoubleCloud, editing.getGame().getTileManager().getDouble_clouds(), xStart + (int) (w * 1.1f),
				yStart + (int) (w * 1.1f) * 4, 0, w, h, i++, BTN_DOUBLE_CLOUDS);
		buttons.add(bDoubleCloud);
		
		// Get big tree tiles
		initMapButton(bTrees, editing.getGame().getTileManager().getBig_trees(), xStart + (int) (w * 1.1f),
				yStart + (int) (w * 1.1f) * 5, 0, w, h, i++, BTN_BIG_TREE);
		buttons.add(bTrees);
		
		// Get arrows tiles
		initMapButton(bArrows, editing.getGame().getTileManager().getArrows(), xStart + (int) (w * 1.1f),
				yStart + (int) (w * 1.1f) * 6, 0, w, h, i++);
		buttons.add(bArrows);
		
	}

	private void initMapButton(MyButton b, ArrayList<Tile> list, int x, int y, int xOff, int w, int h, int id) {
		b = new MyButton("", x, y + xOff * (id - 1), w, h, id);
		map.put(b, list);
	}

	private void initMapButton(MyButton b, ArrayList<Tile> list, int x, int y, int xOff, int w, int h, int id,
			int CONST) {
		b = new MyButton("", x, y + xOff * (id - 1), w, h, id);
		b.setBtnConst(CONST);
		map.put(b, list);
	}

	private void saveLevel() {
		editing.saveLevel();
	}

	public void rotateSprite() {
		currentIndex++;
		if (currentIndex >= map.get(currentButton).size())
			currentIndex = 0;
		if(map.get(currentButton).get(0).getBtnConst() == BTN_CLOUDS || map.get(currentButton).get(0).getBtnConst() == BTN_DOUBLE_CLOUDS)
			if(currentIndex > 1)
				currentIndex = 0;
		if(map.get(currentButton).get(0).getBtnConst() == BTN_BIG_TREE)
			if(currentIndex > 3)
				currentIndex = 0;
		
		selectedTile = map.get(currentButton).get(currentIndex);
		editing.setSelectedTile(selectedTile);

	}

	public void draw(Graphics g) {

		// Background
		g.setColor(new Color(15, 123, 150));
		g.fillRect(x, y, width, editing.getGame().getToolkit().getScreenSize().height);

		// Buttons
		drawButtons(g);
		drawSelectedTile(g);
	}

	private void drawButtons(Graphics g) {
		bSave.draw(g);
		g.drawRect(bSave.x, bSave.y, bSave.width - 1, bSave.height- 1);
		bExit.draw(g);
		g.drawRect(bExit.x, bExit.y, bExit.width - 1, bExit.height- 1);
		bGomma.draw(g);
		g.drawRect(bGomma.x, bGomma.y, bGomma.width - 1, bGomma.height- 1);
		bGrid.draw(g);
		g.drawRect(bGrid.x, bGrid.y, bGrid.width - 1, bGrid.height- 1);
		bDelete.draw(g);
		g.drawRect(bDelete.x, bDelete.y, bDelete.width - 1, bDelete.height- 1);
		drawNormalButton(g, bGrass);
		drawNormalButton(g, bWind);
		drawNormalButton(g, bFlowatingIsland);
		drawNormalButton(g, bWater);
		drawSelectedTile(g);
		drawMapButtons(g);

	}

	private void drawNormalButton(Graphics g, MyButton b) {
		g.drawImage(getButtImg(b.getId()), b.x, b.y, b.width, b.height, null);
		g.drawRect(b.x, b.y, 49, 49);
		drawButtonFeedback(g, b);

	}

	private void drawMapButtons(Graphics g) {
		for (Map.Entry<MyButton, ArrayList<Tile>> entry : map.entrySet()) {
			MyButton b = entry.getKey();
			BufferedImage img = entry.getValue().get(0).getSprite();
			if (entry.getValue().get(0).isMultiple()) {
//				System.out.println(entry.getValue().size() + " " + entry.getKey().getBtnConst());
				if (entry.getKey().getBtnConst() == BTN_BIG_TREE)
					img = LoadSave.getSpriteAtlas().getSubimage(0, 12 * 32, 32, 70);
				if (entry.getKey().getBtnConst() == BTN_CLOUDS)
					img = LoadSave.getSpriteAtlas().getSubimage(0, 20 * 32, 32 * 2, 32); 
				if (entry.getKey().getBtnConst() == BTN_SUN)
					img = LoadSave.getSpriteAtlas().getSubimage(0, 22 * 32, 32 * 4, 32 * 2);
				if (entry.getKey().getBtnConst() == BTN_MOON)
					img = LoadSave.getSpriteAtlas().getSubimage(0, 24 * 32, 32 * 4, 32 * 2);
				if (entry.getKey().getBtnConst() == BTN_DOUBLE_CLOUDS)
					img = LoadSave.getSpriteAtlas().getSubimage(0, 26 * 32, 32 * 3, 32);

				double scaleX = (double) b.width / img.getWidth();
				double scaleY = (double) b.height / img.getHeight();

				double scale = Math.min(scaleX, scaleY);
				int newWidth = (int) (img.getWidth() * scale);
				int newHeight = (int) (img.getHeight() * scale);

				int x = b.x + (b.width - newWidth) / 2;
				int y = b.y + (b.height - newHeight) / 2;

				g.drawImage(img, x, y, newWidth, newHeight, null);
				g.drawRect(b.x, b.y, 49, 49);
			} else {
				img = entry.getValue().get(0).getSprite();
				g.drawImage(img, b.x, b.y, b.width, b.height, null);
				g.drawRect(b.x, b.y, 49, 49);
			}
			drawButtonFeedback(g, b);
		}

	}

	private void drawSelectedTile(Graphics g) {
		int size = 130;
		int xButton = 15;
		int yButton = editing.getGame().getToolkit().getScreenSize().height - 150;
		if (selectedTile != null) {
			if (!selectedTile.isAnimation() && !selectedTile.isMultiple()) {
				g.drawImage(selectedTile.getSprite(), xButton, yButton, size, size, null);
				g.setColor(Color.black);
				g.drawRect(xButton, yButton, size, size);
				g.drawRect(xButton, yButton, size-1, size-1);
				
			} else if (selectedTile.isMultiple()) {
				BufferedImage img = selectedTile.getSprite();
				if (selectedTile.getBtnConst() == BTN_BIG_TREE) {
					img = LoadSave.getSpriteAtlas().getSubimage(0 + currentIndex * 32, 12 * 32, 32, 70);
				}if (selectedTile.getBtnConst() == BTN_CLOUDS)
					img = LoadSave.getSpriteAtlas().getSubimage(0, (20 + currentIndex) * 32, 32 * 2, 32);
				if (selectedTile.getBtnConst() == BTN_SUN)
					img = LoadSave.getSpriteAtlas().getSubimage(0, 22 * 32, 32 * 4, 32 * 2);
				if (selectedTile.getBtnConst() == BTN_MOON)
					img = LoadSave.getSpriteAtlas().getSubimage(0, 24 * 32, 32 * 4, 32 * 2);
				if (selectedTile.getBtnConst() == BTN_DOUBLE_CLOUDS)
					img = LoadSave.getSpriteAtlas().getSubimage(0, (26 + currentIndex) * 32, 32 * 3, 32);
				double scaleX = (double) size / img.getWidth();
				double scaleY = (double) size / img.getHeight();

				double scale = Math.min(scaleX, scaleY);

				int newWidth = (int) (img.getWidth() * scale);
				int newHeight = (int) (img.getHeight() * scale);

				int x = xButton + (size - newWidth) / 2;
				int y = yButton + (size - newHeight) / 2;

				g.drawImage(img, x, y, newWidth, newHeight, null);
				g.setColor(Color.black);
				g.drawRect(15, editing.getGame().getToolkit().getScreenSize().height - 150, 130, 130);
				g.drawRect(xButton, yButton, size-1, size-1);
//				System.out.println(selectedTile.getId());
			} else if (selectedTile.isAnimation()) {
				try {
					aniIndex = aniIndex >= selectedTile.getSpriteLenght() ? 0 : aniIndex + 0.014;
					g.drawImage(selectedTile.getSprite((int) aniIndex), 15,
							editing.getGame().getToolkit().getScreenSize().height - 150, 130, 130, null);
					g.setColor(Color.black);
					g.drawRect(15, editing.getGame().getToolkit().getScreenSize().height - 150, 130, 130);
					g.drawRect(xButton, yButton, size-1, size-1);
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
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
		else if (bGomma.getBounds().contains(x, y)) {
			selectedTile = editing.getGame().getTileManager().getGomma();
			editing.setSelectedTile(selectedTile);
			return;
		} else if (bGrid.getBounds().contains(x, y)) {
			editing.setDrawGrid();
			return;
		} else if (bDelete.getBounds().contains(x, y)) {
			int scelta = JOptionPane.showConfirmDialog(null, "Sei Sicuro di voler cancellare tutto", "Reset", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			if(scelta == 0)
				editing.deleteAll();
			return;
		}else if (bWater.getBounds().contains(x, y)) {
			selectedTile = editing.getGame().getTileManager().getTile(bWater.getId());
			editing.setSelectedTile(selectedTile);
			return;
		} else if (bGrass.getBounds().contains(x, y)) {
			selectedTile = editing.getGame().getTileManager().getTile(bGrass.getId());
			editing.setSelectedTile(selectedTile);
			return;
		} else if (bWind.getBounds().contains(x, y)) {
			selectedTile = editing.getGame().getTileManager().getTile(bWind.getId());
			editing.setSelectedTile(selectedTile);
			return;
		} else if (bFlowatingIsland.getBounds().contains(x, y)) {
			selectedTile = editing.getGame().getTileManager().getTile(bFlowatingIsland.getId());
			editing.setSelectedTile(selectedTile);
			return;
		} else {
			for (MyButton b : map.keySet())
				if (b.getBounds().contains(x, y)) {
					if (!map.get(b).get(0).isMultiple()) {
						selectedTile = map.get(b).get(0);
						editing.setSelectedTile(selectedTile);
						currentButton = b;
						currentIndex = 0;
						return;
					} else if (map.get(b).get(0).getBtnConst() == BTN_SUN 
							|| map.get(b).get(0).getBtnConst() == BTN_MOON
                            || map.get(b).get(0).getBtnConst() == BTN_CLOUDS
                            || map.get(b).get(0).getBtnConst() == BTN_DOUBLE_CLOUDS
                            || map.get(b).get(0).getBtnConst() == BTN_BIG_TREE) {
						selectedTile = map.get(b).get(currentIndex);
						editing.setSelectedTile(selectedTile);
						currentButton = b;
						currentIndex = 0;
						return;
					}
				}
		}
	}

	public int getNext(int index, int size) {
		for (MyButton b : map.keySet())
			if (b.getBtnConst() == currentButton.getBtnConst() && map.get(b).get(0).isMultiple()) {
				if (map.get(b).size() > index) {
					currentButton = b;
					if (map.get(b).get(0).getBtnConst() == BTN_BIG_TREE) {
						switch (currentIndex) {
							case 1:
								size = 6;
								break;
							case 2:
								size = 9;
								break;
							case 3:
								size = 12;
								break;
						}
						editing.setSelectedTile(map.get(currentButton).get(index));
						index = index >= size ? 0 : index + 1;
						return index;
					}else if (map.get(b).get(0).getBtnConst() == BTN_CLOUDS) {
						if(currentIndex > 0)
                            size = 6;
						editing.setSelectedTile(map.get(currentButton).get(index));
						index = index >= size ? 0 : index + 1;
						return index;
					}else if (map.get(b).get(0).getBtnConst() == BTN_DOUBLE_CLOUDS) {
                        if(currentIndex > 0)
                            size = 6;
                        editing.setSelectedTile(map.get(currentButton).get(index));
						index = index >= size ? 0 : index + 1;
						return index;
                    }
					
					editing.setSelectedTile(map.get(currentButton).get(index));
					index = index >= size ? 0 : index + 1;
					return index;
				}
					editing.setSelectedTile(map.get(currentButton).get(index));
					index = index > size ? 0 : index + 1;
					return index;
				}
		return 0;
	}

	public void mouseMoved(int x, int y) {
		bSave.setMouseOver(false);
		bExit.setMouseOver(false);
		bGomma.setMouseOver(false);
		bGrid.setMouseOver(false);
		bDelete.setMouseOver(false);
		
		if (bSave.getBounds().contains(x, y))
			bSave.setMouseOver(true);
		else if (bExit.getBounds().contains(x, y))
			bExit.setMouseOver(true);
		else if (bGomma.getBounds().contains(x, y))
			bGomma.setMouseOver(true);
		else if (bGrid.getBounds().contains(x, y))
			bGrid.setMouseOver(true);
		else if (bDelete.getBounds().contains(x, y))
			bDelete.setMouseOver(true);
		
	}

	public void mousePressed(int x, int y) {
		if (bSave.getBounds().contains(x, y)) {
			bSave.setMousePressed(true);
		}else if (bExit.getBounds().contains(x, y)) {
			bExit.setMousePressed(true);
		}else if (bGomma.getBounds().contains(x, y)) {
			bGomma.setMousePressed(true);
		}else if (bGrid.getBounds().contains(x, y)) {
			bGrid.setMousePressed(true);
		}else if (bDelete.getBounds().contains(x, y)) {
			bDelete.setMousePressed(true);
		}else if (bWater.getBounds().contains(x, y)) {
			bWater.setMousePressed(true);
		}else if (bGrass.getBounds().contains(x, y)) {
			bGrass.setMousePressed(true);
		}else if (bWind.getBounds().contains(x, y)) {
			bWind.setMousePressed(true);
		}else if (bFlowatingIsland.getBounds().contains(x, y)) {
			bFlowatingIsland.setMousePressed(true);
		}else {
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
		bWind.resetBooleans();
		bGomma.resetBooleans();
		bGrid.resetBooleans();
		bDelete.resetBooleans();
		bFlowatingIsland.resetBooleans();
		for (MyButton b : map.keySet())
			b.resetBooleans();

	}
	
	public int getCurrentIndex() {
		return currentIndex;
	}
	
}
