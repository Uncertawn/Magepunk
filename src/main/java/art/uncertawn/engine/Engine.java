package art.uncertawn.engine;

import art.uncertawn.engine.listeners.input.KeyboardListener;
import art.uncertawn.engine.scene.SceneManager;

import static org.lwjgl.glfw.GLFW.*;

public class Engine {

    public SceneManager sceneManager;

    public Engine() {
        sceneManager = SceneManager.getInstance();

    }

    public void init() {

    }

    public void tick(float delta) {
        sceneManager.tick(delta);
    }

    public void render() {
        sceneManager.render();

    }
}
