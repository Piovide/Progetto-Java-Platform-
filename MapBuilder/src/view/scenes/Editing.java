package view.scenes;

import static controller.helpz.Constants.Buttons.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import controller.helpz.LoadSave;
import controller.ui.Toolbar;
import model.objects.Tile;
import view.main.Game;

public class Editing extends GameScene implements SceneMethods {

	private int[][] lvlBlocks, lvlEntities, lvlObjects;
	private Tile selectedTile;
	private int mouseX, mouseY;
	private LinkedList<Integer> lastTileX, lastTileY, lastTileId;
	private boolean drawSelect, drawMultiple, drawGrid, deleteAll;
	private Toolbar toolbar;
	private Game game;

	private static final int TILE_SIZE = 64;

	private int multiTileIndex, multiPrintTileIndex;
	private Graphics graphics = null;

	public Editing(Game game) {
		super(game);
		this.game = game;
		toolbar = new Toolbar(0, 10, 160, 160, this);
		loadSavedLevel();
		initLinkedList();
	}

	private void initLinkedList() {
		lastTileX = new LinkedList<Integer>();
		lastTileY = new LinkedList<Integer>();
		lastTileId = new LinkedList<Integer>();

		lastTileX.add(-1);
		lastTileY.add(-1);
		lastTileId.add(-1);

		lvlBlocks = new int[14][90];
		lvlEntities = new int[14][90];
		lvlObjects = new int[14][90];
	}

	private void loadSavedLevel() {
		// ROSSO VERDE BLU
		LoadSave.LoadLevelData(lvlBlocks, lvlEntities, lvlObjects, this);
	}

	public void update() {
		updateTick();
	}

	@Override
	public void render(Graphics g) {
		if (graphics == null)
			this.graphics = g;
		drawLevelBackground(g);
		drawLevel(g);
		toolbar.draw(g);
		drawSelectedTile(g);

	}

	// SETTA IL BACKGROND CON I TILE GIALLI A RIGHR
	private void drawLevelBackground(Graphics g) {
		int startX = 160;
		int startY = 92 - 24;
		g.drawImage(LoadSave.getSpriteAtlas("editing.png"), startX, startY, 1760, 944, null);
	}

