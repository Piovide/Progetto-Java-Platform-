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
/**
 * questa classe si occupa della gestione della scena dell editing 
 */
public class Editing extends GameScene implements SceneMethods {

	private int[][] lvlBlocks, lvlEntities, lvlObjects;
	private Tile selectedTile;
	private int mouseX, mouseY;
	private LinkedList<Integer> lastTileX, lastTileY, lastTileId;
	private boolean drawSelect, drawMultiple, drawGrid, deleteAll;
	private Toolbar toolbar;
	private Game game;

	private static final int TILE_SIZE = 64;
	
	private int offsetIndex = 0;
	
	private int multiTileIndex, multiPrintTileIndex;
	private Graphics graphics = null;
	/**
	 * metodo costruttore 
	 * @param game
	 */
	public Editing(Game game) {
		super(game);
		this.game = game;
		toolbar = new Toolbar(0, 10, 160, 160, this);
		initComponents();
		loadSavedLevel();
	}
	/**
	 * questo metodo inizzializza le linked list  
	 * e le matrici 
	 */
	private void initComponents() {
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
	/**
     * Carica il livello salvato.
     */
	private void loadSavedLevel() {
		// ROSSO VERDE BLU
		LoadSave.LoadLevelData(lvlBlocks, lvlEntities, lvlObjects, this);
	}
	/**
     * Aggiorna la logica della scena di editing.
     */
	public void update() {
		updateTick();
	}

	@Override
	 /**
     * Disegna la scena di editing.
     * @param g il contesto grafico su cui disegnare
     */
	public void render(Graphics g) {
		if (graphics == null)
			this.graphics = g;
		drawLevelBackground(g);
		drawLevel(g);
		toolbar.draw(g);
		drawSelectedTile(g);
	}

	// SETTA IL BACKGROND CON I TILE GIALLI A RIGHR
	/**
     * Disegna lo sfondo della scena di editing.
     * @param g il contesto grafico su cui disegnare
     */
	private void drawLevelBackground(Graphics g) {
		int startX = 160;
		int startY = 92 - 24;
		g.setColor(new Color(238, 189, 138));
		g.fillRect(0, 0, 1920, 1080);
		g.drawImage(LoadSave.getSpriteAtlas("editing.png"), startX, startY, 1760, 944, null);
	}
	/**
     * Disegna il livello della scena di editing.
     * @param g il contesto grafico su cui disegnare
     */
	private void drawLevel(Graphics g) {
		g.setColor(Color.BLACK);
		int screenHeight = game.getHeight();
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
			for (int x = offsetIndex; x < lvlBlocks[y].length; x++) {
				int id = lvlBlocks[y][x];

				// TOGLIERE IL COMMENTO SOTTO PER RESETTARE LA TELA POI SALVA E RIAVVIA
				// RIMETTENDO IL COMMENTO
				if (deleteAll) {
					lvlBlocks[y][x] = Game.getTileManager().getGomma().getId();
					lvlEntities[y][x] = Game.getTileManager().getGomma().getId();
					lvlObjects[y][x] = Game.getTileManager().getGomma().getId();
				}
				int drawX = startX + (x - offsetIndex) * TILE_SIZE;
				int drawY = startY + y * TILE_SIZE;
				if (drawY + TILE_SIZE >= 0 && drawY < screenHeight) {
					BufferedImage img = getSprite(id);
					if (img.getWidth() != TILE_SIZE || img.getHeight() != TILE_SIZE) {

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
		for (int y = 0; y < lvlEntities.length; y++) {
			for (int x = offsetIndex; x < lvlEntities[y].length; x++) {
				int id = lvlEntities[y][x];

				// TOGLIERE IL COMMENTO SOTTO PER RESETTARE LA TELA POI SALVA E RIAVVIA
				// RIMETTENDO IL COMMENTO
				int drawX = startX + (x - offsetIndex) * TILE_SIZE;
				int drawY = startY + y * TILE_SIZE;
				if (drawY + TILE_SIZE >= 0 && drawY < screenHeight) {
					BufferedImage img = getSprite(id);
					if ((img.getWidth() != TILE_SIZE || img.getHeight() != TILE_SIZE)) {

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
		for (int y = 0; y < lvlObjects.length; y++) {
			for (int x = offsetIndex; x < lvlObjects[y].length; x++) {
				int id = lvlObjects[y][x];
 
				int drawX = startX + (x - offsetIndex) * TILE_SIZE;
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
		int sX = 160;
		int sY = 92 - 24;
		g.drawImage(LoadSave.getSpriteAtlas("EditingTop.png"), sX, sY, 1760, 944, null);
	}
	/**
     * Disegna il tile selezionato.
     * @param g il contesto grafico su cui disegnare
     */
	private void drawSelectedTile(Graphics g) {
		int modX = -17;
		int modY = 28;
		if (selectedTile != null) {
			BufferedImage img = selectedTile.getSprite();

			int newWidth;
			int newHeight;

			int newX;
			int newY;
			if (selectedTile.getId() != 11 && selectedTile.getId() != 10) {
				double scaleX = (double) TILE_SIZE / img.getWidth();
				double scaleY = (double) TILE_SIZE / img.getHeight();
				double scale = Math.max(scaleX, scaleY);
				newWidth = (int) (img.getWidth() * scale);
				newHeight = (int) (img.getHeight() * scale);
				newX = mouseX + modX + (TILE_SIZE - newWidth) / 2;
				newY = mouseY + modY + (TILE_SIZE - newHeight) / 2;
			} else {
				newWidth = TILE_SIZE;
				newHeight = TILE_SIZE;
				newX = mouseX + modX;
				newY = mouseY + modY;
			}

			if (selectedTile.getId() == Game.getTileManager().getGomma().getId()) {
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
				} else if (selectedTile.gettileBtnConst() == BTN_TREE && toolbar.getCurrentIndex() == 2) {
					multiTileIndex = 7;
                    height = 2;
                    width = 2;
                    size = 10;

                    for (int h = 0; h < height; h++) {
                        for (int w = 2; w > 0; w--) {
                        	multiTileIndex = toolbar.getNext(multiTileIndex, size);
                            g.drawImage(selectedTile.getSprite(), newX + w * TILE_SIZE, newY + h * TILE_SIZE, newWidth,
                                    newHeight, null);
                            
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
	 /**
     * Salva il livello corrente.
     */
	public void saveLevel() {
		LoadSave.SaveLevel(lvlBlocks, lvlEntities, lvlObjects, "level.png");
	}
	/**
     * Imposta il tile selezionato.
     * @param tile il tile da impostare come selezionato
     */
	public void setSelectedTile(Tile tile) {
		this.selectedTile = tile;
		if (tile.isMultiple())
			drawMultiple = true;
		else
			drawSelect = true;
	}
	 /**
	  * cambia il tile nella posizione corrente dell mouse
	  * @param x
	  * @param y
	  */
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
						lvlBlocks[tileY][tileX + offsetIndex] = selectedTile.getId();
						break;
					case 1:
						lvlObjects[tileY][tileX + offsetIndex] = selectedTile.getId();
						break;
					case 2:
						lvlEntities[tileY][tileX + offsetIndex] = selectedTile.getId();
						break;
					}
					
					if (selectedTile.getId() == Game.getTileManager().getGomma().getId()) {
						lvlBlocks[tileY][tileX + offsetIndex] = selectedTile.getId();
						lvlEntities[tileY][tileX + offsetIndex] = selectedTile.getId();
						lvlObjects[tileY][tileX + offsetIndex] = selectedTile.getId();
					}
				}
			}
		}
	}
	/**
	 * questo metodo cambia molteplici tiles in base alla posizione 
	 * @param x
	 * @param y
	 * @param nx
	 * @param ny
	 */
	private void changeMultipleTile(int x, int y, int nx, int ny) {
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
					multiPrintTileIndex = 0;
					System.out.println(selectedTile.gettileBtnConst());
					if (selectedTile.gettileBtnConst() == BTN_TREE && toolbar.getCurrentIndex() == 0) {
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
								lvlObjects[tileY + h][tileX + w + offsetIndex] = selectedTile.getId();
							}
						}
					} else if (selectedTile.gettileBtnConst() == BTN_SHIP) {
						for (int h = 0; h < ny; h++) {
							for (int w = 0; w < nx; w++) {
								multiPrintTileIndex = toolbar.getNext(multiPrintTileIndex, ny * nx);
								lvlObjects[tileY + h][tileX + w + offsetIndex] = selectedTile.getId();
							}
						}
					} else if (selectedTile.gettileBtnConst() == BTN_TREE && toolbar.getCurrentIndex() == 1) {
						multiPrintTileIndex = 3;
						nx = 2;
						ny = 2;
						for (int h = 0; h < ny; h++) {
							for (int w = 0; w < nx; w++) {
								multiPrintTileIndex = toolbar.getNext(multiPrintTileIndex, ny * nx);
								lvlObjects[tileY + h][tileX + w + offsetIndex] = selectedTile.getId();
							}
						}
					}else if (selectedTile.gettileBtnConst() == BTN_TREE && toolbar.getCurrentIndex() == 2) {
						multiPrintTileIndex = 7;
						nx = 2;
						ny = 2;
						for (int h = 0; h < ny; h++) {
							for (int w = nx; w > 0; w--) {
								multiPrintTileIndex = toolbar.getNext(multiPrintTileIndex, ny * nx);
								lvlObjects[tileY + h][tileX + w + offsetIndex] = selectedTile.getId();
							}
						}
					}
				}
			}
		}
	}

	@Override
	 /**
     * questo metodo gestisce un clic del mouse.
     * @param x la coordinata x del clic
     * @param y la coordinata y del clic
     */
	public void mouseClicked(int x, int y) {
		if ((x <= 160 && y <= 1080) || (y >= 944 && x >= 160)) {
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
	/**
     * questo metodo gestisce il movimento del mouse.
     * @param x la coordinata x del mouse
     * @param y la coordinata y del mouse
     */
	public void mouseMoved(int x, int y) {
		if (x >= 160 && y < 944) {
			if (selectedTile != null) {
				if (selectedTile.isMultiple())
					drawMultiple = true;
				else
					drawSelect = true;
				mouseX = x / TILE_SIZE * TILE_SIZE;
				mouseY = y / TILE_SIZE * TILE_SIZE;
			}
		} else if ((x <= 160 && y <= 1080) || (y >= 944 && x >= 160)) {
			drawSelect = false;
			drawMultiple = false;
			toolbar.mouseMoved(x, y);
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if ((x <= 160 && y <= 1080) || (y >= 944 && x >= 160)) {
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
	public int getOffsetIndex() {
		return offsetIndex;
	}
	public void increaseOffsetIndex() {
		if(offsetIndex < (lvlBlocks[0].length - ((game.getWidth() - 160) / TILE_SIZE)))
			this.offsetIndex ++;
	}
	
	public void decreaseOffsetIndex() {
		if(offsetIndex > 0)
			this.offsetIndex--;
	}


}
