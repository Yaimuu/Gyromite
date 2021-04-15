package Engine.View;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yamuu
 * @project Gyromite
 */
public abstract class View extends JPanel {

    protected Canvas viewCanvas = new Canvas();

    public void draw(Graphics g) throws Exception {
//        repaint();
//        revalidate();
    }

    public Canvas getCanvas()
    {
        return this.viewCanvas;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }
}
