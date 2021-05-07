package reghzy.laserdrill.utils;

/**
 * <h2>
 * Axis Aligned Bounding Box
 * </h2>
 * <p>
 * <h3>
 * An AABB is a box that contains X and Y for both the "minimum" and "maximum",
 * </h3>
 * <p>
 * If you imagine standing in the center of a cube/box/AABB looking forward (0,0),
 * with a positive X right and positive Y up coordinate system (right hand)
 * </p>
 * <p>
 * <b>
 * the minimum is the bottom left back
 * </b>
 * </p>
 * <p>
 * <b>
 * the maximum is the top right front
 * </b>
 * </p>
 * </p>
 * <p>
 * An AABB cannot be rotated due to the math behind it and the name:
 * Axis aligned; aligned with the axis. no rotation
 * </p>
 */
public class AxisAlignedBB {
    /**
     * The minimum coordinate on the X axis. Standing in the center of the AABB looking forward, this is the on the left
     */
    public int minX;
    /**
     * The minimum coordinate on the Y axis. Standing in the center of the AABB looking forward, this is the on the bottom
     */
    public int minY;
    /**
     * The minimum coordinate on the X axis. Standing in the center of the AABB looking forward, this is the on the right
     */
    public int maxX;
    /**
     * The maximum coordinate on the Y axis. Standing in the center of the AABB looking forward, this is the on the top
     */
    public int maxY;

    public AxisAlignedBB() { }

