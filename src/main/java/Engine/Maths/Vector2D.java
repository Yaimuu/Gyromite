/**
 * @project Gyromite
 * @author Yamuu
 */

package Engine.Maths;

import Engine.GyromiteEngine;

public class Vector2D
{
    public float x;
    public float y;

    public Vector2D()
    {
        this.x = 0f;
        this.y = 0f;
    }

    public Vector2D(float _x, float _y)
    {
        this.x = _x;
        this.y = _y;
    }

    public Direction getDirection(Vector2D posB)
    {
        Direction dir = new Direction(0, 0);

        dir.x = this.x - posB.x;
        dir.y = this.y - posB.y;

        return dir;
    }

    public static Vector2D newVector2DInCell(float cellX, float cellY)
    {
        return new Vector2D(cellX * GyromiteEngine.GYROMITE_CELL_SIZE, cellY * GyromiteEngine.GYROMITE_CELL_SIZE);
    }

    public static float distance(Vector2D A, Vector2D B)
    {
        return (float)Math.sqrt(Math.pow(A.getX() + B.getX(), 2) +  Math.pow(A.getY() + B.getY(), 2));
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
