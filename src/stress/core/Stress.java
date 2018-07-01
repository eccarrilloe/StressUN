package stress.core;

import frames.processing.Scene;
//import frames.primitives.Vector;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import processing.opengl.PGraphics3D;

import stress.constants.Status;
import stress.exceptions.InvalidSceneException;

public class Stress {
    public PApplet pApplet;
    public Scene scene;

    private CommandLine commandLine;
//    private Physics physics;
//    private FileManager fileManager;
    private Style style; private String message = "Por qué no necesito importar Style explicitamente ?";
    public Status status;

    public Stress(Scene scene) throws InvalidSceneException {
        this(scene, null);
    }

    public Stress(Scene scene, String configFile) throws InvalidSceneException {
        this.pApplet = scene.pApplet();
        this.scene = scene;

        if (!(pApplet.g instanceof PGraphics3D)) {
            throw new InvalidSceneException("This is not a 3D Scene");
        }

        commandLine = new CommandLine(this);
//        physics = new Physics(this);
//        fileManager = new FileManager(this);
        style = new Style(this);
        status = Status.CANVAS;

//        if (configFile != null) {
//            System.out.println("Load configuration file: " + configFile);
//        }

        this.pApplet.registerMethod("pre", this);
        this.pApplet.registerMethod("draw", this);
        this.pApplet.registerMethod("keyEvent", this);
        this.pApplet.registerMethod("mouseEvent", this);
    }

    public void pre() {
        style.pre();
    }

    public void draw() {
        style.draw();
        if (status == Status.COMMANDLINE) commandLine.draw();
    }

    public void keyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.PRESS) {
            if (event.getKey() == pApplet.ENTER && status == Status.CANVAS) {
                status = Status.COMMANDLINE;
            } else if (event.getKey() == pApplet.ENTER || status == Status.COMMANDLINE) {
                commandLine.keyPressed(event);
            } else {
                style.keyPressed(event);
            }
        }
    }

    public void mouseEvent(MouseEvent event) {
        style.mouseEvent(event);
    }
}
