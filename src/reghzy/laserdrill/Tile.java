package reghzy.laserdrill;

import reghzy.laserdrill.utils.Vector2;

public class Tile {
    public TileType type;
    public Vector2 location;
    public TileDirection direction;

    public Tile(Vector2 location, TileType type, TileDirection direction) {
        this.location = location;
        this.type = type;
        this.direction = direction;
    }

    public boolean intersectsLocation(Tile tile) {
        return this.location.equals(tile.location);
    }
}
