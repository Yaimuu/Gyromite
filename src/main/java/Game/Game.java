/**
 * @project Gyromite
 * @author Yamuu
 */

package Game;

import Engine.Model.GameObject;
import Engine.GyromiteEngine;
import Engine.Scene;
import Game.View.ViewGame;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Game {

    private GyromiteEngine engine = new GyromiteEngine(this, 25);
    private ViewGame view;
    private boolean active;

    List<Scene> scenes = new LinkedList<>();

    public Game()
    {
        this.view = ViewGame.getInstance();
        this.view.setGame(this);
        this.active = false;
        this.initialization();
    }

    public void start()
    {
        this.engine.start();
    }

    public void start(long _pause)
    {
        this.engine.start(_pause);
    }

    public void initialization()
    {
        // TODO : Lire fichier scene ou en créer un vide
        /*for (Scene scene:
                scenes) {
            scene.loadScene();
        }*/

    }

    private void addEntity(GameObject e, Point pos)
    {

    }

    public GyromiteEngine getEngine() {
        return engine;
    }

    public ViewGame getView() {
        return view;
    }

    public Canvas getGameCanvas() {
        return this.view.getCanvas();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
