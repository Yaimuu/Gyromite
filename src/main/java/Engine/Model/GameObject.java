/**
 * @project Gyromite
 * @author Yamuu
 */

package Engine.Model;

import Engine.GyromiteEngine;
import Engine.Maths.Collider;
import Engine.Maths.Direction;
import Engine.Maths.Vector2D;
import Engine.View.Sprite;
import Engine.View.ViewEngine;
import Game.View.ViewGame;

import java.awt.*;

public class GameObject {

    protected String name;
    protected String tag;

    protected ViewGame game = ViewGame.getInstance();
    protected ViewEngine engine = ViewEngine.getInstance();
    protected Sprite sprite;
    protected Vector2D position;
    protected Dimension size;
    protected Collider hitBox;

    protected boolean mobile;
    protected boolean consumable;

    protected GameObject()
    {
        this.mobile = false;
        this.consumable = false;
        this.size = new Dimension(GyromiteEngine.GYROMITE_CELL_SIZE, GyromiteEngine.GYROMITE_CELL_SIZE);
    }

    public GameObject(Vector2D position)
    {
        this();
        this.position = position;
        this.hitBox = new Collider(this.position, this.size);
    }

    public void animate(Graphics g, Direction dir)
    {
        //TODO : Sprite animation
    }

    public void draw(Graphics g)
    {
        this.renderSprite(g);
        this.hitBox.setColliding(false);

        if(GyromiteEngine.DEBUG)
        {
            this.hitBox.paintComponent(g);
        }
    }

    protected void destroy()
    {
        this.game.getScene().getGameObjects().remove(this);
    }

    protected void renderSprite(Graphics g)
    {
        this.sprite.paintComponent(g);
    }

    public void OnCollisionEnter(GameObject col) {}

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
        this.sprite.setPosition(this.position);
        this.hitBox.setPosition(this.position);
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
        this.sprite.setSpriteSize(this.size);
        this.hitBox.setSize(this.size);
    }

    public void setSquareSize(Dimension size) {
        this.size.width = size.width * GyromiteEngine.GYROMITE_CELL_SIZE;
        this.size.height = size.height * GyromiteEngine.GYROMITE_CELL_SIZE;
        this.sprite.setSpriteSize(this.size);
        this.hitBox.setSize(this.size);
    }

    public Collider getHitBox() {
        return hitBox;
    }

    public void setHitBox(Collider hitBox) {
        this.hitBox = hitBox;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GameObject{" +
                "name='" + name + '\'' +
                ", position=[" + position.x + ", " + position.y + "]" +
                ", size=[" + size.width + ", " + size.height + "]" +
                ", movable=" + mobile +
                ", stockable=" + consumable +
                '}';
    }
}
