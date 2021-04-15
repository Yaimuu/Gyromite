package Engine.View;

import Game.View.ViewGame;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yamuu
 * @project Gyromite
 */
public class ViewEngine extends View {

    public static ViewEngine engineView;
    private JPanel imagesPanel;

    public static ViewEngine getInstance()
    {
        if(engineView == null)
        {
            engineView = new ViewEngine();
            engineView.initialize();
        }

        return engineView;
    }

    public ViewEngine()
    {
        this.imagesPanel = new JPanel();

        add(imagesPanel);
//        add(engineCanvas);
    }

    private void initialize()
    {

    }

    @Override
    public void draw(Graphics g) {

    }

    public Canvas getCanvas()
    {
        return this.viewCanvas;
    }
}
