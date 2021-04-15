package Engine;

import Engine.Maths.Direction;
import Engine.Maths.Vector2D;
import Engine.Model.GameObject;
import Engine.Model.MovingObject;

/**
 * @author Yamuu
 * @project Gyromite
 */
public class Physics {

    final float g = 5f;

    public boolean move(MovingObject entity) {
//        System.out.println(entity.getClass().getName());
//        if(entity.getClass().getName() == "Game.Model.Player")
//        {
//            System.out.println(entity.getHitBox().isColliding());
//        }
        if(entity.isFalling())
//        if(!entity.getHitBox().isColliding() && !entity.getHitBox().getCollidingSides().contains(Direction.direction.Bottom))
        {
            entity.setPosition(new Vector2D(entity.getPosition().x, entity.getPosition().y + this.g));
        }

        return true;
    }

    public void impulse(MovingObject entity, Direction dir, float force)
    {
        entity.setPosition(new Vector2D(entity.getPosition().x + dir.x * force, entity.getPosition().y + dir.y * force));
    }
}
