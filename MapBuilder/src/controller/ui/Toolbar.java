package controller.ui;

import static controller.helpz.Constants.Buttons.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import controller.helpz.LoadSave;
import model.objects.Tile;
import view.main.Game;
import view.scenes.Editing;
import model.managers.*;


public class Toolbar extends Bar {
	private Editing editing;
	private Tile selectedTile;
	private Map<MyButton, ArrayList<Tile>> map = new HashMap<MyButton, ArrayList<Tile>>();

	private ArrayList<MyButton> buttons = new ArrayList<MyButton>();

	// BOTTONI FUNZIONI
	private MyButton bExit, bSave, bGomma, bGrid, bDelete, bArrowLeft, bArrowRight;

	// BOTTONI SINGOLI
	private MyButton bWaterTop, bWaterBottom, bSpikes;

	// BOTTONI MAP
	private MyButton bGrass, bCannon, bBarrels, bPotions, bOutsideTerrain, bOutsidePillar, bOutsideFlowting,
			bShip, bTrees, bEntity;

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

		bExit = new MyButton("Esci", 40, y + 20, 80, 28, 100);
		bSave = new MyButton("Salva", 40, y + 60, 80, 28, 101);
		bGomma = new MyButton("Gomma", 40, y + 100, 80, 28, 102);
		bGrid = new MyButton("Griglia", 40, y + 140, 80, 28, 103);
		bDelete = new MyButton("Delete", 40, y + 180, 80, 28, 104);
		
		int w = 50;
		int h = 50;
		int xStart = 30;
		int yStart = y + 240;
		int yOffset = (int) (w * 1.1f);
		int xOffset = (int) (w * 1.1f);
		int i = 2;
		
		TileManager tileManager = Game.getTileManager();

		// Get water tiles
		bWaterBottom = new MyButton("Water bottom", xStart, yStart, w, h, tileManager.getWater_bottom().get(0).getId());
		buttons.add(bWaterBottom);
		
		// Get water tiles
		bWaterTop = new MyButton("Water top", xStart, yStart + yOffset, w, h, tileManager.getWater_top().get(0).getId());
		buttons.add(bWaterTop);
		
		// Get grass tiles
		initMapButton(bGrass, tileManager.getGrass(), xStart , yStart, yOffset * i++, w, h, tileManager.getGrass().get(0).getId(), "Grass");
		buttons.add(bGrass);
		
		// Get cannon tiles
		initMapButton(bCannon, tileManager.getCannon(), xStart, yStart, yOffset * i++, w, h, tileManager.getCannon().get(0).getId(), "Cannon");
		buttons.add(bCannon);
		
		// Get spikes tiles
		bSpikes = new MyButton("Spikes", xStart + xOffset, yStart, w, h, tileManager.getSpikes().get(0).getId());
		buttons.add(bSpikes);
		
		// Get barrels tiles
		initMapButton(bBarrels, tileManager.getBarrels(), xStart, yStart, yOffset * i++, w, h, tileManager.getBarrels().get(0).getId(), "Barrels");
		buttons.add(bBarrels);
		
		// Get potions tiles
		initMapButton(bPotions, tileManager.getPotions(), xStart, yStart, yOffset * i++, w, h, tileManager.getPotions().get(0).getId(), "Potions");
		buttons.add(bPotions);
		
		// Get outside terrain tiles
		initMapButton(bOutsideTerrain, tileManager.getOutside_terrain(), xStart,
				yStart, yOffset * i++, w, h, tileManager.getOutside_terrain().get(0).getId(), "Outside terrain");
		buttons.add(bOutsideTerrain);
		
		// Get outside pillar tiles
		initMapButton(bOutsidePillar, tileManager.getOutside_pillar(), xStart, yStart,
				yOffset * i++, w, h, tileManager.getOutside_pillar().get(0).getId(), "Outside pillar");
		buttons.add(bOutsidePillar);
		
		// Get outside flowting tiles
		initMapButton(bOutsideFlowting, tileManager.getOutside_floating(), xStart,
				yStart, yOffset * i++, w, h, tileManager.getOutside_floating().get(0).getId(), "Outside floating");
		buttons.add(bOutsideFlowting);
		
		// Get ship tiles
		initMapButton(bShip, tileManager.getShip(), xStart, yStart, yOffset * i++, w, h, tileManager.getShip().get(0).getId(), "Ship", BTN_SHIP);
		buttons.add(bShip);
		
