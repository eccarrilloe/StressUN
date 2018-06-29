package basics;

import frames.core.Graph;
import frames.processing.Scene;

import processing.core.PApplet;

import stress.core.Stress;
import stress.exceptions.InvalidSceneException;

public class BuildingModel extends PApplet {
    private Scene scene;
    private Stress stress;

    public void settings() {
        size(1600, 800, P3D);
    }

    public void setup() {
        scene = new Scene(this);
        try {
            stress = new Stress(scene);
        } catch (InvalidSceneException e) {
            e.printStackTrace();
        }

        scene.setRightHanded();
        scene.setType(Graph.Type.ORTHOGRAPHIC);
        scene.setFieldOfView(PApplet.PI / 3);
        scene.fitBall();
    }

    public void draw() {
        scene.beginScreenDrawing(); text("src/basics/BuildingModel\nEl usuario debe ser capaz de cambiar el scene.type", 600, 600); scene.endScreenDrawing();
    }

    public static void main(String args[]) {
        PApplet.main(new String[] {"basics.BuildingModel"});
    }
}