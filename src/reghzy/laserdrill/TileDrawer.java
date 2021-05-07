package reghzy.laserdrill;

import reghzy.laserdrill.utils.Vector2;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.*;
import java.util.HashMap;

public class TileDrawer extends JFrame {
    private final Vector2 gridSize;
    private final Vector2 tileSize;
    private final Vector2 viewportSize;
    private final HashMap<Vector2, LaserDrillSetup> laserDrillSetups;
    private static final int WindowBorderX = 8;
    private static final int WindowBorderY = 31;
    private static final int WindowOffsetX = WindowBorderX + 1;
    private static final int WindowOffsetY = WindowBorderY + 1;

    public TileDrawer(Vector2 gridSize, Vector2 tileSize) {
        this.gridSize = gridSize;
        this.tileSize = tileSize;
        this.viewportSize = new Vector2((gridSize.x * tileSize.x) + gridSize.x, (gridSize.y * tileSize.y) + gridSize.y);
        this.getContentPane().setBackground(new Color(24, 24, 24));
        this.setPreferredSize(new Dimension(this.viewportSize.x + WindowBorderX - 4, this.viewportSize.y + WindowBorderY - 4));
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.laserDrillSetups = new HashMap<Vector2, LaserDrillSetup>(8);
    }

    public void addLaserDrillSetup(LaserDrillSetup setup) {
        this.laserDrillSetups.put(setup.center, setup);
    }

    public void removeLaserDrillSetup(LaserDrillSetup setup) {
        this.laserDrillSetups.remove(setup.center);
    }

    private void renderSetups(Graphics graphics) {
        for(LaserDrillSetup setup : this.laserDrillSetups.values()) {
            drawEffectBetweenTiles(setup.laserDrill, setup.preCharger1, false, graphics);
            drawEffectBetweenTiles(setup.laserDrill, setup.preCharger2, true, graphics);
            drawEffectBetweenTiles(setup.laserDrill, setup.preCharger3, false, graphics);
            drawEffectBetweenTiles(setup.laserDrill, setup.preCharger4, true, graphics);
            renderTiles(setup, graphics);
        }
    }

    private void drawEffectBetweenTiles(Tile a, Tile b, boolean isVertical, Graphics graphics) {
        drawLineEffect(getTileCenterX(a), getTileCenterY(a), getTileCenterX(b), getTileCenterY(b), isVertical, graphics);
    }

    private void drawLineEffect(int x1, int y1, int x2, int y2, boolean isVertical, Graphics graphics) {
        int hThickness = getLaserThickness() / 2;
        if (isVertical) {
            graphics.setColor(Color.WHITE);
            graphics.drawLine(x1 - hThickness, y1, x2 - hThickness, y2);
            graphics.setColor(Color.DARK_GRAY);
            graphics.drawLine(x1, y1, x2, y2);
            graphics.setColor(Color.WHITE);
            graphics.drawLine(x1 + hThickness, y1, x2 + hThickness, y2);
        }
        else {
            graphics.setColor(Color.WHITE);
            graphics.drawLine(x1, y1 - hThickness, x2, y2 - hThickness);
            graphics.setColor(Color.DARK_GRAY);
            graphics.drawLine(x1, y1, x2, y2);
            graphics.setColor(Color.WHITE);
            graphics.drawLine(x1, y1 + hThickness, x2, y2 + hThickness);
        }
    }

    private void renderTiles(LaserDrillSetup setup, Graphics graphics) {
        drawTile(setup.laserDrill, graphics);
        drawTile(setup.preCharger1, graphics);
        drawTile(setup.preCharger2, graphics);
        drawTile(setup.preCharger3, graphics);
        drawTile(setup.preCharger4, graphics);
    }

    private void drawGrid(Graphics graphics) {
        graphics.setColor(new Color(40, 40, 40));
        for(int tileX = 0; tileX < this.gridSize.x; tileX++) {
            for (int tileY = 0; tileY < this.gridSize.y; tileY++) {
                drawTileOutline(tileX, tileY, graphics);
            }
        }

        //for(int gridX = 0; gridX < this.gridSize.x + 2; gridX++) {
        //    int x = getTileOffsetX(gridX);
        //    graphics.drawLine(x, 0, x, this.getSize().height);
        //}
        //for (int gridY = 0; gridY < this.gridSize.y + 2; gridY++) {
        //    int y = getTileOffsetY(gridY);
        //    graphics.drawLine(0, y , this.getSize().width, y);
        //}
    }

    private void drawArrow(Vector2 a, Vector2 b, TileDirection direction, Graphics graphics) {
        graphics.drawLine(a.x, a.y, b.x, b.y);
        drawArrowHead(b.x, b.y, 7, direction, graphics);
    }

    private void drawArrowHead(int x, int y, int size, TileDirection direction, Graphics graphics) {
        int extraSize = (size * 3) / 2;
        switch (direction) {
            case NORTH:
                y -= 2;
                graphics.drawLine(x - size, y, x, y - extraSize);
                graphics.drawLine(x, y - extraSize, x + size, y);
                return;
            case EAST:
                x -= 2;
                graphics.drawLine(x, y - size, x + extraSize, y);
                graphics.drawLine(x + extraSize, y, x, y + size);
                return;
            case SOUTH:
                y += 2;
                graphics.drawLine(x - size, y, x, y + extraSize);
                graphics.drawLine(x, y + extraSize, x + size, y);
                return;
            case WEST:
                x += 2;
                graphics.drawLine(x, y - size, x - extraSize, y);
                graphics.drawLine(x - extraSize, y, x, y + size);
                return;
            case NONE:
                break;
        }
    }

    private void drawTile(Tile tile, Graphics graphics) {
        graphics.setColor(tile.type.getColour());
        graphics.fill3DRect(getTileOffsetX(tile), getTileOffsetY(tile), getTileSizeX(), getTileSizeY(), true);
        graphics.setColor(Color.RED);
        drawArrowHead(getTileCenterX(tile), getTileCenterY(tile), 7, tile.direction, graphics);
        drawArrowHead(getTileCenterX(tile), getTileCenterY(tile), 6, tile.direction, graphics);
    }

    private void drawTile(int x, int y, Graphics graphics) {
        graphics.fill3DRect(getTileOffsetX(x), getTileOffsetY(y), getTileSizeX(), getTileSizeY(), true);
    }

    private void drawTileOutline(int x, int y, Graphics graphics) {
        graphics.drawRect(getTileOffsetX(x), getTileOffsetY(y), getTileSizeX(), getTileSizeY());
    }

    private int getTileCenterX(Tile tile) {
        return getTileOffsetX(tile) + (getTileSizeX() / 2);
    }

    private int getTileCenterY(Tile tile) {
        return getTileOffsetY(tile) + (getTileSizeY() / 2);
    }

    private int getTileOffsetX(Tile tile) {
        return getTileOffsetX(tile.location.x);
    }

    private int getTileOffsetY(Tile tile) {
        return getTileOffsetY(tile.location.y);
    }

    private int getTileOffsetX(int x) {
        return (tileSize.x * x) + WindowOffsetX;
    }

    private int getTileOffsetY(int y) {
        return (tileSize.y * y) + WindowOffsetY;
    }

    private int getTileSizeX() {
        return this.tileSize.x;
    }

    private int getTileSizeY() {
        return this.tileSize.y;
    }

    private int getLaserThickness() {
        return ((getTileSizeX() + getTileSizeY()) / 2) / 8;
    }

    private Vector2 getGridSize() {
        return gridSize;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawGrid(g);
        renderSetups(g);
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        this.viewportSize.x = width;
        this.viewportSize.y = height;
    }
}
