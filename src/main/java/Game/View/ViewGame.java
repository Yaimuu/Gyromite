package Game.View;

import Engine.Controller.ViewControllerGyromite;
import Engine.GyromiteEngine;
import Engine.Maths.Vector2D;
import Engine.Model.GameObject;
import Engine.Scene;
import Engine.SceneManager;
import Engine.View.LevelMenu;
import Engine.View.View;
import Game.Controller.ColumnController;
import Game.Game;
import Game.Model.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Yamuu
 * @project Gyromite
 */
public class ViewGame extends View {

    public static ViewGame gameView;
    public static int score = 0;

    private Game game;
    private Scene scene;

    private ViewGameOver gameOver;
    private LevelMenu levelMenu;
    private BufferedImage background;

    private boolean debugGrid = false;

    public static ViewGame getInstance()
    {
        if(gameView == null)
        {
            gameView = new ViewGame();
            gameView.initialize();
        }

        return gameView;
    }

    public ViewGame()
    {
//        this.scene = new Scene();

        this.setBackground(new Color(0,true));
        this.setPreferredSize(new Dimension(GyromiteEngine.GYROMITE_WIDTH, GyromiteEngine.GYROMITE_HEIGTH));
        this.setSize(GyromiteEngine.GYROMITE_WIDTH, GyromiteEngine.GYROMITE_HEIGTH);
        this.setVisible(true);
        this.setFocusable(true);

//        this.setIgnoreRepaint(true);

        this.loadBackground();
    }

    private void initialize()
    {
//        System.out.println("Init");
        this.setLayout(new GridLayout(1,1));
        this.levelMenu = new LevelMenu(this);
        this.add(this.levelMenu);
//        ViewControllerGyromite.getViewControllerGyromite().add(this.levelMenu);
//        this.remove(this.gameOver);
        /*this.viewCanvas.setSize(1, 1);
        this.viewCanvas.setBackground(new Color(0,0,0,0));

        this.viewCanvas.setVisible(false);

        this.viewCanvas.createBufferStrategy(1);*/

//        this.viewCanvas.setIgnoreRepaint(true);
//        this.viewCanvas.setFocusable(true);
//        this.viewCanvas.requestFocusInWindow();

//        add(this.viewCanvas);
//        SceneManager.getAllScenes();
        ViewControllerGyromite.setCurrentCanvas(this.viewCanvas);
        ViewControllerGyromite.currentView = this;

        if(this.scene != null)
            this.scene.loadScene();
        else
        {
            this.levelMenu.setVisible(true);

//            System.out.println("add level menu");
        }

        this.requestFocusInWindow();
        repaint();
        this.levelMenu.repaint();
    }

    private void drawScene(Graphics g) throws Exception
    {
        try
        {
            for (GameObject entity:
                    this.scene.getGameObjects()) {
                entity.getHitBox().clearCollidingSides();
                for (GameObject entity2:
                        this.scene.getGameObjects()) {

                    if(!entity.equals(entity2))
                    {
//                    System.out.println(entity.getTag() + " - " + entity2.getTag());
                        entity.getHitBox().areColliding(entity2);
                    }
                }

                entity.draw(g);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
//            e.printStackTrace();
        }

    }

    private void drawScore(Graphics g)
    {
        g.setColor(new Color(50,55,60));
        g.fillRect(GyromiteEngine.GYROMITE_WIDTH/2 - 50, 0, 100, 50);
        g.setColor(Color.white);
        g.drawString("Score : " + ViewGame.score, GyromiteEngine.GYROMITE_WIDTH/2 - 25, 25);
        g.dispose();
    }

    private void drawLevelMenu() {

        if(this.scene == null)
        {

        }

    }

    public void checkForWin()
    {
        boolean win = true;
        for (GameObject entity : this.scene.getGameObjects())
        {
            if(entity instanceof Bomb)
            {
                win = false;
            }
        }
//        this.gameOver.win();
        this.gameOver.draw();
        this.add(this.gameOver);
        System.out.println("Win : " + win);
    }

    @Override
    public void draw(Graphics g) throws Exception {
        super.draw(g);

        g.drawImage(this.background, 0, 0, this.getWidth(), this.getHeight(), null);

        try {
            if(this.scene != null)
            {
//                System.out.println(this.scene.getGameObjects().size());
                this.drawScene(g);
                this.drawScore(g);
            }
            else
            {
                this.levelMenu.repaint();
                this.repaint();
                revalidate();
            }

        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        try {
            this.draw(g);
            if(this.debugGrid)
            {
                this.drawDebugCells(g);
            }

        } catch (Exception e) {
//            e.printStackTrace();
        }

        g.dispose();
    }

    private void loadBackground()
    {
        try
        {
            this.background = ImageIO.read(new File("Images/" + "background.jpg"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(ViewControllerGyromite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void drawDebugCells(Graphics g)
    {
        g.setColor(Color.WHITE);
        for(int i = 0; i < GyromiteEngine.GYROMITE_WIDTH_CELL+2; i++)
        {
            g.drawLine(i * GyromiteEngine.GYROMITE_CELL_SIZE, 0, i * GyromiteEngine.GYROMITE_CELL_SIZE, GyromiteEngine.GYROMITE_HEIGTH);
        }

        for(int j = 0; j < GyromiteEngine.GYROMITE_HEIGTH_CELL+1; j++)
        {
            g.drawLine(0, j * GyromiteEngine.GYROMITE_CELL_SIZE, GyromiteEngine.GYROMITE_WIDTH, j * GyromiteEngine.GYROMITE_CELL_SIZE);
        }
//        g.dispose();
    }

    @Override
    public Canvas getCanvas() {
        return this.viewCanvas;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        this.game.getEngine().setUpdate(true);
        this.game.start();

        this.levelMenu.setVisible(false);
    }

    public static void increaseScore()
    {
        score += 100;
    }

    public static void decreaseScore()
    {
        if(score > 0)
            score -= 100;
    }

    public static void resetScore()
    {
        score = 0;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setDebugScene() {

        this.scene = new Scene();
        this.scene.loadScene();
        this.game.getEngine().setUpdate(true);
        this.game.start();

        this.levelMenu.setVisible(false);
    }
}
