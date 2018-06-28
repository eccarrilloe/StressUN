package stress.core;

import processing.core.PApplet;
import processing.event.KeyEvent;
import stress.constants.Status;

public class CommandLine {

    protected Stress stress;
    private String command;

    public CommandLine(Stress stress) {
        this.stress = stress;

        command = "";
    }

    public void runCommand() {

    }

    public void keyPressed(KeyEvent event) {
        if (event.getKey() == stress.parent.ENTER) {
            runCommand();
            stress.status = Status.CANVAS;
            command = "";
        } else if (event.getKeyCode() >= 32 && event.getKeyCode() < 126) {
            command += event.getKey();
        } else if (event.getKey() == stress.parent.BACKSPACE && command.length() > 0) {
            command = command.substring(0, command.length() - 1);
        }
    }

    public void draw() {
        PApplet parent = stress.parent;
        stress.scene.beginScreenDrawing();

        parent.pushStyle();
        parent.rectMode(parent.CORNERS);
        parent.fill(0);
        parent.noStroke();
        parent.rect(0, parent.height - 25, parent.width, parent.height);

        parent.stroke(255);
        parent.fill(255);
        parent.textSize(15);
        parent.text("> " + command, 5, parent.height - 10);
        parent.popStyle();

        stress.scene.endScreenDrawing();
    }
}
