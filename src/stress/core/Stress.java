package stress.core;

import frames.processing.Scene;
import processing.core.PApplet;

public class Stress {
    protected PApplet parent;
    protected Scene scene;
    private Physics physics;
    private CommandLine commandLine;
    private FileManager filemanger;

    public Stress(PApplet parent, Scene scene) {
        this.parent = parent;
        this.scene = scene;
        this.commandLine = new CommandLine(this);
    }

}
