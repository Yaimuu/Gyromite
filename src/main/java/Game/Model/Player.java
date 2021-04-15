package Game.Model;

import Engine.Controller.ViewControllerGyromite;
import Engine.GyromiteEngine;
import Engine.Maths.Direction;
import Engine.Maths.Vector2D;
import Engine.Model.GameObject;
import Engine.Model.MovingObject;
import Engine.View.Sprite;
import Game.View.ViewGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Yamuu
 * @project Gyromite
 */
public class Player extends MovingObject {

    public Player(Vector2D position)
    {
        super(position);

        this.name = "Player";
        this.tag = "Player";

        this.speed = new Vector2D(5, 60);

        this.sprite = new Sprite("Images/", "josuke.png", this.size, this.position);

        this.setSquareSize(new Dimension(1, 1));

        this.addPlayerListeners();
    }

    public void draw(Graphics g)
    {
        this.move();
        super.draw(g);
    }

    public void move()
    {
        if(Math.abs(this.direction.x) <= 0.1f)
            this.direction.x = 0;
        else
            this.direction.x -= 0.1f * (this.direction.x/Math.abs(this.direction.x));

        super.move();

    }

    @Override
    public void OnCollisionEnter(GameObject col) {
//        System.out.println(col.getTag());
        Direction dir = this.position.getDirection(col.getPosition());

        if( col instanceof Ennemy )
        {
            System.out.println("Dead");
//            this.destroy();
        }

        if(col instanceof Wall || col instanceof Column) {
            if (this.direction.x != 0 && dir.y > 0) {
                if (this.direction.x > 0 && this.hitBox.getCollidingSides().contains(Direction.direction.Right) /*&& dir.x < 0*/) {
                    this.direction.x = 0;
                }
                if (this.direction.x < 0 && this.hitBox.getCollidingSides().contains(Direction.direction.Left) /*&& dir.x > 0*/) {
                    this.direction.x = 0;
                }
            }
        }
        super.OnCollisionEnter(col);
    }

    private void addPlayerListeners()
    {
        this.game.getInputMap().put(KeyStroke.getKeyStroke("UP"), "MOVE_UP");
        this.game.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "MOVE_DOWN");
        this.game.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "MOVE_LEFT");
        this.game.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "MOVE_RIGHT");
        this.game.getInputMap().put(KeyStroke.getKeyStroke("T"), "TEST");

        this.game.getActionMap().put("MOVE_UP", new MoveAction(KeyEvent.VK_UP) );
        this.game.getActionMap().put("MOVE_DOWN", new MoveAction(KeyEvent.VK_DOWN) );
        this.game.getActionMap().put("MOVE_LEFT", new MoveAction(KeyEvent.VK_LEFT) );
        this.game.getActionMap().put("MOVE_RIGHT", new MoveAction(KeyEvent.VK_RIGHT) );
        this.game.getActionMap().put("TEST", new MoveAction(KeyEvent.VK_T) );
    }

    private class MoveAction extends AbstractAction implements ActionListener {

        private int keyPressed;

        MoveAction(int _keyPressed)
        {
            this.keyPressed = _keyPressed;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if(this.keyPressed == KeyEvent.VK_UP)
            {
                if(climbing)
                {
                    setDirection(new Direction(direction.x, -1));
//                    System.out.println("Climb");
                }
                else if(!jumping)
                {
                    setDirection(new Direction(direction.x, -1));
                    jumping = true;

                }
            }
            if(this.keyPressed == KeyEvent.VK_DOWN)
            {
                if(climbing)
                {
                    setDirection(new Direction(direction.x, 1));
                }
            }
            if(this.keyPressed == KeyEvent.VK_LEFT)
            {
                setDirection(new Direction(-1, direction.y));
            }
            if(this.keyPressed == KeyEvent.VK_RIGHT)
            {
                setDirection(new Direction(1, direction.y));
            }
            if(this.keyPressed == KeyEvent.VK_T)
            {
                if(GyromiteEngine.DEBUG)
                {
                    System.out.println("----- DEBUG -----");
                    System.out.println("Directions { ");
                    for (Direction.direction d:
                            hitBox.getCollidingSides())
                    {
                        System.out.println(d);
                    }
                    System.out.println("}");
                    System.out.println("Falling : " + falling);
                    System.out.println("Jumping : " + jumping);
                }
            }
        }
    }

    @Override
    protected void destroy()
    {
        this.game.getScene().getGameObjects().remove(this);
    }

}
