package Game.Model;

import Engine.Maths.Direction;
import Engine.Maths.Vector2D;
import Engine.Model.GameObject;
import Engine.Model.MovingObject;
import Engine.View.Sprite;

import java.awt.*;
import java.util.Random;

/**
 * @author Yamuu
 * @project Gyromite
 */
public class Ennemy extends MovingObject {

    private boolean stay = false;


    public Ennemy(Vector2D position)
    {
        super(position);

        this.name = "Ennemy";
        this.tag = "Ennemy";

        this.speed = new Vector2D(1, 30);

        this.sprite = new Sprite("Images/", "SheerHeartAttack.png", this.size, this.position);

        Random rand = new Random();
        int randInt = rand.nextInt(2);
        if(randInt == 1)
            this.direction = new Direction(1, 0);
        else
            this.direction = new Direction(-1, 0);
//        this.direction = new Direction(0, 0);
        this.hitBox.setTrigger(true);
    }

    public void draw(Graphics g)
    {
        this.move();
        super.draw(g);
        if(!(this.hitBox.getCollidingSides().contains(Direction.direction.Right) ||
                this.hitBox.getCollidingSides().contains(Direction.direction.Left)))
        this.stay = false;
    }

    public void move()
    {
        super.move();

//        this.physics.move(this);
    }

    @Override
    public void OnCollisionEnter(GameObject col) {

        super.OnCollisionEnter(col);

        Direction dir = this.position.getDirection(col.getPosition());

        if(!(col instanceof Player) && !col.getHitBox().isTrigger()
            && col.getPosition().y + col.getSize().height > this.position.y && col.getPosition().y < this.position.y)
        {
            if(this.hitBox.getCollidingSides().contains(Direction.direction.Right) ||
                    this.hitBox.getCollidingSides().contains(Direction.direction.Left))
            {
                this.direction.x *= -1;
            }
//            if(this.hitBox.getCollidingSides().contains(Direction.direction.Right))
//                System.out.println("RIGHT");
        }
    }

}