    public AxisAlignedBB(int minX, int minY, int maxX, int maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    /**
     * Creates an axis aligned bounding box, where the minimum/maximum
     * coordinates are calculated from the given center and scale
     *
     * @param center The center of the AABB
     * @param scale  The scale of the AABB (aka the distance from the center to the edges)
     */
    public static AxisAlignedBB fromCenterScale(Vector2 center, Vector2 scale) {
        AxisAlignedBB aabb = new AxisAlignedBB();
        aabb.repositionFromCenterAndScale(center, scale);
        return aabb;
    }

    /**
     * Creates an axis aligned bounding box, where the minimum/maximum
     * coordinates are the exact values in the min and max vectors
     *
     * @param min The center of the AABB
     * @param max The scale of the AABB (aka the distance from the center to the edges)
     */
    public static AxisAlignedBB fromMinMax(Vector2 min, Vector2 max) {
        AxisAlignedBB aabb = new AxisAlignedBB();
        aabb.setMin(min);
        aabb.setMax(max);
        return aabb;
    }

    /**
     * Returns the coordinates of this AABB instance's minimum
     * coordinates (in every axis), and returns it in a vector
     */
    public Vector2 getMin() {
        return new Vector2(this.minX, this.minY);
    }

    /**
     * Returns the coordinates of this AABB instance's maximum
     * coordinates (in every axis), and returns it in a vector
     */
    public Vector2 getMax() {
        return new Vector2(this.maxX, this.maxY);
    }

    /**
     * Gets the size of this AABB instance, and returns it in a vector
     *
     * @return
     */
    public Vector2 getSize() {
        return new Vector2(getSizeX(), getSizeY());
    }

    /**
     * Calculates the scale of this AABB instance by halving
     * the sizes in each axis, and returns it in a vector
     * <p>
     * The scale being the distance from the center and the edges, or half
     * the size (size being the distance from the maximum or minimum coordinates)
     * </p>
     */
    public Vector2 getScale() {
        return new Vector2(getScaleX(), getScaleY());
    }

    /**
     * Returns the scale of this AABB instance in the X axis,
     * that being half of the size in the X axis
     */
    public int getScaleX() {
        return getSizeX() / 2;
    }

    /**
     * Returns the scale of this AABB instance in the Y axis,
     * that being half of the size in the Y axis
     */
    public int getScaleY() {
        return getSizeY() / 2;
    }

    /**
     * Calculates the center of this AABB instance using the
     * minimum coordinates and the scale, and returns it in a vector
     */
    public Vector2 getCenter() {
        return getMin().add(getScaleX(), getScaleY());
    }

    /**
     * Gets the size of this AABB instance in the X axis, that being the
     * distance from the maximum to the minimum X coordinates
     */
    public int getSizeX() {
        return maxX - minX;
    }

    /**
     * Gets the size of this AABB instance in the Y axis, that being the
     * distance from the maximum to the minimum Y coordinates
     */
    public int getSizeY() {
        return maxY - minY;
    }

    /**
     * Sets this AABB instance's minimum coordinates as the exact given values
     */
    public void setMin(int x, int y) {
        this.minX = x;
        this.minY = y;
    }

    /**
     * Sets this AABB instance's maximum coordinates as the exact given values
     */
    public void setMax(int x, int y) {
        this.maxX = x;
        this.maxY = y;
    }

    /**
     * Sets this AABB instance's minimums coordinates as the exact values within the given vector
     */
    public void setMin(Vector2 v) {
        this.minX = v.x;
        this.minY = v.y;
    }

    /**
     * Sets this AABB instance's maximums coordinates as the exact values within the given vector
     */
    public void setMax(Vector2 v) {
        this.maxX = v.x;
        this.maxY = v.y;
    }

    /**
     * Calculates new coordinates for this AABB instance's minimum
     * and maximum coordinates using the given center and scale
     *
     * @param center The center of the AABB
     * @param scale  The scale of the AABB (aka the distance from the center to the edges)
     */
    public void repositionFromCenterAndScale(Vector2 center, Vector2 scale) {
        this.minX = center.x - scale.x;
        this.minY = center.y - scale.y;
        this.maxX = center.x + scale.x;
        this.maxY = center.y + scale.y;
    }

    /**
     * Uses the scale of this AABB instance and repositions the minimums
     * and maximums using that scale and the given center
     *
     * @param center The center of the AABB
     */
    public void repositionFromCenter(Vector2 center) {
        Vector2 scale = getScale();
        repositionFromCenterAndScale(center, scale);
    }

    /**
     * Resizes this AABB instance using the center of this AABB and the given scale
     *
     * @param scale The scale of the AABB (aka the distance from the center to the edges)
     */
    public void resizeFromScale(Vector2 scale) {
        Vector2 center = getCenter();
        repositionFromCenterAndScale(center, scale);
    }

    /**
     * Returns the amount that this AABB instance intersects the given AABB in the X axis
     * <p>
     * Returns a positive number if an intersection has happened
     * </p>
     * <p>
     * Returns a negative number if there is no intersection
     * </p>
     */
    public int getIntersectAmountX(AxisAlignedBB box) {
        int size = box.getSizeX();
        int scale = size / 2;
        int center = box.maxX - scale;
        if (this.maxX < center) {
            return this.maxX - box.minX;
        }
        else {
            return box.maxX - this.maxX;
        }
    }

    /**
     * Returns the amount that this AABB instance intersects the given AABB in the Y axis
     * <p>
     * Returns a positive number if an intersection has happened
     * </p>
     * <p>
     * Returns a negative number if there is no intersection
     * </p>
     */
    public int getIntersectAmountY(AxisAlignedBB box) {
        int size = box.getSizeY();
        int scale = size / 2;
        int center = box.maxY - scale;
        if (this.maxY < center) {
            return this.maxY - box.minY;
        }
        else {
            return box.maxY - this.maxY;
        }
    }

    /**
     * Returns true if the given vector is inside (intersecting) this AABB instance
     */
    public boolean intersectsVector(Vector2 v) {
        return intersectsPointX(v.x) &&
               intersectsPointY(v.y);
    }

    /**
     * Returns true if this AABB instance is inside (intersecting, in every axis) the given AABB
     */
    public boolean intersectsAABB(AxisAlignedBB box) {
        return intersectsAABBX(box) &&
               intersectsAABBY(box);
    }

    /**
     * Returns true if this AABB instance is intersecting the given AABB in the X axis
     */
    public boolean intersectsAABBX(AxisAlignedBB box) {
        return intersectsRangeX(box.minX, box.maxX);
    }

    /**
     * Returns true if this AABB instance is intersecting the given AABB in the Y axis
     */
    public boolean intersectsAABBY(AxisAlignedBB box) {
        return intersectsRangeY(box.minY, box.maxY);
    }

    /**
     * Returns true if this AABB instance is intersecting the given minimum X and maximum X range
     */
    public boolean intersectsRangeX(int min, int max) {
        return (min <= this.maxX) && (max >= this.minX);
    }

    /**
     * Returns true if this AABB instance is intersecting the given minimum Y and maximum Y range
     */
    public boolean intersectsRangeY(int min, int max) {
        return (min <= this.maxY) && (max >= this.minY);
    }

    /**
     * Returns true if this AABB instance is intersecting the given Vector2 in the X axis
     */
    public boolean intersectsPointX(int x) {
        return (x <= this.maxX) && (x >= this.minX);
    }

    /**
     * Returns true if this AABB instance is intersecting the given Vector2 in the Y axis
     */
    public boolean intersectsPointY(int y) {
        return (y <= this.maxY) && (y >= this.minY);
    }

    public boolean intersectsPoint(Vector2 v) {
        return intersectsPointX(v.x) && intersectsPointY(v.y);
    }

    /**
     * Returns a copy of this AABB instance (same minimum/maximum values, but different object)
     */
    public AxisAlignedBB copy() {
        return new AxisAlignedBB(this.minX, this.minY, this.maxX, this.maxY);
    }
}