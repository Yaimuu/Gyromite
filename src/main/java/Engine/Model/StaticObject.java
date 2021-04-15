package Engine.Model;

import Engine.Maths.Collider;
import Engine.Maths.Vector2D;

import java.awt.*;

/**
 * @author Yamuu
 * @project Gyromite
 */
public class StaticObject extends GameObject
{

    private StaticObject()
    {
        super();
        this.mobile = false;
    }

    public StaticObject(Vector2D position)
    {
        this();
        this.position = position;
        this.hitBox = new Collider(this.position, this.size, this);
    }

    public void draw(Graphics g)
    {
        super.draw(g);
    }

    @Override
    public void OnCollisionEnter(GameObject col) {}
}
