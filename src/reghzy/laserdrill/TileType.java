package reghzy.laserdrill;

import java.awt.*;

public enum TileType {
    PRE_CHARGER(new Color(20, 240, 60)),
    DRILL(Color.RED),
    LASER(Color.WHITE),
    EMPTY(Color.DARK_GRAY);

    private Color colour;

    TileType(Color colour) {
        this.colour = colour;
    }

    public Color getColour() {
        return this.colour;
    }
}
