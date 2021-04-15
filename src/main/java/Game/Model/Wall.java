package Game.Model;

import Engine.Maths.Collider;
import Engine.Maths.Vector2D;
import Engine.Model.GameObject;
import Engine.Model.StaticObject;
import Engine.Physics;
import Engine.View.Sprite;

import java.awt.*;

/**
 * @author Yamuu
 * @project Gyromite
 */
public class Wall extends StaticObject {

    public Wall(Vector2D position)
    {
        super(position);

        this.name = "Wall";
        this.tag = "Wall";

        this.sprite = new Sprite("Images/", "Wall.png", this.size, this.position);
        this.sprite.setMode(Sprite.SpriteMode.Repeat);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    public void addWallTestListeners()
    {

    }

    @Override
    public void OnCollisionEnter(GameObject col) {

        if(col instanceof Wall)
        {

        }
    }
}
