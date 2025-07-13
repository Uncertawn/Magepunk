package art.uncertawn.engine;

import art.uncertawn.engine.listeners.input.KeyboardListener;
import art.uncertawn.engine.listeners.input.MouseListener;
import art.uncertawn.engine.util.Time;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    public int width, height;
    public String title;
    private long glfwWindow;

    private static Window window = null;

    Engine engine;

    private Window() {
        this.width = 640;
        this.height = 480;
        this.title = "Uncertainty Machina";

        engine = new Engine();
    }

    public static Window get() {
        if (Window.window == null)
            Window.window = new Window();
        return Window.window;
    }

    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        init();
        loop();

        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void init() {
        // Setup an error callback
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW.");
        }

        // Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        // glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        // Create the window
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if (glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create the GLFW window.");
        }

        glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallback);
        glfwSetKeyCallback(glfwWindow, KeyboardListener::keyCallback);

        // Make the OpenGL context current
        glfwMakeContextCurrent(glfwWindow);
        // Enable v-sync
         glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(glfwWindow);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
    }

    public void loop() {
//        float t = 0.0f;
        float dt = 1.0f/60.0f;
        float currentTime = Time.getTime();

        while (!glfwWindowShouldClose(glfwWindow)) {
            float newTime = Time.getTime();
            float frameTime = newTime - currentTime;
            currentTime = newTime;

            // make sure that physics runs on a physics step
            // https://gafferongames.com/post/fix_your_timestep/#the-final-touch
            while (frameTime > 0.0f) {
                float deltaTime = Math.min(frameTime, dt);
//                 System.out.println("" + (1.0f/deltaTime) + " " + deltaTime); // print out fps
                // engine call physics update
                engine.tick(deltaTime);
                frameTime -= deltaTime;
//                t += deltaTime;
            }

            // Poll events
            glfwPollEvents();

            glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            // call engine render update
            engine.render();

            glfwSwapBuffers(glfwWindow);
        }
    }
}