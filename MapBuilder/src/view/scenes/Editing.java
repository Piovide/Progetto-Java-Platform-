package view.scenes;

import static controller.helpz.Constants.Buttons.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import controller.helpz.LoadSave;
import controller.ui.Toolbar;
import model.objects.Tile;
import view.main.Game;

public class Editing extends GameScene implements SceneMethods {

	private int[][] lvl;
	private Tile selectedTile;
	private int mouseX, mouseY;
	private LinkedList<Integer> lastTileX, lastTileY, lastTileId;
	private boolean drawSelect, drawMultiple, drawGrid, deleteAll;
	private Toolbar toolbar;
	private Game game;
<<<<<<<< Updated upstream:MapBuilder/src/Controller/scenes/Editing.java
	private int TILE_SIZE = 23;
========
	private static final String LEVEL_SAVE_FILE = "level.bmp";
	private int TILE_SIZE = 32;
>>>>>>>> Stashed changes:MapBuilder/src/view/scenes/Editing.java
	private int multiTileIndex, multiPrintTileIndex;
	private Graphics graphics = null;

	public Editing(Game game) {
		super(game);
		this.game = game;
<<<<<<<< Updated upstream:MapBuilder/src/Controller/scenes/Editing.java
		toolbar = new Toolbar(0, 0, 160, 160, this);
========
		loadDefaultLevel();
		toolbar = new Toolbar(0, 10, 160, 160, this);
>>>>>>>> Stashed changes:MapBuilder/src/view/scenes/Editing.java
		initTileSize();
		initLinkedList();
		loadSavedLevel();
	}

	private void initTileSize() {
		TILE_SIZE = (game.getToolkit().getScreenSize().width - 160) / 60;
	}

	private void initLinkedList() {
		lastTileX = new LinkedList<Integer>();
		lastTileY = new LinkedList<Integer>();
		lastTileId = new LinkedList<Integer>();

		lastTileX.add(-1);
		lastTileY.add(-1);
		lastTileId.add(-1);
	}

	private void loadSavedLevel() {
		lvl = LoadSave.LoadLevelData();
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
		g.drawImage(LoadSave.getSpriteAtlas("editing.png"), startX, 0, 1760, 1080, null);
	}

	private void drawLevel(Graphics g) {
		g.fillRect(0, 0, TILE_SIZE, TILE_SIZE);
		int screenHeight = game.getHeight();
		int levelWidth = lvl[0].length * TILE_SIZE;
		int levelHeight = lvl.length * TILE_SIZE;

		// SETTA IL COLORE DI BACKGROUND
//        int screenWidth = game.getWidth();
//        g.setColor(new Color(219, 207, 117));
//        g.setColor(Color.black);
//		g.fillRect(0, 0, screenWidth, screenHeight);
//        
		int startX = 170;
		int startY = 50;
		for (int y = 0; y < lvl.length; y++) {
			for (int x = 0; x < lvl[y].length; x++) {
				int id = lvl[y][x];

				// TOGLIERE IL COMMENTO SOTTO PER RESETTARE LA TELA POI SALVA E RIAVVIA
				// RIMETTENDO IL COMMENTO
				if(deleteAll) {
					lvl[y][x] = getGame().getTileManager().getGomma().getId();
				}
				int drawX = startX + x * TILE_SIZE;
				int drawY = startY + y * TILE_SIZE;
				if (drawY + TILE_SIZE >= 0 && drawY < screenHeight) {
					if (isAnimation(id)) {
						g.drawImage(getSprite(id, animationIndex), drawX, drawY, TILE_SIZE, TILE_SIZE, null);
					} else {
						g.drawImage(getSprite(id), drawX, drawY, TILE_SIZE, TILE_SIZE, null);
					}
					if (drawGrid)
						g.drawRect(drawX, drawY, TILE_SIZE, TILE_SIZE);
				}
			}
		}
		deleteAll=false;
		g.setColor(Color.black);
		g.drawRect(startX, startY, levelWidth, levelHeight);
	}

