package Engine.Model;

import Engine.Maths.Collider;
import Engine.Maths.Direction;
import Engine.Maths.Vector2D;
import Engine.Physics;
import Game.Model.Column;
import Game.Model.Rope;
import Game.Model.Wall;

import java.awt.*;

/**
 * @author Yamuu
 * @project Gyromite
 */
public class MovingObject extends GameObject {

    protected Physics physics;
    protected Direction direction;
    protected Vector2D speed;

    protected boolean jumping;
    protected boolean falling;
    protected boolean climbing;

    protected MovingObject()
    {
        super();
        this.mobile = true;
//        this.position = new Vector2D(100, 100);
//        this.speed = new Vector2D(2, 60);
        this.physics = new Physics();
        this.direction = new Direction(0, 0);
        this.jumping = false;
        this.falling = true;
        this.climbing = false;
    }

    protected MovingObject(Vector2D position)
    {
        this();
        this.position = position;
        this.hitBox = new Collider(this.position, this.size, this);
    }

    public void move()
    {
        if(!this.hitBox.isColliding())
        {
            this.falling = true;
            this.climbing = false;
        }

        if(!this.hitBox.isColliding() || this.climbing)
        {
            if(Math.floor(Math.abs(this.direction.y)) == 0)
            {
                this.direction.y = 0;
            }
            else
            {
                this.direction.y -= 0.01f * (this.direction.y/Math.abs(this.direction.y));
            }
        }

        this.position.x += this.direction.x * this.speed.x;
//        this.position.y += this.direction.y * this.speed.y;

        if(this.direction.x < 0 && sprite.getDirection().x > 0 ||
                this.direction.x > 0 && sprite.getDirection().x < 0)
        {
            sprite.flipY();
        }

        this.physics.move(this);

        if(this.climbing)
        {
            this.position.y += this.direction.y * this.speed.x;
        }
        else if(this.jumping && !this.falling)
        {
            if(!this.hitBox.getCollidingSides().contains(Direction.direction.Top))
            {
                this.physics.impulse(this, new Direction(0, this.direction.y), this.speed.y);
                this.falling = true;

            }
//            System.out.println("JUMP");
        }
//        System.out.println(this.hitBox.getCollidingSides().contains(Direction.direction.Bottom));
        if(this.hitBox.isColliding() && this.hitBox.getCollidingSides().contains(Direction.direction.Bottom))
        {
            this.jumping = false;
            this.falling = false;
//            System.out.println(this.hitBox.getCollidingSides().contains(Direction.direction.Bottom));
        }

        this.sprite.setPosition(this.position);
        this.hitBox.setPosition(this.position);
    }

    public void draw(Graphics g)
    {
        this.move();
        super.draw(g);
    }

    @Override
    public void OnCollisionEnter(GameObject col) {
        Direction dir = this.position.getDirection(col.getPosition());

        if(col instanceof Rope)
        {
            this.climbing = true;
            this.falling = false;
//            System.out.println("Rope");
        }
        else
        {
            if(dir.y > 0 && this.hitBox.getCollidingSides().contains(Direction.direction.Top) && !this.hitBox.getCollidingSides().contains(Direction.direction.Bottom))
            {
//                System.out.println("test");
                this.falling = true;
            }
            if(this.climbing)
            {
                if((dir.y < 0 && this.direction.y > 0) || (dir.y > 0 && this.direction.y < 0))
                {
                    this.direction.y = 0;
                }
//                this.climbing = false;
            }

            if(this.falling && this.hitBox.getCollidingSides().contains(Direction.direction.Bottom))
            {
                this.jumping = false;
                this.falling = false;
//                System.out.println("bite");
            }
        }

//        if(col.getTag() == "Column")
//        {
//            for (Direction.direction d:
//                    this.hitBox.getCollidingSides()) {
//                System.out.println(d);
//            }
//        }

        if(col instanceof Wall || col instanceof Column)
        {
            /*if(this.direction.x != 0 && dir.y > 0 )
            {
                if(this.direction.x > 0 && this.hitBox.getCollidingSides().contains(Direction.direction.Right) )
                {
                    this.direction.x = 0;
                }
                if(this.direction.x < 0 && this.hitBox.getCollidingSides().contains(Direction.direction.Left) )
                {
                    this.direction.x = 0;
                }
            }*/

            if( (col instanceof Column && this.hitBox.getCollidingSides().contains(Direction.direction.Top) && this.hitBox.getCollidingSides().contains(Direction.direction.Bottom)
                    && !(this.jumping || this.falling) && ((Column) col).isMoving() /*&& ( (dir.y > 0 && !((Column) col).wasMoved()) || (dir.y < 0 && ((Column) col).wasMoved()) )*/ )  )
            {
                System.out.println("Dead");
//                this.destroy();
            }
        }

        if(!col.getHitBox().isTrigger())
        {
            // Prevents glitches in the floor
            if(this.hitBox.getCollidingSides().contains(Direction.direction.Bottom) && dir.y < 0 && !this.hitBox.getCollidingSides().contains(Direction.direction.Top))
            {
                this.position.y = col.getPosition().y - this.size.height;
            }
//            if(this.hitBox.getCollidingSides().contains(Direction.direction.Top) && dir.y > col.getSize().height/2)
//            {
//                this.position.y = (col.getPosition().y + col.getSize().height) + this.size.height;
//            }
        }

    }

    public void setDirection(Direction _direction) {
        this.direction = _direction;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }
}
