package basics;

import frames.core.Frame;
import frames.processing.Scene;
import frames.processing.Shape;

import processing.core.PApplet;
import processing.core.PShape;
import processing.event.MouseEvent;
import stress.core.Stress;
import stress.exceptions.InvalidSceneException;

public class BuildingModel extends PApplet {
    Scene scene;
    Stress stress;

    public void settings() {
        size(1600, 800, P3D);
    }

    public void setup() {
        rectMode(CENTER);
        scene = new Scene(this);
        try {
            stress = new Stress(scene);
        } catch (InvalidSceneException e) {
            e.printStackTrace();
        }

        scene.setFieldOfView(PI / 3);
        scene.fitBallInterpolation();
    }

    public void draw() {

    }

    public void keyPressed() {
        if (key == 's')
            scene.fitBallInterpolation();
    }

    public void mouseDragged() {
        if (mouseButton == LEFT)
            scene.spin(scene.eye());
        else if (mouseButton == RIGHT)
            scene.translate(scene.eye());
        else
            scene.scale(mouseX - pmouseX, scene.eye());
    }

    public void mouseWheel(MouseEvent event) {
        scene.zoom(event.getCount() * 50, scene.eye());
    }

    public static void main(String args[]) {
        PApplet.main(new String[] {"basics.BuildingModel"});
    }
}