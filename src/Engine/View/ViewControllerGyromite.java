package Engine.View;

import Game.Game;
import _Example.VueControleur.VueControleurGyromite;

import javax.imageio.ImageIO;
import javax.swing.*;
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
    Game game;

    public ViewControllerGyromite(Game _game)
    {

    }

    private ImageIcon loadImage(String urlIcone) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(urlIcone));
        } catch (IOException ex) {
            Logger.getLogger(VueControleurGyromite.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return new ImageIcon(image);
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
