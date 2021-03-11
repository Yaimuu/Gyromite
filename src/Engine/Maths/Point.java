/**
 * @project Gyromite
 * @author Yamuu
 */

package Engine.Maths;

public class Point
{
    private float x;
    private float y;

    public Point()
    {
        this.x = 0f;
        this.y = 0f;
    }

    public Point(float _x, float _y)
    {
        this.x = _x;
        this.y = _y;
    }

    public static float distance(Point A, Point B)
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
