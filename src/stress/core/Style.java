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

    public int background;
    private Vector screenCoordinates;
    private char keyPressed = ' ';

    private Boolean showSelector;
    private Boolean showShortcut;
    private Boolean showGlobalAxes;
    private Boolean showCoordinates;
//    private Boolean showLocalAxes;
//    private Boolean showTrackedObject;
//    private Boolean showExtrude;
//    private Boolean showForces;
//    private Boolean showHoverObject;
//    private Boolean showLevels;

    private String message = "src/stress/core/Style";

    public Style(Stress stress) {
        this.pApplet = stress.pApplet;

        this.scene = stress.scene;

        this.stress = stress;

        this.background = pApplet.color(100, 100, 100); message+= "\nEl background debe poder ser modificado por el usuario !";

        showSelector = false;
        showShortcut = true;
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
        pApplet.background(background);
    }

    public void draw() {
        scene.beginScreenDrawing(); pApplet.text(message, 350, 350); scene.endScreenDrawing();

        if (showSelector) drawSelector();
        if (showShortcut) drawShortcut();
        if (showGlobalAxes) drawGlobalAxes();
        if (showCoordinates) drawCoordinates();
    }

    private void drawSelector() {
        scene.beginScreenDrawing(); pApplet.text("src/stress/core/Style\nEl usuario puede cambiar estos parametros, donde almacenar dicha información\nHace falta implementar el selector", 600, 600); scene.endScreenDrawing();
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

    private void drawShortcut() {
        if (keyPressed != ' ') {
            scene.beginScreenDrawing(); pApplet.text("src/stress/core/Style\nEl usuario debe ser capaz de cambiar el valor de esta variable", 86, 56); scene.endScreenDrawing();

            pApplet.pushStyle();

            pApplet.textSize(16);
            pApplet.fill(0);

            scene.beginScreenDrawing();
            pApplet.text(keyPressed, pApplet.mouseX, pApplet.mouseY);
            scene.endScreenDrawing();

            pApplet.popStyle();
        }
    }

    private void drawGlobalAxes() {
        scene.beginScreenDrawing(); pApplet.text("src/stress/core/Style\nDibujar nuestros propios ejes para que las letras quden bien orientadas", 50, 50); scene.endScreenDrawing();
        scene.drawAxes();
    }

    private void drawCoordinates() {
        Vector mouseCoordinates = null;

        scene.beginScreenDrawing(); pApplet.text("src/stress/core/Style\nMirar como administar esto y la modelación con clics", 542, 234); scene.endScreenDrawing();
        scene.beginScreenDrawing(); pApplet.text("Mirar la implementarción de esta función con calma", 361, 689); scene.endScreenDrawing();

        if (scene.trackedFrame() == null ||
                scene.trackedFrame().getClass().getName().equals("stress.primitives.Axis")) {
            mouseCoordinates = scene.location(new Vector(pApplet.mouseX, pApplet.mouseY));
        } else {// } else if () {
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
        pApplet.text(PApplet.nf(mouseCoordinates.z(), 0, 3), pApplet.width - pApplet.textWidth(coordinates) - offsetX, pApplet.height - offsetY);
        coordinates = PApplet.nf(mouseCoordinates.z(), 0, 3) + coordinates;

        pApplet.fill(0);
        pApplet.text(", ", pApplet.width - pApplet.textWidth(coordinates) - offsetX, pApplet.height - offsetY);
        coordinates = ", " + coordinates;

        pApplet.fill(0, 255, 0);
        pApplet.text(PApplet.nf(mouseCoordinates.y(), 0, 3), pApplet.width - pApplet.textWidth(coordinates) - offsetX, pApplet.height - offsetY);
        coordinates = PApplet.nf(mouseCoordinates.y(), 0, 3) + coordinates;

        pApplet.fill(0);
        pApplet.text(", ", pApplet.width - pApplet.textWidth(coordinates) - offsetX, pApplet.height - offsetY);
        coordinates = ", " + coordinates;

        pApplet.fill(255, 0, 0);
        pApplet.text(PApplet.nf(mouseCoordinates.x(), 0, 3), pApplet.width - pApplet.textWidth(coordinates) - offsetX, pApplet.height - offsetY);
        coordinates = PApplet.nf(mouseCoordinates.x(), 0, 3) + coordinates;

        pApplet.fill(0);
        pApplet.text("(", pApplet.width - pApplet.textWidth(coordinates) - offsetX, pApplet.height - offsetY);

        pApplet.popStyle();
        scene.endScreenDrawing();
    }

    public void mouseEvent(MouseEvent event) {
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
                    scene.rotateCAD(new Vector(0, 0, 1));
                    scene.beginScreenDrawing(); pApplet.text("src/stress/core/Style\nCon respecto a que punto está rotando ?", 500, 500); scene.endScreenDrawing();
                }
                break;

            case MouseEvent.WHEEL:
                // scene.setTrackedFrame(scene.eye());
                scene.scale(-20 * event.getCount(), scene.eye());
                scene.beginScreenDrawing(); pApplet.text("src/stress/core/Style\nHacia donde apuntar el wheel ?\nEl usuario debe ser capaz de modificar la sensibilidad", 550, 550); scene.endScreenDrawing();
                break;
        }
    }

    public void keyPressed(KeyEvent event) {
        if (event.getAction() == KeyEvent.PRESS) {
            if (keyPressed == ' ') {
                keyPressed = event.getKey();
            } else {
                switch ("" + keyPressed + event.getKey()) {
                    case KeyShortcuts.GLOBAL_AXES:
                        showGlobalAxes = !showGlobalAxes;
                        break;
                    case KeyShortcuts.COORDINATES:
                        showCoordinates = !showCoordinates;
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
                }
                keyPressed = ' ';
            }

            if (event.getKeyCode() == PApplet.ESC) {
                pApplet.key = 0;
            }
        }
    }
}