	private void drawLevel(Graphics g) {
		g.fillRect(0, 0, TILE_SIZE, TILE_SIZE);
		int screenHeight = game.getHeight();
		int levelWidth = lvlBlocks[0].length * TILE_SIZE;
		int levelHeight = lvlBlocks.length * TILE_SIZE;

		// SETTA IL COLORE DI BACKGROUND
//        int screenWidth = game.getWidth();
//        g.setColor(new Color(219, 207, 117));
//        g.setColor(Color.black);
//		g.fillRect(0, 0, screenWidth, screenHeight);
//        
		int startX = 175;
		int startY = screenHeight / 2 - levelHeight / 2;

		for (int y = 0; y < lvlBlocks.length; y++) {
			for (int x = 0; x < lvlBlocks[y].length; x++) {
				int id = lvlBlocks[y][x];

				// TOGLIERE IL COMMENTO SOTTO PER RESETTARE LA TELA POI SALVA E RIAVVIA
				// RIMETTENDO IL COMMENTO
				if (deleteAll) {
					lvlBlocks[y][x] = getGame().getTileManager().getGomma().getId();
//					lvlEntities[y][x] = getGame().getTileManager().getGomma().getId();
//					lvlObjects[y][x] = getGame().getTileManager().getGomma().getId();
				}
				int drawX = startX + x * TILE_SIZE;
				int drawY = startY + y * TILE_SIZE;
				if (drawY + TILE_SIZE >= 0 && drawY < screenHeight) {
					BufferedImage img = getSprite(id);
					if ((img.getWidth() != TILE_SIZE || img.getHeight() != TILE_SIZE) && id != 10 && id != 11) {

						double scaleX = (double) TILE_SIZE / img.getWidth();
						double scaleY = (double) TILE_SIZE / img.getHeight();

						double scale = Math.max(scaleX, scaleY);

						int newWidth = (int) (img.getWidth() * scale);
						int newHeight = (int) (img.getHeight() * scale);

						int newX = drawX + (TILE_SIZE - newWidth) / 2;
						int newY = drawY + (TILE_SIZE - newHeight) / 2;
						if (isAnimation(id)) {
							g.drawImage(getSprite(id, animationIndex), newX, newY, newWidth, newHeight, null);
						} else {
							g.drawImage(getSprite(id), newX, newY, newWidth, newHeight, null);
						}
					} else {
						if (isAnimation(id)) {
							g.drawImage(getSprite(id, animationIndex), drawX, drawY, TILE_SIZE, TILE_SIZE, null);
						} else {
							g.drawImage(getSprite(id), drawX, drawY, TILE_SIZE, TILE_SIZE, null);
						}
					}
					if (drawGrid)
						g.drawRect(drawX, drawY, TILE_SIZE, TILE_SIZE);
				}
			}
		}
		for (int y = 0; y < lvlBlocks.length; y++) {
			for (int x = 0; x < lvlBlocks[y].length; x++) {
				int id = lvlEntities[y][x];

				// TOGLIERE IL COMMENTO SOTTO PER RESETTARE LA TELA POI SALVA E RIAVVIA
				// RIMETTENDO IL COMMENTO
				if (deleteAll) {
//					lvlBlocks[y][x] = getGame().getTileManager().getGomma().getId();
					lvlEntities[y][x] = getGame().getTileManager().getGomma().getId();
//					lvlObjects[y][x] = getGame().getTileManager().getGomma().getId();
				}
				int drawX = startX + x * TILE_SIZE;
				int drawY = startY + y * TILE_SIZE;
				if (drawY + TILE_SIZE >= 0 && drawY < screenHeight) {
					BufferedImage img = getSprite(id);
					if ((img.getWidth() != TILE_SIZE || img.getHeight() != TILE_SIZE) && id != 10 && id != 11) {

						double scaleX = (double) TILE_SIZE / img.getWidth();
						double scaleY = (double) TILE_SIZE / img.getHeight();

						double scale = Math.max(scaleX, scaleY);

						int newWidth = (int) (img.getWidth() * scale);
						int newHeight = (int) (img.getHeight() * scale);

						int newX = drawX + (TILE_SIZE - newWidth) / 2;
						int newY = drawY + (TILE_SIZE - newHeight) / 2;
						if (isAnimation(id)) {
							g.drawImage(getSprite(id, animationIndex), newX, newY, newWidth, newHeight, null);
						} else {
							g.drawImage(getSprite(id), newX, newY, newWidth, newHeight, null);
						}
					} else {
						if (isAnimation(id)) {
							g.drawImage(getSprite(id, animationIndex), drawX, drawY, TILE_SIZE, TILE_SIZE, null);
						} else {
							g.drawImage(getSprite(id), drawX, drawY, TILE_SIZE, TILE_SIZE, null);
						}
					}
					if (drawGrid)
						g.drawRect(drawX, drawY, TILE_SIZE, TILE_SIZE);
				}
			}
		}
		for (int y = 0; y < lvlBlocks.length; y++) {
			for (int x = 0; x < lvlBlocks[y].length; x++) {
				int id = lvlObjects[y][x];
				// TOGLIERE IL COMMENTO SOTTO PER RESETTARE LA TELA POI SALVA E RIAVVIA
				// RIMETTENDO IL COMMENTO
				if (deleteAll) {
//					lvlBlocks[y][x] = getGame().getTileManager().getGomma().getId();
//					lvlEntities[y][x] = getGame().getTileManager().getGomma().getId();
					lvlObjects[y][x] = getGame().getTileManager().getGomma().getId();
				}
				int drawX = startX + x * TILE_SIZE;
				int drawY = startY + y * TILE_SIZE;
				if (drawY + TILE_SIZE >= 0 && drawY < screenHeight) {
					BufferedImage img = getSprite(id);
					if ((img.getWidth() != TILE_SIZE || img.getHeight() != TILE_SIZE) && id != 10 && id != 11) {

						double scaleX = (double) TILE_SIZE / img.getWidth();
						double scaleY = (double) TILE_SIZE / img.getHeight();

						double scale = Math.max(scaleX, scaleY);

						int newWidth = (int) (img.getWidth() * scale);
						int newHeight = (int) (img.getHeight() * scale);

						int newX = drawX + (TILE_SIZE - newWidth) / 2;
						int newY = drawY + (TILE_SIZE - newHeight) / 2;
						if (isAnimation(id)) {
							g.drawImage(getSprite(id, animationIndex), newX, newY, newWidth, newHeight, null);
						} else {
							g.drawImage(getSprite(id), newX, newY, newWidth, newHeight, null);
						}
					} else {
						if (isAnimation(id)) {
							g.drawImage(getSprite(id, animationIndex), drawX, drawY, TILE_SIZE, TILE_SIZE, null);
						} else {
							g.drawImage(getSprite(id), drawX, drawY, TILE_SIZE, TILE_SIZE, null);
						}
					}
					if (drawGrid)
						g.drawRect(drawX, drawY, TILE_SIZE, TILE_SIZE);
				}
			}
		}

		deleteAll = false;
		g.setColor(Color.black);
		g.drawRect(startX, startY, levelWidth, levelHeight);
	}

