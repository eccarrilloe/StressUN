package basics;

import frames.core.Graph;
import frames.processing.Scene;

import processing.core.PApplet;

import stress.core.Stress;
import stress.exceptions.InvalidSceneException;

public class BuildingModel extends PApplet {
    private Scene scene;
    private Stress stress; private String message = "Poner un init en el stress ?";

    public void settings() {
        size(1600, 800, P3D); message+= "\nCambiar el tamaño de la ventana dinamicamente !";
    }

    public void setup() {
        scene = new Scene(this);
        try {
            stress = new Stress(scene);
        } catch (InvalidSceneException e) {
            e.printStackTrace();
        }

        scene.setRightHanded();
        scene.setType(Graph.Type.ORTHOGRAPHIC); message+="\nEl usuario debe ser capaz de modificar scene.type";
        scene.setFieldOfView(PApplet.PI / 3); message+="\nAveriguar como afecta el fieldOfView";
        scene.fitBall();

        stress.init();
    }

    public void draw() {
        scene.traverse(); message+="\nmirar esto con calma";
        scene.cast(); message+="\nmirar esto con calma";
    }

    public static void main(String args[]) {
        PApplet.main(new String[] {"basics.BuildingModel"});
    }
}