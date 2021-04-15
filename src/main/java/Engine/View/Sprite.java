package Engine.View;

import Engine.Controller.ViewControllerGyromite;
import Engine.GyromiteEngine;
import Engine.Maths.Direction;
import Engine.Maths.Vector2D;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Yamuu
 * @project Gyromite
 */
public class Sprite extends JPanel {

    private String path;
    private String filename;
    private Dimension size;
    private Vector2D position;
    private BufferedImage img;
//    private BufferStrategy bs;
    private Direction direction;
    private SpriteMode mode = SpriteMode.Cover;

    public enum SpriteMode
    {
        Cover,
        Repeat
    }

    private Sprite()
    {
        this.img = new BufferedImage(ViewControllerGyromite.currentView.getWidth(), ViewControllerGyromite.currentView.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.setVisible(true);
        this.setPreferredSize(this.size);
        this.direction = new Direction(1, 0);
    }

    public Sprite(String path, String filename)
    {
        this();

        this.path = path;
        this.filename = filename;
        this.size = new Dimension(GyromiteEngine.GYROMITE_CELL_SIZE, GyromiteEngine.GYROMITE_CELL_SIZE);
        this.position = new Vector2D();
        this.loadImage();
    }

    public Sprite(String path, String filename, Dimension size, Vector2D pos)
    {
        this(path, filename);

        this.size = size;
        this.position = pos;

//        this.flipY(1);
    }

    private void loadImage()
    {
        try
        {
            this.img = ImageIO.read(new File(this.path + this.filename));
        }
        catch (IOException ex)
        {
            Logger.getLogger(ViewControllerGyromite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void paintComponent(Graphics g)
    {
        /*if(this.bs == null && ViewControllerGyromite.getViewControllerGyromite().getBufferStrategy() == null)
        {
            ViewControllerGyromite.getViewControllerGyromite().createBufferStrategy(1);
        }
        this.bs = ViewControllerGyromite.getViewControllerGyromite().getBufferStrategy();

        Graphics g2 = this.bs.getDrawGraphics();*/
        super.paintComponent(g);

        try {
            switch (this.mode)
            {
                case Cover:
                    g.drawImage(this.img, (int)this.position.getX(), (int)this.position.getY(), (int)this.size.getWidth(), (int)this.size.getHeight(), null);
                    break;

                case Repeat:
                    for(int i = 0; i < this.size.getWidth(); i+=GyromiteEngine.GYROMITE_CELL_SIZE)
                    {
                        for(int j = 0; j < this.size.getHeight(); j+=GyromiteEngine.GYROMITE_CELL_SIZE)
                        {
                            g.drawImage(this.img, (int)this.position.getX() + i, (int)this.position.getY() + j, GyromiteEngine.GYROMITE_CELL_SIZE, GyromiteEngine.GYROMITE_CELL_SIZE, null);
                        }
                    }
                    break;
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

//        g.dispose();
    }

    public void flipY()
    {
        int w = this.img.getWidth(), h = this.img.getHeight();
        GraphicsConfiguration gc = getDefaultConfiguration();

        BufferedImage result = new BufferedImage(w, h,
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = result.createGraphics();
        g.setBackground(new Color(0,true));
        g.clearRect(0, 0, w, h);

        g.drawImage(this.img, this.img.getHeight(), 0, -this.img.getWidth(), this.img.getHeight(), null);

        g.dispose();

        this.img = result;
        this.direction.x *= -1;
    }

    public void rotate(double angle)
    {
        int w = this.img.getWidth(), h = this.img.getHeight();
        GraphicsConfiguration gc = getDefaultConfiguration();

        BufferedImage result = gc.createCompatibleImage(w, h);
        Graphics2D g = result.createGraphics();
        g.rotate(Math.toRadians(angle), w / 2, h / 2);
        g.drawRenderedImage(this.img, null);
        g.dispose();
        this.img = result;
    }

    public static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSpriteSize(Dimension size) {
        this.size = size;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public SpriteMode getMode() {
        return mode;
    }

    public void setMode(SpriteMode mode) {
        this.mode = mode;
    }
}
