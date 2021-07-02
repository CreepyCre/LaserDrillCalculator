package reghzy.laserdrill;

import reghzy.laserdrill.utils.AxisAlignedBB;
import reghzy.laserdrill.utils.Vector2;

/*
 *          2
 *          |
 *  1 ----  D  ---- 3
 *          |
 *          4
 */
public class LaserDrillSetup{
    public AxisAlignedBB boundingBox;
    public Vector2 center;
    public Tile laserDrill;
    public Tile preCharger1;
    public Tile preCharger2;
    public Tile preCharger3;
    public Tile preCharger4;
    public Tile laser1;
    public Tile laser2;
    public Tile laser3;
    public Tile laser4;

    public LaserDrillSetup(Vector2 center) {
        this.center = center;
        this.boundingBox = new AxisAlignedBB();
        this.boundingBox.setMin(this.center.x - 2, this.center.y - 2);
        this.boundingBox.setMax(this.center.x + 2, this.center.y + 2);
        this.laserDrill = new Tile(this.center, TileType.DRILL, TileDirection.NONE);
        this.preCharger1 = new Tile(this.center.copy().subtract(2, 0), TileType.PRE_CHARGER, TileDirection.EAST);
        this.preCharger2 = new Tile(this.center.copy().subtract(0, 2), TileType.PRE_CHARGER, TileDirection.SOUTH);
        this.preCharger3 = new Tile(this.center.copy().add(2, 0), TileType.PRE_CHARGER, TileDirection.WEST);
        this.preCharger4 = new Tile(this.center.copy().add(0, 2), TileType.PRE_CHARGER, TileDirection.NORTH);
        this.laser1 = new Tile(this.center.copy().subtract(1, 0), TileType.LASER, TileDirection.EAST);
        this.laser2 = new Tile(this.center.copy().subtract(0, 1), TileType.LASER, TileDirection.SOUTH);
        this.laser3 = new Tile(this.center.copy().add(1, 0), TileType.LASER, TileDirection.WEST);
        this.laser4 = new Tile(this.center.copy().add(0, 1), TileType.LASER, TileDirection.NORTH);
    }

    // We can stack them on different layers, only the path below the drill itself needs to be open to bedrock.
    public boolean canPlaceAbove(LaserDrillSetup setup) {
        return !intersectsArea(setup) || !tileIntersectsSolidTile(setup.laserDrill);
    }



    private boolean intersectsArea(LaserDrillSetup setup) {
        return this.boundingBox.intersectsAABB(setup.boundingBox);
    }

//    public boolean intersectsAnyTile(LaserDrillSetup setup) {
//        if (tileIntersectsAnyTile(setup.laserDrill))
//            return true;
//        if (tileIntersectsAnyTile(setup.preCharger1))
//            return true;
//        if (tileIntersectsAnyTile(setup.preCharger2))
//            return true;
//        if (tileIntersectsAnyTile(setup.preCharger3))
//            return true;
//        if (tileIntersectsAnyTile(setup.preCharger4))
//            return true;
//
//        // allow the laser beams to cross eachother for a more compact search ;))
//        //if (tileIntersectsAnyTile(setup.laser1))
//        //    return true;
//        //if (tileIntersectsAnyTile(setup.laser2))
//        //    return true;
//        //if (tileIntersectsAnyTile(setup.laser3))
//        //    return true;
//        //if (tileIntersectsAnyTile(setup.laser4))
//        //    return true;
//
//        return false;
//    }

    private boolean tileIntersectsSolidTile(Tile tile) {
        if (tile.intersectsLocation(this.laserDrill))
            return true;
        if (tile.intersectsLocation(this.preCharger1))
            return true;
        if (tile.intersectsLocation(this.preCharger2))
            return true;
        if (tile.intersectsLocation(this.preCharger3))
            return true;
        if (tile.intersectsLocation(this.preCharger4))
            return true;

        return false;
    }
//
//    public boolean tileIntersectsAnyTile(Tile tile) {
//        if (tile.intersectsLocation(this.laserDrill))
//            return true;
//        if (tile.intersectsLocation(this.preCharger1))
//            return true;
//        if (tile.intersectsLocation(this.preCharger2))
//            return true;
//        if (tile.intersectsLocation(this.preCharger3))
//            return true;
//        if (tile.intersectsLocation(this.preCharger4))
//            return true;
//        if (tile.intersectsLocation(this.laser1))
//            return true;
//        if (tile.intersectsLocation(this.laser2))
//            return true;
//        if (tile.intersectsLocation(this.laser3))
//            return true;
//        if (tile.intersectsLocation(this.laser4))
//            return true;
//
//        return false;
//    }
}
