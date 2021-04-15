package Game.Model;

import Engine.Maths.Collider;
import Engine.Maths.Vector2D;
import Engine.Model.GameObject;
import Engine.Model.StaticObject;
import Engine.View.Sprite;
import Game.View.ViewGame;

import java.awt.*;

/**
 * @author Yamuu
 * @project Gyromite
 */
public class Bomb extends StaticObject {


    public Bomb(Vector2D position)
    {
        super(position);

        this.consumable = true;
        this.hitBox.setTrigger(true);

        this.name = "Bomb";
        this.tag = "Bomb";

        this.sprite = new Sprite("Images/", "BombV2.png", this.size, this.position);

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    @Override
    public void OnCollisionEnter(GameObject col) {

        if(col.getTag() == "Player")
        {
            ViewGame.increaseScore();
            this.destroy();
            this.game.checkForWin();
        }
    }
}
