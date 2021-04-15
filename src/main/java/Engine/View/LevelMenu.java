package Engine.View;

import Engine.GyromiteEngine;
import Engine.Scene;
import Engine.SceneManager;
import Game.View.ViewGame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author Yamuu
 * @project Gyromite
 */
public class LevelMenu extends JPanel {

    ViewGame vg;

    private List<Scene> scenes;
    private JPanel buttonPanel;
    private GridBagConstraints gbc;

    public LevelMenu(ViewGame vg)
    {
        this.vg = vg;

        this.scenes = SceneManager.getAllScenes();

        this.setBackground(new Color(0, true));

        setBorder(new EmptyBorder(20, 20, 20, 20));
        setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        this.add(new JLabel("<html><h1 style='color:white;'><strong><i>Choose your level !</i></strong></h1><hr></html>"), gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;


        this.buttonPanel = new JPanel(new GridBagLayout());

        drawAllScenes();

        gbc.weighty = 1;
        this.add(this.buttonPanel, gbc);

        this.setVisible(true);

        repaint();
    }

    public void drawAllScenes()
    {
        if(GyromiteEngine.DEBUG)
        {
            JButton debugSceneButton = new JButton("Debug Scene");
            debugSceneButton.setPreferredSize(new Dimension((int)(GyromiteEngine.GYROMITE_WIDTH * 0.75), 40));
            debugSceneButton.setVisible(true);

            debugSceneButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    vg.setDebugScene();
                    System.out.println("Click : Debug Scene");
                }
            } );

            this.buttonPanel.add(debugSceneButton, gbc);
        }

        for (Scene scene : this.scenes)
        {
            String name = scene.getName().replace(".scene", "");
            JButton sceneButton = new JButton(name);
            sceneButton.setPreferredSize(new Dimension((int)(GyromiteEngine.GYROMITE_WIDTH * 0.75), 40));
            sceneButton.setVisible(true);

            sceneButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    scene.loadScene();
                    vg.setScene(scene);
                    System.out.println("Click : " + name);
                }
            } );

            System.out.println(name);
            this.buttonPanel.add(sceneButton, gbc);
        }
    }

}
