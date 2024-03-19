package scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import helpz.LoadSave;
import main.Game;
import objects.Tile;
import ui.Toolbar;

public class Editing extends GameScene implements SceneMethods {

    private int[][] lvl;
    private Tile selectedTile;
    private int mouseX, mouseY;
    private int lastTileX, lastTileY, lastTileId;
    private boolean drawSelect;
    private Toolbar toolbar;
    private Game game;
    private static final String LEVEL_SAVE_FILE = "level.dat";
    private static final int TILE_SIZE = 23;

    private int offsetY = 0;

    public Editing(Game game) {
        super(game);
        this.game = game;
        loadDefaultLevel();
        toolbar = new Toolbar(0, game.getHeight(), 160, 160, this);
        loadSavedLevel();
    }

    private void loadSavedLevel() {
        lvl = LoadSave.LoadLevelData(LEVEL_SAVE_FILE);
        if (lvl == null) {
            loadDefaultLevel();
        }
    }

    private void loadDefaultLevel() {
        lvl = LoadSave.GetLevelData();
    }

    public void update() {
        updateTick();
    }

    @Override
    public void render(Graphics g) {
    	drawLevelBackground(g);
        drawLevel(g);
        toolbar.draw(g);
        drawSelectedTile(g);
    }
    
    // SETTA IL BACKGROND CON I TILE GIALLI A RIGHR
    private void drawLevelBackground(Graphics g) {
    	int startX = 160;
        for (int y = 0; y < game.getHeight()/29; y++) {
            for (int x = 0; x < game.getWidth()/32; x++) {
                int id = game.getTileManager().getTilesLenght();
                int drawX = startX + x * TILE_SIZE;
                int drawY = y * TILE_SIZE;
                g.drawImage(getSprite(id-1,3), drawX, drawY, 48, 48, null);   
            }
        }
    }
    
    private void drawLevel(Graphics g) {
    	g.fillRect(0, 0, lastTileId, TILE_SIZE);
        int screenWidth = game.getWidth();
        int screenHeight = game.getHeight();
        int levelWidth = lvl[0].length * TILE_SIZE;
        int levelHeight = lvl.length * TILE_SIZE;
       
        // SETTA IL COLORE DI BACKGROUND
//        g.setColor(new Color(219, 207, 117));
//        g.setColor(Color.black);
//        g.fillRect(0, 0, screenWidth, screenHeight);
        
        int startX = 160;
        int startY = 50;
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                int drawX = startX + x * TILE_SIZE;
                int drawY = startY + y * TILE_SIZE;
                if (drawY + TILE_SIZE >= 0 && drawY < screenHeight) {
	                    if (isAnimation(id)) {
	                        g.drawImage(getSprite(id, animationIndex), drawX, drawY, TILE_SIZE, TILE_SIZE, null);
	                    } else {
	                        g.drawImage(getSprite(id), drawX, drawY, TILE_SIZE, TILE_SIZE, null);
	                    }
                }
            }
        }
        g.setColor(Color.black);
        g.drawRect(startX, startY, levelWidth-5, levelHeight);
    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null && drawSelect) {
        	if(!selectedTile.isMultiple())
        		g.drawImage(selectedTile.getSprite(), mouseX - 1, mouseY+4, TILE_SIZE, TILE_SIZE, null);
        	else {
        		for (int i = 0; i < selectedTile.getSpriteLenght(); i++) {
            		g.drawImage(selectedTile.getSprite(i), mouseX - 1 - (TILE_SIZE * i), mouseY + 4, TILE_SIZE, TILE_SIZE, null);            		
        		}
        	}
        		
        }
    }

    public void saveLevel() {
        LoadSave.SaveLevel(lvl, LEVEL_SAVE_FILE);
    }

    public void setSelectedTile(Tile tile) {
        this.selectedTile = tile;
        drawSelect = true;
    }

    private void changeTile(int x, int y) {
        if (selectedTile != null) {
            int tileX = (x - 160) / TILE_SIZE;
            int tileY = (y - 50) / TILE_SIZE;

            if (tileX >= 0 && tileX < lvl[0].length && tileY >= 0 && tileY < lvl.length) {
                if (selectedTile.getId() >= 0) {
                    if (lastTileX == tileX && lastTileY == tileY && lastTileId == selectedTile.getId())
                        return;

                    lastTileX = tileX;
                    lastTileY = tileY;
                    lastTileId = selectedTile.getId();

                    lvl[tileY][tileX] = selectedTile.getId();
                }
            }
        }
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (x <= 160) {
            toolbar.mouseClicked(x, y);
        } else {
            changeTile(x, y);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (x >= 160) {
            drawSelect = true;
            mouseX = x / TILE_SIZE * TILE_SIZE;
            mouseY = y / TILE_SIZE * TILE_SIZE;
        } else {
            drawSelect = false;
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
        if (x <= 160) {
        } else {
            changeTile(x, y);
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_R) {
            toolbar.rotateSprite();
        }
    }

    public Game getGame() {
        return game;
    }

}
