package Engine.Maths;

import Engine.Model.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Yamuu
 * @project Gyromite
 */
public class Collider extends JPanel {

    private GameObject associatedObject;
    private Vector2D position;
    private Dimension size;
    private boolean colliding;
    private List<Direction.direction> collidingSides;
    private List<GameObject> ignoreCollisions;
    private boolean trigger;
    private float threshold;

    public Collider(Vector2D pos, Dimension size)
    {
        this.position = pos;
        this.size = size;
        this.setColliding(false);
        this.collidingSides = new LinkedList<>();
        this.ignoreCollisions = new LinkedList<>();
        this.trigger = false;
        this.threshold = 0.15f;

        this.setVisible(true);
        this.setBackground(new Color(0,0,0,0));
    }

    public Collider(Vector2D pos, Dimension size, boolean isTrigger)
    {
        this(pos, size);
        this.trigger = isTrigger;
    }

    public Collider(Vector2D pos, Dimension size, GameObject entity)
    {
        this(pos, size);
        this.associatedObject = entity;
    }

    public Collider(Vector2D pos, Dimension size, boolean isTrigger, GameObject entity)
    {
        this(pos, size, isTrigger);
        this.associatedObject = entity;
    }

    public boolean areColliding(Collider col)
    {
//        this.collidingSides.clear();

        boolean bottom = this.position.y + this.size.height >= col.getPosition().y &&
                this.position.y + this.size.height <= col.getPosition().y + col.getSize().height;
        boolean top = this.position.y >= col.getPosition().y &&
                this.position.y <= col.getPosition().y + col.getSize().height;

        boolean right = this.position.x + this.size.width >= col.getPosition().x &&
                this.position.x + this.size.width <= col.getPosition().x + col.getSize().width;
        boolean left = this.position.x >= col.getPosition().x &&
                this.position.x <= col.getPosition().x + col.getSize().width;

        boolean inIntervalX = right || left;

        boolean inIntervalY = bottom || top;

        if( inIntervalX && inIntervalY )
        {
            this.setColliding(true);

            Direction dir = this.position.getDirection(col.getPosition());
            if(dir.y > 0 /*&& top*/ && this.position.y > col.getPosition().y + col.getSize().height - this.size.height * (1-this.threshold)  )
            {
                this.collidingSides.add(Direction.direction.Top);
            }
            else if(dir.y < 0 /*&& bottom*/&& this.position.y + this.size.height > col.getPosition().y)
            {
                this.collidingSides.add(Direction.direction.Bottom);
            }
            if(dir.x > 0 && /*left&&*/ this.position.x > col.getPosition().x + col.getSize().width - this.size.width * (1-this.threshold))
            {
                this.collidingSides.add(Direction.direction.Left);
            }
            else if(dir.x < 0 /*&& right*/&& this.position.x + this.size.width > col.getPosition().x)
            {
                this.collidingSides.add(Direction.direction.Right);
            }
        }

        return inIntervalX && inIntervalY;
    }

    public boolean areColliding(GameObject object)
    {
        boolean colliding = this.areColliding(object.getHitBox());

        if(colliding && this.associatedObject != null)
        {
            this.associatedObject.OnCollisionEnter(object);
        }

        return colliding;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

//        this.setBackground(new Color(0,0,0,0));

        g.setColor(Color.green);
        g.drawRect((int)this.position.x, (int)this.position.y, this.size.width, this.size.height);
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public void setColliding(boolean colliding) {
        this.colliding = colliding;
    }

    public boolean isColliding()
    {
        return this.colliding;
    }

    public List<GameObject> getIgnoreCollisions() {
        return ignoreCollisions;
    }

    public void setIgnoreCollisions(List<GameObject> ignoreCollisions) {
        this.ignoreCollisions = ignoreCollisions;
    }

    public List<Direction.direction> getCollidingSides() {
        return collidingSides;
    }

    public void clearCollidingSides() {
        this.collidingSides.clear();
    }

    public boolean isTrigger() {
        return trigger;
    }

    public void setTrigger(boolean trigger) {
        this.trigger = trigger;
    }

    public GameObject getAssociatedObject() {
        return associatedObject;
    }

    public void setAssociatedObject(GameObject associatedObject) {
        this.associatedObject = associatedObject;
    }
}
