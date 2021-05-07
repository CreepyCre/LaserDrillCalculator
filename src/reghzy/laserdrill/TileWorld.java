package reghzy.laserdrill;

import reghzy.laserdrill.utils.Vector2;

import java.net.URL;
import java.util.ArrayList;

public class TileWorld {
    private final ArrayList<LaserDrillSetup> setups = new ArrayList<LaserDrillSetup>(16);

    public static void main(String[] args) {
        new TileWorld().run();
    }

    public void run() {
        int laserDrills = 4;

        Vector2 gridSize = new Vector2(16, 16);
        Vector2 tileSize = new Vector2(32, 32);

        for (int x = 2; x < (gridSize.x - 2); x++) {
            for (int y = 2; y < (gridSize.y - 2); y++) {
                LaserDrillSetup setup = new LaserDrillSetup(new Vector2(x, y));
                if (canPlace(setup)) {
                    this.setups.add(setup);
                }
            }
        }

        TileDrawer drawer = new TileDrawer(gridSize, tileSize);
        for (LaserDrillSetup setup : setups) {
            drawer.addLaserDrillSetup(setup);
        }

        //drawer.addLaserDrillSetup(new LaserDrillSetup(new Vector2(6, 8)));
    }

    public boolean canPlace(LaserDrillSetup laserSetup) {
        for(LaserDrillSetup setup : setups) {
            if (setup.intersectsArea(laserSetup)) {
                if (setup.intersectsAnyTile(laserSetup)) {
                    return false;
                }
            }
        }
        return true;
    }
}
