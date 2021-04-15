package Game.View;

import Engine.View.View;

/**
 * @author Yamuu
 * @project Gyromite
 */
public class ViewGameOver extends View {

    public boolean win;

    public ViewGameOver()
    {
        this.win = false;
        this.setVisible(true);
    }

    private void win()
    {

    }

    private void lose()
    {

    }

    public void draw()
    {
        if(this.win)
        {
            win();
        }
        else
        {
            lose();
        }
    }
}
