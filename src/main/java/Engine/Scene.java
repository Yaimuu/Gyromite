package Engine;

import Engine.Maths.Vector2D;
import Engine.Model.GameObject;
import Game.Controller.ColumnController;
import Game.Model.*;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Yamuu
 * @project Gyromite
 */
public class Scene {

    private String name;
    private String path;

    private boolean loaded = false;

    private List<GameObject> gameObjects = new LinkedList<>();
    private HashMap<GameObject, Integer> gameObjectsDepth = new HashMap<>();
    private HashMap<GameObject, String> gameObjectsMap = new HashMap<>();

    private ColumnController columnController;

    public Scene()
    {
        this.path = "";
        this.name = "";

    }

    public Scene(String name, String path)
    {
        this();
        this.path = path;
        this.name = name;
    }

    public void saveScene()
    {

    }

    public void loadScene()
    {
        this.columnController = new ColumnController();

        if(this.path == "" || this.name == "")
        {
            this.testScene();
        }
        else
        {
            try
            {
                // Le fichier d'entrée
                File file = new File(this.path + this.name);
                // Créer l'objet File Reader
                FileReader fr = new FileReader(file);
                // Créer l'objet BufferedReader
                BufferedReader br = new BufferedReader(fr);
                StringBuffer sb = new StringBuffer();
                String line;
                while((line = br.readLine()) != null)
                {
                    // ajoute la ligne au buffer
                    sb.append(line);
                    sb.append("\n");
                }
                fr.close();
                System.out.println("Contenu du fichier: ");
                System.out.println(sb.toString());
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }

        this.loadMapLimits();
    }

    private void testScene()
    {
        GameObject rope = new Rope(new Vector2D(250, 0));
        rope.setSquareSize(new Dimension(1, 20));
        this.getGameObjects().add(rope);

        GameObject player = new Player(Vector2D.newVector2DInCell(10, 13));
        this.getGameObjects().add(player);

        GameObject floor = new Wall(new Vector2D(0, 400));
        floor.setTag("Floor");
        floor.setSize(new Dimension(GyromiteEngine.GYROMITE_WIDTH, GyromiteEngine.GYROMITE_CELL_SIZE));

        GameObject wall = new Wall(new Vector2D(200, 360));
        wall.setSize(new Dimension(GyromiteEngine.GYROMITE_CELL_SIZE, GyromiteEngine.GYROMITE_CELL_SIZE * 2));
        this.getGameObjects().add(floor);
        this.getGameObjects().add(wall);

        GameObject colonne = new Column(Vector2D.newVector2DInCell(2, 8), true);
        GameObject colonne1 = new Column(Vector2D.newVector2DInCell(5, 15), true);
        GameObject colonne2 = new Column(Vector2D.newVector2DInCell(15, 8), false);
        GameObject colonne3 = new Column(Vector2D.newVector2DInCell(20, 5), false);
        this.getGameObjects().add(colonne);
        this.getGameObjects().add(colonne1);
        this.getGameObjects().add(colonne2);
        this.getGameObjects().add(colonne3);

        this.columnController.getColumns().add((Column) colonne);
        this.columnController.getColumns().add((Column) colonne1);
        this.columnController.getColumns().add((Column) colonne2);
        this.columnController.getColumns().add((Column) colonne3);

        GameObject bomb = new Bomb(new Vector2D(250, 350));
        this.getGameObjects().add(bomb);

        GameObject ennemy = new Ennemy(Vector2D.newVector2DInCell(15, 5));
        GameObject ennemy1 = new Ennemy(Vector2D.newVector2DInCell(6, 15));
        this.getGameObjects().add(ennemy);
        this.getGameObjects().add(ennemy1);
    }

    private void loadMapLimits()
    {
        GameObject floor = new Wall(Vector2D.newVector2DInCell(0, GyromiteEngine.GYROMITE_HEIGTH_CELL - 1));
        floor.setSquareSize(new Dimension(GyromiteEngine.GYROMITE_WIDTH_CELL, 1));

        GameObject roof = new Wall(Vector2D.newVector2DInCell(0, 0));
        roof.setSquareSize(new Dimension(GyromiteEngine.GYROMITE_WIDTH_CELL, 1));

        GameObject wall1 = new Wall(Vector2D.newVector2DInCell(GyromiteEngine.GYROMITE_WIDTH_CELL, 0));
        wall1.setSquareSize(new Dimension(1, GyromiteEngine.GYROMITE_HEIGTH_CELL));
        GameObject wall2 = new Wall(new Vector2D(0, 0));
        wall2.setSquareSize(new Dimension(1, GyromiteEngine.GYROMITE_HEIGTH_CELL));

        this.gameObjects.add(floor);
        this.gameObjects.add(roof);
        this.gameObjects.add(wall1);
        this.gameObjects.add(wall2);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

}
