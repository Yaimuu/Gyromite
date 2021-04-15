/**
 * @project Gyromite
 * @author Yamuu
 */

package Engine;

import Engine.View.ViewEngine;
import Game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;


import static java.lang.Thread.sleep;

public class GyromiteEngine extends Observable implements Runnable {

    public static int GYROMITE_WIDTH = 800;
    public static int GYROMITE_HEIGTH = 600;
    // Size of a square in the game
    public static int GYROMITE_CELL_SIZE = 30;
    public static int GYROMITE_WIDTH_CELL = (GYROMITE_WIDTH - GYROMITE_CELL_SIZE) / GYROMITE_CELL_SIZE;
    public static int GYROMITE_HEIGTH_CELL = (GYROMITE_HEIGTH - GYROMITE_CELL_SIZE) / GYROMITE_CELL_SIZE;

    public static boolean DEBUG = true;

    private Game game;
    private long pause;
    private boolean update;
    private boolean active;

    private ViewEngine view;
    private Timer timer;

    public GyromiteEngine(Game game, int pause) {
        this.game = game;
        this.active = false;
        this.view = new ViewEngine();
        this.update = false;
        this.timer = new Timer((int)pause, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                run();
            }
        });
    }

    public void start(long _pause) {
        this.pause = _pause;
//        new Thread(this).start();
        this.timer.start();
    }

    public void start() {
        this.timer.start();
    }

    public void stop()
    {
        this.timer.stop();
    }

    public void run()
    {
//        update = false;

//        while(true) {
//            update = true;
//            game.resetCmptDepl();
//            for (RealisateurDeDeplacement d : lstDeplacements) {
//                if (d.realiserDeplacement())
//                    update = true;
//            }
//
//            Controle4Directions.getInstance().resetDirection();

            if (this.update) {
                setChanged();
                notifyObservers();
//                update = false;
            }

//            try {
//                sleep(pause);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public ViewEngine getView() {
        return view;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Canvas getCanvas()
    {
        return this.view.getCanvas();
    }
}
