package art.uncertawn.engine.scene;

import art.uncertawn.engine.Window;
import art.uncertawn.engine.game.GameObject;
import art.uncertawn.engine.graphics.Color;
import art.uncertawn.engine.graphics.Colors;

import java.util.HashMap;

public class SceneManager extends GameObject {
    private static SceneManager instance;

    private static Scene currentScene;
    private static HashMap<String, Scene> scenes;

    private SceneManager() {

    }

    @Override
    public void init() {
        super.init();
        scenes = new HashMap<>();
    }

    public static SceneManager getInstance() {
        if (SceneManager.instance == null) {
            SceneManager.instance = new SceneManager();
            SceneManager.instance.init();
        }
        return SceneManager.instance;
    }

    public void addScene(Scene scene) {
        scenes.put(scene.name, scene);
    }

    public void setScene(String name) {
        if (scenes.containsKey(name)) {
            currentScene = scenes.get(name);
            currentScene.init();
            System.out.println("Successfully set scene " + name);
        }
        else
            System.out.println("No scene with the name '" + name + "' has been found!");
    }

    @Override
    public void tick(float delta) {
        super.tick(delta);
        currentScene.tick(delta);
    }

    @Override
    public void render() {
        super.render();
        currentScene.render();
    }
}
