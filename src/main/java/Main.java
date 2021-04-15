import Engine.Controller.ViewControllerGyromite;
import Game.Game;

/**
 *
 * @project Gyromite
 * @authors Yamuu
 */
public class Main {

    public static void main(String[] args)
    {
        System.setProperty("sun.awt.noerasebackground", "true");
        Game game = new Game();

        ViewControllerGyromite vc = new ViewControllerGyromite(game);
        ViewControllerGyromite.getCurrentCanvas();
        ViewControllerGyromite.setViewControllerGyromite(vc);

        game.getEngine().addObserver(vc);


    }

}
