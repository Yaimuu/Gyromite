package Engine.View;

import Engine.Controller.ViewControllerGyromite;
import Game.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Yamuu
 * @project Gyromite
 */
public class Menu extends JPanel {

    ViewControllerGyromite viewController;

    JPanel buttonPanel;

    private JButton playButton;
    private JButton engineButton;
    private JButton controlsButton;
    private JButton quitButton;

    Game game;

    private BufferedImage background;

    public Menu(Game _game, ViewControllerGyromite viewController)
    {
        this.game = _game;

        this.viewController = viewController;

        this.setBackground(new Color(50,55,60));
        loadBackground();

        setBorder(new EmptyBorder(20, 20, 20, 20));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        this.add(new JLabel("<html><h1 style='color:white;'><strong><i>Gyromite</i></strong></h1><hr></html>"), gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        this.buttonPanel = new JPanel(new GridBagLayout());

        this.playButton = new JButton("Play");
        this.playButton.setPreferredSize(new Dimension(200, 40));

        this.engineButton = new JButton("Map Engine");
        this.engineButton.setPreferredSize(new Dimension(200, 40));

        this.controlsButton = new JButton("Controls");
        this.controlsButton.setPreferredSize(new Dimension(200, 40));

        this.quitButton = new JButton("Quit");
        this.quitButton.setPreferredSize(new Dimension(200, 40));

        this.buttonPanel.add(playButton, gbc);
        this.buttonPanel.add(engineButton, gbc);
        this.buttonPanel.add(controlsButton, gbc);
        this.buttonPanel.add(quitButton, gbc);

        gbc.weighty = 1;
        this.add(this.buttonPanel, gbc);

        this.instantiateListeners();

    }


    public void instantiateListeners()
    {
        this.playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewController.renderGame();
            }
        } );

        this.engineButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewController.renderEngine();
            }
        } );

        this.controlsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewController.renderControls();
            }
        } );

        this.quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        } );
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

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);

        g.drawImage(this.background, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
