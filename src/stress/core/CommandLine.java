package stress.core;

import processing.core.PApplet;
import processing.event.KeyEvent;

import frames.processing.Scene;

import stress.constants.Status;

public class CommandLine {
    private PApplet pApplet;

    private Scene scene;

    protected Stress stress;
//    private Status status;
    private String command;

    public CommandLine(Stress stress) {
        this.pApplet = stress.pApplet;

        this.scene = stress.scene;

        this.stress = stress;
//        this.status = stress.status;  por que no funciona esto ? acaso genera una copia ?

        command = "";
    }

    public void runCommand() {

    }

    public void keyPressed(KeyEvent event) {
        if (event.getKey() == stress.pApplet.ENTER) {
            runCommand();
            stress.status = Status.CANVAS;
            command = "";
        } else if (event.getKeyCode() >= 32 && event.getKeyCode() < 126) {
            command += event.getKey();
        } else if (event.getKey() == pApplet.BACKSPACE && command.length() > 0) {
            command = command.substring(0, command.length() - 1);
        }
    }

    public void draw() {
        stress.scene.beginScreenDrawing();

        pApplet.pushStyle();
        pApplet.rectMode(pApplet.CORNERS);
        pApplet.fill(0);
        pApplet.noStroke();
        pApplet.rect(0, pApplet.height - 25, pApplet.width, pApplet.height);

        pApplet.stroke(255);
        pApplet.fill(255);
        pApplet.textSize(15);
        pApplet.text("> " + command, 5, pApplet.height - 10);
        pApplet.popStyle();

        stress.scene.endScreenDrawing();
    }
}
