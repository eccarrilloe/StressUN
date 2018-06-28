package stress.core;

import frames.processing.Scene;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import processing.opengl.PGraphics3D;
import stress.constants.Status;

import stress.exceptions.InvalidSceneException;

public class Stress {
    protected PApplet parent;
    protected Scene scene;

    private Physics physics;
    private CommandLine commandLine;
    private FileManager fileManager;
    private Style style;

    protected Status status;

    public Stress(Scene scene) throws InvalidSceneException {
        this(scene, null);
    }

    public Stress(Scene scene, String configFile) throws InvalidSceneException {
        this.scene = scene;
        parent = scene.pApplet();

        if (!(parent.g instanceof PGraphics3D)) {
            throw new InvalidSceneException("This is not a 3D Scene");
        }

        physics = new Physics(this);
        fileManager = new FileManager(this);
        commandLine = new CommandLine(this);
        style = new Style(this);

        status = Status.CANVAS;

        if (configFile != null) {
            System.out.println("Load configuration file: " + configFile);
        }

        this.parent.registerMethod("draw", this);
        this.parent.registerMethod("keyEvent", this);
        this.parent.registerMethod("mouseEvent", this);
    }

    public void draw() {
        style.draw();

        if (status == Status.COMMANDLINE) {
            commandLine.draw();
        }
    }

    public void keyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.PRESS) {
            if (event.getKey() == parent.ENTER && status == Status.CANVAS) {
                status = Status.COMMANDLINE;
            } else if (event.getKey() == parent.ENTER || status == Status.COMMANDLINE) {
                commandLine.keyPressed(event);
            } else {
                style.keyPressed(event);
            }
        }
    }

    public void mouseEvent(MouseEvent event) {

    }

    public Physics getPhysics() {
        return physics;
    }

    public void setPhysics(Physics physics) {
        this.physics = physics;
    }

    public CommandLine getCommandLine() {
        return commandLine;
    }

    public void setCommandLine(CommandLine commandLine) {
        this.commandLine = commandLine;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }
}
