package art.uncertawn.engine.scene;

import art.uncertawn.engine.util.Logger;

public abstract class Scene {
    public SceneManager sceneManager;
    public String name;

    public void init(){}
    public void tick(float delta) {}
    public void render() {}

    public Scene(SceneManager sceneManager, String name) {
        if (name.contains(" ")) {
            Logger.Error("Name can not contain spaces! Snake case required");
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.sceneManager = sceneManager;
    }
}
