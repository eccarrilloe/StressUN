package stress.primitives;

import frames.primitives.Vector;
import frames.primitives.Quaternion;
import frames.processing.Scene;
import frames.processing.Shape;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Axis extends Shape {
    private PApplet pApplet;

    private Scene scene;

    private Vector i;
    private Vector j;

    private String bubbleText;
    private int bubbleSize;
    private int bubbleTextColor; private String message = "/src/stress/primitives/Axis" +
            "\nEl tamaño de la burbuja debería estar almacenada en la clase o en otro lugar ?" +
            "\nEl color del texto de la burbuja debería estar almacendao en la clase o en otro lugar ?";

    private int axisColor;
    private float axisStroke;

    public Axis(Scene scene, Vector i, Vector j, String bubbleText) {
        this(scene, i, j, bubbleText, 50, scene.pApplet().color(0), scene.pApplet().color(239, 127, 26),
                2.5f);
    }

    private Axis(Scene scene, Vector i, Vector j, String bubbleText, int bubbleSize, int bubbleTextColor, int axisColor,
                float axisStroke) {
        super(scene);

        this.pApplet = scene.pApplet();

        this.scene = scene;

        this.i = i;
        this.j = j;

        this.bubbleText = bubbleText;
        this.bubbleSize = bubbleSize;
        this.bubbleTextColor = bubbleTextColor;

        this.axisColor = axisColor;
        this.axisStroke = axisStroke;

        setPosition(i);
        setRotation(new Quaternion(new Vector(1, 0, 0), ij())); message+="El eje Z siempre queda en dirección Z positivo global ?";
        setPrecision(Precision.EXACT);
        setHighlighting(Highlighting.NONE);
    }

    public Vector ij() {
        return Vector.subtract(j, i);
    }

//        float nivelZ() {
//            return position().z();
//        }

//        public void setNivelZ(float nivelZ) {
//            setI(new Vector(i().x(), i().y(), nivelZ));
//            setJ(new Vector(j().x(), j().y(), nivelZ));
//            setPosition(new Vector(position().x(), position().y(), nivelZ));
//        }

    @Override
    public void setGraphics(PGraphics pGraphics) {
        // Position
        Vector iScreen = scene.screenLocation(i);
        Vector jScreen = scene.screenLocation(j);
        Vector parallelDirection = Vector.subtract(jScreen, iScreen);
        parallelDirection.normalize();
        Vector center = Vector.add(iScreen, Vector.multiply(parallelDirection, -bubbleSize / 2));

        pGraphics.pushStyle();

        // Color
        int axisColorTracked = pApplet.color(255 - pApplet.red(axisColor), 255 - pApplet.green(axisColor),
                255 - pApplet.blue(axisColor));
        pGraphics.strokeWeight(axisStroke);
        if (!scene.isTrackedFrame(this)) {
            pGraphics.stroke(axisColor);
            pGraphics.fill(pApplet.red(axisColor), pApplet.green(axisColor), pApplet.blue(axisColor), 63);
        } else {
            pGraphics.stroke(axisColorTracked);
            pGraphics.fill(pApplet.red(axisColorTracked), pApplet.green(axisColorTracked),
                    pApplet.blue(axisColorTracked), 63);
        }

        // Line
        pGraphics.pushStyle();
        /* No he podido implementar la linea. Problemas con Precision
        pGraphics.pushMatrix();
        pGraphics.rotateY(PApplet.radians(90));
        Scene.drawCylinder(pGraphics, 0.1f, ij().magnitude());
        pGraphics.popMatrix();*/
        pGraphics.line(0, 0, 0, ij().magnitude(), 0, 0);
        pGraphics.popStyle();

        scene.beginScreenDrawing(pGraphics);
        pGraphics.pushStyle();

        // Bubble
        pGraphics.ellipseMode(PApplet.CENTER);
        pGraphics.ellipse(center.x(), center.y(), bubbleSize, bubbleSize);
        // Text
        pGraphics.textAlign(PApplet.CENTER, PApplet.CENTER);
        pGraphics.textSize(0.5f * bubbleSize);   // problemas, ver https://processing.org/tutorials/typography/
        pGraphics.fill(bubbleTextColor);
        pGraphics.text(bubbleText, center.x(), center.y());

        pGraphics.popStyle();
        scene.endScreenDrawing(pGraphics);

        pGraphics.popStyle();
    }
}