		// Get trees tiles
		initMapButton(bTrees, tileManager.getTrees(), xStart, yStart, yOffset * i++, w, h, tileManager.getTrees().get(0).getId(), "Trees", BTN_TREE);
		buttons.add(bTrees);
		
		// Get entities tiles
//		bEntity = new MyButton("Player", xStart, yStart + yOffset * i++, w, h, tileManager.getEntities().get(0).getId());
		initMapButton(bEntity, tileManager.getEntities(), xStart, yStart, yOffset * i++, w, h, tileManager.getEntities().get(0).getId(), "Player");
		buttons.add(bEntity);
		
		// Get arrow left
		bArrowRight = new MyButton("Arrow right", 1920 - 70, 1080 - 70, 70, 70, 105);
		buttons.add(bArrowRight);

		// Get arrow right
		bArrowLeft = new MyButton("Arrow left", 160, 1080 - 70, 70, 70, 106);
		buttons.add(bArrowLeft);
		
	}

	private void initMapButton(MyButton b, ArrayList<Tile> list, int x, int y, int xOff, int w, int h, int id, String name) {
		b = new MyButton(name, x, y + xOff, w, h, id);
//		System.out.println("b: " + b.toString() + " " + list.size());
		b.setBtnConst(-1);
		for(int i=0; i < list.size(); i++)
			list.get(i).settileBtnConst(-1);
		map.put(b, list);
	}

	private void initMapButton(MyButton b, ArrayList<Tile> list, int x, int y, int xOff, int w, int h, int id,String name, int CONST) {
		b = new MyButton(name, x, y + xOff, w, h, id);
		for(int i=0; i < list.size(); i++)
			list.get(i).settileBtnConst(CONST);
		map.put(b, list);
	}

	private void saveLevel() {
		editing.saveLevel();
	}

	public void rotateSprite() {
			currentIndex++;
			if (currentIndex >= map.get(currentButton).size())
				currentIndex = 0;
			if (map.get(currentButton).get(0).gettileBtnConst() == BTN_TREE)
				if (currentIndex > 2)
					currentIndex = 0;
	
			selectedTile = map.get(currentButton).get(currentIndex);
			editing.setSelectedTile(selectedTile);
	}

	public void draw(Graphics g) {

		// Background
		g.drawImage(LoadSave.getSpriteAtlas("toolbar.png"), 0, 0, 160, 1080, null);
		// Buttons
		drawButtons(g);
		drawSelectedTile(g);
	}

	private void drawButtons(Graphics g) {
		bSave.draw(g);
		bExit.draw(g);
		bGomma.draw(g);
		bGrid.draw(g);
		bDelete.draw(g);
		bArrowLeft.draw(g);
		bArrowRight.draw(g);
		drawNormalButton(g, bWaterTop);
		drawNormalButton(g, bWaterBottom);
		drawNormalButton(g, bSpikes);
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
				if (entry.getValue().get(0).gettileBtnConst() == BTN_SHIP)
					img = LoadSave.getSpriteAtlas(LoadSave.SHIP).getSubimage(0, 0, 39 * 2, 36 * 2);
				if (entry.getValue().get(0).gettileBtnConst() == BTN_TREE) {
					img = LoadSave.getSpriteAtlas(LoadSave.TREE_ONE_ATLAS).getSubimage(0, 0, 39, 92);
					}
				
				double scaleX = (double) b.width / img.getWidth();
				double scaleY = (double) b.height / img.getHeight();

				double scale = Math.min(scaleX, scaleY);
				int newWidth = (int) (img.getWidth() * scale);
				int newHeight = (int) (img.getHeight() * scale);

				int x = b.x + (b.width - newWidth) / 2;
				int y = b.y + (b.height - newHeight) / 2;

				g.drawImage(img, x, y, newWidth, newHeight, null);
				g.drawRect(b.x, b.y, 49, 49);
			} else if (!entry.getValue().get(0).isMultiple()){
				double scaleX = (double) b.width / img.getWidth();
				double scaleY = (double) b.height / img.getHeight();

				double scale = Math.min(scaleX, scaleY);
				int newWidth = (int) (img.getWidth() * scale);
				int newHeight = (int) (img.getHeight() * scale);

				int x = b.x + (b.width - newWidth) / 2;
				int y = b.y + (b.height - newHeight) / 2;

				g.drawImage(img, x, y, newWidth, newHeight, null);
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
				BufferedImage img = selectedTile.getSprite();
				
				double scaleX = (double) size / img.getWidth();
				double scaleY = (double) size / img.getHeight();

				double scale = Math.min(scaleX, scaleY);

				int newWidth = (int) (img.getWidth() * scale);
				int newHeight = (int) (img.getHeight() * scale);

				int x = xButton + (size - newWidth) / 2;
				int y = yButton + (size - newHeight) / 2;
				
				g.drawImage(selectedTile.getSprite(), x, y, newWidth, newHeight, null);
				g.setColor(Color.black);
				g.drawRect(xButton, yButton, size, size);
				g.drawRect(xButton, yButton, size - 1, size - 1);

			} else if (selectedTile.isMultiple()) {
				BufferedImage img = selectedTile.getSprite();
				if (selectedTile.gettileBtnConst() == BTN_SHIP)
					img = LoadSave.getSpriteAtlas(LoadSave.SHIP).getSubimage(0, 0, 39 * 2, 36 * 2);
				if (selectedTile.gettileBtnConst() == BTN_TREE)
					img = LoadSave.getSpriteAtlas(LoadSave.TREE_ONE_ATLAS).getSubimage(0, 0, 39, 92);
				img.getScaledInstance(39/2, 92/2, BufferedImage.SCALE_SMOOTH);
				double scaleX = (double) size / img.getWidth();
				double scaleY = (double) size / img.getHeight();

				double scale = Math.min(scaleX, scaleY);

				int newWidth = (int) (img.getWidth() * scale);
				int newHeight = (int) (img.getHeight() * scale);

				int x = xButton + (size - newWidth) / 2;
				int y = yButton + (size - newHeight) / 2;

				g.drawImage(img, x, y, newWidth, newHeight, null);
				g.setColor(Color.black);
				g.drawRect(15, editing.getGame().getToolkit().getScreenSize().height - 150, size, size);
				g.drawRect(xButton, yButton, size - 1, size - 1);
//				System.out.println(selectedTile.getId());
			} else if (selectedTile.isAnimation()) {
				try {
					aniIndex = aniIndex >= selectedTile.getSpriteLenght() ? 0 : aniIndex + 0.04;
					
					BufferedImage img = selectedTile.getSprite();
					
					double scaleX = (double) size / img.getWidth();
					double scaleY = (double) size / img.getHeight();

					double scale = Math.min(scaleX, scaleY);

					int newWidth = (int) (img.getWidth() * scale);
					int newHeight = (int) (img.getHeight() * scale);

					int x = xButton + (size - newWidth) / 2;
					int y = yButton + (size - newHeight) / 2;
					
					g.drawImage(selectedTile.getSprite((int) aniIndex), x, y, newWidth, newHeight, null);
					g.setColor(Color.black);
					g.drawRect(15, editing.getGame().getToolkit().getScreenSize().height - 150, 130, 130);
					g.drawRect(xButton, yButton, size - 1, size - 1);
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
		}

	}

	public BufferedImage getButtImg(int id) {
		return Game.getTileManager().getSprite(id);
	}

	public void mouseClicked(int x, int y) {
		if (bSave.getBounds().contains(x, y))
			saveLevel();
		else if (bExit.getBounds().contains(x, y))
			editing.getGame().close();
		else if (bGomma.getBounds().contains(x, y)) {
			selectedTile = Game.getTileManager().getGomma();
			editing.setSelectedTile(selectedTile);
			return;
		} else if (bGrid.getBounds().contains(x, y)) {
			editing.setDrawGrid();
			return;
		}  else if (bDelete.getBounds().contains(x, y)) {
			int scelta = JOptionPane.showConfirmDialog(null, "Vuoi cancellare tutto?", "ATTENZIONE", 2, JOptionPane.WARNING_MESSAGE);
			if (scelta == 0)
				editing.deleteAll();
			return;
		} else if (bArrowRight.getBounds().contains(x, y)) {
			editing.increaseOffsetIndex();
			return;
		} else if (bArrowLeft.getBounds().contains(x, y)) {
			editing.decreaseOffsetIndex();
			return;
		} else if (bWaterTop.getBounds().contains(x, y)) {
			selectedTile = Game.getTileManager().getTile(bWaterTop.getId());
			editing.setSelectedTile(selectedTile);
			return;
		} else if (bWaterBottom.getBounds().contains(x, y)) {
			selectedTile = Game.getTileManager().getTile(bWaterBottom.getId());
			editing.setSelectedTile(selectedTile);
			return;
		} else if (bSpikes.getBounds().contains(x, y)) {
			selectedTile = Game.getTileManager().getTile(bSpikes.getId());
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
					} else if (map.get(b).get(0).gettileBtnConst() == BTN_SHIP) {
						selectedTile = map.get(b).get(currentIndex);
						editing.setSelectedTile(selectedTile);
						currentButton = b;
						currentIndex = 0;
						return;
					}else if (map.get(b).get(0).gettileBtnConst() == BTN_TREE) {
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
			if (map.get(b).get(0).gettileBtnConst() == map.get(currentButton).get(0).gettileBtnConst() && map.get(b).get(0).isMultiple()) {
				if (map.get(b).size() > index) {
					currentButton = b;
					if (map.get(b).get(0).gettileBtnConst() == BTN_TREE && currentIndex == 0) {
						editing.setSelectedTile(map.get(currentButton).get(index));
						index = index >= size ? 0 : index + 1;
						return index;
					}else if (map.get(b).get(0).gettileBtnConst() == BTN_TREE && currentIndex == 1) {
						size = 6;
						editing.setSelectedTile(map.get(currentButton).get(index));
						index = index >= size ? 0 : index + 1;
						return index;
					}else if(map.get(b).get(0).gettileBtnConst() == BTN_TREE && currentIndex == 2) {
						size = 10;
						editing.setSelectedTile(map.get(currentButton).get(index));
						index = index >= size ? 0 : index + 1;
						return index;
					}
					else if (map.get(b).get(0).gettileBtnConst() == BTN_SHIP) {
						if (currentIndex > 0)
							size = 4;
						editing.setSelectedTile(map.get(currentButton).get(index));
						index = index >= size ? 0 : index + 1;
						return index;
					}
				}
			}
		return 0;
	}

	public void mouseMoved(int x, int y) {
		bSave.setMouseOver(false);
		bExit.setMouseOver(false);
		bGomma.setMouseOver(false);
		bGrid.setMouseOver(false);
		bDelete.setMouseOver(false);
		bArrowLeft.setMouseOver(false);
		bArrowRight.setMouseOver(false);
		
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
		else if (bArrowLeft.getBounds().contains(x, y)) {
			bArrowLeft.setMouseOver(true);
			System.out.println("Arrow left");
			}
		else if (bArrowRight.getBounds().contains(x, y)) {
			bArrowRight.setMouseOver(true);
			System.out.println("Arrow right");
		}
		

	}

	public void mousePressed(int x, int y) {
		if (bSave.getBounds().contains(x, y)) {
			bSave.setMousePressed(true);
		} else if (bExit.getBounds().contains(x, y)) {
			bExit.setMousePressed(true);
		} else if (bGomma.getBounds().contains(x, y)) {
			bGomma.setMousePressed(true);
		} else if (bGrid.getBounds().contains(x, y)) {
			bGrid.setMousePressed(true);
		} else if (bDelete.getBounds().contains(x, y)) {
			bDelete.setMousePressed(true);
		} else if (bArrowLeft.getBounds().contains(x, y)) {
			bArrowLeft.setMousePressed(true);
		} else if (bArrowRight.getBounds().contains(x, y)) {
			bArrowRight.setMousePressed(true);
		} else if (bWaterTop.getBounds().contains(x, y)) {
			bWaterTop.setMousePressed(true);
		} else if (bWaterBottom.getBounds().contains(x, y)) {
			bWaterBottom.setMousePressed(true);
		} else if (bSpikes.getBounds().contains(x, y)) {
			bSpikes.setMousePressed(true);
		} else {
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
		bGomma.resetBooleans();
		bGrid.resetBooleans();
		bDelete.resetBooleans();
		bArrowLeft.resetBooleans();
		bArrowRight.resetBooleans();
		bWaterTop.resetBooleans();
		bWaterBottom.resetBooleans();
		bSpikes.resetBooleans();
		for (MyButton b : map.keySet())
			b.resetBooleans();

	}

	public int getCurrentIndex() {
		return currentIndex;
	}
	
	public ArrayList<MyButton> getButtons() {
		return buttons;
	}
}
