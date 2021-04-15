package Game.Model;

import Engine.Maths.Collider;
import Engine.Maths.Vector2D;
import Engine.Model.GameObject;
import Engine.Model.StaticObject;
import Engine.View.Sprite;

/**
 * @author Yamuu
 * @project Gyromite
 */
public class Rope extends StaticObject {



    public Rope(Vector2D position)
    {
        super(position);

        this.name = "Rope";
        this.tag = "Rope";
        this.hitBox.setTrigger(true);
        this.sprite = new Sprite("Images/", "Rope3V2.png", this.size, this.position);
        this.sprite.setMode(Sprite.SpriteMode.Repeat);
    }


}
