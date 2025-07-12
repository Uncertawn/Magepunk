package art.uncertawn.listeners.input;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {
    private static MouseListener instance;
    private double scrollX, scrollY;
    private double x, y, lastX, lastY;
    private boolean mousePressed[] = new boolean[3];
    private boolean dragging;

    private MouseListener() {
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.x = 0.0;
        this.y = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
        this.dragging = false;
    }

    public static MouseListener get() {
        if (MouseListener.instance == null)
            MouseListener.instance = new MouseListener();
        return MouseListener.instance;
    }

    public static void mousePosCallback(long window, double xpos, double ypos) {
        get().lastX = get().x;
        get().lastY = get().y;
        get().x = xpos;
        get().y = ypos;

        get().dragging = get().mousePressed[0];
    }

    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        if (button > get().mousePressed.length) return;
        if (action == GLFW_PRESS) {
            get().mousePressed[button] = true;
        } else if (action == GLFW_RELEASE) {
            get().mousePressed[button] = false;
            get().dragging = false;
        }
    }

    public static void mouseScrollCallback(long window, double xOffset, double yOffset) {
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }

    public static void endFrame() {
        get().scrollX = 0.0;
        get().scrollY = 0.0;
        get().lastX = get().x;
        get().lastY = get().y;
    }

    public static float getX() {
        return (float)get().x;
    }

    public static float getY() {
        return (float)get().y;
    }

    public static float getDeltaX() {
        return (float)(get().lastX - get().x);
    }

    public static float getDeltaY() {
        return (float)(get().lastY - get().y);
    }

    public static float getScrollX() {
        return (float)get().scrollX;
    }

    public static float getScrollY() {
        return (float)get().scrollY;
    }

    public static boolean isDragging() {
        return get().dragging;
    }

    public static boolean isButtonDown(int button) {
        if (button > get().mousePressed.length || button < 0) return false;
        return get().mousePressed[button];
    }

}
