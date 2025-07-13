package art.uncertawn.example_platformer.scene;

import art.uncertawn.engine.Window;
import art.uncertawn.engine.graphics.Colors;
import art.uncertawn.engine.listeners.input.KeyboardListener;
import art.uncertawn.engine.scene.Scene;
import art.uncertawn.engine.scene.SceneManager;
import art.uncertawn.engine.util.Logger;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;

public class TestScene extends Scene {
    public TestScene(SceneManager sceneManager) {
        super(sceneManager, "test_scene");
    }

    public void init() {
        Window.get().clearColor = Colors.Allred;
    }

    @Override
    public void tick(float delta) {
        super.tick(delta);

        if (KeyboardListener.isKeyPressed(GLFW_KEY_A))
            sceneManager.setScene("test_scene_2");
    }
}
