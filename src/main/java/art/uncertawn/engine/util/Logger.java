package art.uncertawn.engine.util;

public class Logger {
    public static void Log(String text) {
        System.out.println("[INFO]: " + text);
    }

    public static void Error(String text) {
        System.out.println("[ERROR]: " + text);
    }
}