	private void drawSelectedTile(Graphics g) {
		int modX = -17;
		int modY = 28;
		if (selectedTile != null) {
			BufferedImage img = selectedTile.getSprite();

			double scaleX = (double) TILE_SIZE / img.getWidth();
			double scaleY = (double) TILE_SIZE / img.getHeight();

			double scale = Math.max(scaleX, scaleY);

			int newWidth = (int) (img.getWidth() * scale);
			int newHeight = (int) (img.getHeight() * scale);

			int newX = mouseX + modX + (TILE_SIZE - newWidth) / 2;
			int newY = mouseY + modY + (TILE_SIZE - newHeight) / 2;
			if (selectedTile.getId() == game.getTileManager().getGomma().getId()) {
				g.drawRect(mouseX - (-modX), mouseY - (-modY), TILE_SIZE, TILE_SIZE);
				g.drawRect(mouseX - (-modX), mouseY - (-modY), TILE_SIZE - 1, TILE_SIZE - 1);
				g.drawRect(mouseX - (-modX) - 1, mouseY - (-modY) - 1, TILE_SIZE - 1, TILE_SIZE - 1);
			} else if (!selectedTile.isMultiple() && drawSelect) {
				g.drawImage(selectedTile.getSprite(), newX, newY, newWidth, newHeight, null);
			} else if (drawMultiple) {
				Dimension dim = selectedTile.getmultipleBounds(selectedTile.getTileType());
				int height = (int) dim.getHeight();
				int width = (int) dim.getWidth();
				int size = height * width;

				multiTileIndex = 0;
				if (selectedTile.gettileBtnConst() == BTN_TREE && toolbar.getCurrentIndex() == 0) {
					for (int h = 0; h < height; h++) {
						for (int w = 0; w < width; w++) {
							multiTileIndex = toolbar.getNext(multiTileIndex, size);
							g.drawImage(selectedTile.getSprite(), newX + w * TILE_SIZE, newY + h * TILE_SIZE, newWidth,
									newHeight, null);
							// System.out.println(selectedTile.getId());
						}
					}
				} else if (selectedTile.gettileBtnConst() == BTN_TREE && toolbar.getCurrentIndex() == 1) {
					multiTileIndex = 3;
					height = 2;
					width = 2;
					size = 4;
					for (int h = 0; h < height; h++) {
						for (int w = 0; w < width; w++) {
							multiTileIndex = toolbar.getNext(multiTileIndex, size);
							g.drawImage(selectedTile.getSprite(), newX + w * TILE_SIZE, newY + h * TILE_SIZE, newWidth,
									newHeight, null);
//							System.out.println(selectedTile.getId());
						}
					}
				} else if (selectedTile.gettileBtnConst() == BTN_SHIP) {
					multiTileIndex = 0;
					System.out.println("heigh" + height + " width" + width + " size" + size);
					for (int h = 0; h < height; h++) {
						for (int w = 0; w < width; w++) {
							multiTileIndex = toolbar.getNext(multiTileIndex, size);
							g.drawImage(selectedTile.getSprite(), newX + w * TILE_SIZE, newY + h * TILE_SIZE, newWidth,
									newHeight, null);
//							System.out.println(selectedTile.getId());
						}
					}
				}
			}

		}
	}

	public void saveLevel() {
		LoadSave.SaveLevel(lvlBlocks, lvlEntities, lvlObjects, "level.png");
	}

	public void setSelectedTile(Tile tile) {
		this.selectedTile = tile;
		if (tile.isMultiple())
			drawMultiple = true;
		else
			drawSelect = true;
	}

	private void changeTile(int x, int y) {
		if (selectedTile != null) {
			int screenHeight = game.getHeight();
			int levelHeight = lvlBlocks.length * TILE_SIZE;

			int tileX = (x - 160) / TILE_SIZE;
			int tileY = (y - (screenHeight / 2 - levelHeight / 2)) / TILE_SIZE;

			if (tileX >= 0 && tileX < lvlBlocks[0].length && tileY >= 0 && tileY < lvlBlocks.length) {
				if (selectedTile.getId() >= 0) {
					if (lastTileX.getLast() == tileX && lastTileY.getLast() == tileY
							&& lastTileId.getLast() == selectedTile.getId())
						return;
					if (lastTileId.getLast() == -1) {
						lastTileX.removeLast();
						lastTileY.removeLast();
						lastTileId.removeLast();
					}
					lastTileX.add(tileX);
					lastTileY.add(tileY);
					lastTileId.add(selectedTile.getId());
					switch (selectedTile.getType()) {
						case 0:
							lvlBlocks[tileY][tileX] = selectedTile.getId();
							break;
						case 1:
							lvlEntities[tileY][tileX] = selectedTile.getId();
							break;
						case 2:
							lvlObjects[tileY][tileX] = selectedTile.getId();
							break;
					}
				}
			}
		}
	}