	private void drawSelectedTile(Graphics g) {
		int modX = 4;
		int modY = 8;
		if (selectedTile != null) {
			if (selectedTile.getId() == game.getTileManager().getGomma().getId()) {
				g.drawRect(mouseX - modX, mouseY - modY, TILE_SIZE, TILE_SIZE);
				g.drawRect(mouseX - modX, mouseY - modY, TILE_SIZE - 1, TILE_SIZE - 1);
				g.drawRect(mouseX - modX - 1, mouseY - modY - 1, TILE_SIZE - 1, TILE_SIZE - 1);
			} else if (!selectedTile.isMultiple() && drawSelect) {
				g.drawImage(selectedTile.getSprite(), mouseX - modX, mouseY - modY, TILE_SIZE, TILE_SIZE, null);
			} else if (drawMultiple) {
				Dimension dim = selectedTile.getmultipleBounds(selectedTile.getTileType());
				int height = (int) dim.getHeight();
				int width = (int) dim.getWidth();
				int size = height * width;
				multiTileIndex = 0;
<<<<<<<< Updated upstream:MapBuilder/src/Controller/scenes/Editing.java
				if (selectedTile.getBtnConst() == BTN_BIG_TREE) {
					switch (toolbar.getCurrentIndex()) {
					case 1:
						multiTileIndex = 3;
						break;
					case 2:
						multiTileIndex = 6;
						break;
					case 3:
						multiTileIndex = 9;
						break;
					}
					for (int h = 0; h < height; h++) {
						for (int w = 0; w < width; w++) {
							multiTileIndex = toolbar.getNext(multiTileIndex, size);
							g.drawImage(selectedTile.getSprite(), mouseX - modX + w * TILE_SIZE,
									mouseY - modY + h * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
							// System.out.println(selectedTile.getId());
						}
					}
				} else if (selectedTile.getBtnConst() == BTN_CLOUDS) {
========
				if (selectedTile.gettileBtnConst() == BTN_TREE) {
>>>>>>>> Stashed changes:MapBuilder/src/view/scenes/Editing.java
					switch (toolbar.getCurrentIndex()) {
					case 1:
						multiTileIndex = 4;
						break;
<<<<<<<< Updated upstream:MapBuilder/src/Controller/scenes/Editing.java
					}

					for (int h = 0; h < height; h++) {
						for (int w = 0; w < width; w++) {
							multiTileIndex = toolbar.getNext(multiTileIndex, size);
							g.drawImage(selectedTile.getSprite(), mouseX - modX + w * TILE_SIZE,
									mouseY - modY + h * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
							// System.out.println(selectedTile.getId());
						}

					}
				} else if (selectedTile.getBtnConst() == BTN_DOUBLE_CLOUDS) {
					switch (toolbar.getCurrentIndex()) {
					case 1:
						multiTileIndex = 3;
========
					case 2:
						multiTileIndex = 8;
						break;
					case 3:
						multiTileIndex = 12;
>>>>>>>> Stashed changes:MapBuilder/src/view/scenes/Editing.java
						break;
					}
					for (int h = 0; h < height; h++) {
						for (int w = 0; w < width; w++) {
							multiTileIndex = toolbar.getNext(multiTileIndex, size);
							g.drawImage(selectedTile.getSprite(), mouseX - modX + w * TILE_SIZE,
									mouseY - modY + h * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
							// System.out.println(selectedTile.getId());
						}
					}
				}else if (selectedTile.gettileBtnConst() == BTN_SHIP) {
					multiTileIndex = 0;
					for (int h = 0; h < height; h++) {
						for (int w = 0; w < width; w++) {
							multiTileIndex = toolbar.getNext(multiTileIndex, size);
							g.drawImage(selectedTile.getSprite(), mouseX - modX + w * TILE_SIZE,
									mouseY - modY + h * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
//							System.out.println(selectedTile.getId());
						}
					}
				}
			}

		}
	}

	public void saveLevel() {
		LoadSave.SaveLevel(lvl);
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
			int tileX = (x - 160) / TILE_SIZE;
			int tileY = (y - 50) / TILE_SIZE;

			if (tileX >= 0 && tileX < lvl[0].length && tileY >= 0 && tileY < lvl.length) {
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

					lvl[tileY][tileX] = selectedTile.getId();
				}
			}
		}
	}

	private void changeMultipleTile(int x, int y, int nx, int ny) {
		if (selectedTile != null) {
			int tileX = (x - 160) / TILE_SIZE;
			int tileY = (y - 50) / TILE_SIZE;

			if (tileX >= 0 && tileX < lvl[0].length && tileY >= 0 && tileY < lvl.length) {
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
<<<<<<<< Updated upstream:MapBuilder/src/Controller/scenes/Editing.java
								multiPrintTileIndex = toolbar.getNext(multiPrintTileIndex, ny * nx);
                                lvl[tileY + h][tileX + w] = selectedTile.getId();
							}
						}
					}else if (selectedTile.getBtnConst() == BTN_CLOUDS) {
						switch (toolbar.getCurrentIndex()) {
						case 1:
							multiPrintTileIndex = 4;
							break;
						}

						for (int h = 0; h < ny; h++) {
							for (int w = 0; w < nx; w++) {
								multiPrintTileIndex = toolbar.getNext(multiPrintTileIndex, ny*nx);
								lvl[tileY + h][tileX + w] = selectedTile.getId();
							}

						}
					} else if (selectedTile.getBtnConst() == BTN_DOUBLE_CLOUDS) {
						switch (toolbar.getCurrentIndex()) {
						case 1:
							multiPrintTileIndex = 3;
							break;
						}

						for (int h = 0; h < ny; h++) {
							for (int w = 0; w < nx; w++) {
								multiPrintTileIndex = toolbar.getNext(multiPrintTileIndex, nx*ny);
								lvl[tileY + h][tileX + w] = selectedTile.getId();
							}

========
								multiPrintTileIndex = toolbar.getnext(multiPrintTileIndex, ny * nx);
								lvl[tileY + h][tileX + w] = selectedTile.getId();
							}
>>>>>>>> Stashed changes:MapBuilder/src/view/scenes/Editing.java
						}
					} else {
						for (int h = 0; h < ny; h++) {
							for (int w = 0; w < nx; w++) {
								multiPrintTileIndex = toolbar.getNext(multiPrintTileIndex, ny * nx);
								lvl[tileY + h][tileX + w] = selectedTile.getId();
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
				changeTile(x, y);
			} else if (drawMultiple && selectedTile.isMultiple()) {
				int w = (int) selectedTile.getmultipleBounds(selectedTile.getTileType()).getWidth();
				int h = (int) selectedTile.getmultipleBounds(selectedTile.getTileType()).getHeight();
				changeMultipleTile(x, y, w, h);
			}
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		if (x >= 160) {
			if (selectedTile != null) {
				if (selectedTile.isMultiple()) {
					drawMultiple = true;
					mouseX = x / TILE_SIZE * TILE_SIZE;
					mouseY = y / TILE_SIZE * TILE_SIZE;
				} else {
					drawSelect = true;
					mouseX = x / TILE_SIZE * TILE_SIZE;
					mouseY = y / TILE_SIZE * TILE_SIZE;
				}
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

}
