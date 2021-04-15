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
public class DebugCell extends GameObject {

    public DebugCell(Vector2D position)
    {
        super(position);
        this.position = position;
        this.hitBox = new Collider(this.position, this.size);
        this.hitBox.setTrigger(true);
//        this.sprite = new Sprite("Images/", "Vide.png", this.size, this.position);
    }

}
