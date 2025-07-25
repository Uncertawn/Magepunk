package art.uncertawn.engine.listeners.input;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyboardListener {
    private static KeyboardListener instance;
    private boolean keyPressed[] = new boolean[350];

    private KeyboardListener() {

    }

    public static void keyCallback(long window, int key, int scancode, int action, int mods) {
        if (key < 0) return;
        if (action == GLFW_PRESS) {
            get().keyPressed[key] = true;
        } else if (action == GLFW_RELEASE) {
            get().keyPressed[key] = false;
        }
    }

    public static KeyboardListener get() {
        if (KeyboardListener.instance == null)
            KeyboardListener.instance = new KeyboardListener();
        return KeyboardListener.instance;
    }

    public static boolean isKeyPressed(int key) {
        return get().keyPressed[key];
    }
}
