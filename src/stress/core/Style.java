package stress.core;

import frames.primitives.Vector;
import frames.processing.Scene;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import stress.constants.KeyShortcuts;
import stress.constants.Status;

public class Style {
    private PApplet pApplet;

    private Scene scene;

    protected Stress stress;

    public float background[] = {100f, 100f, 100f};

    private Vector screenCoordinates;

    private char keyPressed = ' ';
    private long time = System.currentTimeMillis();
    private long timeResponse = 2500;

    private String message = "src/stress/core/Style\nEl background debe poder ser modificado por el usuario !\n timeResponse debe poder ser modificado por el usuario ";

    private Boolean showSelector;
    private Boolean showGlobalAxes;
    private Boolean showCoordinates;
//    private Boolean showLocalAxes;
//    private Boolean showTrackedObject;
//    private Boolean showExtrude;
//    private Boolean showForces;
//    private Boolean showHoverObject;
//    private Boolean showLevels;

    public Style(Stress stress) {
        this.pApplet = stress.pApplet;

        this.scene = stress.scene;

        this.stress = stress;

        showSelector = false;
        showGlobalAxes = true;
        showCoordinates = true;
//        showLocalAxes = false;
//        showTrackedObject = false;
//        showExtrude = false;
//        showForces = false;
//        showHoverObject = true;
//        showLevels = false;
    }

    public void pre() {
        pApplet.background(background[0], background[1], background[2]);
    }

    public void draw() {
        scene.beginScreenDrawing(); pApplet.text(message, 350, 350); scene.endScreenDrawing();

        if (showSelector) drawSelector();
        if (showGlobalAxes) drawGlobalAxes();
        if (showCoordinates) drawCoordinates();
    }

    private void drawSelector() {
        scene.beginScreenDrawing(); pApplet.text("src/stress/core/Style\nEl usuario puede cambiar estos parametros, donde almacenar dicha información\nHace falta implementar el selector ?", 600, 600); scene.endScreenDrawing();
        int colorStroke = 127;
        int colorFill = 127;
        int colorAlpha = 63;

        pApplet.pushStyle();

        pApplet.rectMode(pApplet.CORNERS);

        if (pApplet.mouseX >= screenCoordinates.x()) {
            pApplet.stroke(0, colorStroke, 0);
            pApplet.fill(0, colorFill, 0, colorAlpha);
        } else if (pApplet.mouseX < screenCoordinates.x()) {
            pApplet.stroke(colorStroke, 0, 0);
            pApplet.fill(colorFill, 0, 0, colorAlpha);
        }

        scene.beginScreenDrawing();
        pApplet.rect(screenCoordinates.x(), screenCoordinates.y(), pApplet.mouseX, pApplet.mouseY);
        scene.endScreenDrawing();

        pApplet.popStyle();
    }

    private void drawGlobalAxes() {
        scene.beginScreenDrawing(); pApplet.text("Dibujar nuestros propios ejes para que las letras quden bien orientadas", 50, 50); scene.endScreenDrawing();
        scene.drawAxes();
    }

    private void drawCoordinates() {
        Vector mouseCoordinates = null;

        if (scene.trackedFrame() == null) {
            mouseCoordinates = scene.location(new Vector(pApplet.mouseX, pApplet.mouseY));
        } else if (!scene.trackedFrame().getClass().getName().equals("stress.primitives.Axis")) {
            mouseCoordinates = scene.trackedFrame().position();
        }

        String coordinates = ")";
        float offsetX = 10;
        scene.beginScreenDrawing(); pApplet.text("No hay una forma de leer 30 ó de algún lugar ?", 110, 100); scene.endScreenDrawing();
        float offsetY = stress.status == Status.COMMANDLINE ? 30 : 5;

        scene.beginScreenDrawing();
        pApplet.pushStyle();
        pApplet.textSize(16);
        pApplet.textAlign(pApplet.RIGHT, pApplet.BOTTOM);

        pApplet.fill(0);
        pApplet.text(coordinates, pApplet.width - offsetX, pApplet.height - offsetY);

        pApplet.fill(0, 0, 255);
        pApplet.text(pApplet.nf(mouseCoordinates.z(), 0, 3), pApplet.width - pApplet.textWidth(coordinates) - offsetX, pApplet.height - offsetY);
        coordinates = pApplet.nf(mouseCoordinates.z(), 0, 3) + coordinates;

        pApplet.fill(0);
        pApplet.text(", ", pApplet.width - pApplet.textWidth(coordinates) - offsetX, pApplet.height - offsetY);
        coordinates = ", " + coordinates;

        pApplet.fill(0, 255, 0);
        pApplet.text(pApplet.nf(mouseCoordinates.y(), 0, 3), pApplet.width - pApplet.textWidth(coordinates) - offsetX, pApplet.height - offsetY);
        coordinates = pApplet.nf(mouseCoordinates.y(), 0, 3) + coordinates;

        pApplet.fill(0);
        pApplet.text(", ", pApplet.width - pApplet.textWidth(coordinates) - offsetX, pApplet.height - offsetY);
        coordinates = ", " + coordinates;

        pApplet.fill(255, 0, 0);
        pApplet.text(pApplet.nf(mouseCoordinates.x(), 0, 3), pApplet.width - pApplet.textWidth(coordinates) - offsetX, pApplet.height - offsetY);
        coordinates = pApplet.nf(mouseCoordinates.x(), 0, 3) + coordinates;

        pApplet.fill(0);
        pApplet.text("(", pApplet.width - pApplet.textWidth(coordinates) - offsetX, pApplet.height - offsetY);

        pApplet.popStyle();
        scene.endScreenDrawing();
    }

