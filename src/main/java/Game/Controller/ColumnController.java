package Game.Controller;

import Engine.View.ViewEngine;
import Game.Model.Column;
import Game.View.ViewGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Yamuu
 * @project Gyromite
 */
public class ColumnController {

    private ViewGame game = ViewGame.getInstance();
    private ViewEngine engine = ViewEngine.getInstance();
    private List<Column> columns;

    public ColumnController()
    {
        this.columns = new LinkedList<>();

        addColumnListeners();
    }

    public ColumnController(List<Column> columns)
    {
        this();

        this.columns = columns;
    }

    private void addColumnListeners()
    {
        this.game.getInputMap().put(KeyStroke.getKeyStroke("F"), "MOVE_BLUE_COLUMN");
        this.game.getInputMap().put(KeyStroke.getKeyStroke("G"), "MOVE_RED_COLUMN");
        this.game.getActionMap().put("MOVE_BLUE_COLUMN", new MoveAction(KeyEvent.VK_F) );
        this.game.getActionMap().put("MOVE_RED_COLUMN", new MoveAction(KeyEvent.VK_G) );
    }

    private class MoveAction extends AbstractAction implements ActionListener {

        private int keyPressed;

        MoveAction(int _keyPressed)
        {
            this.keyPressed = _keyPressed;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(this.keyPressed == KeyEvent.VK_F)
            {
                for (Column column:
                        columns) {
                    if(!column.isMoving() && column.isBlueRedColumn())
                        column.setMoving(true);
                }
            }
            if(this.keyPressed == KeyEvent.VK_G)
            {
                for (Column column:
                        columns) {
                    if(!column.isMoving() && !column.isBlueRedColumn())
                        column.setMoving(true);
                }
            }
        }
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
