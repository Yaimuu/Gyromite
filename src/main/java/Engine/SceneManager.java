/**
 * @author Yamuu
 * @project Gyromite
 */
package Engine;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class SceneManager {

    private static String scenesDirectory = "Scenes/";
    private static List<Scene> scenes = new LinkedList<>();

    public SceneManager()
    {

    }

    public static List<Scene> getAllScenes()
    {
        File dir  = new File(scenesDirectory);
        File[] liste = dir.listFiles();
        List<Scene> scenes = new LinkedList<>();

        for(File item : liste){
            if(item.isFile() && item.getName().contains(".scene"))
            {
//                System.out.format("Nom du fichier: %s%n", item.getName());
                scenes.add(new Scene(item.getName(), scenesDirectory));
            }
            else if(item.isDirectory())
            {
//                System.out.format("Nom du répertoir: %s%n", item.getName());
            }
        }
        return scenes;
    }



}
