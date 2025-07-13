package art.uncertawn.magepunk;

import art.uncertawn.engine.Window;

import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        String name = System.getProperty("gameName");
        String version = System.getProperty("gameVersion");
        Window window = Window.get();
        window.width = 640;
        window.height = 480;
        window.title = name+" V"+version;
        window.run();
    }
}