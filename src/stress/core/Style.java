package stress.core;

import frames.primitives.Vector;
import frames.processing.Scene;
import processing.core.PApplet;
import processing.event.KeyEvent;
import stress.constants.KeyShortcuts;
import stress.constants.Status;

public class Style {
    protected Stress stress;

    private float background[] = { 100f, 100f, 100f };
    private Boolean showAxes;
    private Boolean showCoordinates;
    private Boolean showTrackedObject;
    private Boolean showExtrude;
    private Boolean showForces;
    private Boolean showHoverObject;
    private Boolean showLevels;

    public Style(Stress stress) {
        this.stress = stress;

        showAxes = false;
        showCoordinates = true;
        showTrackedObject = false;
        showExtrude = false;
        showForces = false;
        showHoverObject = true;
        showLevels = false;
    }

    public void draw() {
        PApplet parent = stress.parent;
        Scene scene = stress.scene;

        parent.background(background[0], background[1], background[2]);
        if (showAxes) {
            scene.drawAxes();
        }

        if (showCoordinates) {
            Vector mouseCoordinates = null;

            if (scene.trackedFrame() == null) {
                mouseCoordinates = scene.location(new Vector(parent.mouseX, parent.mouseY));
            } else if (!scene.trackedFrame().getClass().getName().equals("stress.primitives.Axis")) {
                mouseCoordinates = scene.trackedFrame().position();
            }

            String coordinates = ")";
            float offsetX = 5;
            float offsetY = stress.status == Status.COMMANDLINE ? 30 : 5;

            scene.beginScreenDrawing();
            parent.pushStyle();
            parent.textSize(16);
            parent.textAlign(parent.RIGHT, parent.BOTTOM);

            parent.fill(0);
            parent.text(coordinates, parent.width - offsetX, parent.height - offsetY);

            parent.fill(0, 0, 255);
            parent.text(parent.nf(mouseCoordinates.z(), 0, 3), parent.width - parent.textWidth(coordinates) - offsetX, parent.height - offsetY);
            coordinates = parent.nf(mouseCoordinates.z(), 0, 3) + coordinates;

            parent.fill(0);
            parent.text(", ", parent.width - parent.textWidth(coordinates) - offsetX, parent.height - offsetY);
            coordinates = ", " + coordinates;

            parent.fill(0, 255, 0);
            parent.text(parent.nf(mouseCoordinates.y(), 0, 3), parent.width - parent.textWidth(coordinates) - offsetX, parent.height - offsetY);
            coordinates = parent.nf(mouseCoordinates.y(), 0, 3) + coordinates;

            parent.fill(0);
            parent.text(", ", parent.width - parent.textWidth(coordinates) - offsetX, parent.height - offsetY);
            coordinates = ", " + coordinates;

            parent.fill(255, 0, 0);
            parent.text(parent.nf(mouseCoordinates.x(), 0, 3), parent.width - parent.textWidth(coordinates) - offsetX, parent.height - offsetY);
            coordinates = parent.nf(mouseCoordinates.x(), 0, 3) + coordinates;

            parent.fill(0);
            parent.text("(", parent.width - parent.textWidth(coordinates) - offsetX, parent.height - offsetY);

            parent.popStyle();
            scene.endScreenDrawing();
        }
    }

    public void keyPressed(KeyEvent event) {
        switch (event.getKey()) {
            case KeyShortcuts.AXES:
                setShowAxes(!getShowAxes());
                break;
            case KeyShortcuts.COORDINATES:
                setShowCoordinates(!getShowCoordinates());
                break;
            case KeyShortcuts.TRACKEDOBJECT:
                setShowTrackedObject(!getShowCoordinates());
                break;
            case KeyShortcuts.EXTRUDE:
                setShowExtrude(!getShowExtrude());
                break;
            case KeyShortcuts.FORCES:
                setShowForces(!getShowForces());
                break;
            case KeyShortcuts.HOVEROBJECT:
                setShowHoverObject(!getShowHoverObject());
                break;
            case KeyShortcuts.LEVEL:
                setShowLevels(!getShowLevels());
                break;
        }
    }

    public Boolean getShowAxes() {
        return showAxes;
    }

    public void setShowAxes(Boolean showAxes) {
        this.showAxes = showAxes;
    }

    public Boolean getShowCoordinates() {
        return showCoordinates;
    }

    public void setShowCoordinates(Boolean showCoordinates) {
        this.showCoordinates = showCoordinates;
    }

    public Boolean getShowTrackedObject() {
        return showTrackedObject;
    }

    public void setShowTrackedObject(Boolean showTrackedObject) {
        this.showTrackedObject = showTrackedObject;
    }

    public Boolean getShowExtrude() {
        return showExtrude;
    }

    public void setShowExtrude(Boolean showExtrude) {
        this.showExtrude = showExtrude;
    }

    public Boolean getShowForces() {
        return showForces;
    }

    public void setShowForces(Boolean showForces) {
        this.showForces = showForces;
    }

    public Boolean getShowHoverObject() {
        return showHoverObject;
    }

    public void setShowHoverObject(Boolean showHoverObject) {
        this.showHoverObject = showHoverObject;
    }

    public Boolean getShowLevels() {
        return showLevels;
    }

    public void setShowLevels(Boolean showLevels) {
        this.showLevels = showLevels;
    }
}
