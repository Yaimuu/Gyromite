package Engine.Controller;

import Engine.GyromiteEngine;
import Engine.View.View;
import Engine.View.ViewEngine;
import Game.Game;
import Engine.View.Menu;
import Game.View.ViewGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Yamuu
 * @project Gyromite
 */
public class ViewControllerGyromite extends JFrame implements Observer {

    public static Canvas currentCanvas;
    public static View currentView;
    public static ViewControllerGyromite viewControllerGyromite;

    private Game game;
    private Menu menu;
    private GyromiteEngine engine;


    public static Canvas getCurrentCanvas()
    {
        if(currentCanvas == null)
        {
            currentCanvas = new Canvas();
        }

        return currentCanvas;
    }

    public static void setCurrentCanvas(Canvas newCanvas)
    {
        currentCanvas = newCanvas;
    }

    public static ViewControllerGyromite getViewControllerGyromite()
    {
        if(viewControllerGyromite == null)
        {
            viewControllerGyromite = new ViewControllerGyromite(new Game());
        }

        return viewControllerGyromite;
    }

    public static void setViewControllerGyromite(ViewControllerGyromite vc)
    {
        viewControllerGyromite = vc;
    }

    public ViewControllerGyromite(Game _game)
    {
        this.game = _game;
        this.engine = this.game.getEngine();

        this.menu = new Menu(this.game, this);

        this.drawMenu();

        this.setResizable(GyromiteEngine.DEBUG);
        this.setTitle("Gyromite");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(GyromiteEngine.GYROMITE_WIDTH, GyromiteEngine.GYROMITE_HEIGTH);
        this.setIgnoreRepaint(true);
        this.setFocusable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.createBufferStrategy(1);

        viewControllerGyromite = this;
    }

    private ImageIcon loadImage(String urlIcone) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(urlIcone));
        } catch (IOException ex) {
            Logger.getLogger(ViewControllerGyromite.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return new ImageIcon(image);
    }

    public void renderEngine()
    {
        remove(menu);
        remove(game.getView());
        remove(this.engine.getView());

        this.engine.setActive(true);
        this.game.setActive(false);

        add(engine.getView());
        ViewControllerGyromite.currentCanvas = this.engine.getCanvas();
        ViewControllerGyromite.currentView = this.engine.getView();

        this.engine.getView().draw(currentCanvas.getGraphics());
        repaint();
    }

    public void renderGame()
    {
        remove(this.menu);
        remove(this.engine.getView());
//        remove(this.game.getView());

//        this.game.getView().setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
//        this.game.getView().setSize(this.getWidth(), this.getHeight());
//        this.game.getView().setBackground(Color.BLACK);

        this.game.setActive(true);
        this.engine.setActive(false);
        this.engine.setUpdate(false);

        add(this.game.getView());

        ViewControllerGyromite.setCurrentCanvas(this.game.getGameCanvas());
        ViewControllerGyromite.currentView = this.game.getView();

        this.game.getView().repaint();

//        this.engine.setUpdate(true);
//        game.start();
        this.getContentPane().repaint();
    }

    public void renderControls() {


    }

    public void drawMenu()
    {
        remove(game.getView());
        remove(engine.getView());

        this.engine.setUpdate(false);

        add(menu);
    }

    public void draw()
    {
        if(this.game.isActive())
        {
            this.game.getView().repaint();
            this.game.getView().requestFocusInWindow();
        }
        else if(this.engine.isActive())
        {
//            this.engine.getView().draw(this.engine.getView().getGraphics());
            this.engine.getView().requestFocusInWindow();
        }
    }

    @Override
    public void update(Observable observable, Object o)
    {
        this.draw();
    }


}