	private void changeMultipleTile(int x, int y, int nx, int ny) {
		if (selectedTile != null) {
			int screenHeight = game.getHeight();
			int levelHeight = lvlEntities.length * TILE_SIZE;

			int tileX = (x - 160) / TILE_SIZE;
			int tileY = (y - (screenHeight / 2 - levelHeight / 2)) / TILE_SIZE;

			if (tileX >= 0 && tileX < lvlEntities[0].length && tileY >= 0 && tileY < lvlEntities.length) {
				if (selectedTile.getId() >= 0) {
					if (lastTileX.getLast() == tileX && lastTileY.getLast() == tileY
							&& lastTileId.getLast() == selectedTile.getId())
						return;
					if (lastTileId.getLast() == -1) {
						lastTileX.removeLast();
						lastTileY.removeLast();
						lastTileId.removeLast();
					}
					lastTileX.add(tileX);
					lastTileY.add(tileY);
					lastTileId.add(selectedTile.getId());
					multiPrintTileIndex = 0;
					System.out.println(selectedTile.gettileBtnConst());
					if (selectedTile.gettileBtnConst() == BTN_TREE) {
						switch (toolbar.getCurrentIndex()) {
						case 1:
							multiPrintTileIndex = 3;
							break;
						case 2:
							multiPrintTileIndex = 6;
							break;
						case 3:
							multiPrintTileIndex = 9;
							break;
						}
						for (int h = 0; h < ny; h++) {
							for (int w = 0; w < nx; w++) {
								multiPrintTileIndex = toolbar.getNext(multiPrintTileIndex, ny * nx);
								lvlObjects[tileY + h][tileX + w] = selectedTile.getId();
							}
						}
					} else {
						for (int h = 0; h < ny; h++) {
							for (int w = 0; w < nx; w++) {
								multiPrintTileIndex = toolbar.getNext(multiPrintTileIndex, ny * nx);
								lvlObjects[tileY + h][tileX + w] = selectedTile.getId();
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (x <= 160) {
			toolbar.mouseClicked(x, y);
		} else {
			if (drawSelect && !selectedTile.isMultiple()) {
				changeTile(x - (TILE_SIZE / 2), y);
			} else if (drawMultiple && selectedTile.isMultiple()) {
				int w = (int) selectedTile.getmultipleBounds(selectedTile.getTileType()).getWidth();
				int h = (int) selectedTile.getmultipleBounds(selectedTile.getTileType()).getHeight();
				changeMultipleTile(x - (TILE_SIZE / 2), y, w, h);
			}
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		if (x >= 160) {
			if (selectedTile != null) {
				if (selectedTile.isMultiple())
					drawMultiple = true;
				else
					drawSelect = true;
				mouseX = x / TILE_SIZE * TILE_SIZE;
				mouseY = y / TILE_SIZE * TILE_SIZE;
			}
		} else if (x <= 160) {
			drawSelect = false;
			drawMultiple = false;
			toolbar.mouseMoved(x, y);
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if (x <= 160) {
			toolbar.mousePressed(x, y);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		toolbar.mouseReleased(x, y);
	}

	@Override
	public void mouseDragged(int x, int y) {
		if (x >= 160) {
			if (drawSelect && !selectedTile.isMultiple()) {
				changeTile(x, y);
				mouseX = x / TILE_SIZE * TILE_SIZE;
				mouseY = y / TILE_SIZE * TILE_SIZE;
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_R) {
			if ((selectedTile != null && !drawMultiple)
					|| (selectedTile != null && selectedTile.gettileBtnConst() == BTN_TREE)) {
				toolbar.rotateSprite();
			}
		}
	}

	public Game getGame() {
		return game;
	}

	public void setDrawGrid() {
		this.drawGrid = !drawGrid;
	}

	public void deleteAll() {
		this.deleteAll = true;
	}

	public int[][] getLvlBlocks() {
		return lvlBlocks;
	}

	public void setLvlBlocks(int[][] lvlBlocks) {
		this.lvlBlocks = lvlBlocks;
	}

	public int[][] getLvlEntities() {
		return lvlEntities;
	}

	public void setLvlEntities(int[][] lvlEntities) {
		this.lvlEntities = lvlEntities;
	}

	public int[][] getLvlObjects() {
		return lvlObjects;
	}

	public void setLvlObjects(int[][] lvlObjects) {
		this.lvlObjects = lvlObjects;
	}

}
