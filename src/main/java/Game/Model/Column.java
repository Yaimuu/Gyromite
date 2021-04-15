package Game.Model;

import Engine.GyromiteEngine;
import Engine.Maths.Vector2D;
import Engine.Model.GameObject;
import Engine.Model.MovingObject;
import Engine.View.Sprite;

import java.awt.*;

/**
 * @author Yamuu
 * @project Gyromite
 */
public class Column extends MovingObject {

    private Vector2D initialPosition;
    /**
     * Blue : true
     * Red : false
     */
    private boolean blueRedColumn;
    private boolean moving;
    private boolean moved;

    private Column()
    {

    }

    public Column(Vector2D position, boolean blueColumn)
    {
        super(position);

        this.name = "Column";
        this.tag = "Column";

        this.blueRedColumn = blueColumn;
        this.initialPosition = this.position;

        this.moving = false;
        this.moved = false;

        this.size = new Dimension(GyromiteEngine.GYROMITE_CELL_SIZE, GyromiteEngine.GYROMITE_CELL_SIZE * 3);

        if(this.blueRedColumn)
            this.sprite = new Sprite("Images/", "Colonne_bleue.png", this.size, this.position);
        else
        {
            this.position.y += GyromiteEngine.GYROMITE_CELL_SIZE * 3;
            this.sprite = new Sprite("Images/", "Colonne_rouge.png", this.size, this.position);
            this.moved = true;
        }

        this.hitBox.setSize(this.size);



//        addColumnListeners();
    }

    @Override
    public void draw(Graphics g)
    {
        this.move();
        super.draw(g);
    }

    @Override
    public void move()
    {
        if(!this.moved)
        {
            if(this.moving)
            {
                this.setPosition(new Vector2D(this.position.x, this.position.y + 1));
            }
            if(this.position.y - GyromiteEngine.GYROMITE_CELL_SIZE * (this.size.height/GyromiteEngine.GYROMITE_CELL_SIZE) == this.initialPosition.y)
            {
                this.moving = false;
                this.moved = true;
            }
        }
        else
        {
            if(this.moving)
            {
                this.setPosition(new Vector2D(this.position.x, this.position.y - 1));
            }
            if(this.position.y == this.initialPosition.y)
            {
                this.moving = false;
                this.moved = false;
            }
        }
    }

    @Override
    public void OnCollisionEnter(GameObject col)
    {

    }

//    private void addColumnListeners()
//    {
//        this.game.getInputMap().put(KeyStroke.getKeyStroke("F"), "MOVE_COLUMN");
//
//        this.game.getActionMap().put("MOVE_COLUMN", new MoveAction(KeyEvent.VK_F) );
//    }
//
//    private class MoveAction extends AbstractAction implements ActionListener {
//
//        private int keyPressed;
//
//        MoveAction(int _keyPressed)
//        {
//            this.keyPressed = _keyPressed;
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if(this.keyPressed == KeyEvent.VK_F)
//            {
//                if(!moving)
//                    moving = true;
//            }
//        }
//    }

    public Vector2D getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(Vector2D initialPosition) {
        this.initialPosition = initialPosition;
    }

    public boolean isBlueRedColumn() {
        return blueRedColumn;
    }

    public void setBlueRedColumn(boolean blueRedColumn) {
        this.blueRedColumn = blueRedColumn;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean wasMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }
}
