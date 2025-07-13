package art.uncertawn.example_platformer;

import art.uncertawn.engine.Engine;
import art.uncertawn.engine.Window;
import art.uncertawn.engine.graphics.Colors;
import art.uncertawn.engine.util.Logger;
import art.uncertawn.example_platformer.scene.TestScene;
import art.uncertawn.example_platformer.scene.TestScene2;

public class Main {
    Main instance;
    static Engine engine;

    public static void main(String[] args) {
        String name = System.getProperty("gameName");
        String version = System.getProperty("gameVersion");
        Window window = Window.get();
        window.width = 640;
        window.height = 480;
        window.title = name+" V"+version;

        engine = window.engine;
        window.clearColor = Colors.Black;

        engine.sceneManager.addScene(new TestScene(engine.sceneManager));
        engine.sceneManager.addScene(new TestScene2(engine.sceneManager));
        engine.sceneManager.setScene("test_scene");


        window.run();
    }

}