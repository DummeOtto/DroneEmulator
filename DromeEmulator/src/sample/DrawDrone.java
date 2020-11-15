package sample;

import javafx.scene.canvas.GraphicsContext;

public class DrawDrone {

    static void draw(GraphicsContext pen, DroneFill drone) {
        pen.clearRect(0, 0, 500, 500); //clear canvas
        pen.strokeRect(drone.rectX, drone.rectY, 25, 25); //draw on canvas
    }
}