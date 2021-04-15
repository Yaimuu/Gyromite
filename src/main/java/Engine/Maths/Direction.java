package Engine.Maths;

/**
 * @author Yamuu
 * @project Gyromite
 */
public class Direction {
    public float x;
    public float y;

    public enum direction
    {
        Top,
        Bottom,
        Left,
        Right,
        None
    }

    public Direction()
    {
        this.clear();
    }

    public Direction(float _x, float _y)
    {
        this.x = _x;
        this.y = _y;
    }

    public void clear()
    {
        this.x = 0.0f;
        this.y = 0.0f;
    }
}

//public enum Direction2 {
//    Up, Down, Left, Right, Stand
//}