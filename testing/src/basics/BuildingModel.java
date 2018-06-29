package basics;

//import frames.core.Frame;
import frames.core.Graph;
import frames.processing.Scene;
//import frames.processing.Shape;

import processing.core.PApplet;
//import processing.core.PShape;

import stress.core.Stress;
import stress.exceptions.InvalidSceneException;

public class BuildingModel extends PApplet {
    private Scene scene;
    private Stress stress;

    private String messages;

    public void settings() {
        size(1600, 800, P3D);
    }

    public void setup() {
//        rectMode(CENTER);
        scene = new Scene(this);
        try {
            stress = new Stress(scene);
        } catch (InvalidSceneException e) {
            e.printStackTrace();
        }

        messages = "testring/src/basics/BuildingModel\nQuitar esto de aquí !";
        scene.setRightHanded();
        scene.setType(Graph.Type.ORTHOGRAPHIC);
        scene.setFieldOfView(PI / 3);
        scene.fitBall();
    }

    public void draw() {
        scene.beginScreenDrawing(); text(messages, mouseX, mouseY); scene.endScreenDrawing();
    }

    public void keyPressed() {
//        if (key == 's') scene.fitBallInterpolation();
    }

    public static void main(String args[]) {
        PApplet.main(new String[] {"basics.BuildingModel"});
    }
}