    public void mouseEvent (MouseEvent event) {
        switch (event.getAction()) {
            case MouseEvent.MOVE:/*
                if (scene.trackedFrame() == null) {
                     mouseCoordinates = scene.location(new Vector(parent.mouseX, parent.mouseY));
                } else if (!scene.trackedFrame().getClass().getName().equals("stress.primitives.Axis")) {
                    mouseCoordinates = scene.trackedFrame().position();
                }*/
                break;

            case MouseEvent.PRESS:
                if (event.getButton() == pApplet.LEFT) { // && event.getCount() == 1
                    scene.beginScreenDrawing(); pApplet.text("src/stress/core/Style\nProblemas administrando la cantidad de clics", 400, 500); scene.endScreenDrawing();
                    screenCoordinates = new Vector(pApplet.mouseX, pApplet.mouseY);/*
                if (showAddNode) {
                    this.addNode();
                    this.showAddNode = false;
                } else if (showAddPortico) {
                    if (i == null) {
                        i = mouseCoordinates;
                        return;
                    } else {
                        j = mouseCoordinates;
                    }
                    addPortico();
                    i = null;
                    j = null;
                    showAddPortico = false;
                }*/
                }
                break;

            case MouseEvent.RELEASE:
                screenCoordinates = null;
                showSelector = false;
                break;

            case MouseEvent.DRAG:
                if (event.getButton() == pApplet.LEFT) {
                    showSelector = true;
                } else if (event.getButton() == pApplet.CENTER) {
                    scene.translate();
                } else if (event.getButton() == pApplet.RIGHT) {
                    scene.rotateCAD(new Vector(0, 0, 1));scene.beginScreenDrawing(); pApplet.text("src/stress/core/Style\nCon respecto a que punto está rotando ?", 500, 500); scene.endScreenDrawing();
                }
                break;

            case MouseEvent.WHEEL:
                // scene.setTrackedFrame(scene.eye());
                scene.scale(-20 * event.getCount()); scene.beginScreenDrawing(); pApplet.text("src/stress/core/Style\nHacia donde apuntar el wheel ?\nEl usuario debe ser capaz de modificar la sensibilidad", 550, 550); scene.endScreenDrawing();
                break;
        }
    }

    public void keyPressed(KeyEvent event) {
        if (event.getAction() == KeyEvent.PRESS) {
            if (keyPressed == ' ') {
                time = System.currentTimeMillis();
                keyPressed = event.getKey();
            } else if ((System.currentTimeMillis() - time) < timeResponse) {
                switch ("" + keyPressed + event.getKey()) {
                    case KeyShortcuts.GLOBAL_AXES:
                        setShowGlobalAxes(!getShowGlobalAxes());
                        break;
                    case KeyShortcuts.COORDINATES:
                        setShowCoordinates(!getShowCoordinates());
                        break;
//                    case KeyShortcuts.LOCAL_AXES:
//                        setShowLocalAxes(!getShowLocalAxes());
//                        break;
//                    case KeyShortcuts.TRACKEDOBJECT:
//                        setShowTrackedObject(!getShowCoordinates());
//                        break;
//                    case KeyShortcuts.EXTRUDE:
//                        setShowExtrude(!getShowExtrude());
//                        break ;
//                    case KeyShortcuts.FORCES:
//                        setShowForces(!getShowForces());
//                        break;
//                    case KeyShortcuts.HOVEROBJECT:
//                        setShowHoverObject(!getShowHoverObject());
//                        break;
//                    case KeyShortcuts.LEVEL:
//                        setShowLevels(!getShowLevels());
//                        break;
                    default:
                        keyPressed = ' ';
                        break;
                }
            } else {
                time = System.currentTimeMillis();
                keyPressed = event.getKey();
            }
        }
    }

    public boolean getShowGlobalAxes() {
        return showGlobalAxes;
    }

    public void setShowGlobalAxes(Boolean showGlobalAxes) {
        this.showGlobalAxes = showGlobalAxes;
    }

    public Boolean getShowCoordinates() {
        return showCoordinates;
    }

    public void setShowCoordinates(Boolean showCoordinates) {
        this.showCoordinates = showCoordinates;
    }

//    public Boolean getShowLocalAxes() {
//        return showLocalAxes;
//    }

//    public void setShowLocalAxes(Boolean showLocalAxes) {
//        this.showLocalAxes = showLocalAxes;
//    }

//    public Boolean getShowTrackedObject() {
//        return showTrackedObject;
//    }

//    public void setShowTrackedObject(Boolean showTrackedObject) {
//        this.showTrackedObject = showTrackedObject;
//    }

//    public Boolean getShowExtrude() {
//        return showExtrude;
//    }

//    public void setShowExtrude(Boolean showExtrude) {
//        this.showExtrude = showExtrude;
//    }

//    public Boolean getShowForces() {
//        return showForces;
//    }

//    public void setShowForces(Boolean showForces) {
//        this.showForces = showForces;
//    }

//    public Boolean getShowHoverObject() {
//        return showHoverObject;
//    }

//    public void setShowHoverObject(Boolean showHoverObject) {
//        this.showHoverObject = showHoverObject;
//    }

//    public Boolean getShowLevels() {
//        return showLevels;
//    }

//    public void setShowLevels(Boolean showLevels) {
//        this.showLevels = showLevels;
//    }
}
